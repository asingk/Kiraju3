package kiraju.model;
// Generated Oct 3, 2017 10:59:09 AM by Hibernate Tools 4.3.1



/**
 * MetodePembayaran generated by hbm2java
 */
public class MetodePembayaran  implements java.io.Serializable {


     private int id;
     private String nama;
     private Boolean status;

    public MetodePembayaran() {
    }

	
    public MetodePembayaran(int id) {
        this.id = id;
    }
    public MetodePembayaran(int id, String nama, Boolean status) {
       this.id = id;
       this.nama = nama;
       this.status = status;
    }
   
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
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }




}


