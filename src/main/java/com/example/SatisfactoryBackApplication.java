package com.example;

import com.example.task.TaskController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class SatisfactoryBackApplication {

    public static void main(String[] args) {
        new File(TaskController.uploadDirectory).mkdir();
        SpringApplication.run(SatisfactoryBackApplication.class, args);
    }

}
