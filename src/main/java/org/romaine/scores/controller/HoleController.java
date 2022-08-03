package org.romaine.scores.controller;

import java.util.List;
import java.util.Optional;

import org.romaine.scores.model.golf.Hole;
import org.romaine.scores.repository.HoleRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HoleController {

    private final HoleRepository repository;

    HoleController(HoleRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/holes")
    List<Hole> all() {
        return (List<Hole>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/hole")
    Hole newHole(@RequestBody Hole newHole) {
        return repository.save(newHole);
    }

    // Single item

    @GetMapping("/hole/{id}")
    Optional<Hole> one(@PathVariable Integer id) {

        return repository.findById(id);
        // not found exception handler here...
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/holes/{id}")
    Hole replaceHole(@RequestBody Hole newHole, @PathVariable Integer id) {

        return repository.findById(id)
                .map(hole -> {
                    hole.setHoleNumber(newHole.getHoleNumber());
                    hole.setPar(newHole.getPar());
                    hole.setCourse(newHole.getCourse());
                    return repository.save(hole);
                })
                .orElseGet(() -> {
                    newHole.setId(id);
                    return repository.save(newHole);
                });
    }

    @DeleteMapping("/holes/{id}")
    void deleteHole(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}