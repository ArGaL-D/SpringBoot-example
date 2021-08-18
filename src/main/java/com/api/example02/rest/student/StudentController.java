package com.api.example02.rest.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    private final StudentService studentService;

    /* Constructor */
    @Autowired
    public StudentController (StudentService studentService){
        this.studentService = studentService;
    }

    /* GET, POST, PUT, DELETE */

    @GetMapping
	public List <Student> getStudents () {
        return studentService.getStudents();
	}

    /* 
    Basic form
    
    @PostMapping
    public void registerNewStudent (@RequestBody Student student) {
        studentService.registerNewStudent(student);
    }    
    */

    @PostMapping
    public void addNewStudent (@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent (@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    // Update

    @PutMapping("/{studentId}")
    public void updateStudent (
                               @PathVariable(name = "studentId") Long id, 
                               @RequestParam(required = false) String email, 
                               @RequestParam(required = false) String name 
                              ) {
        
        studentService.updateStudent(id, email, name);
    }

}
