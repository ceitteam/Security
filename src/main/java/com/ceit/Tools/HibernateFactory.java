package com.ceit.Tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-30
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class HibernateFactory {
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static Configuration configuration  = new Configuration();
    private static SessionFactory sessionFactory = null;
    private static String configFile = "Hibernate.cfg.xml";
    static{
        try
        {
            configuration.configure(configFile);
            ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private HibernateFactory(){}
    public static Session getSession()
    {
        Session session = threadLocal.get();
        if (session==null || !session.isOpen())
        {
            if (sessionFactory==null)
                rebuildSessionFactory();
            session = (sessionFactory !=null)?sessionFactory.openSession():null;
            threadLocal.set(session);
        }
        return session;
    }
    public static void closeSession()
    {
        Session session  = threadLocal.get();
        threadLocal.set(null);
        if (session !=null)
            session.close();
    }
    private static void rebuildSessionFactory() {
        try
        {
            configuration.configure(configFile);
            ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
