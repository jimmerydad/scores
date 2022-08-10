package org.romaine.scores.controller;

import org.romaine.scores.model.Game;
import org.romaine.scores.model.golf.Course;
import org.romaine.scores.repository.CourseRepository;
import org.romaine.scores.repository.GameRepository;
import org.romaine.scores.util.FileUploadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
class GameController {

    private final GameRepository repository;

    GameController(GameRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/games")
    List<Game> all() {
        return (List<Game>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/game")
    Game newGame(@RequestBody Game newGame) {
        return repository.save(newGame);
    }

//    @PostMapping("/profile-picture")
//    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile imageFile, @ModelAttribute UserDTO requestDto) {
//        try {
//            UserDTO created  =userDetailsService.saveImg(requestDto, file.getBytes());
//            return new ResponseEntity<>(created, HttpStatus.OK);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }



    // Single item

    @GetMapping("/game/{id}")
    Optional<Game> one(@PathVariable Integer id) {

        return repository.findById(id);
        // not found exception handler here...
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/games/{id}")
    Game replaceGame(@RequestBody Game newGame, @PathVariable Integer id) {

        return repository.findById(id)
                .map(game -> {
                    game.setName(newGame.getName());
                    game.setType(newGame.getType());
                    return repository.save(game);
                })
                .orElseGet(() -> {
                    newGame.setId(id);
                    return repository.save(newGame);
                });
    }

    @DeleteMapping("/games/{id}")
    void deleteGame(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}