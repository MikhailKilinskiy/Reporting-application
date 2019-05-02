package org.dao;

import java.io.Serializable;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.internal.SessionImpl;
import org.hibernate.cfg.Configuration;

public class GenericDao<T, Id extends Serializable>
    implements DaoInterface<T, Id> {

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;
    private static Session currentSession;
    private static Transaction currentTransaction;

    private Class<T> entityClass;

    public GenericDao() {
    }

    private static SessionFactory getSessionFactory() {
       SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
       return sessionFactory;
    }

    public static void openSessionAndTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public static void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    public static Connection getJdbcConnection() throws SQLException {
        Connection jdbcConnection = null;
        try {
            openCurrentSession();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (currentSession != null) {
            SessionImpl sessionImpl = (SessionImpl) currentSession;
            jdbcConnection = sessionImpl.connection();
        } else {
            throw new SQLException("There is no openning Hibernate session!");
        }
        return jdbcConnection;
    }

    public static void closeSessionAndTransaction() {
        if (currentTransaction != null) currentTransaction.commit();
        if (currentSession != null) currentSession.close();
    }

    public void persist(T entity) {
        try {
            openSessionAndTransaction();
            currentSession.save(entity);
        } catch (Exception e) {
            if(currentTransaction != null) {
                currentTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            closeSessionAndTransaction();
        }
    }

    public void update(T entity) {
        try {
            openSessionAndTransaction();
            currentSession.update(entity);
        } catch (Exception e) {
            if(currentTransaction != null) {
                currentTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            closeSessionAndTransaction();
        }
    }

    public void delete(T entity) {
        try {
            openSessionAndTransaction();
            currentSession.delete(entity);
        } catch (Exception e) {
            if(currentTransaction != null) {
                currentTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            closeSessionAndTransaction();
        }
    }

    public T findById(Id id) {
        T entity = null;
        try {
            openSessionAndTransaction();
            entity = currentSession.find(entityClass, id);
        } catch (Exception e) {
            if(currentTransaction != null) {
                currentTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            closeSessionAndTransaction();
        }
        return entity;
    }

    public List<T> findAll() {
        List<T> entities = null;
        try {
            openSessionAndTransaction();
            Table table = entityClass.getAnnotation(Table.class);
            String tableName = table.name();
            entities = currentSession.createQuery("from " + tableName, entityClass).list();
        } catch (Exception e) {
            if(currentTransaction != null) {
                currentTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            closeSessionAndTransaction();
        }
        return entities;
    }

}
