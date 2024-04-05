package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String title;
    private String description;
    @Column(nullable = false)
    private Double max_speed;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Hangar hangar;

    public Plan(String title, String description, Double max_speed, Hangar hangar) {
        this.title = title;
        this.description = description;
        this.max_speed = max_speed;
        this.hangar = hangar;
    }

    public Plan() {
    }
}
