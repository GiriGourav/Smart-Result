package com.substring.smartresult.service;

import com.substring.smartresult.entities.Mark;
import com.substring.smartresult.entities.Student;
import com.substring.smartresult.payload.StudentForm;
import com.substring.smartresult.repository.MarkRepo;
import com.substring.smartresult.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ResultService {

    @Autowired
    private StudentRepo studentRepo;

    public String save(StudentForm studentForm)
    {
        Student student=new Student();

        student.setId(UUID.randomUUID().toString());
        student.setName(studentForm.getName());
        student.setRollNumber(studentForm.getRollNumber());
        student.setEmail(studentForm.getEmail());
        student.setGender(studentForm.getGender());
        student.setSchoolName(studentForm.getSchoolName());
        student.setDob(studentForm.getDob());


        List<Mark> marksList= studentForm.getMarks().stream().map(markForm->{
             Mark mark=new Mark();
             mark.setSubject(markForm.getSubject());
             mark.setMarks(markForm.getMarks());
             mark.setMaxMarks(markForm.getMaxMarks());
             mark.setRemark(markForm.getRemark());
//             mark.setGrade(markForm.getGrade());

            // Handle file upload
            if (!markForm.getAnswerSheet().isEmpty()) {
                String fileName = student.getRollNumber() + "_" + markForm.getSubject() + ".pdf";
                Path uploadPath = Paths.get("uploads/answerSheets");
                if (!Files.exists(uploadPath)) {
                    try {
                        Files.createDirectories(uploadPath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    markForm.getAnswerSheet().transferTo(uploadPath.resolve(fileName));
                    mark.setAnswerSheetPath(uploadPath.resolve(fileName).toString());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save answer sheet", e);
                }
            }
             mark.setStudent(student);

             return mark;
         }).toList();

         student.setMarkList(marksList);

         Student savedStudent =studentRepo.save(student);
        System.out.println("Student saved with id: "+savedStudent.getId());

        return savedStudent.getId();
    }

    public Student getResultByRollNumberANDDob(String rollNumber, LocalDate dob)
    {
        return studentRepo.findByRollNumberAndDob(rollNumber,dob);
    }

    @Autowired
    private MarkRepo markRepo;
    public Mark getMarkById(Long markId) {
        return markRepo.findById(markId).orElse(null);
    }
}
