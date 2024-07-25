package com.first.starting.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class runNotFoundException extends RuntimeException{
    public runNotFoundException(){
        super("Run not Found");
    }

}
