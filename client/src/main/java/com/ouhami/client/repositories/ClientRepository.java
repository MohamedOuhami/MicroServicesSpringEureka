package com.ouhami.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ouhami.client.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{    
}
