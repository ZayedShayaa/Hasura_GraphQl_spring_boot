package com.sofa.graphql.controller;

import com.sofa.graphql.service.HasuraService;
import com.sofa.graphql.service.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final HasuraService studentService;

    public StudentController(HasuraService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student insertStudent(@RequestBody StudentInput input) {
        return studentService.insertNewStudent(input.name(), input.email(), input.age());
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentInput input) {
        return studentService.updateStudent(id, input.name(), input.email(), input.age());
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    public record StudentInput(String name, String email, Integer age) {}
}




//package com.sofa.graphql.controller;
//
//import com.sofa.graphql.service.HasuraService;
//import com.sofa.graphql.service.model.Student;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/students")
//public class StudentController {
//
//    private final HasuraService studentService;
//
//    public StudentController(HasuraService studentService) {
//        this.studentService = studentService;
//    }
//
//    @GetMapping
//    public List<Student> getAllStudents() throws IOException {
//        return studentService.getAllStudents();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getStudentById(@PathVariable Long id) throws IOException {
//        Student student = studentService.getStudentById(id);
//
//        if (student == null) {
//            return ResponseEntity.status(404).body("Student not found");
//        }
//
//        return ResponseEntity.ok(student);
//    }
//
//    @PostMapping
//    public Student insertStudent(@RequestBody StudentInput input) throws IOException {
//        return studentService.insertNewStudent(input.name(), input.email(), input.age());
//    }
//
//    @PutMapping("/{id}")
//    public Student updateStudent(
//            @PathVariable Long  id,
//            @RequestBody StudentInput input
//    ) throws IOException {
//        return studentService.updateStudent(id, input.name(), input.email(), input.age());
//    }
//
//    @DeleteMapping("/{id}")
//    public Student deleteStudent(@PathVariable Long  id) throws IOException {
//        return studentService.deleteStudent(id);
//    }
//
//    public record StudentInput(String name, String email, Integer age) {}
//}
