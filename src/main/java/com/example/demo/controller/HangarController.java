package com.example.demo.controller;

import com.example.demo.model.Hangar;
import com.example.demo.repo.HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class HangarController {
    @Autowired
    private HangarRepository hangarRepository;

    @GetMapping("/hangars")
    public String hangarMain(Model model){
        Iterable<Hangar> hangars = hangarRepository.findAll();
        model.addAttribute("hangars", hangars);
        return "hangar-list";
    }

    @GetMapping("/hangars/add")
    public String hangarAdd(Model model){
        return "hangar-add";
    }

    @PostMapping("/hangars/add")
    public String hangarPostAdd(@RequestParam String planet, @RequestParam Boolean is_enabled, Model model){
        Hangar hangar = new Hangar(planet, is_enabled);
        hangarRepository.save(hangar);
        return "redirect:/hangars";
    }

    @GetMapping("/hangars/{id}")
    public String hangarDetails(@PathVariable(value = "id") UUID id, Model model){
        if (!hangarRepository.existsById(id)){
            return "redirect:/hangars";
        }

        Optional<Hangar> hangar = hangarRepository.findById(id);
        ArrayList<Hangar> res = new ArrayList<>();
        hangar.ifPresent(res::add);
        model.addAttribute("hangar", res);
        return "hangar-details";
    }

    @PostMapping("/hangars/{id}")
    public String hangarActivate(@PathVariable(value = "id") UUID id, @RequestParam Boolean is_enabled, Model model){
        Hangar hangar = hangarRepository.findById(id).orElseThrow();
        Boolean enabled = !hangar.getIs_enabled();
        hangar.setIs_enabled(enabled);
        hangarRepository.save(hangar);

        return "redirect:/hangars/{id}";
    }

    @GetMapping("/hangars/{id}/edit")
    public String hangarEdit(@PathVariable(value = "id") UUID id, Model model){
        if (!hangarRepository.existsById(id)){
            return "redirect:/hangars";
        }

        Optional<Hangar> hangar = hangarRepository.findById(id);
        ArrayList<Hangar> res = new ArrayList<>();
        hangar.ifPresent(res::add);
        model.addAttribute("hangar", res);
        return "hangar-edit";
    }

    @PostMapping("/hangars/{id}/edit")
    public String hangarPostUpdate(@PathVariable(value = "id") UUID id, @RequestParam Boolean is_enabled, Model model){
        Hangar hangar = hangarRepository.findById(id).orElseThrow();
        hangar.setIs_enabled(is_enabled);
        hangarRepository.save(hangar);

        return "redirect:/hangars";
    }

    @PostMapping("/hangars/{id}/remove")
    public String hangarPostDelete(@PathVariable(value = "id") UUID id, Model model){
        Hangar hangar = hangarRepository.findById(id).orElseThrow();
        hangarRepository.delete(hangar);
        return "redirect:/hangars";
    }
}

