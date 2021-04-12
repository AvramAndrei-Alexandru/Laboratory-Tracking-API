package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractBaseRepository<T extends AbstractEntity, ID extends Serializable> extends JpaRepository<T, ID> {
}