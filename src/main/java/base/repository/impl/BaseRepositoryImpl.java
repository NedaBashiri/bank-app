package base.repository.impl;

import base.repository.BaseRepository;
import entity.BankAccount;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T> implements BaseRepository<T> {

    private EntityManager em;

    public BaseRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void persist(T account) {
        em.persist(account);
    }


    @Override
    public void remove(Long id) {
        BankAccount bankAccount = em.find(BankAccount.class, id);
        em.remove(bankAccount);
    }

    @Override
    public Optional<T> findById(Class<T> eClass, Long id) {
        return Optional.ofNullable((T) em.find(eClass, id));
    }

    @Override
    public List<T> findAll(Class<T> eClass) {

        TypedQuery<T> query = em.createQuery("select o from " + eClass.getName()+ " o", eClass);
        return query.getResultList();
    }

    @Override
    public void update(T eClass) {
        em.merge(eClass);
    }


}
