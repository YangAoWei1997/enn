package com.enn.commodity.synergistic.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class UpdateProgress extends JobServiceImpl{
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("run...");
	}

}
