package com.hibernate.annotation.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 * User: assanai.manurat
 * Date: 4/6/2014
 * Time: 12:08 PM
 */
public class PrePersistIntercepter extends EmptyInterceptor {

    private static final long serialVersionUID = 5532484391021720288L;

    public static final Logger logger = Logger.getLogger(PrePersistIntercepter.class.getName());

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {

        try {
            logger.info("> onSave interceptor");
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createDateTime".equals(propertyNames[i])) {
                    state[i] = new Date();
                    return true;
                }
            }
        } catch (Exception ex) {
            throw new HibernateException("Only one method with no argument allow for PrePersist on each entity.", ex);
        }
        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        logger.info("> onFlushDirty interceptor");
        for (int i = 0; i < propertyNames.length; i++) {
            if ("updateDateTime".equals(propertyNames[i])) {
                currentState[i] = new Date();
                return true;
            }
        }
        return false;

    }
}
