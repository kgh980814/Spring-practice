package com.google.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.domain.Criteria;
import com.google.domain.ReplyPageDTO;
import com.google.domain.ReplyVO;
import com.google.service.ReplyService;

import lombok.extern.log4j.Log4j;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("replies/*")
@Log4j
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;

	/**
	 * 댓글 등록
	 * 
	 * @param vo
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")//로그인한 사용자만 사용가능
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })

	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		int insertCount = service.insert(vo);
		return insertCount == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK)
				: new ResponseEntity<String>("Success", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * http://localhost/admin/replies/pages/3/1
	 * 
	 * @param page
	 * @param bno
	 * @return
	 */

	@GetMapping(value = "/pages/{bno}/{page}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }) // 생략가능
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<ReplyPageDTO>(service.getListWithPaging(cri, bno), HttpStatus.OK);

	}

	/**
	 * 댓글 읽어들이기 http://localhost/admin/replies/4
	 * 
	 * @param rno
	 * @return
	 */
	@GetMapping(value = "/{rno}")
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		return new ResponseEntity<ReplyVO>(service.read(rno), HttpStatus.OK);
	}
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value = "/{rno}")
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo ,@PathVariable("rno") Long rno) {
		int deleteCount = service.delete(rno);

		return deleteCount == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "/{rno}")

	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, 
										 @PathVariable("rno") Long rno) {
		vo.setRno(rno);
		int updateCount = service.update(vo);

		return updateCount == 1 ? new ResponseEntity<String>("Success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
