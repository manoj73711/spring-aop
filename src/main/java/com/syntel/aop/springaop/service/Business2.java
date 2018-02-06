package com.syntel.aop.springaop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntel.aop.springaop.dao.Dao2;

@Service
public class Business2 {
	@Autowired
	private Dao2 dao2;

	public String CalculateSomething() {
		// Business logic
		return dao2.retrieveSomething();

	}
}
