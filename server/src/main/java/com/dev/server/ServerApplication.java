package com.dev.server;

import com.dev.server.dao.AppDAO;
import com.dev.server.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
//            deleteInstructorDetail(appDAO);
//            createInstructorWithCourse(appDAO);
//            createInstructorWithAnotherCourse(appDAO);
//            findInstructorWithCourse(appDAO);
//            findCoursesWithInstructor(appDAO);
//            findInstructorWithCoursesJoinFetch(appDAO);
//            updateInstructor(appDAO);
//            updateCourse(appDAO);
//            deleteInstructor(appDAO);
//            deleteCourse(appDAO);
//            createCourseAndReviews(appDAO);
//            retriveCourseAndReviews(appDAO);
//            deleteCourseAndReviews(appDAO);
//            createCourseAndStudents(appDAO);
//            findCourseAndStudents(appDAO);
//            findStudentAndCourses(appDAO);
//            addMoreCoursesForStudent(appDAO);
//            deleteCourse(appDAO);
            deleteStudent(appDAO);
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

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("Instructor detail: " + tempInstructorDetail);
        System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());
        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void createInstructorWithAnotherCourse(AppDAO appDAO) {
        // create a new Instructor
        Instructor tempInstructor = new Instructor("Harsh", "Choudhary", "harshvirat894@gmail.com");

        // add the instructor details
        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.harsh.com/youtube", "Code");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 = new Course("Rust for all!");
        Course tempCourse2 = new Course("Solana for only braves!");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        System.out.println("Saving instructor: " + tempInstructor);

        System.out.println("The courses: " + tempInstructor.getCourses());

        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void createInstructorWithCourse(AppDAO appDAO) {
        // create a new Instructor
        Instructor tempInstructor = new Instructor("Palak", "Kumari", "palakkumari@gmail.com");

        // add the instructor details
        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.palak.com/youtube", "Bakwas");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 = new Course("Bakwas for all!");
        Course tempCourse2 = new Course("Bakwas for only braves!");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        System.out.println("Saving instructor: " + tempInstructor);

        System.out.println("The courses: " + tempInstructor.getCourses());

        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void findInstructorWithCourse(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    public void findCoursesWithInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        // find instructor
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        // associate the objects
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        // find instructor
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;

        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        // update the instructor
        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setEmail("nunjathegreat@gmail.com");

        appDAO.update(tempInstructor);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 12;

        // find the course
        System.out.println("Finding the course: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);

        // update the course
        tempCourse.setTitle("Jyada bakwas karna!");

        appDAO.update(tempCourse);

        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Done!");
    }


    private void createCourseAndReviews(AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("How to master Solana!");

        // add some reviews
        tempCourse.addReview(new Review("Best course ever seen!"));
        tempCourse.addReview(new Review("Cool course!"));
        tempCourse.addReview(new Review("Great course!"));

        // save the course
        appDAO.save(tempCourse);
        System.out.println("Done!");
    }

    private void retriveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);

        // print the reviews
        System.out.println(tempCourse.getReviews());
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }


    private void createCourseAndStudents(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Spring for all!");

        // create the students
        Student tempStudent1 = new Student("Harsh", "Bhunja", "harsh@gmail.com");
        Student tempStudent2 = new Student("Palak", "Nunja", "nunu@gmail.com");
        Student tempStudent3 = new Student("Rabindra", "Punja", "bapu@gmail.com");
        Student tempStudent4 = new Student("Chhoti", "Mazza", "mazza@gmail.com");

        // add students to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);
        tempCourse.addStudent(tempStudent3);
        tempCourse.addStudent(tempStudent4);

        // save the course and associated student
        System.out.println("Saving course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());


        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {

        int theId = 10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: " + tempCourse);
        System.out.println("Students enrolled in this course: " + tempCourse.getStudents());
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        System.out.println("Loaded student: " + tempStudent);
        System.out.println("associated courses: " + tempStudent.getCourses());
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int theId = 2;

        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rust for all!");
        Course tempCourse2 = new Course("Fun Solana!");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        // updating the student
        appDAO.update(tempStudent);
        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }

    private void deleteStudent(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);
        System.out.println("Done!");
    }

}
