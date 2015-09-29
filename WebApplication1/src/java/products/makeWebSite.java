/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import carte.Cart;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bruno
 */
public class makeWebSite extends HttpServlet {
    
    private ArrayList<Produits> listeDesProduits;
    private Cart cart;
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, String> map = new HashMap<String, String>();
        Enumeration tete = request.getParameterNames();            
         
        while (tete.hasMoreElements()) {
		String key = (String) tete.nextElement();
		String value = request.getHeader(key);
		map.put(key, value);
	}
        
        if(map.containsKey("addToCart")){
            addToCart(request, response);
        }
        
        else if(map.containsKey("checkout")){
            loadCheckout(request, response);
        }
        
        else if(map.containsKey("loadCatalogue") || map.containsKey("clearCart")){
            loadCatalogue(request, response);
        }
        else{
            modificationCart(request, response);
        }
             
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadProducts(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void loadProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        
        ServletContext context = getServletContext();
        setListeDesProduits(new ArrayList<Produits>());
        
        InputStream inputStream = context.getResourceAsStream("/Ressources/listeProduits.txt");
        InputStreamReader inputStrmReader = new InputStreamReader(inputStream);
        
        BufferedReader productFileBuffer = new BufferedReader(inputStrmReader);
        
        String line = productFileBuffer.readLine();
        
        do {
            String[] parameters = line.split(";");
            /* for (int i=0; i<6; i++){
                System.out.println(parameters[i]);
            }
            */
            Produits tempProduit = new Produits(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], Float.parseFloat(parameters[5]), parameters[6]);
            getListeDesProduits().add(tempProduit);
            
            line = productFileBuffer.readLine();
            if (line==null) {
                System.out.println("End of file reached...");
                break;
            }
        } while (true);
        
        request.setAttribute("listeProduits", getListeDesProduits());
        
        String url = "/produits.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
    
    // METHODES
    
    public void loadCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        String url = "/checkout.jsp";
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        
        request.setAttribute("cart", getCart());
        request.setAttribute("listeProduits", getListeDesProduits());
        request.setAttribute("total", getCart().getTotal());
        
        dispatcher.forward(request, response);
    }
    
        /**
     * Adding products to the cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession();
        String code = request.getParameter("productNumber"); 
        int qty = Integer.parseInt(request.getParameter("qty")); 
        
        getCart().addItem(getProductByCode(code), qty);
        
        int nbItems = getCart().getNbItems();

        if (session.isNew()) {
            setCart(new Cart());
        } else {
            session.setAttribute("produit", nbItems);
        }
        
        String url = "/cart.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        request.setAttribute("listeProduits", getListeDesProduits());
        dispatcher.forward(request, response);
        
    }
    
    private void loadCatalogue(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            if(request.getParameter("sessionSetter").equals("1")){
                // Paramètres de la session
                HttpSession session = request.getSession();
                session.setAttribute("nom", request.getParameter("sessionName"));
                session.setAttribute("password", request.getParameter("sessionPassword"));
            }
        } catch (Exception e){
            System.err.println("old session");
        }
        loadProducts(request, response);
        setCart(new Cart());
        
    }
    
    private void modificationCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String url = "/checkout.jsp";
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        
        String code = request.getParameter("productNumber");
        getCart().removeProduct(code);
        
        request.setAttribute("cart", getCart());
        request.setAttribute("listeProduits", getListeDesProduits());
        request.setAttribute("total", getCart().getTotal());

        dispatcher.forward(request, response);
    }
    
    // GETTER ET SETTER

    public ArrayList<Produits> getListeDesProduits() {
        return listeDesProduits;
    }

    public void setListeDesProduits(ArrayList<Produits> listeDesProduits) {
        this.listeDesProduits = listeDesProduits;
    }
    
    public Produits getProductByCode(String code){
        Produits prod = null;
        
        for(Produits p : getListeDesProduits()){
            if(p.getCode().equals(code)){
                prod = p;
            }
        }
        
        return prod;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    private boolean checkLogin(String userName, String password){
        return true;
    }
   
}
