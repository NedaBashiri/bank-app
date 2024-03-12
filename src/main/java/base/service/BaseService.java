package base.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    void save(T account);

    void deleteById(Long id);

    Optional<T> findById(Class<T> eClass, Long id);

    List<T> findAll(Class<T> eClass);

    void update(T eClass);

}