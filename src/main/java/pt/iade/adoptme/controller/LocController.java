package pt.iade.adoptme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iade.adoptme.models.Loc;
import pt.iade.adoptme.service.LocService;

import java.util.List;

@RestController@RequestMapping("/api/loc")
public class LocController {
    @Autowired
    private LocService locService;

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Loc>> getLocsByType(@PathVariable String type) {
        return ResponseEntity.ok(locService.getLocsByType(type));
    }

    // apenas locais onde pode se doar
    @GetMapping("/donations")
    public ResponseEntity<List<Loc>> getDonationLocs() {
        return ResponseEntity.ok(locService.getDonationLocations());
    }

    // obter os dados da (associação fictícia)
    @GetMapping("/{locId}")
    public ResponseEntity<Loc> getLoc(@PathVariable Long locId) {
        return ResponseEntity.ok(locService.getLocById(locId));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Loc>> getAllLocs() {
        return ResponseEntity.ok(locService.getAllLocs());
    }
}
