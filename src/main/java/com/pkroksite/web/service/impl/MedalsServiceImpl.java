package com.pkroksite.web.service.impl;

import com.pkroksite.web.domain.MedalsDTO;
import com.pkroksite.web.entity.MedalsEntity;
import com.pkroksite.web.exceptions.ResourceNotFoundException;
import com.pkroksite.web.repository.MedalsRepository;
import com.pkroksite.web.service.MedalsService;
import com.pkroksite.web.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedalsServiceImpl implements MedalsService {
    private MedalsRepository medalsRepository;
    private ObjectMapperUtils modelMapper;

    @Autowired
    public MedalsServiceImpl(MedalsRepository medalsRepository, ObjectMapperUtils modelMapper){
        this.medalsRepository=medalsRepository;
        this.modelMapper=modelMapper;
    }


    @Override
    public void addMedal(MedalsDTO medalsDTO) {
        MedalsEntity medal = modelMapper.map(medalsDTO, MedalsEntity.class);
        medalsRepository.save(medal);
    }

    @Override
    public List<MedalsDTO> findAllOrderById() {
        List<MedalsEntity> medalsEntities = medalsRepository.findAllByOrderById();
        return modelMapper.mapAll(medalsEntities, MedalsDTO.class);
    }

    @Override
    public void addImageToProduct(String image, Long id) {
        System.out.println("Set image to product + " + id);
        MedalsEntity medalsEntity = medalsRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product with id [" + id + "] not found")
                );
        medalsEntity.setImage(image);
        medalsRepository.save(medalsEntity);
    }

    @Override
    public void deleteMedalById(Long id) {
        MedalsEntity medalsEntity = medalsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete type with id[" + id + "]not found"));
        medalsRepository.deleteById(id);
    }
}
