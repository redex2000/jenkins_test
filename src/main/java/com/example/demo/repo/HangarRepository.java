package com.example.demo.repo;

import com.example.demo.model.Hangar;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HangarRepository extends CrudRepository<Hangar, UUID> {
}
