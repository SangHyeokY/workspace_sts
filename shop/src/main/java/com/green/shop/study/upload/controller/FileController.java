package com.green.shop.study.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//파일 업로드, 다운로드 학습용 컨트롤러
@Controller
@RequestMapping("/file")
public class FileController {

    //파일 첨부 페이지로 이동
    @GetMapping("/uploadForm")
    public String uploadForm(){

        //test. 파일 확장자 추출
        String file1 = "abc.jpg";
        //'1'번째부터~'5'번째 사이
        System.out.println(file1.substring(1, 5));
        //.은 몇번째에 나오냐
        System.out.println(file1.indexOf("."));
        //가장 마지막에 찍은 .은 몇번째냐
        System.out.println(file1.lastIndexOf("."));
        //확장자만 불러오기
        System.out.println(file1.substring(file1.lastIndexOf(".")));

        return "test/upload/upload_form";
    }

    //파일 업로드 (img1, img2)
    @PostMapping("/upload")
    public String upload(@RequestParam(name="img1") MultipartFile img1
                       , @RequestParam(name="img2") MultipartFile img2){

        //원래 파일 이름을 얻어오겠다. 첨부한 파일명
        //첨부파일 이름이 중복될 가능성이 있어서 안겹치려고
        //  첨부파일 이름을 유니크하게 다시 바꾸는 경우도 있음
        String originFileName = img1.getOriginalFilename();
        System.out.println(originFileName);

        //업로드 경로 뒤에 \\붙여라
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        //업로드 경로 및 파일명을 하나의 문자열로 나열
        String fileName = uploadPath + originFileName;

        //형식이 java.io.File인 File
        //새 변수를 생성하고 파일 업로드
        try {
            File file = new File(fileName);
            img1.transferTo(file);
        } catch (IOException e) {
            System.out.println("!! 파일 첨부 중 오류 발생 !!");
            throw new RuntimeException(e);
        }

        //두번째 파일 업로드
        //원본 파일의 확장자만 추출 (바뀌면 안되니까)
        //"abc.jpg"    →
        //"abcd.jpg"
        //"abcde.jpg"

        //서버에 저장할 파일명 생성
        String secondOriginFileName = img2.getOriginalFilename();
        String extension = secondOriginFileName.substring(secondOriginFileName.lastIndexOf("."));

        //랜덤 유효 아이디
        String uuid = UUID.randomUUID().toString();

        //파일명 = 유효 아이디 + 확장자명
        String attachedFileName = uuid + extension;

        //경로 + 파일명
        //예외처리
        //printStackTrace() 오류 알려줌
        try {
            File file1 = new File(uploadPath + attachedFileName);
            img2.transferTo(file1);
        } catch (Exception e) {
            System.out.println("파일 첨부 중 오류 발생");
            e.printStackTrace();
        }
        //img1+img2 같이 첨부해야 들어가더라
        return "";
    }

    //다중 첨부 페이지로 이동
    @GetMapping("/multiForm")
    public String multiForm(){
        return "test/upload/multi_form";
    }

    //다중 첨부 실행
    @PostMapping("/multi")
    public String multi(@RequestParam(name="imgs") MultipartFile[] imgs){

        //첨부할 파일 경로
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        //다중이므로 for
        for(MultipartFile img : imgs) {
            System.out.println(img.getOriginalFilename());

            //확장자 추출
            String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));

            //첨부될 파일명 = 이름 + 확장자
            String attachedFileName = UUID.randomUUID().toString() + extension;

            //첨부
            try {
                File file = new File(uploadPath + attachedFileName);
                img.transferTo(file);
            } catch (Exception e) {
                System.out.println("다중 첨부 중 오류 발생");
                e.printStackTrace();
            }
        }
        return "";
    }

}