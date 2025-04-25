package com.dev.server;

import com.dev.server.dao.AppDAO;
import com.dev.server.entity.Instructor;
import com.dev.server.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createInstructor(appDAO);
//            createAnotherInstructor(appDAO);
//            findInstructor(appDAO);
//            deleteInstructor(appDAO);
//            findInstructorDetail(appDAO);
            deleteInstructorDetail(appDAO);
        };
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Harsh", "dev", "harshvirat894@gmail.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.harsh.com", "Code");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("Saving Instructor: " + tempInstructor);

        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }

    private void createAnotherInstructor(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Palak", "Nunja", "nunu@gmail.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.nunja.com", "Bakwas Karna");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("Saving instructor: " + tempInstructor);

        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Finding the id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Associated Instructor Detail: " + tempInstructor.getInstructorDetail());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting the instructor: " + appDAO.findInstructorById(theId));

        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("Instructor detail: " + tempInstructorDetail);
        System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());
        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

}
