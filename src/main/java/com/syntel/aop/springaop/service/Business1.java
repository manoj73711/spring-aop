package com.syntel.aop.springaop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntel.aop.springaop.dao.Dao1;

@Service
public class Business1 {
	@Autowired
	private Dao1 dao1;

	public String CalculateSomething() {
		// Business logic
		return dao1.retrieveSomething();

	}
}
