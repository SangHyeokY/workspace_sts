setFinalPrice();
//총 가격을 계산하는 함수
function setFinalPrice(){
    //체크된 장바구니 상품의 총 가격을 모두 더해서 계산
    //클래스가 chk인 태그 중에서 체크가 된 태그만 선택
    const chks = document.querySelectorAll('.chk:checked');
    let finalPrice = 0;

    chks.forEach(function(chk, i){
    //chk 각각의 같은 행에 있는 총 가격 데이터 찾아가기
        const strPrice = chk.closest('tr').children[5].textContent; //5번째로 가장 가까운 tr
        
        //정규식을 사용해서 쉼표와 원화표시를 제거한 후 총합 계산
        const regex = /[^0-9]/g;
        const price = parseInt(strPrice.replace(regex,'')); //regex: ￦20000 -> 20000 
        finalPrice += price;
    });

    document.querySelector('#finalPrice-span').textContent = finalPrice.toLocaleString();
}

function checkAll(){
    const chkAll = document.querySelector('#chkAll');
    const chks = document.querySelectorAll('.chk');

    if(chkAll.checked){
        for(const chk of chks){
            chk.checked = true;
        }
    }
    else{
        for(const chk of chks){
            chk.checked = false;
        }
    }
    setFinalPrice();
}

//삭제버튼 클릭 시 장바구니에서 삭제
function goDelete(cartCode){
    if(confirm('선택한 상품을 장바구니에서 삭제하시겠습니까?')){
        location.href=`/cart/deleteCart?cartCode=${cartCode}`;
    }
}

//'비동기'로 장바구니 수량 변경 구현
function cntChange(cartCode, selectedTag, itemPrice){
    const cartCnt = selectedTag.closest('.row').querySelector('input[type="number"]').value;

    fetch('/cart/cntChange', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'cartCnt' : cartCnt,
           'cartCode' : cartCode
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

        //totalPrice -> cartCnt * itemPrice
        const totalPrice = cartCnt * itemPrice;
        selectedTag.closest('tr').querySelector('.totalPrice-div').textContent = '₩'+totalPrice.toLocaleString();
        setFinalPrice();
        alert("수량이 변경되었습니다.");

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}



//선택 삭제
function deleteCarts(){
    //만약 체크된 상품이 하나도 없다면
    //alert로 '삭제할 상품을 선택하세요' 출력
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){
       alert("삭제할 상품을 선택하세요."); 
       return;
    }

    //컨트롤러로 넘겨 줄 cartCodes들
    //체크된 체크박스들에서 cartCode값
    const cartCodes = [];
    for(const chk of chks){
        cartCodes.push(chk.value);
    }

    location.href=`/cart/deleteCarts?cartCodeList=${cartCodes}`;
}



//선택 구매 함수
function buys(){
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length==0){
        alert("구매할 상품을 선택하세요.");
        return;
    }

    //컨트롤러로 자료 보내기
    const cartCodeList = [];
    for(const chk of chks){
        cartCodeList.push(chk.value);
    }
    location.href=`/buy/buyCarts?cartCodeList=${cartCodeList}`;
}