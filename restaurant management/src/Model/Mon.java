/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author ns_red
 */
public class Mon {
    private int id;
    private String ten;
    private double gia;

    public Mon() {
    }
    ////////////////////////////////SETTER/////////////////////////////////////
    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    //////////////////////////////////GETTER//////////////////////////////////
    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public double getGia() {
        return gia;
    }    
    
    //override equal object to compare

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mon other = (Mon) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.gia) != Double.doubleToLongBits(other.gia)) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return true;
    }
    
}
