package models;

import java.io.Serializable;

public class UserForm implements Serializable {

    private String customerNumber;
    private String name;
    private String streetAddress;
    private String city;
    private String huisNummer;
    private Rol rol;
    

    public UserForm() {
    }

    public UserForm(String customerNumber, String naam, String straatnaam, String stad, String hn, Rol rol) {
        this.setCustomerNumber(customerNumber);
        this.setName(naam);
        this.setStreetAddress(straatnaam);
        this.setCity(stad);
        huisNummer = hn;
        this.rol = rol;
    }

    /* Getters en setters voor de verschillende attributen van het Model */
    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException("Customer name may not be empty");
        }
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHuisNummer() {
        return huisNummer;
    }

    public void setHuisNummer(String huisNummer) {
        this.huisNummer = huisNummer;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
