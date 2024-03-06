package com.benzair.myimdb.presentationlayer.directors;

import com.benzair.myimdb.businesslayer.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
/*
Tells Spring how to configure this bean.
This is a Rest controller and behind the scenes, Spring Boot takes care of the configuration.
*/
@RestController
//Means that all the endpoints in this controller will be about directors.
@RequestMapping("/api/v1/directors")
public class DirectorController {

    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getDirectors() {
        List<DirectorResponseDTO> directors = directorService.getAllDirectors();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{directorId}") // Define a new endpoint for getting a single movie
    public ResponseEntity<DirectorResponseDTO> getDirectorById(@PathVariable String directorId) {
        DirectorResponseDTO directorResponseDTO = directorService.findDirectorByDirectorId(directorId);
        if (directorResponseDTO != null) {
            return ResponseEntity.ok(directorResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<DirectorResponseDTO> addDirector(@RequestBody DirectorRequestDTO directorRequestDTO){
        DirectorResponseDTO createdDirector = directorService.addDirector(directorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDirector);
    }

    @PutMapping("/{directorId}")
    public ResponseEntity<DirectorResponseDTO> updateDirector(@RequestBody DirectorRequestDTO directorRequestDTO,
                                              @PathVariable String directorId){
        DirectorResponseDTO updatedDirector = directorService.updateDirector(directorRequestDTO, directorId);
        if (updatedDirector != null) {
            return ResponseEntity.ok(updatedDirector);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{directorId}")
    public ResponseEntity<Void> deleteDirector(@PathVariable String directorId){
        directorService.deleteDirector(directorId);
        return ResponseEntity.noContent().build();

    }

}
