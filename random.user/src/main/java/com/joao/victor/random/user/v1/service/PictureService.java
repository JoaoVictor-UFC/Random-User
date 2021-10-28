package com.joao.victor.random.user.v1.service;

import com.joao.victor.random.user.v1.dtos.PictureRequest;
import com.joao.victor.random.user.v1.entities.PictureEntity;
import com.joao.victor.random.user.v1.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PictureService {

    @Autowired private PictureRepository pictureRepository;

    public PictureEntity savePicture(PictureRequest request){
        PictureEntity entity = new PictureEntity();
        entity.setLarge(request.getLarge());
        entity.setMedium(request.getMedium());
        entity.setThumbnail(request.getThumbnail());
        return pictureRepository.save(entity);
    }
}
