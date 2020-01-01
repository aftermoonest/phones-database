package com.aftermoonest.util;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.entity.SmartPhone;
import com.aftermoonest.repository.PhoneRepository;
import org.hibernate.Session;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseManagement {
    public static void backUp(){
        if(HibernateUtil.getSessionFactory().getCurrentSession() == null){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
        }
        List<Phone> phones = PhoneRepository.getPhones();
        try{
            FileOutputStream backup = new FileOutputStream("phones.dat");
            ObjectOutputStream backupWriter = new ObjectOutputStream(backup);
            backupWriter.writeObject(phones);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<SmartPhone> smartPhones = PhoneRepository.getSmartPhones();
        try{
            FileOutputStream backup = new FileOutputStream("smartphones.dat");
            ObjectOutputStream backupWriter = new ObjectOutputStream(backup);
            backupWriter.writeObject(smartPhones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void restore(){
        if(HibernateUtil.getSessionFactory().getCurrentSession() == null){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
        }
        List<Phone> phones;
        try{
            FileInputStream restore = new FileInputStream("phones.dat");
            ObjectInputStream restoreWriter = new ObjectInputStream(restore);
            phones = (List<Phone>) restoreWriter.readObject();
            System.out.println(Arrays.toString(phones.toArray()));

            List<Phone> phones1 = phones;

            PhoneRepository.insert(phones1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
