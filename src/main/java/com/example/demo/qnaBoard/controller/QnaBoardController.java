package com.example.demo.qnaBoard.controller;

import com.example.demo.qnaBoard.dto.QnaDTO;
import com.example.demo.qnaBoard.repository.QnaRepository;
import com.example.demo.qnaBoard.service.QnaBoardService;
import com.example.demo.qnaBoard.service.QnaBoardServiceImpl;
import com.example.demo.runningBoard.dto.RunningDTO;
import org.checkerframework.checker.units.qual.A;
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
import java.util.List;

@Controller
@RequestMapping("/qnaBoard")
public class QnaBoardController {

    @Autowired
    QnaBoardService qnaBoardService;

    @Autowired
    QnaBoardServiceImpl service;

    @GetMapping("/list")
    public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
        Page<QnaDTO> list = service.getList(page);
        model.addAttribute("list", list);
    }

    @PostMapping("/register")
    public String registerPost(QnaDTO dto, RedirectAttributes redirectAttributes, Principal principal) {

        String id = principal.getName();
        dto.setWriter(id);

        int no = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", no);

        return "redirect:/qnaBoard/list";
    }
}
