package POJOs;
// Generated Jul 16, 2020, 6:37:08 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Diadiemtochuc generated by hbm2java
 */
public class Diadiemtochuc  implements java.io.Serializable {


     private Integer idDiaDiemToChuc;
     private String ten;
     private String diaChi;
     private int sucChua;
     private Set hoinghis = new HashSet(0);

    public Diadiemtochuc() {
    }

	
    public Diadiemtochuc(String ten, String diaChi, int sucChua) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.sucChua = sucChua;
    }
    public Diadiemtochuc(String ten, String diaChi, int sucChua, Set hoinghis) {
       this.ten = ten;
       this.diaChi = diaChi;
       this.sucChua = sucChua;
       this.hoinghis = hoinghis;
    }
   
    public Integer getIdDiaDiemToChuc() {
        return this.idDiaDiemToChuc;
    }
    
    public void setIdDiaDiemToChuc(Integer idDiaDiemToChuc) {
        this.idDiaDiemToChuc = idDiaDiemToChuc;
    }
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getDiaChi() {
        return this.diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public int getSucChua() {
        return this.sucChua;
    }
    
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }
    public Set getHoinghis() {
        return this.hoinghis;
    }
    
    public void setHoinghis(Set hoinghis) {
        this.hoinghis = hoinghis;
    }




}


