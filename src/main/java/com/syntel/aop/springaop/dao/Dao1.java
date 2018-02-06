package com.syntel.aop.springaop.dao;

import org.springframework.stereotype.Service;

import com.syntel.aop.springaop.dao.aspect.customannotation.TrackTime;

@Service
public class Dao1 {

	@TrackTime
	public String retrieveSomething() {
		return  "Dao1";
	}
	
}
