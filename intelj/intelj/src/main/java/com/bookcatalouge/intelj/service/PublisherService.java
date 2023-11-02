package com.bookcatalouge.intelj.service;

import com.bookcatalouge.intelj.dto.PublisherDTO;
import com.bookcatalouge.intelj.entity.Publisher;
import com.bookcatalouge.intelj.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
   private PublisherRepository publisherRepository;

    public Publisher savePublisher(PublisherDTO publisher){
        Publisher publisherDetails = new Publisher();
        publisherDetails.setName(publisher.getName());
        publisherDetails.setAddress(publisher.getAddress());
        return publisherRepository.save(publisherDetails);
    }

    public List<Publisher> findALlPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(Long id){
        return publisherRepository.findById(id).orElse(null);
    }
}
