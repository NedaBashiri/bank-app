package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.User;
import repository.UserRepository;
import shared.utilities.PersistenceUnitManager;
import shared.utilities.PersistenceUnits;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {
    public UserRepositoryImpl(Class<User> clazz) {
        super(clazz);
    }

    private EntityManager entityManager = PersistenceUnitManager.getEntityManager(PersistenceUnits.UNIT_ONE);

    @Override
    public Optional<User> findUserByName(String name) {

        entityManager.getTransaction().begin();
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a where a.name=:name", User.class);
        query.setParameter("name", name);
        User user = new User();
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        entityManager.getTransaction().commit();

        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        entityManager.getTransaction().begin();
        TypedQuery<User> query = entityManager.createQuery("SELECT a FROM User a where a.email=:email", User.class);
        query.setParameter("email", email);
        User user = new User();
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        entityManager.getTransaction().commit();

        return user != null ? Optional.of(user) : Optional.empty();
    }
}

