package com.dev.server.rest;


import com.dev.server.dao.StudentDAO;
import com.dev.server.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentDAO studentDAO;

    @Autowired
    public StudentRestController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentDAO.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentsByID(@PathVariable int studentId) {
        Student existingStudent = studentDAO.findById(studentId);

        if (existingStudent == null) {
            throw new RuntimeException("Student with ID " + studentId + " not found");
        }

        return existingStudent;
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student theStudent) {
        studentDAO.save(theStudent);
        return "Student Added Successfully";
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent) {
        // Fetch the existing student from the database
        Student existingStudent = studentDAO.findById(theStudent.getId());

        if (existingStudent == null) {
            throw new RuntimeException("Student with ID " + theStudent.getId() + " not found");
        }

        // Update only the non-null fields
        if (theStudent.getFirstName() != null) {
            existingStudent.setFirstName(theStudent.getFirstName());
        }
        if (theStudent.getLastName() != null) {
            existingStudent.setLastName(theStudent.getLastName());
        }
        if (theStudent.getEmail() != null) {
            existingStudent.setEmail(theStudent.getEmail());
        }

        // Save the updated student
        studentDAO.update(existingStudent);
        return existingStudent;
    }
}
