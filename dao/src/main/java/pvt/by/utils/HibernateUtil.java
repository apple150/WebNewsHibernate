/**
 * Copyright (c) 2012 by VeriFone, Inc.
 * All Rights Reserved.
 *
 * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
 * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
 *
 * Use, disclosure, or reproduction is prohibited
 * without prior written approval from VeriFone, Inc.
 *
 * User: yslabko
 * Date: 14.04.14
 * Time: 13:55
 */

package pvt.by.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final Logger log = LogManager.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory;
    private static final ThreadLocal sessions = new ThreadLocal();

    static {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed. " + ex);
            throw new RuntimeException(ex);
        }
    }

    private static HibernateUtil util = null;

    private HibernateUtil() {
    }

    public static Session getSession() {
        Session session = (Session) sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public static Session closeSession() {
        Session session = (Session) sessions.get(); //Object ThreadLocal
        sessions.set(null);
        if ((session != null) && (session.isOpen())) {
            session.close();
        }
        return session;
    }

    public static synchronized HibernateUtil getHibernateUtil() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }
}