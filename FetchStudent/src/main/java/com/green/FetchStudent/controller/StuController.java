package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StuController {

    @Resource(name="stuService")
    private StuServiceImpl stuService;

    //선택시 출력
    @GetMapping("/")
    //받아야 하는 데이터 : classCode
    //보내는 데이터 : model,    stuVO는 알아서 보내짐
    public String main(Model model, StuVO stuVO){

      //학급 목록 데이터 조회
      //html로 가져가려면 model 필요
      List<ClassVO> classList = stuService.selectClassList();
      model.addAttribute("classList", classList);

      //학생 목록 데이터 조회
      List<StuVO> stuList = stuService.selectStuList(stuVO);
      model.addAttribute("stuList", stuList);

      return "stu_manager";
    }

    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        //학생 목록 데이터 조회
        List<StuVO> stuList = stuService.selectStuList(stuVO);
        return stuList;
    }

    //학생 이름 클릭시 (내가 한거)
//    @ResponseBody
//    @PostMapping("/detail")
//    public List<StuVO> showStuAllInfo(StuVO stuVO, ScoreVO scoreVO){
        //해당 학생의 데이터 조회
//            List<StuVO> showList = stuService.selectStuInfo(stuVO);
        //해당 학생의 점수 조회
//            List<ScoreVO> showList2 = stuService.selectStuScore(scoreVO);
        //조회한 리스트 2개를 한번에 return 어케함
        //비동기로 2개 한번에 뽑는 방법?
//        return null;
//    }

    @ResponseBody
    @PostMapping("/detail")
    public DetailVO detail(@RequestParam(name="stuNum") int stuNum){
        //클릭한 학생의 상세 정보 조회
        StuVO stuInfo = stuService.selectStuDetail(stuNum);
        //클릭한 학생의 점수 정보 조회
        ScoreVO scoreInfo = stuService.selectScoreInfo(stuNum);

        //조회한 데이터를 가지고 자바스크립트로 이동
        //2개를 담을 통을 만든다.
        DetailVO result = new DetailVO();
        result.setStuVO(stuInfo);
        result.setScoreVO(scoreInfo);
        return result;
    }

    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);
    }

}
