package com.example.demo.repo;

import com.example.demo.model.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlanRepository extends CrudRepository<Plan, UUID> {
}
