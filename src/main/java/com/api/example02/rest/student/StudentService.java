package com.api.example02.rest.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// @Component or @Service - para la Inyeccion de dependencia
@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}
    
    public List <Student> getStudents () {
		/*
			return List.of( new Student(1L, "Mario", "Alberto", "mario.alberto2000@gmail.com"),
						  );
	    */				
		return studentRepository.findAll();
	}

	public void registerNewStudent (Student student) {
		System.out.println(student);
	}

	public void addNewStudent (Student student) {
	 	Optional <Student> studenOptional = studentRepository.findStudentByEmail(student.getEmail());

		 if (studenOptional.isPresent() ) { // Si existe el objeto -> el email ya esta en uso.
			 throw new IllegalStateException("Email taken");
		 }

		 studentRepository.save(student);
	}

	public void deleteStudent(Long id){
		boolean exists = studentRepository.existsById(id);

		if (!exists) {
			throw new IllegalStateException ("The student id (" + id +")does not exist");
		}

		studentRepository.deleteById(id);
	}

	// Actualizar datos - usar la anotacion @Transactional
	@Transactional
	public void updateStudent (Long id, String email, String name) {
		
		// Comprobar si existe el estudiante
		Student student = studentRepository.findById(id).orElseThrow
		( 
			() -> new IllegalStateException("The student (" + id + ") does not exist to update")
		);

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
						
			// En caso de encontrar un email ya existente - Error
			Optional <Student> optional = studentRepository.findStudentByEmail(email);

			if (optional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}			
			student.setEmail(email);
		}




	}
}
