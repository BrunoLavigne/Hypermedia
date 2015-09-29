/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

import java.util.ArrayList;
import products.Produits;

/**
 *
 * @author bruno
 */
public class Cart {
    
    // ATTRIBUTS
    
    private int nbItems;
    private Produits item;
    private String clientName;
    private ArrayList<Produits> cartClient;   
    
    // CONSTRUCTEURS
    
    public Cart(){
        setCartClient(new ArrayList<Produits>());
        setClientName("guest");
        setNbItems(0);
    }
    
    public Cart(String clientName){
        setClientName(clientName);
        setNbItems(0);
        setCartClient(new ArrayList<Produits>());
    }
    
    // METHODES
    
    public void addItem(Produits prod, int qty){
        Produits pt = prod;
        pt.setQty(qty);
        getCartClient().add(pt);
        setNbItems(getNbItems() + qty);
    }   
    
    public Produits getCartProduct(int index){
        return getCartClient().get(index);
    }
    
    public void removeProduct(String code){

        for(int i = 0; i < getCartClient().size(); i++){
            if(getCartClient().get(i).getCode().equals(code)){
                getCartClient().remove(i);
            }
        }
    }
    
    public void eraseCart(){
        getCartClient().clear();
    }
    
    public float getTotal(){
        float total = 0;
        for(Produits p : getCartClient()){
            total += (p.getBaseUnitPrice() * p.getQty());
        }
        return total;
    }
    
    @Override
    public String toString(){
        String message = "";
        for(Produits p : cartClient){
            message += p.getDescription() + "\"&emsp;\"" + p.getQty() + "\"&emsp;\"" + p.getBaseUnitPrice() + " $" + "</br>";
        }
        return message;
    }
    
    // GETTER ET SETTER

    public int getNbItems() {
        return nbItems;
    }

    private void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    public Produits getItem() {
        return item;
    }

    private void setItem(Produits item) {
        this.item = item;
    }

    public String getClientName() {
        return clientName;
    }

    private void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<Produits> getCartClient() {
        return cartClient;
    }

    private void setCartClient(ArrayList<Produits> cartClient) {
        this.cartClient = cartClient;
    }  
    
}
