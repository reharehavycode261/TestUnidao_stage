package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Region;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private RegionRepository regionRepository; // Assurez-vous que celui-ci est correctement configuré avec Spring Data JPA

    public List<Region> retrieveDashboardData() {
        return regionRepository.findAll();  // Exemple de récupération, ajustez selon le modèle de données réel
    }
}