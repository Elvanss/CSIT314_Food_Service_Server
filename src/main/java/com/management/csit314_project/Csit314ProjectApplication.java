package com.management.csit314_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.management.csit314_project.Security",
        "com.management.csit314_project.Controller",
        "com.management.csit314_project.Service",
        "com.management.csit314_project.Repository",
        "com.management.csit314_project.Model",
        "com.management.csit314_project.System",
        "com.management.csit314_project.Security",
        "com.management.csit314_project.Mapper"
})
public class Csit314ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Csit314ProjectApplication.class, args);
    }

}
