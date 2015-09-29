<%-- 
    Document   : index
    Created on : 22-Sep-2015, 3:22:07 PM
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Articles de bureau</title>
    </head>
    <body>
        
            <form action='makeWebSite' method="get">
            
            <h1>Index</h1>
            <form action='makeWebSite' value='sessionName'>
                <input type='text' placeholder='Inscrire votre nom' name='sessionName'>
                <input type='text' placeholder='Inscrire votre age' name='sessionAge'>       
        
                <%if(session.isNew()){ %>
                    <input type='hidden' name='sessionSetter' value='1'></input>
                <%}%>
                
                <input type='submit' class='btn-regular' name='loadCatalogue' value='produits' id='btnLoadProducts'></input>
            </form>
        </form>
    </body>
</html>