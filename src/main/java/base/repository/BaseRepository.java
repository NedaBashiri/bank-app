package base.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, PK extends Serializable> {

    void persist(T entity);

    void remove(PK id);

    Optional<T> findById(PK id);

    List<T> findAll();

    void update(T eClass);

}
