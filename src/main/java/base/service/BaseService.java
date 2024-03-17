package base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, PK extends Serializable> {

    void save(T entity);

    void deleteById(Class<T> eClass,PK id);

    Optional<T> findById(PK id);

    List<T> findAll();

    void update(T eClass);

}