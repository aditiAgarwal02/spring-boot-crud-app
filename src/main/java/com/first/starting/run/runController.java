package com.first.starting.run;

import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/runs")
public class runController {

    private final runRepository runRepo;

    public runController(runRepository runRepo){
        this.runRepo=runRepo;
    }

    @GetMapping("")
    List<run> findAll(){
        return runRepo.findAll();
    }

    @GetMapping("/{id}")
    run findById(@PathVariable Integer id){
        Optional<run> runId= runRepo.findById(id);
        if(runId.isEmpty()){
            throw new runNotFoundException();
        }
        return runId.get();
    }

    //post to create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody run newRun){
        runRepo.create(newRun);
    }


    //put for update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody run updatedRun , @PathVariable Integer id){
        runRepo.update(updatedRun,id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepo.delete(id);
    }


    @GetMapping("/home")
    String home(){
        return "Hello , Runners";
    }
}
