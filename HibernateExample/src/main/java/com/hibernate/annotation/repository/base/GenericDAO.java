package com.hibernate.annotation.repository.base;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author assanai.manurat
 */
public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id, boolean lock);

    T findById(ID id);

    List<T> findAll();

    List<T> findByExample(T exampleInstance);

    T makePersistent(T entity);

    void makeTransient(T entity);
}
