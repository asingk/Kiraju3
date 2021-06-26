/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.implement;

import java.util.List;
import kiraju.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import kiraju.interfaces.IUmum;
import kiraju.model.Umum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arvita
 */
public class UmumModel implements IUmum{
    
    private final static Logger LOGGER = LoggerFactory.getLogger(UmumModel.class);

    @Override
    public Umum getUmum() {
        Umum umum = new Umum();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Umum.class);
            List<Umum> resultList = criteria.list();
            if(null != resultList && !resultList.isEmpty()) {
                umum = resultList.get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to select to database", e);
        } finally {
            session.close();
        }
        return umum;
    }

    @Override
    public void update(Umum umum) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update General set modeCafe = :modeCafe, printerCode = :printerCode");
            query.setParameter("modeCafe", umum.getModeCafe());
            query.setParameter("printerCode", umum.getPrinterCode());
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            LOGGER.error("failed to update to database", e);
        } finally {
            session.close();
        }
    }
    
}
