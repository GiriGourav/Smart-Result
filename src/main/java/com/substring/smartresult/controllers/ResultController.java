package com.substring.smartresult.controllers;

import com.substring.smartresult.entities.Mark;
import com.substring.smartresult.entities.Student;
import com.substring.smartresult.payload.StudentForm;
import com.substring.smartresult.service.ResultService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Controller
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;


    @RequestMapping("/input")
    public String viewResult()
    {
        System.out.println("Result page");
        return "view_Result";
    }


    @RequestMapping("/view")
    public String getResult()
    {
        return "result_data";
    }


//Add result ka form show kar rha hai
    @RequestMapping("/add")
    public String addResult(Model model)
    {
        StudentForm studentForm =new StudentForm();
        model.addAttribute("studentForm",studentForm);
        return "add_result";
    }

    @RequestMapping(value = "/process-form",method = RequestMethod.POST )
    public String saveResult(@ModelAttribute StudentForm studentForm,Model model )
    {
//           Logic to save Result
        System.out.println(studentForm.getName());
        resultService.save(studentForm);
        return "result_Success";
    }


//    View result controller method
//    @RequestMapping(value = "/view-result",method = RequestMethod.POST)
    @PostMapping("/view-result")
    public String viewResultByRollNumber(@RequestParam ("rollNumber") String rollNumber, @RequestParam("dob")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob, Model model)
    {
        System.out.println("Roll number : "+ rollNumber);
        System.out.println("Password : "+ dob);

        Student student=resultService.getResultByRollNumberANDDob(rollNumber,dob);

        if(student==null)
        {
            return "result_not_found";
        }
        else {

            double totalMarks=0;
            double totalMaxMarks=0;

            for (Mark mark:student.getMarkList()){
                totalMarks +=mark.getMarks();
                totalMaxMarks +=mark.getMaxMarks();

            }

            double percentage= (totalMarks/totalMaxMarks)*100;

            DecimalFormat df=new DecimalFormat("#0.00");
            model.addAttribute("percentage",df.format(percentage));
            model.addAttribute("totalMarks",totalMarks);
            model.addAttribute("totalMaxMarks",totalMaxMarks);

            String totalGrade="F";

            if (percentage > 90) {
                totalGrade = "A+";
            } else if (percentage > 80 && percentage <= 90) {
                totalGrade = "A";
            } else if (percentage > 70 && percentage <= 80) {
                totalGrade = "B+";
            } else if (percentage > 60 && percentage <= 70) {
                totalGrade = "B";
            } else {
                totalGrade = "F";
            }

            model.addAttribute("totalGrade", totalGrade);



            model.addAttribute("student",student);
            model.addAttribute("marks",student.getMarkList());
            return "result_data";
        }
    }


    @GetMapping("/download/{markId}")
    public ResponseEntity<Resource> downloadAnswerSheet(@PathVariable Long markId) {

        Mark mark = resultService.getMarkById(markId);

        if (mark == null || mark.getAnswerSheetPath() == null) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource file = new FileSystemResource(mark.getAnswerSheetPath());

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(file);
    }

}
