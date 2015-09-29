/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Produits {
    
    private String code;
    private String categorie;
    private String nom;
    private String description;
    private String imageURL;
    private float baseUnitPrice;
    private String qtyType;
    private int qty;
    private ArrayList<Produits> productsListe = new ArrayList<Produits>();
    
    public Produits() {
        setCode("");
        setCategorie("");
        setNom("");
        setDescription("");
        setImageURL("");
        setBaseUnitPrice(0);
        setQtyType("");
    }
    
    public Produits(String code, String categorie, String nom, String description, String imageURL, float baseUnitPrice, String qtyType){
        
        setCode(code);
        setCategorie(categorie);
        setNom(nom);
        setDescription(description);
        setImageURL(imageURL);
        setBaseUnitPrice(baseUnitPrice);
        setQtyType(qtyType);
    }
    
    private void add(){
        getProductsListe().add(new Produits(getCode(), getCategorie(), getNom(), getDescription(), getImageURL(), getBaseUnitPrice(), getQtyType()));
    }
    
    public Produits getProductByCode(String code){
        Produits rechercheProduct = null;
        for(Produits p : getProductsListe()){
            if(p.getCode().equals(code)){
                rechercheProduct = p;
            }
        }
        return rechercheProduct;
    }

    private void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    private void setBaseUnitPrice(float baseUnitPrice) {
        this.baseUnitPrice = baseUnitPrice;
    }

    public float getBaseUnitPrice() {
        return baseUnitPrice;
    }

    private void setQtyType(String qtyType) {
        this.qtyType = qtyType;
    }

    public String getQtyType() {
        return qtyType;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    private void setImageURL(String linkedFileName) {
        this.imageURL = linkedFileName;
    }

    public String getImageURL() {
        return imageURL;
    }
    
    public ArrayList<Produits> getProductsListe() {
        return productsListe;
    }

    private void setProductsListe(ArrayList<Produits> productsListe) {
        this.productsListe = productsListe;
    }  

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
