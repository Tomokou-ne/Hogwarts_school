package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private long counter = 0;

    Map<Long, Student> studentMap= new HashMap<>();


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
        if (studentMap.containsKey(id)) {
            return studentMap.get(id);
        }
        return null;
    }

    //student expulsion
    public Student removeStudent(long id) {
        if (this.studentMap.containsKey(id)) {
            return this.studentMap.remove(id);
        }
        return null;
    }

    //update student-information
    public Student updateStudent(long id, Student student) {
        if (studentMap.containsKey(id)) {
            return studentMap.put(id, student);
        }
        return null;
    }

    //get students by age
    public Map<Long, Student> getStudentsByAge(int age) {
        Map<Long, Student> total = new HashMap<Long, Student>();
        long counter = 0;
        for (Map.Entry<Long, Student> entry : studentMap.entrySet()) {
            if(entry.getValue().getAge() == age) {
                total.put(this.counter++, entry.getValue());
            }
        }
        return total;
    }
}
