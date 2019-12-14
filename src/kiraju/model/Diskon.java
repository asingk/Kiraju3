package kiraju.model;
// Generated Sep 27, 2017 5:09:04 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




/**
 * Diskon generated by hbm2java
 */
@Entity
@Table(name = "diskon", schema = "APP")
public class Diskon  implements java.io.Serializable {


     private int id;
     private Integer tipe;
     private Integer bilangan;
     private Boolean status;
     private String nama;

    public Diskon() {
    }

	
    public Diskon(int id) {
        this.id = id;
    }
    public Diskon(int id, Integer tipe, Integer bilangan, Boolean status, String nama) {
       this.id = id;
       this.tipe = tipe;
       this.bilangan = bilangan;
       this.status = status;
       this.nama = nama;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskon_generator")
    @SequenceGenerator(name = "diskon_generator", sequenceName = "diskon_id")
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Integer getTipe() {
        return this.tipe;
    }
    
    public void setTipe(Integer tipe) {
        this.tipe = tipe;
    }
    public Integer getBilangan() {
        return this.bilangan;
    }
    
    public void setBilangan(Integer bilangan) {
        this.bilangan = bilangan;
    }
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }




}


