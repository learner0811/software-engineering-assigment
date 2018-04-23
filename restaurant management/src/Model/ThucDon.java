/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author ns_red
 */
public class ThucDon {
    private int id;
    private String ten;
    private double tongTien;
    private ArrayList<Mon> danhSachMon;
    
    /////////////////////////////////////SETTER/////////////////////////////////////
    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setDanhSachMon(ArrayList<Mon> danhSachMon) {
        this.danhSachMon = danhSachMon;
    }
    
    //////////////////////////////////////GETTER/////////////////////////////////////
    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public double getTongTien() {
        return tongTien;
    }

    public ArrayList<Mon> getDanhSachMon() {
        return danhSachMon;
    }
    
}
