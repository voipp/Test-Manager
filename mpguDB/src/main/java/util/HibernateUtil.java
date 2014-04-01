package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.proxy.HibernateProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.02.14
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new RuntimeException(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }


    public static <T> T unproxy(T object) throws IllegalAccessException {
        T entity = object;
        Boolean fieldWasPrivate = false;
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            fieldWasPrivate = false;
            if(Modifier.isPrivate( field.getModifiers() )){
                fieldWasPrivate = true;
                field.setAccessible(true);
            }

            Object proxied = field.get(entity);

            if (proxied != null && proxied instanceof HibernateProxy) {
                proxied = null;
            }
            else if (proxied != null && proxied instanceof PersistentBag){
                proxied = null;
            }
            Object newFieldInstance = proxied;
            field.set(entity, newFieldInstance);
            if (fieldWasPrivate){
                field.setAccessible(false);
            }
        }
        return entity;
    }

    /**
     * Важно заметить, что класс необходимо брать каждого элемента коллекции, потому,
     * что возможны разные имплементации каждого элемента коллекции
     *
     * @param objects
     * @return
     */
    public static <T> List<T> unproxy(List<T> objects) throws IllegalAccessException {
        List<T> entities = objects;
        Boolean fieldWasPrivate = false;
        for (T entity : entities) {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                fieldWasPrivate = false;
                if(Modifier.isPrivate( field.getModifiers() )){
                    fieldWasPrivate = true;
                    field.setAccessible(true);
                }
                Object proxied = field.get(entity);

                if (proxied != null && proxied instanceof HibernateProxy) {
                    proxied = null;
                }
                else if (proxied != null && proxied instanceof PersistentBag){
                    proxied = null;
                }
                Object newFieldInstance = proxied;
                field.set(entity, newFieldInstance);
                if (fieldWasPrivate){
                    field.setAccessible(false);
                }
            }
        }

        return entities;
    }

    /**
     * откатывает все ранее открытые транзакции, если такие есть
     */
    public static void rollBackOpenedTransactions(){

        if(sessionFactory.getCurrentSession().getTransaction().isActive()){
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }

    }


}