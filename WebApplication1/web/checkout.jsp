<%-- 
    Document   : checkout
    Created on : 28-Sep-2015, 10:28:34 AM
    Author     : bruno
--%>

<%@page import="carte.Cart"%>
<%@page import="products.Produits"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>

    </head>
    <body>
        <h1>Votre s&eacute;lection</h1>
        <p><%= session.getAttribute("nom") %></p>
        <p><%= session.getAttribute("age") %></p>
        <p>Veuillez faire une derni&egrave;re v&eacute;rification</p>
                
        <% Cart cart = (Cart) request.getAttribute("cart"); %>
        <% float total = (Float) request.getAttribute("total"); %>
             
        <table border="0">
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantite</th>      
                    <th>Total</th>
                    <th>Cancel</th>
                    
                </tr>
                                                
                <% for(int i = 0; i < cart.getCartClient().size(); i++){ %>
                                                 
                    <form action="makeWebSite" value="final">
                        <input type="hidden" name="productNumber" value="<%= cart.getCartClient().get(i).getCode() %>" >
                    <tr>
                        <td align="center"><%= cart.getCartClient().get(i).getNom() %></td>
                        <td align="center"><%= cart.getCartClient().get(i).getDescription() %></td>
                        <td align="center"><%= cart.getCartClient().get(i).getBaseUnitPrice() %>$</td>
                        <td align="center"><%= cart.getCartClient().get(i).getQty() %></td>    
                        <td align="center"><%= (cart.getCartClient().get(i).getBaseUnitPrice()) * (cart.getCartClient().get(i).getQty())%>$</td>
                        <td align="center"><input type="submit" name="cancel" value="x" ></td>
                    </tr>
                    </form>                                              
                        
                <% } %>  
        
        </table>
                
        <p>Facture total : <%= total %> $</p>
        
    </body>
</html>
