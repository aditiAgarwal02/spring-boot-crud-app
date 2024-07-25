package com.first.starting.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class inMemoryRunRepository {


    private List<run> runs = new ArrayList<>();

    List<run> findAll(){
        return runs;
    }

    Optional<run> findById(Integer id){
        return  runs.stream()
                .filter(run->run.id().equals(id))
                .findFirst();
    }

    void create(run newRun){
        runs.add(newRun);
    }

    void update(run newRun,Integer id){
        Optional<run> existingRun=findById(id);
        if(existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()),newRun);
        }
    }

    void delete(Integer id){
        runs.removeIf(run->run.id().equals(id));
    }

    @PostConstruct
    private void init(){
        runs.add(new run(1,"Morning run", LocalDateTime.now(),LocalDateTime.now().plus(30, ChronoUnit.MINUTES),5,"INDOOR"));
        runs.add(new run(2,"Aternoon Run",LocalDateTime.now(),LocalDateTime.now().plus(45,ChronoUnit.MINUTES),5,"INDOOR"));
    }
}
