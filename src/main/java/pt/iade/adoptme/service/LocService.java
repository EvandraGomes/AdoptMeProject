package pt.iade.adoptme.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iade.adoptme.models.Loc;
import pt.iade.adoptme.repositories.LocRepository;

import java.util.List;

@Service
public class LocService {
    @Autowired
    private LocRepository locRepository;

    public Loc getLocById(Long locId) {
        return locRepository.findById(locId).orElseThrow(() -> new RuntimeException("Local não encontrado"));
    }

    public List<Loc> getAllLocs() {
        return locRepository.findAll();  // Pode ser ajustado para retornar locais de doação ou filtrados
    }

    public List<Loc> getDonationLocations(double latMin, double latMax, double lonMin, double lonMax) {
        return locRepository.findByLocLatitudeBetweenAndLocLongitudeBetween(latMin, latMax, lonMin, lonMax);
    }

    public List<Loc> getDonationLocations() {
        return locRepository.findByLocType("doação");
    }

    public List<Loc> getLocsByType(String type) {
        return locRepository.findByLocType(type);
    }
}
