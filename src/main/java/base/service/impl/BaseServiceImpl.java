package base.service.impl;

import base.repository.BaseRepository;
import base.service.BaseService;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public void save(T account) {
        baseRepository.persist(account);
    }


    @Override
    public void deleteById(Long id) {
        baseRepository.remove(id);
    }

    @Override
    public Optional<T> findById(Class<T> eClass, Long id) {
        return baseRepository.findById(eClass,id);
    }

    @Override
    public List<T> findAll(Class<T> eClass) {

        return baseRepository.findAll(eClass);
    }

    @Override
    public void update(T eClass) {
        baseRepository.update(eClass);
    }

}
