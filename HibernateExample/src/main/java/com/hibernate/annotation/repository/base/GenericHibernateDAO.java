/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hibernate.annotation.repository.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.hibernate.annotation.util.HibernateUtil;
import com.hibernate.annotation.util.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 *
 * @author assanai.manurat
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;
    private Session session;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected GenericHibernateDAO(Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return HibernateUtil.getSession();
    }


    public Class<T> getPersistentClass() {
        return persistentClass;
    }


    public T findById(ID id) {
        return (T) getSession().get(persistentClass, id);
    }

    public T findById(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), id);
        }

        return entity;
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        criteria.add(example);
        return criteria.list();
    }

    public T makePersistent(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public void makeTransient(T entity) {
        getSession().delete(entity);
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria.list();
    }

}
