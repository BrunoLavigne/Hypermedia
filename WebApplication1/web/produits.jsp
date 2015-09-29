<%-- 
    Document   : produits
    Created on : 22-Sep-2015, 3:40:16 PM
    Author     : bruno
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="products.Produits"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Produits</h1>
        <%                    
            ArrayList<Produits> listeProduits = (ArrayList<Produits>) request.getAttribute("listeProduits");
            session.setAttribute("total", 0);            
        %>
        
        <table border="1">
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantite</th>                
                    <th>Order</th>
                    
                </tr>
                
                <% for(int i = 0; i < listeProduits.size(); i++){ %>
                
                    <form action="makeWebSite" value="produits">
                        <input type="hidden" name="productNumber" value="<%= listeProduits.get(i).getCode() %>" >
                    <tr>
                        <td><%= listeProduits.get(i).getNom() %></td>
                        <td><%= listeProduits.get(i).getDescription() %></td>
                        <td><%= listeProduits.get(i).getBaseUnitPrice()%></td>
                        <td> 
                            <select name="qty">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                          </select> 
                        </td>                        
                        <td><input type="submit" value="Submit" name='addToCart' id='btnCart'></td>
    
                    </tr>
                    </form>

                <% } %>                

        </table>
        
    </body>
</html>
