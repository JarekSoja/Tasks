package com.crud.tasks.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {

        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }
}