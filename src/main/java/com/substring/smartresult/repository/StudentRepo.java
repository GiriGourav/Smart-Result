package com.substring.smartresult.repository;

import com.substring.smartresult.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface StudentRepo extends JpaRepository<Student,String> {

    @Query("""
SELECT s FROM Student s
LEFT JOIN FETCH s.markList
WHERE s.rollNumber = :rollNumber AND s.dob = :dob
""")
    Student findByRollNumberAndDob(
            @Param("rollNumber") String rollNumber,
            @Param("dob") LocalDate dob
    );
}
