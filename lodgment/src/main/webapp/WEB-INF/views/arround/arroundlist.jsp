<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

    <!-- bradcam_area  -->
    <div class="bradcam_area bradcam_bg_1">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <div class="bradcam_text text-center">
                        <h3>reservation</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ bradcam_area  -->

    <div class="popular_places_area">
    
        <div class="container" style="padding-bottom: 150px">
                       	<div>
                             <div class="fw-bold mt-3 mb-1 fs-5"><h2>내주변</h2>
                             </div>
                             <div>
                            	<span>경주시 문무대왕면</span>                            
                             <button type="button" class="boxed-btn5" onclick="pop_relocation();">내 위치 재설정</button>
                       		</div>
                        </div>
                        </div>
             <div class="container">             
            <div class="row">
                <div class="col-lg-4">
                 		<div class="card p-1">
						<ul class="list-group list-group-flush">
							<li class="list-group-item py-3">
								<div class="fw-bold mt-3 mb-1 fs-5">날짜</div>
								<div class="text-small mt-1 mb-3 text-muted">최대 7박까지 조회 가능</div>
								<input type="text" id="datepicker" class="form-control" value="" />
								<input type="hidden" name="startDate" value="" />
								<input type="hidden" name="endDate" value="" />
							</li>
							<li class="list-group-item py-3">
								<div class="fw-bold mb-3 fs-5">상세조건</div>
								<div class="btn-toolbar row g-2" role="toolbar" aria-label="Toolbar with button groups">
									<div class="col">
										<button type="button" id="btn-reset" class="btn btn-outline-danger w-100">초기화</button>
									</div>
									<div class="col">
										<button type="button" id="btn-apply" class="btn btn-secondary w-100">적용</button>
									</div>
								</div>
							</li>
							<li class=" list-group-item py-3 border-bottom-0 text-muted">
								<div class="row d-flex justify-content-center">
									<div class="col-3 fw-bold text-muted my-auto">인원</div>
									<!-- 인원 수 표시 input 대신 span 태그 사용, 이벤트 script에서 설정하는 것으로 변경 예정 -->
									<div class="col-9 hstack gap-3">
										<button type="button" class="form-control2 btn btn-sm btn-light fs-4"
											onclick="this.parentNode.querySelector('input[type=number]').stepDown();">-</button>
										<input type="number" class="form-control2 form-control-lg fs-6 mx-auto" min="1" name="capacity" value="1" max="20" />
										<button type="button" class="form-control2 btn btn-sm btn-light fs-4"
											onclick="this.parentNode.querySelector('input[type=number]').stepUp();">+</button>
									</div>
								</div>
							</li>
							<li class="list-group-item py-3 border-bottom-0 text-muted">
								<div class="fw-bold mb-3">가격<small id="amount" class="text-secondary ms-3">1만원 이상</small></div>
								<input type="hidden" name="minPrice" value="10000" />
								<input type="hidden" name="maxPrice" value="800000" />
								<div id="slider-range"></div>
							</li>
							<li class="list-group-item py-3 border-bottom-0 text-muted ${empty param.type ? 'd-none' : '' }">
								<div class="label-option fw-bold pb-3 border-bottom" style="cursor: pointer;">
									공용 시설
									<i class="bi bi-chevron-double-down float-end"></i>
									<i class="bi bi-chevron-double-up float-end d-none"></i>
								</div>
								<div class="checks-option row d-none">
									<div class="col-12 text-end my-3">
										<label class="form-check-label small">전체 선택/해제</label>
										<input id="toggle-cofa" class="form-check-input toggle" type="checkbox"/>
									</div>
									<!-- 선택한 숙소 유형에 맞는 공용시설 옵션을 컨트롤러로부터 전달받아 반복문으로 출력한다. -->
									<c:forEach var="facility" items="${cofacilities }">
										<div class="col-6 mb-1">
											<input class="form-check-input" type="checkbox" name="commonFacilities" value="${facility.id }">
											<label class="form-check-label small">${facility.name }</label>
										</div>
									</c:forEach>
								</div>
							</li>
							<li class="list-group-item py-3 border-bottom-0 text-muted ${empty param.type ? 'd-none' : '' }">
								<div class="label-option fw-bold pb-3 border-bottom" style="cursor: pointer;">
									객실 시설
									<i class="bi bi-chevron-double-down float-end"></i>
									<i class="bi bi-chevron-double-up float-end d-none"></i>
								</div>
								<div class="checks-option row d-none">
									<div class="col-12 text-end my-3">
										<label class="form-check-label small">전체 선택/해제</label>
										<input id="toggle-rofa" class="form-check-input toggle" type="checkbox"/>
									</div>
									<!-- 모든 객실시설 옵션을 컨트롤러로부터 전달받아 반복문으로 출력한다. -->
									<c:forEach var="facility" items="${rofacilities }">
										<div class="col-6 mb-1">
											<input class="form-check-input" type="checkbox" name="roomFacilities" value="${facility.id }">
											<label class="form-check-label small">${facility.name }</label>
										</div>
									</c:forEach>
								</div>
							</li>
							<li class="list-group-item py-3 border-bottom-0 text-muted ${(empty param.type) or (empty tags) ? 'd-none' : '' }">
								<div class="label-option fw-bold pb-3 border-bottom" style="cursor: pointer;">
									태그
									<i class="bi bi-chevron-double-down float-end"></i>
									<i class="bi bi-chevron-double-up float-end d-none"></i>
								</div>
								<div class="checks-option row d-none">
									<div class="col-12 text-end my-3">
										<label class="form-check-label small">전체 선택/해제</label>
										<input id="toggle-tag" class="form-check-input toggle" type="checkbox"/>
									</div>
									<!-- 모든 부가사항 옵션을 컨트롤러로부터 전달받아 반복문으로 출력한다. -->
									<c:forEach var="tag" items="${tags }">
										<div class="col-6 mb-1">
											<input class="form-check-input" type="checkbox" name="tags" value="${tag }">
											<label class="form-check-label small">${tag }</label>
										</div>
									</c:forEach>
								</div>
							</li>
							<!-- 검색창으로 조회하는 경우 아래 옵션들이 카드에 표시된다. -->
							<li class="list-group-item py-3 border-bottom-0 text-muted ${not empty param.type ? 'd-none' : '' }">
								<div class="fw-bold mb-3">숙소 유형</div>
								<div class="row">
									<!-- 모든 숙소유형을 전달받아 반복문으로 출력 -->
									<c:forEach var="type" items="${types }">
										<div class="col-12 mb-3">
											<input class="form-check-input" type="checkbox" name="types" value="${type.id }">
											<label class="form-check-label">${type.name }</label>
										</div>
									</c:forEach>
								</div>
							</li>
						</ul>
					</div>
                </div>
                <!-- 정렬기준, 지도버튼, 숙소 리스트 -->
				<div class="col-8">
					<!-- 정렬기준 radio button, 지도 modal button (버튼 스타일 식당 조회와 통일시킬 예정) -->
					<div class="d-flex flex-wrap mx-3 mb-3">
						<div id="btn-group-sort" class="btn-group flex-fill pe-2 my-auto" role="group" aria-label="Basic radio toggle button group">
							<input type="radio" class="btn-check" id="btnradio1" name="sort" value="rate" checked>
							<label class="btn btn-secondary" for="btnradio1">평점 순</label>
							<input type="radio" class="btn-check" id="btnradio2" name="sort" value="dist">
							<label class="btn btn-secondary" for="btnradio2">거리 순</label>
							<input type="radio" class="btn-check" id="btnradio3" name="sort" value="lowprice">
							<label class="btn btn-secondary" for="btnradio3">낮은 가격 순</label>
							<input type="radio" class="btn-check" id="btnradio4" name="sort" value="highprice">
						  	<label class="btn btn-secondary" for="btnradio4">높은 가격 순</label>
						</div>
						<button type="button" class="btn btn-light my-auto" id="btn-open-modal-map"><i class="bi bi-map"></i></button>
						</div>
						<div id="accos-wrapper" class="row px-3 mx-auto">
						<!-- 숙소 검색결과가 script를 통해 출력됨-->
						</div>
					</div>
						
                
            </div>
        </div>
    </div>

        <!-- newletter_area_start  -->

        <!-- newletter_area_end  -->
   <script>
   $(function () {
	   /*
		input태그에서 daterangepicker 통해 숙박일정 선택하기
		TO DO : 가능하면 확인 버튼 위치 등 수정 또는 다른 라이브러리 사용?
	*/
		// 화면 로드 시 날짜 및 기간 초기화
		// * 로컬스토리지에 기존에 조회한 날짜가 저장되어 있으면 그 값을, 없으면 오늘/내일 날짜를 가져온다.
		// * 이 변수의 값이 hidden태그, 로컬스토리지, daterangepicker 에서 관리된다.
		let startDayString = getDateValues().start
		let endDayString = getDateValues().end;
		let duration = 1;
		// daterangepicker 생성 설정
	    $('#datepicker').daterangepicker({
	    	// 직접 커스텀한 문자열을 input태그의 value에 넣기 위해 autoUpdate 해제
	    	"autoUpdateInput": false,
	    	// 최대 7박까지 예약 가능
			"maxSpan": {
			    "days": 7
			},
	        "locale": {
	            "format": "YYYY-MM-DD",
	            "separator": " ~ ",
	            "applyLabel": "확인",
	            "cancelLabel": "취소",
	            "fromLabel": "From",
	            "toLabel": "To",
	            "customRangeLabel": "Custom",
	            "weekLabel": "W",
	            "daysOfWeek": ["월", "화", "수", "목", "금", "토", "일"],
	            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
	            "firstDay": 1
	        },
	        "minDate": moment().format('YYYY-MM-DD'), // 오늘 이전의 날짜는 조회 불가능하다.
	        "startDate": startDayString,
	        "endDate": endDayString,
	        "drops": "down"
	    }, function (start, end, label) {
	    	// 생성 시, 날짜를 변경한 뒤 적용시키면 실행되는 함수
	    	// 화면에 출력할 시작일, 종료일, 기간에 대한 문자열을 값을 변경하고, 변경된 날짜를 hidden태그, localStorage에도 저장한다.
	        startDayString = start.format('YYYY-MM-DD');
	        endDayString = end.format('YYYY-MM-DD');
	        duration = end.diff(start,'days');
	        setDateValues(startDayString, endDayString);
	    });
		// html이 출력될 때 datepicker의 input태그의 value 저장
		$('#datepicker').val(startDayString + ' ~ ' + endDayString + ' · '  + duration + '박');
		// html이 출력될 때에도 날짜 정보를 hidden 태그와 localStorage에 저장
		setDateValues(startDayString, endDayString);
		
		// 날짜 변경 여부와 무관하게 적용을 누르면 발생하는 이벤트에 함수 등록
		// * input태그의 value 설정 (생성 설정의 날짜변경 이벤트와 다름)
	    $('#datepicker').on('apply.daterangepicker', function(ev, picker) {
	    	setDateValues(startDayString, endDayString);
	    	$(this).val(startDayString + ' ~ ' + endDayString + ' · '  + duration + '박')
	    	// 검색 조건이 바뀌었으므로 숙소 검색 함수를 실행한다.
	        searchAccos();
	    });
	    
	    // 날짜 정보를 hidden 태그와 localStorage에 저장하는 함수
		// * hidden태그 저장 : 검색조건으로 날짜포맷의 값을 전달하기 위함
	    // * localStorage 저장 : 이 페이지를 다시 요청하거나 상세조회 페이지를 요청해도 설정한 날짜가 유지되도록 한다.
	    function setDateValues(start, end) {
	        $(":hidden[name=startDate]").val(start);
	        $(":hidden[name=endDate]").val(end);
	        localStorage.setItem("startDate", start);
	        localStorage.setItem("endDate", end);
	    }
	    
	    // 초기화할 날짜 정보를 가져오는 함수.
	    // localStorage에 값이 있으면 그 값을, 없으면 현재 날짜, 현재 날짜 + 1을 가져온다.
	    function getDateValues() {
	    	let startvalue = localStorage.getItem("startDate");
	    	let endvalue = localStorage.getItem("endDate")
	    	let selectedDate = {
	   			start : ((typeof startvalue == "undefined" || startvalue == null || startvalue == "") ? moment().format('YYYY-MM-DD') : startvalue),
	   			end : ((typeof endvalue == "undefined" || endvalue == null || endvalue == "") ? moment().add(+1, 'd').format('YYYY-MM-DD') : endvalue)
	    	};
	    	return selectedDate;
	    }
   $("#slider-range").slider({
		range : true,
		min : 1,
		max : 80,
		values : [1, 80],
		slide : function(event, ui) {
			// 슬라이더 값이 바뀔 때마다 텍스트 내용을 변경하고, hidden태그의 값도 변경한다.
			$("#amount").text(ui.values[0] + "만원 ~ " + ui.values[1] + "만원");
			$("input[name='minPrice']").val(ui.values[0]*10000);
			$("input[name='maxPrice']").val(ui.values[1]*10000);
		}
	});
	// 처음에는 1만원 이상으로 표시한다. (hidden태그 기본 최소/최대값도 1~30으로 저장되어있음)
	$("#amount").text($("#slider-range").slider("values", 0) + "만원 이상");
   });
   </script>
  
<%@ include file="../includes/footer.jsp" %>