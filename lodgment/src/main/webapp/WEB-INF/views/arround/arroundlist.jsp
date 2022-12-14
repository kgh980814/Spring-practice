<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
    <!-- bradcam_area  -->
    <div class="bradcam_area bradcam_bg_2">
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
                             <div><h2>MY Arround</h2>
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
                    <div class="filter_result_wrap">
                        <h3>Filter Result</h3>
                        <div class="filter_bordered">
                            <div class="filter_inner">
                                <div class="row">
                                    <div class="col-lg-12">
		                          		<div class="nice-select">
                               		 <input id="datepicker" placeholder="Date" style="border: none; background: transparent;"readonly="readonly">
                            		</div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="single_select">
                                            <select>
                                                <option data-display="숙소유형">모텔</option>
                                                <option value="1">호텔·리조트</option>
                                                <option value="2">펜션</option>
                                                <option value="4">게스트하우스</option>
                                                <option value="4">캠핑</option>
                                              </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="range_slider_wrap">
                                            <span class="range">Price</span>
                                            <div id="slider-range"></div>
                                            <p>
                                                <input type="text" id="amount" readonly style="border:0; color:#7A838B; font-weight:400;">
                                            </p>
                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="search_btn" >
                                  <button class="boxed-btn4" type="submit">apply</button>
                            </div>
                          
                          
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="/resources/img/place/1.png" alt="">
                                    <a href="#" class="prise">$500</a>
                                </div>
                                <div class="place_info">
                                    <a href="ArroundDetail"><h3>California</h3></a>
                                    <p>United State of America</p>
                                    <div class="rating_days d-flex justify-content-between">
                                        <span class="d-flex justify-content-center align-items-center">
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i>
                                             <a href="#">(20 Review)</a>
                                        </span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a href="#">5 Days</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="/resources/img/place/2.png" alt="">
                                    <a href="#" class="prise">$500</a>
                                </div>
                                <div class="place_info">
                                    <a href="ArroundDetail"><h3>Korola Megna</h3></a>
                                    <p>United State of America</p>
                                    <div class="rating_days d-flex justify-content-between">
                                        <span class="d-flex justify-content-center align-items-center">
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i>
                                             <a href="#">(20 Review)</a>
                                        </span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a href="#">5 Days</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="/resources/img/place/3.png" alt="">
                                    <a href="#" class="prise">$500</a>
                                </div>
                                <div class="place_info">
                                    <a href="ArroundDetail"><h3>London</h3></a>
                                    <p>United State of America</p>
                                    <div class="rating_days d-flex justify-content-between">
                                        <span class="d-flex justify-content-center align-items-center">
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i>
                                             <a href="#">(20 Review)</a>
                                        </span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a href="#">5 Days</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="/resources/img/place/4.png" alt="">
                                    <a href="#" class="prise">$500</a>
                                </div>
                                <div class="place_info">
                                    <a href="ArroundDetail"><h3>Miami Beach</h3></a>
                                    <p>United State of America</p>
                                    <div class="rating_days d-flex justify-content-between">
                                        <span class="d-flex justify-content-center align-items-center">
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i>
                                             <a href="#">(20 Review)</a>
                                        </span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a href="#">5 Days</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="/resources/img/place/5.png" alt="">
                                    <a href="#" class="prise">$500</a>
                                </div>
                                <div class="place_info">
                                    <a href="ArroundDetail"><h3>California</h3></a>
                                    <p>United State of America</p>
                                    <div class="rating_days d-flex justify-content-between">
                                        <span class="d-flex justify-content-center align-items-center">
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i>
                                             <a href="#">(20 Review)</a>
                                        </span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a href="#">5 Days</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="/resources/img/place/6.png" alt="">
                                    <a href="#" class="prise">$500</a>
                                </div>
                                <div class="place_info">
                                    <a href="ArroundDetail"><h3>Saintmartine Iceland</h3></a>
                                    <p>United State of America</p>
                                    <div class="rating_days d-flex justify-content-between">
                                        <span class="d-flex justify-content-center align-items-center">
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i> 
                                             <i class="fa fa-star"></i>
                                             <a href="#">(20 Review)</a>
                                        </span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a href="#">5 Days</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="more_place_btn text-center">
                                <a class="boxed-btn4" href="#">More Places</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <!-- newletter_area_start  -->

        <!-- newletter_area_end  -->
   

<%@ include file="../includes/footer.jsp" %>