package com.google.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageDTO {

	private int startPage;// 시작페이지
	private int endPage;// 끝 페이지
	private boolean prev, next;// 이전 , 다음 여부

	private int total;// 전체글수
	private Criteria cri;// 페이징

	public PageDTO( Criteria cri,int total) {
		super();
		this.total = total;
		this.cri = cri;
		// 끝 페이지 번호
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		/**
		 * 끝 번호는 전체 데이터 수에 의해 영향을 받는다. 만일 10개씩 보여주는 경우 전체 데이터 수가 80이라고 가정하면 끝 번호는 10이 아닌
		 * 8이 되어야 한다. 만일 끝 번호와 한 페이지당 출력되는 데이터 수의 곱이 전체 데이터 수보다 크다면 끝 번호는 다시 total을 이용해
		 * 계산되면 된다.
		 */
		if (realEnd < this.endPage) {

			this.endPage = realEnd;

		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

}
