function goSelect(){
    const classCode = document.querySelector('#class-selector').value;
    // alert(selectBox.value);

    // 데이터를 가져가야 됨
    location.href=`/?classCode=${classCode}`;
}

function fetchSelect(){
    const classCode = document.querySelector('#class-selector').value;
    fetch('/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           classCode : classCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        //기존 테이블 내용 삭제
        //tbody 태그를 선택. > : 자식태그
        const tbodyTag = document.querySelector('#stu-list-table > tbody');

        //tbody 태그 안의 모든 내용을 삭제!
        //빈값으로 만들기 : ''
        tbodyTag.innerHTML = '';

        //새로 조회한 데이터로 tbody 안의 내용을 다시 채워 줌.
        //foreach문 써야됨
        let str = ``;
        // for(const stu of data){
        // java식으론 = for(StuVO stu : data){}
        //     str += `<tr>
        //                 <td></td>
        //                 <td>${stu.className}</td>
        //                 <td>${stu.stuNum}</td>
        //                 <td>${stu.stuName}</td>
        //             </tr>`;
        // }

        data.forEach(function(stu, i){
            str += `<tr>
                        <td>${data.length-i}</td>
                        <td>${stu.className}</td>
                        <td>${stu.stuNum}</td>
                        <td>
                        <span onclick="goDetail(${stu.stuNum})" id="name-span">
                        ${stu.stuName}
                        </span>
                        </td>
                    </tr>`;
        });

        //for문
        // for(let i = 0; i < data.length; i++){
        //         str += `<tr>
        //                     <td>${data.length-i}</td>
        //                     <td>${data[i].className}</td>
        //                     <td>${data[i].stuNum}</td>
        //                     <td>${data[i].stuName}</td>
        //                 </tr>`;
        //     }

        tbodyTag.insertAdjacentHTML('afterbegin' , str);
        console.log(data);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function goDetail(stuNum){
    fetch('/detail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'stuNum' : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다!!!');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);

        //조회된 데이터로 그림 그리기
        const detail_div = document.querySelector('.stu-detail-div');
        detail_div.innerHTML = '';
        let str =`
        <div>
                            <div><h3>${data.stuVO.stuName}&nbsp학생의 프로필</h3></div>
                            <div>
                                <table>
                                    <thead>
                                        <tr>
                                            <td nowrap>학번</td>
                                            <td nowrap>소속반</td>
                                            <td nowrap>학생명</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="stuNumTD">${data.stuVO.stuNum}</td>
                                            <td>${data.stuVO.className}</td>
                                            <td>${data.stuVO.stuName}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div>
                            <div><h3>${data.stuVO.stuName}&nbsp학생의 점수</h3></div>
                            <div>
                                <table>
                                    <thead>
                                        <tr>
                                            <td nowrap>국어점수</td>
                                            <td nowrap>영어점수</td>
                                            <td nowrap>수학점수</td>
                                            <td nowrap>평균</td> 
                                        </tr>
                                    </thead>
                                    <tbody id="um">
                                        <tr>
                                            <td class="insertScore">
                                                ${data.scoreVO == null? 0 : data.scoreVO.korScore}
                                            </td>
                                            <td class="insertScore">
                                                ${data.scoreVO == null? 0 : data.scoreVO.engScore}
                                            </td>
                                            <td class="insertScore">
                                                ${data.scoreVO == null? 0 : data.scoreVO.mathScore}
                                            </td>
                                            <td class="averageScore">
                                                ${data.scoreVO == null? 0.0 : 
                                                (data.scoreVO.korScore+data.scoreVO.engScore+data.scoreVO.mathScore)/3.0}
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <div>
                                <input id="btn" type="button" value="점수입력" onclick="inputScore()">
                            </div>
                        </div>`;
    detail_div.insertAdjacentHTML('afterbegin', str);
    console.log(data);
    //${stu.stuNum}           
    //${stu.className}
    //${stu.stuName}

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다!!!!!!\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function inputScore(){
    const btn = document.querySelector('#btn');
// 점수 입력 -> 저장
    if(btn.value == '점수입력'){
    const scores = document.querySelectorAll('.insertScore');

   scores.forEach(function(e, i){
        e.innerHTML = '<input type="text" class="scoreInput">'
   });
   document.querySelector('#btn').value = '저장';
    }
// 저장 누르면 저장된 표를 출력
    else if(btn.value == '저장'){
        const result = confirm('입력한 정보로 점수를 등록할까요?');
        if(result){
            insertStuScore();
        }
    }
}

function insertStuScore(){
    const inputs = document.querySelectorAll('.scoreInput');
    const stuNum = document.querySelector('.stuNumTD').textContent;

    fetch('/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'korScore' : inputs[0].value,
           'engScore' : inputs[1].value,
           'mathScore': inputs[2].value,
           'stuNum' : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert("점수가 저장되었습니다.");
        goDetail(stuNum);


    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}