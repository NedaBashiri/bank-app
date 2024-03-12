package base.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    void persist(T account);

    void remove(Long id);

    Optional<T> findById(Class<T> eClass, Long id);

    List<T> findAll(Class<T> eClass);

    void update(T eClass);

}
