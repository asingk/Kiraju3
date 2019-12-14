/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.implement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kiraju.interfaces.ITransaksiPembelian;
import kiraju.model.Laporan;
import kiraju.model.Pemasok;
import kiraju.model.TransaksiPembelian;
import kiraju.property.TransaksiPembelianProperty;
import kiraju.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author arvita
 */
public class TransaksiPembelianModel implements ITransaksiPembelian{
    
    private final static Logger LOGGER = Logger.getLogger(TransaksiPembelianModel.class);

    @Override
    public ObservableList<TransaksiPembelianProperty> getAllProp(int bulan) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ObservableList<TransaksiPembelianProperty> dataProperty = FXCollections.observableArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TransaksiPembelian tp left join tp.pemasokId p where month(tp.tanggal) = :bulan order by tp.id desc");
            query.setParameter("bulan", bulan);
            List<Object[]> obj = query.list();
            if(null != obj){
                for(Object[] row : obj){
                    TransaksiPembelian pembelian = (TransaksiPembelian) row[0];
                    Pemasok pemasok = (Pemasok) row[1];
                    TransaksiPembelianProperty property = new TransaksiPembelianProperty();
                    property.setId(pembelian.getId());
                    property.setTanggal(pembelian.getTanggal());
                    if(null != pemasok){
                        property.setPemasokNama(pemasok.getNama());
                        property.setPemasokId(pemasok.getId());
                    }
                    property.setTotal(pembelian.getTotal());
                    property.setStatus(pembelian.getStatus());
//                    property.setIsLunas(pembelian.getIsLunas());
                    dataProperty.add(property);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        }
        session.close();
        return dataProperty;
    }

    @Override
    public int insertOrUpdate(TransaksiPembelian pembelian) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(pembelian);
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to insert to database", e);
        } finally {
            session.close();
        }
        return pembelian.getId();
    }

    @Override
    public void remove(TransaksiPembelian pembelian) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.delete(pembelian);
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to insert to database", e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Laporan> getLaporanPembelian(LocalDate tglDari, LocalDate tglSampai) {
        List<Laporan> resultList = new ArrayList();
        Date dateDari = Date.from(tglDari.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateSampai = Date.from(tglSampai.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            String sql = "select sum(banyak) banyak, sum(harga*banyak) subtotal, m.NAMA daftar_menu, j.NAMA jenis_menu, m.satuan satuan, j.ID from DAFTAR_PEMBELIAN d\n" +
                        "join TRANSAKSI_PEMBELIAN t on t.ID = d.TRX_PEMBELIAN_ID\n" +
                        "join menu_item m on m.CODE = d.MENU_ITEM_CD\n" +
                        "join JENIS_MENU j on j.ID = m.JENIS_MENU_ID\n" +
                        "where t.STATUS = :status and t.tanggal between :tglDari and :tglSampai\n" +
                        "group by m.NAMA, j.nama, j.ID, m.satuan\n" +
                        "order by j.ID, subtotal desc";
            Query query = session.createSQLQuery(sql);
            query.setDate("tglDari", dateDari);
            query.setDate("tglSampai", dateSampai);
            query.setParameter("status", true);
            List<Object[]> rows = query.list();
            for(Object[] row : rows){
                Laporan laporan = new Laporan();
                laporan.setDaftarMenu(row[2].toString());
                laporan.setJenisMenu(row[3].toString());
//                laporan.setJumlah(Integer.valueOf(row[0].toString()));
                laporan.setSubtotal(Integer.valueOf(row[1].toString()));
//                String satuan = null == row[4] ? "" : row[4].toString();
                if(null != row[4]) {
                    laporan.setBanyak(row[0].toString() + " " + row[4].toString());
                }else{
                    laporan.setBanyak(row[0].toString());
                }
                
                resultList.add(laporan);
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        }
        session.close();
        return resultList;
    }

    @Override
    public List getChartByBulan(int bulan) {
        List resultList = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select DAY(tanggal), sum(total) from TransaksiPembelian where status = :status and MONTH(tanggal) = :month and YEAR(tanggal) = :year group by tanggal");
            query.setInteger("month", bulan);
            query.setInteger("year", LocalDate.now().getYear());
            query.setParameter("status", true);
            resultList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        } finally {
            session.close();
        }
        return resultList;
    }

    @Override
    public List getChartByTahun(int tahun) {
        List resultList = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select MONTH(tanggal), sum(total) from TransaksiPembelian where status = :status and YEAR(tanggal) = :year group by MONTH(tanggal)");
            query.setInteger("year", tahun);
            query.setParameter("status", true);
            resultList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        } finally {
            session.close();
        }
        return resultList;
    }

    @Override
    public ObservableList<TransaksiPembelianProperty> getAllPropByTgl(LocalDate localDate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ObservableList<TransaksiPembelianProperty> dataProperty = FXCollections.observableArrayList();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TransaksiPembelian tp left join tp.pemasokId p where tanggal = :tanggal order by tp.id desc");
            query.setParameter("tanggal", date);
            List<Object[]> obj = query.list();
            if(null != obj){
                for(Object[] row : obj){
                    TransaksiPembelian pembelian = (TransaksiPembelian) row[0];
                    Pemasok pemasok = (Pemasok) row[1];
                    TransaksiPembelianProperty property = new TransaksiPembelianProperty();
                    property.setId(pembelian.getId());
                    property.setTanggal(pembelian.getTanggal());
                    if(null != pemasok){
                        property.setPemasokNama(pemasok.getNama());
                        property.setPemasokId(pemasok.getId());
                    }
                    property.setTotal(pembelian.getTotal());
                    property.setStatus(pembelian.getStatus());
//                    property.setIsLunas(pembelian.getIsLunas());
                    dataProperty.add(property);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        }
        session.close();
        return dataProperty;
    }
    
}
