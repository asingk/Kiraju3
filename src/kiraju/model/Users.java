package kiraju.model;
// Generated May 18, 2017 12:05:47 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", schema = "APP")
public class Users  implements java.io.Serializable {


     private String id;
     private String nama;
     private String username;
     private String password;
     private Posisi posisiId;
     private Boolean status;
     private Boolean statusAbsensi;

    public Users() {
    }
    
    public Users(String id) {
        this.id = id;
    }
    
    public Users(String id, String nama, Posisi posisiId) {
        this.id = id;
        this.nama = nama;
        this.posisiId = posisiId;
    }
    

    public Users(String nama, String username, String password, Posisi posisiId, Boolean deletedFlag) {
       this.nama = nama;
       this.username = username;
       this.password = password;
       this.posisiId = posisiId;
       this.status = deletedFlag;
    }
   
    @Id
    @Column(name="id", nullable=false, updatable = false)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    @Column(name="username")
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="posisi_id")
    public Posisi getPosisiId() {
        return this.posisiId;
    }
    
    public void setPosisiId(Posisi posisiId) {
        this.posisiId = posisiId;
    }

//    public Boolean getDeletedFlag() {
//        return status;
//    }
//
//    public void setDeletedFlag(Boolean deletedFlag) {
//        this.status = deletedFlag;
//    }

    @Column(name="status_absensi")
    public Boolean getStatusAbsensi() {
        return statusAbsensi;
    }

    public void setStatusAbsensi(Boolean statusAbsensi) {
        this.statusAbsensi = statusAbsensi;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }




}


