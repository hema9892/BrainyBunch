package com.bookcatalouge.intelj.controller;

import com.bookcatalouge.intelj.dto.PublisherDTO;
import com.bookcatalouge.intelj.entity.Publisher;
import com.bookcatalouge.intelj.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

   @PostMapping
    public Publisher createPublisher(@RequestBody PublisherDTO publisher){
       return publisherService.savePublisher(publisher);
    }
    @GetMapping
    public List<Publisher> getALlPublishers(){

       return publisherService.findALlPublishers();
    }
    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable Long id){
        return publisherService.findPublisherById(id);
    }
}
