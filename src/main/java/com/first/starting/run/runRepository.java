package com.first.starting.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class runRepository {
    private static final Logger log = LoggerFactory.getLogger(runRepository.class);
    private final JdbcClient jdbcClient;

    public runRepository(JdbcClient jdbcClient){
        this.jdbcClient=jdbcClient;
    }

    public List<run> findAll(){//    private List<run> runs = new ArrayList<>();

        return jdbcClient.sql("select * from run")
                .query(run.class)
                .list();
    }

    public Optional<run> findById(Integer id){
        return jdbcClient.sql("select * from run where id = :id")
                .param("id",id)
                .query(run.class)
                .optional();
    }

    public void create(run newRun){
        var updated= jdbcClient.sql("Insert into run(id,title,started_on,completed_on,miles,location) VALUES(?,?,?,?,?,?)")
                .params(List.of(newRun.id(),newRun.title(),newRun.startedOn(),newRun.completedOn(),newRun.miles(),newRun.location()))
                .update();
        Assert.state(updated == 1, "Failed to create run " + newRun.title());
    }

    public void update(run updatedRun,Integer id){
        var updated= jdbcClient.sql("update run set title=?,started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(updatedRun.title(),updatedRun.startedOn(),updatedRun.completedOn(),updatedRun.miles(),updatedRun.location().toString(), id))
                .update();
        Assert.state(updated==1, "Failed to update run " +updatedRun.title());
    }
    public void delete(Integer id){
        var updated= jdbcClient.sql("delete from run where id = :id")
                .param("id",id)
                .update();
        Assert.state(updated==1,"Failed to delete run "+id);
    }
    public int count(){
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }
    public void saveAll(List<run> runs){
        runs.stream().forEach(this::create);
    }
    public List<run> findByLocation(String location){
        return jdbcClient.sql("select * from run where location = :location")
                .param("location",location)
                .query(run.class)
                .list();
    }
}
