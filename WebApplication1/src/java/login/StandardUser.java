/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

/**
 *
 * @author Marc-Éric Boury
 */
public class StandardUser {

    private String nom;
    private String password;
    
    public StandardUser(String nom, String password) {
        setNom(nom);
        setPassword(password);
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
    
    public boolean checkPassword(String receivedPW){
        return password.equals(receivedPW);
    }
}
