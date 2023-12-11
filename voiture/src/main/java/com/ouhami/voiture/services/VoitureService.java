package com.ouhami.voiture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ouhami.voiture.entities.Voiture;
import com.ouhami.voiture.repositories.VoitureRepository;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    public Voiture enregistrerVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    public Voiture getVoitureById(Integer id) {
        return voitureRepository.findById(id).orElse(null);
    }

    public List<Voiture> getVoituresByClientId(Integer clientId) {
        return voitureRepository.findByClientId(clientId);
    }

}
