/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Daan
 */
public class Rol {
    public String rolName;
    public long rolNumber;
    
    public Rol(){
        
    }
    
    public Rol(long nmb, String name){
        rolName = name;
        rolNumber = nmb;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public long getRolNumber() {
        return rolNumber;
    }

    public void setRolNumber(long rolNumber) {
        this.rolNumber = rolNumber;
    }
    
    
}
