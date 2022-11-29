package com.google.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.domain.BoardAttachVO;
import com.google.domain.BoardVO;
import com.google.domain.Criteria;
import com.google.domain.PageDTO;
import com.google.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	private BoardService service;

	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));

		int total = service.getListTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@PreAuthorize("isAuthenticated()")//로그인된 사용자
	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {

		service.register(board);

		// board/list로 이동하면서 result값(글번호)을 전달함.
		rttr.addFlashAttribute("result", board.getBno());

		return "redirect:/board/list";
	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") long bno, Model model,Criteria cri) {
		model.addAttribute("board", service.get(bno));
		model.addAttribute("pageMaker", new PageDTO(cri));
	}

	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {// 첨부파일 목록

		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
	}
	/*
	 * @GetMapping("/modify") public void modify(@RequestParam("bno") long bno,
	 * Model model) { model.addAttribute("board", service.get(bno)); }
	 */
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno, Criteria cri, RedirectAttributes rttr,String writer) {

		List<BoardAttachVO> attachList = service.getAttachList(bno);

		if(service.remove(bno)) {// 해당되는글이 지워졌으면
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result","success");//삭제 성공.
		}

		return "redirect:/board/list"+cri.getListLink();

	}
	@PreAuthorize("principal.username == #board.writer")//로그인된 사용자와 작성자가 같으면
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {

		service.modify(board);

		// board/list로 이동하면서 result값(글번호)을 전달함.
		rttr.addFlashAttribute("result", board.getBno());

		return "redirect:/board/list";
	}

	/**
	 * 파일 삭제 처리
	 */
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if (attachList == null || attachList.size() == 0) {
			return;
		}

		attachList.forEach(attach -> {

			try {
				Path file = Paths.get(
						"D:/upload/" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());

				Files.deleteIfExists(file);// 해당파일이 존재하면 삭제해라

				// 이미지일경우 썸네일삭제
				if (Files.probeContentType(file).startsWith("image")) {
					Path thumNail = Paths.get("D:/upload/" + attach.getUploadPath() + "/s_" + attach.getUuid() + "_"
							+ attach.getFileName());
					Files.delete(thumNail);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
