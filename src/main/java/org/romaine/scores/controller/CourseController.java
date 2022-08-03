package org.romaine.scores.controller;

import org.romaine.scores.model.golf.Course;
import org.romaine.scores.model.golf.Hole;
import org.romaine.scores.repository.CourseRepository;
import org.romaine.scores.repository.HoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class CourseController {

    private final CourseRepository repository;

    CourseController(CourseRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/courses")
    List<Course> all() {
        return (List<Course>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/course")
    Course newCourse(@RequestBody Course newCourse) {
        return repository.save(newCourse);
    }

    // Single item

    @GetMapping("/course/{id}")
    Optional<Course> one(@PathVariable Integer id) {

        return repository.findById(id);
        // not found exception handler here...
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/courses/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Integer id) {

        return repository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setLocation(newCourse.getLocation());
                    course.setHoles(newCourse.getHoles());
                    return repository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return repository.save(newCourse);
                });
    }

    @DeleteMapping("/courses/{id}")
    void deleteCourse(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}