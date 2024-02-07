package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuServiceimpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Resource(name= "stuService")
    private StuServiceimpl stuService;


    //학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회
        List<StuVO> list = stuService.selectStuList();

        //조회한 목록을 html 로 전달
        model.addAttribute("stuList", list);

        return "stu_list";
    }

    //1개 빼고 다 get
    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }

    //get, post 다르므로 같아도 가능
    @PostMapping("/regStu")
    public String reg(StuVO stuVO){
        //학생 등록
        stuService.insertStu(stuVO);
        return "redirect:/";
    }

    //내가 만들어 넣은거
    @GetMapping("/stuDetail")
    private String stuDetail(@RequestParam(name="stuNo") int stuNo, Model model){
        //학생의 상세정보 조회
        StuVO stu = stuService.selectStu(stuNo);
        //조회한 데이터 html에 전달
        model.addAttribute("stuInfo", stu);
        return "student_detail";
    }

    //이미 입력받은 학생 이름
    @GetMapping("/goDelete")
    private String delete(StuVO stuVO){
    //학생 삭제
        stuService.deleteStu(stuVO.getStuNo());
    //다시 학생 목록 데이터 조회
        return "redirect:/";
    }
}
