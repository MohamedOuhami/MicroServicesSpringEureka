package com.ouhami.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ouhami.client.entities.Client;
import java.util.List;
import com.ouhami.client.repositories.ClientRepository;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> findAll(){
        return clientRepository.findAll();
    }


    @GetMapping("/client/{id}")
    public Client findById(@PathVariable Integer id) throws Exception {
        return clientRepository.findById(id).orElseThrow(()-> new Exception("Client not found"));
    }


    
}


