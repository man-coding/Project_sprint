package com.example.demo.qnaBoard.controller;

import com.example.demo.diaryBoard.dto.DiaryDTO;
import com.example.demo.member.service.MemberService;
import com.example.demo.qnaBoard.dto.QnaDTO;
import com.example.demo.qnaBoard.service.QnaBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/qnaBoard")
public class QnaBoardController {

    @Autowired
    QnaBoardService qnaBoardService;

    @Autowired
    MemberService memberService;

    @GetMapping("/list")
    public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
        Page<QnaDTO> list = qnaBoardService.getList(page);
        model.addAttribute("list", list);
    }

    /* register 페이지 접근 */
    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String registerPost(QnaDTO dto, RedirectAttributes redirectAttributes, Principal principal) throws Exception {

        String id = principal.getName();
        String name = memberService.findNameById(id);
        dto.setWriter(name);

        int no = qnaBoardService.register(dto);

        redirectAttributes.addFlashAttribute("msg", no);

        return "redirect:/qnaBoard/read?no="+no;
    }

    @GetMapping("/read")
    public void read(@RequestParam(name="no") int no, @RequestParam(defaultValue = "0", name = "page") int page, Model model) {
        QnaDTO dto = qnaBoardService.read(no);
        model.addAttribute("dto", dto);
        model.addAttribute("page", page);
    }

    @GetMapping("/modify")
    public void modify(@RequestParam(name = "no") int no, Model model) {
        QnaDTO dto = qnaBoardService.read(no);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modifyPost(QnaDTO dto, RedirectAttributes redirectAttributes) {
        qnaBoardService.modify(dto);
        redirectAttributes.addAttribute("no", dto.getNo());
        return "redirect:/qnaBoard/read?no=" + dto.getNo();

    }

    @PostMapping("/remove")
    public String removePost(@RequestParam(name = "no") int no) {
        qnaBoardService.remove(no);
        return "redirect:/qnaBoard/list";
    }

}