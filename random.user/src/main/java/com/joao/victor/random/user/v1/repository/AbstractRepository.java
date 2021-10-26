package com.joao.victor.random.user.v1.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class AbstractRepository<T, PK extends Serializable> {

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass =
            (Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void save(T entity) {

        entityManager. persist(entity);
        entityManager.flush();
    }

    public T update(T entity) {

        entity = entityManager.merge(entity);
        return entity;
    }

    public void delete(PK id) {

        entityManager.remove(entityManager.getReference(entityClass, id));
        entityManager.flush();

    }

    public T findById(PK id) {

        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {

        return entityManager
                .createQuery("from " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }

    protected List<T> createQuery(String jpql, Object... params) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1, params[i]);
        }
        return query.getResultList();
    }

    protected T createSingleQuery(String jpql, Object... params) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1, params[i]);
        }
        return query.getSingleResult();
    }
}
