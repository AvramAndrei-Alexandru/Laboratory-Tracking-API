package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractBaseService<T extends AbstractEntity, ID extends Serializable> {
    List<T> findAll();
    Optional<T> findById(ID id);
    boolean deleteById(ID id);
}
