package com.pkroksite.web.service;

import com.pkroksite.web.domain.MedalsDTO;

import java.util.List;

public interface MedalsService {
    void addMedal(MedalsDTO medalsDTO);

    List<MedalsDTO> findAllOrderById();

    void addImageToProduct(String image, Long id);
}
