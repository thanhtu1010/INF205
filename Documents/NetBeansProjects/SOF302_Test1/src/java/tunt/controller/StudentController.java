/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunt.controller;

import entity.Students;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tunt.model.StudentDAO;

/**
 *
 * @author Iris Nguyen
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("showall")
    public String showAll(ModelMap model){
        StudentDAO st = new StudentDAO();
        List<Students> list = new ArrayList<Students>();
        list = st.layDanhSachSV("");
        model.addAttribute("listStudent", list);
        
        Students sv = new Students();
        sv = list.get(0);
        model.addAttribute("student", sv);
        return "student";
    }
    
    @RequestMapping("edit")
    public String edit(HttpServletRequest request, ModelMap model){
        int masv = Integer.parseInt(request.getParameter("txtMasv"));
        String name = request.getParameter("txtName");
        double mark = Double.parseDouble(request.getParameter("txtMark"));
        String major = request.getParameter("txtMajor");
        Students sv = new Students(masv, name, mark, major);
        model.addAttribute("student", sv);
        
        List<Students> list = new ArrayList<Students>();
        list = StudentDAO.layDanhSachSV("");
        model.addAttribute("listStudent", list);
        return "student";
    }       
    
    @RequestMapping("delete")
    public String delete(HttpServletRequest request, ModelMap model, 
            @ModelAttribute("student") Students student){
        int masv = Integer.parseInt(request.getParameter("txtMasv"));
        StudentDAO.deleteSinhVien(masv);
        
        List<Students> list = new ArrayList<Students>();
        list = StudentDAO.layDanhSachSV("");
        model.addAttribute("listStudent", list);
        return "student";
    }
    @RequestMapping(params="btnUpdate")
    public String update(@ModelAttribute("student") Students student, ModelMap model) {       
        StudentDAO.updateThongTinSinhVien(student);
        
        List<Students> list = new ArrayList<Students>();
        list = StudentDAO.layDanhSachSV("");
        model.addAttribute("listStudent", list);
        return "student";
    }   
    @RequestMapping(params="btnInsert")
    public String insert(@ModelAttribute("student") Students student, ModelMap model){
        StudentDAO.themSinhVien(student);
        
        List<Students> list = new ArrayList<Students>();
        list = StudentDAO.layDanhSachSV("");
        model.addAttribute("listStudent", list);
        return "student";
    }
}
