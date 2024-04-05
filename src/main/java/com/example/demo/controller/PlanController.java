package com.example.demo.controller;


import com.example.demo.model.Hangar;
import com.example.demo.model.Plan;
import com.example.demo.repo.HangarRepository;
import com.example.demo.repo.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private HangarRepository hangarRepository;

    @GetMapping("/plans")
    public String planMain(Model model){
        Iterable<Plan> plans = planRepository.findAll();
        model.addAttribute("plans", plans);
        return "plan-list";
    }

    @GetMapping("/plans/add")
    public String planAdd(Model model){
        Iterable<Hangar> hangars = hangarRepository.findAll();
        model.addAttribute("hangars", hangars);
        return "plan-add";
    }

    @PostMapping("/plans/add")
    public String planPostAdd(@RequestParam String title, @RequestParam String description, @RequestParam Double max_speed, @RequestParam Hangar hangar, Model model){
        Plan plan = new Plan(title, description, max_speed, hangar);
        planRepository.save(plan);
        return "redirect:/plans";
    }

    @GetMapping("/plans/{id}")
    public String planDetails(@PathVariable(value = "id") UUID id, Model model){
        if (!planRepository.existsById(id)){
            return "redirect:/plans";
        }

        Optional<Plan> plan = planRepository.findById(id);
        ArrayList<Plan> res = new ArrayList<>();
        plan.ifPresent(res::add);
        model.addAttribute("plan", res);
        return "plan-details";
    }

    @GetMapping("/plans/{id}/edit")
    public String planEdit(@PathVariable(value = "id") UUID id, Model model){
        if (!planRepository.existsById(id)){
            return "redirect:/plans";
        }
        Iterable<Hangar> hangars = hangarRepository.findAll();
        Optional<Plan> plan = planRepository.findById(id);
        ArrayList<Plan> res = new ArrayList<>();
        plan.ifPresent(res::add);
        model.addAttribute("plan", res);
        model.addAttribute("hangars", hangars);
        return "plan-edit";
    }

    @PostMapping("/plans/{id}/edit")
    public String planPostUpdate(@PathVariable(value = "id") UUID id, @RequestParam String title, @RequestParam String description, @RequestParam Double max_speed, @RequestParam Hangar hangar, Model model){
        Plan plan = planRepository.findById(id).orElseThrow();
        plan.setTitle(title);
        plan.setDescription(description);
        plan.setMax_speed(max_speed);
        plan.setHangar(hangar);
        planRepository.save(plan);

        return "redirect:/plans";
    }

    @PostMapping("/plans/{id}/remove")
    public String planPostDelete(@PathVariable(value = "id") UUID id, Model model){
        Plan plan = planRepository.findById(id).orElseThrow();
        planRepository.delete(plan);
        return "redirect:/plans";
    }
}
