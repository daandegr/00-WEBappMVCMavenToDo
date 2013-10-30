package models;

import java.io.Serializable;

public class User implements Serializable {

    private long customerNumber;
    private String name;
    private String streetAddress;
    private String city;
    private int huisNummer;
    private Rol rol;

    public User() {
    }

    public User(long customerNumber, String naam, String straatnaam, String stad, int hn, Rol rol) {
        this.setCustomerNumber(customerNumber);
        this.setName(naam);
        this.setStreetAddress(straatnaam);
        this.setCity(stad);
        huisNummer = hn;
        this.rol = rol;
    }

    /* Getters en setters voor de verschillende attributen van het Model */
    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        if (customerNumber < 1) {
            throw new IllegalArgumentException(
                    "Customer number may not be negative, value = " + customerNumber);
        }
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

    public int getHuisNummer() {
        return huisNummer;
    }

    public void setHuisNummer(int huisNummer) {
        this.huisNummer = huisNummer;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
