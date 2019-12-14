/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.implement;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kiraju.interfaces.IDaftarPembelian;
import kiraju.interfaces.IMenuItem;
import kiraju.model.DaftarPembelian;
import kiraju.model.MenuItem;
import kiraju.model.TransaksiPembelian;
import kiraju.property.DaftarPembelianProperty;
import kiraju.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author arvita
 */
public class DaftarPembelianModel implements IDaftarPembelian{
    
    private final static Logger LOGGER = Logger.getLogger(DaftarPembelianModel.class);
    private final IMenuItem iMenuItem = new MenuItemModel();

    @Override
    public void insertList(ObservableList<DaftarPembelianProperty> data, int trxPembelianId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            for(int i=0; i<data.size(); i++) {
                DaftarPembelian pembelian = new DaftarPembelian();
                pembelian.setNamaProduk(data.get(i).getProduk());
                pembelian.setBanyak(Integer.valueOf(data.get(i).getJumlah()));
                pembelian.setHarga(Integer.valueOf(data.get(i).getHarga()));
                pembelian.setTrxPembelianId(new TransaksiPembelian(trxPembelianId));
                pembelian.setMenuItemCd(iMenuItem.getByNama(data.get(i).getProduk()));
//                pembelian.setSatuan(new Satuan(data.get(i).getSatuan()));
                session.save(pembelian);
                if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to insert or update to database", e);
        }
        session.close();
    }

    @Override
    public ObservableList<DaftarPembelianProperty> getListBeli(TransaksiPembelian pembelian) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ObservableList<DaftarPembelianProperty> dataProperty = FXCollections.observableArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DaftarPembelian.class);
            criteria.addOrder(Order.asc("id"));
            criteria.add(Restrictions.eq("trxPembelianId", pembelian));
            List resultList = criteria.list();
            if(null != resultList){
                for(Object o : resultList){
                    DaftarPembelian daftarPembelian = (DaftarPembelian) o;
                    DaftarPembelianProperty property = new DaftarPembelianProperty();
                    property.setId(daftarPembelian.getId());
                    property.setProduk(daftarPembelian.getNamaProduk());
                    property.setHarga(daftarPembelian.getHarga().toString());
                    property.setJumlah(daftarPembelian.getBanyak().toString());
                    property.setHargaFormatted(daftarPembelian.getHarga());
                    property.setMenuItemCode(daftarPembelian.getMenuItemCd().getCode());
//                    property.setSatuan(daftarPembelian.getSatuan().getCode());
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
    public void deleteByTrxPembelianId(TransaksiPembelian pembelian) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from DaftarPembelian where trxPembelianId = :trxPembelianId");
            query.setParameter("trxPembelianId", pembelian);
            query.executeUpdate();
//            session.delete(pembelian);
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to delete to database", e);
        } finally {
            session.close();
        }
    }
    
    @Override
    public boolean isMenuExist(MenuItem item) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from DaftarPembelian p join p.trxPembelianId t where p.menuItemCode = :menuItemCode and t.status = :status");
            query.setParameter("menuItemCode", item);
            query.setParameter("status", true);
            List<DaftarPembelian> resultList = query.list();
            if(null != resultList && resultList.size() > 0){
                result = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        } finally {
            session.close();
        }
        return result;
    }
    
}
