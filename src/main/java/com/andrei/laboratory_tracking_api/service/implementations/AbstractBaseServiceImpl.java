package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.AbstractEntity;
import com.andrei.laboratory_tracking_api.repository.AbstractBaseRepository;
import com.andrei.laboratory_tracking_api.service.contracts.AbstractBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public abstract class AbstractBaseServiceImpl<T extends AbstractEntity, ID extends Serializable> implements AbstractBaseService<T,ID> {

    private final AbstractBaseRepository<T, ID> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(ID id) {
        if(findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}


