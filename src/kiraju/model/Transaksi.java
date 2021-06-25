package kiraju.model;
// Generated May 18, 2017 12:05:47 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transaksi generated by hbm2java
 */
@Entity
@Table(name="transaksi"
        ,schema="APP"
)
public class Transaksi  implements java.io.Serializable {


     private int id;
     private Meja mejaId;
     private Date dtStart;
     private Date dtEnd;
     private Integer total;
     private Short status;
     private Set<Pesan> pesan = new HashSet<>(0);
     private String namaPemesan;
     private Users userStart;
     private Users userEnd;
     private Date endDtOnly;
     private Date endTimeOnly;
     private MetodePembayaran metodePembayaranId;
     private Pelanggan pelangganId;
     private Diskon diskonId;
     private Pajak pajakId;
     private Integer diskonTotal;
     private Integer pajakTotal;
     private Integer modalTotal;

    public Transaksi() {
    }
    
    public Transaksi(int id) {
        this.id = id;
    }

    public Transaksi(Meja mejaId, Date dtStart, Date dtEnd, Short status, Set<Pesan> pesan, String namaPemesan, Users userStart, Users userEnd, Date endDtOnly, Date endTimeOnly, MetodePembayaran metodePembayaranId) {
       this.mejaId = mejaId;
       this.dtStart = dtStart;
       this.dtEnd = dtEnd;
       this.status = status;
       this.pesan = pesan;
       this.namaPemesan = namaPemesan;
       this.userStart = userStart;
       this.userEnd = userEnd;
       this.endDtOnly = endDtOnly;
       this.endTimeOnly = endTimeOnly;
       this.metodePembayaranId = metodePembayaranId;
    }
   
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaksi_generator")
    @SequenceGenerator(name = "transaksi_generator", sequenceName = "transaksi_id", allocationSize = 1)
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="meja_id")
    public Meja getMejaId() {
        return this.mejaId;
    }
    
    public void setMejaId(Meja mejaId) {
        this.mejaId = mejaId;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_start")
    public Date getDtStart() {
        return this.dtStart;
    }
    
    public void setDtStart(Date dtStart) {
        this.dtStart = dtStart;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_end")
    public Date getDtEnd() {
        return this.dtEnd;
    }
    
    public void setDtEnd(Date dtEnd) {
        this.dtEnd = dtEnd;
    }
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transaksiId")
    public Set<Pesan> getPesan() {
        return this.pesan;
    }
    
    public void setPesan(Set<Pesan> pesan) {
        this.pesan = pesan;
    }
    
    @Column(name="nama_pemesan")
    public String getNamaPemesan() {
        return this.namaPemesan;
    }
    
    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_start")
    public Users getUserStart() {
        return this.userStart;
    }
    
    public void setUserStart(Users userStart) {
        this.userStart = userStart;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_end")
    public Users getUserEnd() {
        return this.userEnd;
    }
    
    public void setUserEnd(Users userEnd) {
        this.userEnd = userEnd;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="end_dt_only")
    public Date getEndDtOnly() {
        return this.endDtOnly;
    }
    
    public void setEndDtOnly(Date endDtOnly) {
        this.endDtOnly = endDtOnly;
    }
    
    @Temporal(TemporalType.TIME)
    @Column(name="end_time_only")
    public Date getEndTimeOnly() {
        return this.endTimeOnly;
    }
    
    public void setEndTimeOnly(Date endTimeOnly) {
        this.endTimeOnly = endTimeOnly;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="metode_pembayaran_id")
    public MetodePembayaran getMetodePembayaranId() {
        return metodePembayaranId;
    }

    public void setMetodePembayaranId(MetodePembayaran metodePembayaranId) {
        this.metodePembayaranId = metodePembayaranId;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="pelanggan_id")
    public Pelanggan getPelangganId() {
        return pelangganId;
    }

    public void setPelangganId(Pelanggan pelangganId) {
        this.pelangganId = pelangganId;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="diskon_id")
    public Diskon getDiskonId() {
        return diskonId;
    }

    public void setDiskonId(Diskon diskonId) {
        this.diskonId = diskonId;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="pajak_id")
    public Pajak getPajakId() {
        return pajakId;
    }

    public void setPajakId(Pajak pajakId) {
        this.pajakId = pajakId;
    }

    @Column(name="diskon_total")
    public Integer getDiskonTotal() {
        return diskonTotal;
    }

    public void setDiskonTotal(Integer diskonTotal) {
        this.diskonTotal = diskonTotal;
    }

    @Column(name="pajak_total")
    public Integer getPajakTotal() {
        return pajakTotal;
    }

    public void setPajakTotal(Integer pajakTotal) {
        this.pajakTotal = pajakTotal;
    }

    @Column(name="modal_total")
    public Integer getModalTotal() {
        return modalTotal;
    }

    public void setModalTotal(Integer modalTotal) {
        this.modalTotal = modalTotal;
    }




}


