package com.pkroksite.web.repository;

import com.pkroksite.web.entity.MedalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedalsRepository extends JpaRepository<MedalsEntity, Long> {
    MedalsEntity findByName(String name);

    List<MedalsEntity> findAllByOrderById();
}
