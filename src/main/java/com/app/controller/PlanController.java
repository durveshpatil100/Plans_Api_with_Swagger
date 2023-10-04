package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Plan;
import com.app.service.PlanService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanController {

	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planService.getplanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String responseMsg = "";
		boolean isSaved = planService.savePlan(plan);

		if (isSaved) {
			responseMsg = "Plan is Saved";
		} else {
			responseMsg = "Plan is not saved";
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plan() {

		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean isUpdated = planService.updatePlan(plan);

		String msg = "";

		if (isUpdated) {
			msg = "Plan is Updated";
		} else {
			msg = "Plan Not Updated";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);

		String msg = "";

		if (isDeleted) {
			msg = "Plan Deleted";
		} else {
			msg = "Plan Not Deleted";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status){
		
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		
		String msg = "";
		
		if(isStatusChanged) {
			msg = "status changed";
		}else {
			msg = "Status not changed";
		}
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
