package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private long counter = 0;

    private final Map<Long, Student> studentMap= new HashMap<>();


    //get all students
    public Collection<Student> getAllStudents() {
        return studentMap.values();
    }

    //add new student to hashmap
    public Student addStudent (Student student) {
        studentMap.put(this.counter++, student);
        return student;
    }

    //read object from hashmap
    public Student getStudentById(long id) {
        return studentMap.get(id);
    }

    //student expulsion
    public Student removeStudent(long id) {
        return studentMap.remove(id);
    }

    //update student-information
    public Student updateStudent(long id, Student student) {
        if (studentMap.containsKey(id)) {
            return studentMap.put(id, student);
        }
        return null;
    }

    //get students by age
    public List<Student> getStudentsByAge(int age) {
        return studentMap.values()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }
}
