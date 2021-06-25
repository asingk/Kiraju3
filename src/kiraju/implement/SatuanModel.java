/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.implement;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kiraju.interfaces.ISatuan;
import kiraju.model.Satuan;
import kiraju.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author arvita
 */
public class SatuanModel implements ISatuan{
    
    private final static Logger LOGGER = LogManager.getLogger(SatuanModel.class);

    @Override
    public ObservableList<String> getCodeListProp() {
        ObservableList<String> dataProp = FXCollections.observableArrayList();
        dataProp.addAll(getCodeList());
        return dataProp;
    }

    @Override
    public List<String> getCodeList() {
        List<String> result = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Satuan.class);
            List<Satuan> satuanList = criteria.list();
            if(null != satuanList) {
                satuanList.forEach((satuan) -> {
                    result.add(satuan.getCode());
                });
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        }
        session.close();
        return result;
    }
    
}
