//데이터 받기1
function fetch1() {
    fetch('/fetch/fetch1', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            'id': 'java',
            'name': '홍',
            'age': 20
        })
    })
        .then((response) => {
            return response.text(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


//데이터 받기2
function fetch2() {
    fetch('/fetch/fetch2', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            'id': 'java',
            'name' : '흥',
            'age' : 20
        })
    })
        .then((response) => {
            return response.text(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


//데이터 받기3
function fetch3(){
    //Javascript의 객체 배열
    const arr = [];

    for(let i =1; i <= 5; i++){
        const member = {
            'name' : `홍_${i}`,
            'age' : 20 + i,
            'id' : `java_${i}`
        };    
        arr.push(member);
    }

    fetch('/fetch/fetch3', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(arr)

    })
        .then((response) => {
            return response.text(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


//데이터 받기4
function fetch4() {
    
    //자바스크립트의 복잡한 데이터 생성
    const imgList = [
        {
            'imgName' : 'aaa.jpg',
            'imgCode' : 3 
        }, 
        {
            'imgName' : 'bbb.jpg',
            'imgCode' : 5 
        }
    ];

    const cartInfo = {
        'cartCode' : 1,
        'memberInfo' : {
            'memberId' : 'java',
            'memberName' : '읭'
        },
        'itemInfo' : {
            'itemCode' : 5,
            'itemName' : '상품1',
            'itemPrice' : 1000,
            'imgList' : imgList
        }
    };

    //console.log(cartInfo.itemInfo.imgList[0].imgName);

    fetch('/fetch/fetch4', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(cartInfo)
    })
        .then((response) => {
            return response.text(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}