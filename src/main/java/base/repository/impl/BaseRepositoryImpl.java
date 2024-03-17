package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import shared.utilities.PersistenceUnitManager;
import shared.utilities.PersistenceUnits;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T extends BaseEntity<PK>, PK extends Serializable> implements BaseRepository<T, PK> {
    EntityManager em = PersistenceUnitManager.getEntityManager(PersistenceUnits.UNIT_ONE);
    private final Class<T> clazz;

    public BaseRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public void persist(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (EntityExistsException | RollbackException ex) {
            em.getTransaction().rollback();
        }
    }


    @Override
    public void remove(PK id) {
        em.getTransaction().begin();
        em.remove(em.find(clazz, id));
        em.getTransaction().commit();
    }


    @Override
    public Optional<T> findById(PK id) {
        return Optional.ofNullable((T) em.find(clazz, id));
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = em.createQuery("select o from " + clazz.getSimpleName() + " o", clazz);
        List<T> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void update(T eClass) {
        try {
            em.getTransaction().begin();
            em.merge(eClass);
            em.getTransaction().commit();
        } catch (EntityExistsException | RollbackException ex) {
            em.getTransaction().rollback();
        }
    }


}
