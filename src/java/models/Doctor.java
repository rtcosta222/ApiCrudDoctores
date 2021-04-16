/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author lscar
 */
public class Doctor {
   private int hcod;
   private int dcod;
   private String ape;
   private String espec;
   private int sal;
   
   public Doctor(){}

    public Doctor(int hcod, int dcod, String ape, String espec, int sal) {
        this.hcod = hcod;
        this.dcod = dcod;
        this.ape = ape;
        this.espec = espec;
        this.sal = sal;
    }

    public int getHcod() {
        return hcod;
    }

    public void setHcod(int hcod) {
        this.hcod = hcod;
    }

    public int getDcod() {
        return dcod;
    }

    public void setDcod(int dcod) {
        this.dcod = dcod;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    
}
