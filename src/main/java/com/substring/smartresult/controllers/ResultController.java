package com.substring.smartresult.controllers;

import com.substring.smartresult.payload.StudentForm;
import com.substring.smartresult.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/add")
    public String addResult(Model model)
    {
        StudentForm studentForm =new StudentForm();
        model.addAttribute("studentForm",studentForm);
        return "add_result";
    }

    @RequestMapping(value = "/process-form",method = RequestMethod.POST )
    public String saveResult(@ModelAttribute StudentForm studentForm)
    {

        System.out.println(studentForm.getName());
        resultService.save(studentForm);
        return "result_Success";
    }

}
