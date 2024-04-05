package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Hangar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 20)
    private String planet;
    private Boolean is_enabled = false;

    public Hangar(String planet, Boolean is_enabled) {
        this.planet = planet;
        this.is_enabled = is_enabled;
    }

    public Hangar() {
    }
}
