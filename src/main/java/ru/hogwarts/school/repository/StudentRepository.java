package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findById(int id);
    List<Student> findByAgeBetween(int min, int max);

    Student findStudentByNameIgnoreCase(String name);

    List<Student> findStudentByFacultyId(int id);

    @Query(value = "SELECT AVG(student.age) FROM Student", nativeQuery = true)
    Integer findAVGStudentAge();

    @Query(value = "SELECT student.name, student.age FROM Student ORDER BY student.age LIMIT 5", nativeQuery = true)
    List<Student> findYoungerStudents();
}
