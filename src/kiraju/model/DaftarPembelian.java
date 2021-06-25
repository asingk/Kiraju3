package kiraju.model;
// Generated Sep 12, 2018 12:36:22 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




/**
 * DaftarPembelian generated by hbm2java
 */
@Entity
@Table(name = "daftar_pembelian", schema = "APP")
public class DaftarPembelian  implements java.io.Serializable {


     private int id;
     private Integer banyak;
     private Integer harga;
     private String namaProduk;
     private MenuItem menuItemCd;
     private String satuan;
     private TransaksiPembelian trxPembelianId;

    public DaftarPembelian() {
    }

	
    public DaftarPembelian(int id) {
        this.id = id;
    }
    public DaftarPembelian(int id, Integer banyak, Integer harga, String namaProduk, MenuItem menuItemCd, String satuan, TransaksiPembelian trxPembelianId) {
       this.id = id;
       this.banyak = banyak;
       this.harga = harga;
       this.namaProduk = namaProduk;
       this.menuItemCd = menuItemCd;
       this.satuan = satuan;
       this.trxPembelianId = trxPembelianId;
    }
   
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daftar_pembelian_generator")
    @SequenceGenerator(name = "daftar_pembelian_generator", sequenceName = "daftar_pembelian_id", allocationSize = 1)
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Integer getBanyak() {
        return this.banyak;
    }
    
    public void setBanyak(Integer banyak) {
        this.banyak = banyak;
    }
    public Integer getHarga() {
        return this.harga;
    }
    
    public void setHarga(Integer harga) {
        this.harga = harga;
    }
    
    @Column(name="nama_produk")
    public String getNamaProduk() {
        return this.namaProduk;
    }
    
    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="menu_item_cd")
    public MenuItem getMenuItemCd() {
        return this.menuItemCd;
    }
    
    public void setMenuItemCd(MenuItem menuItemCd) {
        this.menuItemCd = menuItemCd;
    }
    public String getSatuan() {
        return this.satuan;
    }
    
    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
    
    @ManyToOne()
    @JoinColumn(name="trx_pembelian_id")
    public TransaksiPembelian getTrxPembelianId() {
        return this.trxPembelianId;
    }
    
    public void setTrxPembelianId(TransaksiPembelian trxPembelianId) {
        this.trxPembelianId = trxPembelianId;
    }




}

