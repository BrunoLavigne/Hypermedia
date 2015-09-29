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
public class AdministratorUser extends StandardUser {
    private boolean hasAdminRights;
    
    public AdministratorUser(String nom, String password){
        super(nom, password);
        setAdmin(true);
    }

    public boolean isAdmin() {
        return hasAdminRights;
    }

    public void setAdmin(boolean hasAdminRights) {
        this.hasAdminRights = hasAdminRights;
    }
}
