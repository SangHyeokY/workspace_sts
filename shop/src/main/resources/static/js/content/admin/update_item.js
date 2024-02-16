//변경 이후에도 페이지 유지
const updateItemCode = document.querySelector('#updateItemCode').value;
if (updateItemCode != 0) {
    getdetail(updateItemCode);
}

//상품 목록 테이블의 행 클릭 시 상품의 상세 정보 조회
function getdetail(itemCode) {
    fetch('/admin/selectItemDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'itemCode': itemCode
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data);

            const detail_div = document.querySelector('.detail-div');
            detail_div.innerHTML = '';
            let str = '';
            str += `
        <form action="/admin/updateItemDetail" method="post">
        <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
        <label class="form-label">&nbsp;<strong>제품 기본 정보</strong></label>
                    <table id="updateItemTable2">
                        <colgroup>
                            <col width="20%">
                            <col width="40%">
                            <col width="20%">
                            <col width="20%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            &nbsp;<strong>카테고리</strong>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row">
                                        <div class="col" style="padding-right: 30px;">
                                            <select class="form-select" aria-label="Default select" style="background-color:rgb(250, 250, 250); border: none; 
                                            box-shadow: 0px 0px 7px 0px rgb(230, 230, 230);" name="cateCode">`;
            for (const category of data.cateList) {
                if (category.cateCode == data.itemDetail.cateCode) {
                    str += `<option value="${category.cateCode}" selected>
                                                            ${category.cateName}
                                                        </option>`
                }
                else {
                    str += `<option value="${category.cateCode}">
                                                    ${category.cateName}
                                                </option>`
                }
            }
            str += `</select>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            &nbsp;<strong>제품수량</strong>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            <input type="number" class="form-control text-center" min="1" style="background-color:rgb(250, 250, 250); border: none; 
                                            box-shadow: 0px 0px 7px 0px rgb(230, 230, 230);" value="${data.itemDetail.itemStock}" name="itemStock">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            &nbsp;<strong>제품명</strong>
                                        </div>
                                </td>
                                <td colspan="3">
                                    <div class="row">
                                        <div class="col">
                                            <input type="text" class="form-control" style="background-color:rgb(250, 250, 250); border: none; 
                                            box-shadow: 0px 0px 7px 0px rgb(230, 230, 230);" value="${data.itemDetail.itemName}" name="itemName">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            &nbsp;<strong>제품상태</strong>
                                        </div>
                                    </div>
                                </td>
                                <td colspan="3">
                                    <div class="row"
                                        style="background-color:rgb(250, 250, 250); padding: 4px; 
                                    margin-right: 1px; margin-left: 1px; border-radius: 7px; box-shadow: 0px 0px 7px 0px rgb(230, 230, 230);">
                                        <div class="col">
                                            <div class="form-check form-check-inline"
                                                style="margin-right: 28px; margin-left: 3px;">`;
            if (data.itemDetail.itemStatus == 1) {
                str += `<input class="form-check-input" type="radio" value="1" name="itemStatus" checked>`;
            }
            else {
                str += `<input class="form-check-input" type="radio" value="1" name="itemStatus">`;
            }
            str += `<label class="form-check-label" for="">준비 중</label>
                                            </div>
                                            <div class="form-check form-check-inline"
                                                style="margin-left: 28px; margin-right: 28px;">`;
            if (data.itemDetail.itemStatus == 2) {
                str += `<input class="form-check-input" type="radio" value="2" name="itemStatus" checked>`;
            }
            else {
                str += `<input class="form-check-input" type="radio" value="2" name="itemStatus">`;
            }
            str += `<label class="form-check-label" for="">판매 중</label>
                                            </div>
                                            <div class="form-check form-check-inline" style="margin-left: 28px;">`;
            if (data.itemDetail.itemStatus == 3) {
                str += `<input class="form-check-input" type="radio" value="3" name="itemStatus" checked >`;
            }
            else {
                str += `<input class="form-check-input" type="radio" value="3" name="itemStatus">`;
            }
            str += `<label class="form-check-label" for="">판매 불가</label>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="header-div"></div>
                    <label class="form-label">&nbsp;<strong>제품 이미지 정보</strong></label>
                    <table id="updateItemTable3">
                        <colgroup>
                            <col width="20%">
                            <col width="80%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            &nbsp;<strong>메인 이미지</strong>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row"
                                        style="background-color:rgb(250, 250, 250); padding: 4px; 
                                    margin-right: 1px; margin-left: 1px; border-radius: 7px; box-shadow: 0px 0px 7px 0px rgb(230, 230, 230);">
                                        <div class="col" id="main-image-info">`;
            for (const img of data.itemDetail.imgList) {
                if (img.isMain == 'Y') {
                    str += `<span onclick="showItemModal('${img.attachedFileName}')">${img.originFileName}</span>`;
                }
                else {
                    str += ``;
                }
            }
            str += `</div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col">
                                            &nbsp;<strong>상세 이미지</strong>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row"
                                        style="background-color:rgb(250, 250, 250); padding: 4px; 
                                    margin-right: 1px; margin-left: 1px; border-radius: 7px; box-shadow: 0px 0px 7px 0px rgb(230, 230, 230);">
                                        <div class="col" id="sub-image-info">`;
            let cnt = 0;
            data.itemDetail.imgList.forEach(function (img, idx) {
                if (img.isMain == 'N') {
                    if (cnt == 0) {
                        str += `
                                                    <div class="col">
                                                        <span onclick="showItemModal('${img.attachedFileName}')">${img.originFileName}</span>
                                                    </div>`;
                        cnt++;
                    }
                    else {
                        str += `
                                                    <div class="offset-3 col">
                                                        <span onclick="showItemModal('${img.attachedFileName}')">${img.originFileName}</span>
                                                    </div>`;
                    }
                }
            });
            str += `
                                    </div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="header-div"></div>
                    <div class="d-grid">
                        <input type="submit" value="변경" class="btn btn-dark" onclick="updateItemInfo()">
                    </div>
        </form>`;
            detail_div.insertAdjacentHTML('afterbegin', str)
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

//이미지 모달창 띄우기
function showItemModal(attachedFileName) {
    const updateModal = new bootstrap.Modal('#img-modal');
    const img_tag = document.querySelector('#img-modal img');
    img_tag.src = `/upload/${attachedFileName}`;
    updateModal.show();
}
