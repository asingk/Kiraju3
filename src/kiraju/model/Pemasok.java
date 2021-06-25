package kiraju.model;
// Generated Sep 12, 2018 12:36:22 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




/**
 * Pemasok generated by hbm2java
 */
@Entity
@Table(name="pemasok", schema = "APP")
public class Pemasok  implements java.io.Serializable {


     private int id;
     private String nama;
     private String telp;
     private String alamat;
     private String email;
     private Boolean status;

    public Pemasok() {
    }

	
    public Pemasok(int id) {
        this.id = id;
    }
    public Pemasok(int id, String nama, String telp, String alamat, String email, Boolean status) {
       this.id = id;
       this.nama = nama;
       this.telp = telp;
       this.alamat = alamat;
       this.email = email;
       this.status = status;
    }
   
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pemasok_generator")
    @SequenceGenerator(name = "pemasok_generator", sequenceName = "pemasok_id", allocationSize = 1)
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getTelp() {
        return this.telp;
    }
    
    public void setTelp(String telp) {
        this.telp = telp;
    }
    public String getAlamat() {
        return this.alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }




}


