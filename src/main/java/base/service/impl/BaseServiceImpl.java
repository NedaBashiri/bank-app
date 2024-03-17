package base.service.impl;

import base.repository.BaseRepository;
import base.service.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T, PK extends Serializable, Repository extends BaseRepository<T, PK>> implements BaseService<T, PK> {

    protected Repository baseRepository;

    public BaseServiceImpl(Repository baseRepository) {
        this.baseRepository = baseRepository;
    }


    @Override
    public void save(T entity) {
        baseRepository.persist(entity);
    }

    @Override
    public void deleteById(Class<T> eClass,PK id) {
        baseRepository.remove(id);
    }

    @Override
    public Optional<T> findById(PK id) {
        return baseRepository.findById(id);
    }

    @Override
    public List<T> findAll() {

        return baseRepository.findAll();
    }

    @Override
    public void update(T eClass) {
        baseRepository.update(eClass);
    }

}
