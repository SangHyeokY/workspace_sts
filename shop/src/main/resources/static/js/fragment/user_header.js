  //[함수]///////////////////////////////////////////////////////////////////////////////
  
  // search 버튼 클릭 시 주소록 검색 팝업 창 띄우기
  function searchAddress() {
    new daum.Postcode({
      oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
          // 예제를 참고하여 다양한 활용법을 확인해 보세요.
          document.querySelector('#postCode').value = data.zonecode;
          document.querySelector('#address').value = data.roadAddress;
      }
  }).open();
  }
//join 버튼 클릭시 form 태그를 submit
  function join(){
    //1. submit 전에 유효성 검사
      //(1) ID를 입력했는지 확인
      const idInput = document.querySelector('#memberId');
    
      if(idInput.value == ''){
        alert('아이디를 입력해주세요.')
        return ;
      }
      //(2) ID 입력 문자의 길이가 20을 초과하는지 검사
      if(idInput.value.length > 20){
        alert('입력하신 아이디가 20자를 초과하였습니다.');
        return ;
      }
      //(3) 비밀번호가 일치하지 않는지 검사
      const pwInputs = document.querySelectorAll('input[type="password"]')
      if(pwInputs[0].value != pwInputs[1].value){
        alert('비밀번호가 서로 일치하지 않습니다. \n비밀번호를 다시 입력해주세요.');
        return ;
      }
      //문자 + 숫자 +특수 문자가 포함된 4~12 자리의 글자
      const regExp = /^.*(?=^.{4,12}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
      //test 함수의 매개변수로 들어온 문자가 정규식 조건에 부합하면 return true
      const expResult = regExp.test(pwInputs[0].value);
//        if(!expResult){
//          alert('비밀번호 형식에 일치하지 않습니다.');
//          return ;
//        }
    //2. sumbmit 시킬 form 태그를 선택
    document.querySelector('#join-form').submit();
  }

//클릭 시 로그인 페이지로 이동
function goLoginForm(){
  location.href='/member/loginForm';
}

//클릭 시 로그아웃
function goLogoutForm(){
  const result = confirm('로그아웃 하시겠습니까?');
  if(result){
    location.href='/member/logoutForm';
  }
}


//[이벤트]///////////////////////////////////////////////////////////////////////////////



// 회원가입 모달창이 닫힐 때 실행되는 이벤트
// 1. 모달창을 선택한다.
const joinModal = document.querySelector('#join-modal');
// 2. 선택한 태그에 이벤트 달아주기

// hedden.bs.modal : 모달창이 닫힐 때 실행
joinModal.addEventListener('hidden.bs.modal', function(event){
    document.querySelector('#join-form').reset();
});