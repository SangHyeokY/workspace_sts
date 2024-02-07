//수량이 변경될때마다 총 가격을 변경
function setTotalPrice(inputTag, itemPrice){
//단가*수량=합계
    const cnt = inputTag.value;
    //만약에 0개면 강제로 1개로 바꿈
    // if(cnt==''){
    //     inputTag.value=1;
    //     cnt=1;
    // }

    const totalPrice = parseInt(itemPrice)*parseInt(cnt);

    document.querySelector('.totalPrice-span').textContent = totalPrice.toLocaleString();
}

//장바구니 버튼 클릭시 실행
function goBag(loginInfo, itemCode){
    fetch('/cart/insertCart', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'itemCode' : itemCode ,
           'cartCnt' : document.querySelector('input[type="number"]').value 
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('오류가 발생했습니다. \n로그인 중인지 다시 확인해주세요. \n로그아웃 중인 경우 장바구니에 저장되지 않습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        const result = confirm('장바구니에 상품을 담았습니다.\n장바구니 페이지로 이동하겠습니까?');
        if(result==true){
            location.href='/cart/list?page=cartList';
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}


function goBuy() {
    //총 가격 정보를 input value로 세팅
    const totalPriceStr = document.querySelector('.totalPrice-span').textContent;

    //숫자가 아닌 것들을 빈문자로 바꾸기 (순수 숫자로만 뽑아내기)
    const regex = /[^0-9]/g;
    const totalPrice = totalPriceStr.replace(regex, '');

    document.querySelector('input[name="totalPrice"]').value = totalPrice;

    //sumit
    document.querySelector('#insert-buy-form').submit();


    alert("반응");
    // rh
    //location.href=`/buy/buyDirect?cartCodeList=${cartCodeList}`;
  //  location.href=`/buy/buyDirect`;

}
