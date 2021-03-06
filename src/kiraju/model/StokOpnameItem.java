package kiraju.model;
// Generated Sep 22, 2017 2:46:46 PM by Hibernate Tools 4.3.1

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
 * StokOpnameItem generated by hbm2java
 */
@Entity
@Table(name="stok_opname_item"
        ,schema="APP"
)
public class StokOpnameItem  implements java.io.Serializable {


     private int id;
     private StokOpname stokOpnameId;
//     private MenuItem menuItemId;
     private Integer stokTersedia;
     private Integer stokSelisih;
     private Integer stokAwal;
     private MenuItem menuItemCode;
     private String ket;

    public StokOpnameItem() {
    }

	
    public StokOpnameItem(int id) {
        this.id = id;
    }
    public StokOpnameItem(int id, StokOpname stokOpnameId) {
       this.id = id;
       this.stokOpnameId = stokOpnameId;
//       this.menuItemId = menuItemId;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stok_opname_item_generator")
    @SequenceGenerator(name = "stok_opname_item_generator", sequenceName = "stok_opname_item_id")
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="stok_opname_id")
    public StokOpname getStokOpnameId() {
        return this.stokOpnameId;
    }
    
    public void setStokOpnameId(StokOpname stokOpnameId) {
        this.stokOpnameId = stokOpnameId;
    }
//    public MenuItem getMenuItemId() {
//        return this.menuItemId;
//    }
//    
//    public void setMenuItemId(MenuItem menuItemId) {
//        this.menuItemId = menuItemId;
//    }

    @Column(name="stokTersedia")
    public Integer getStokTersedia() {
        return stokTersedia;
    }

    public void setStokTersedia(Integer stokTersedia) {
        this.stokTersedia = stokTersedia;
    }

    @Column(name="stokSelisih")
    public Integer getStokSelisih() {
        return stokSelisih;
    }

    public void setStokSelisih(Integer stokSelisih) {
        this.stokSelisih = stokSelisih;
    }

    @Column(name="stokAwal")
    public Integer getStokAwal() {
        return stokAwal;
    }

    public void setStokAwal(Integer stokAwal) {
        this.stokAwal = stokAwal;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="menu_item_code")
    public MenuItem getMenuItemCode() {
        return menuItemCode;
    }

    public void setMenuItemCode(MenuItem menuItemCode) {
        this.menuItemCode = menuItemCode;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }




}


