package com.example.demo.controller;

import com.example.demo.model.Hangar;
import com.example.demo.repo.HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

}

