package com.genderfrender.autoServiceApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/test")
public class TestController 
{
	@GetMapping
	public ResponseEntity test(){
		return ResponseEntity.ok(LocalDateTime.now());
	}
}
