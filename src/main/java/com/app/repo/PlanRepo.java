package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
