package com.enn.commodity.synergistic.controller;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.service.impl.UpdateProgress;
import com.enn.commodity.synergistic.util.QuartzManager;

@RestController
@RequestMapping("/Scheduler")
@CrossOrigin
public class SchedulerController {
	
	@Value("${scheduler.timer.updateProgressTimer}")
	private String updateProgressTimer;
	
	@ResponseBody
	@RequestMapping(value = "/startJob", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String startJob() throws Exception {
		
		SchedulerFactory schedulerFactory=new StdSchedulerFactory();
		Scheduler scheduler=schedulerFactory.getScheduler();
		String job_name="每日更新报告";
		System.out.println("updateProgressTimer");
		QuartzManager .addJob(scheduler, job_name, UpdateProgress.class, updateProgressTimer);
		return "";
		
	}

}
