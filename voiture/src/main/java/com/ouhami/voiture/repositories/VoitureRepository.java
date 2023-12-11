package com.ouhami.voiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ouhami.voiture.entities.Voiture;

import feign.Param;

import java.util.List;



@Repository
public interface VoitureRepository extends JpaRepository<Voiture,Integer>{

     @Query("SELECT v FROM Voiture v WHERE v.id_client = :clientId")
    List<Voiture> findByClientId(@Param("clientId") Integer clientId);

    
}
