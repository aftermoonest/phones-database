package com.aftermoonest.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            registry = new StandardServiceRegistryBuilder().configure().build();
            System.out.println("\tregistry created");
            MetadataSources sources = new MetadataSources(registry);
            System.out.println("\tsources created");
            Metadata metadata = sources.getMetadataBuilder().build();
            System.out.println("\tmetadata created");
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            System.out.println("\tsessionFactory created");
        }
        return sessionFactory;
    }
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
