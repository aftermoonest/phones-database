package com.aftermoonest.repository;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.SmartPhone;
import com.aftermoonest.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PhoneRepository {

    private static final String tableName = "Phones";

    public static void insert(Object object) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.save(object);
        session.getTransaction().commit();
        System.out.println("Object inserted");
    }

    public static void update(Object object) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        System.out.println("Object updated");
    }

    public static void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        try {
            Phone phone = session.get(Phone.class, id);
            session.delete(phone);
        } catch (Exception ex) {
            SmartPhone smartPhone = session.get(SmartPhone.class, id);
            session.delete(smartPhone);
        }
        session.getTransaction().commit();
    }

    public static SmartPhone getSmartPhone(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        SmartPhone smartPhone = session.get(SmartPhone.class, id);
        session.getTransaction().commit();
        return smartPhone;
    }

    public static Phone getPhone(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Phone smartPhone = session.get(Phone.class, id);
        session.getTransaction().commit();
        return smartPhone;
    }

    public static List getSmartPhones() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        List smartPhones = session.createQuery("FROM SmartPhone").list();
        session.getTransaction().commit();
        return smartPhones;
    }

    public static List getPhones() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        List phones = session.createQuery("FROM Phone").list();
        session.getTransaction().commit();
        return phones;
    }
}
