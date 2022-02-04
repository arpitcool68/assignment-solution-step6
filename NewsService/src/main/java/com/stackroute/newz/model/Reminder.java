package com.stackroute.newz.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Reminder {

	private String reminderId;
	private Date schedule;

	public Reminder(String reminderId, Date schedule) {
		super();
		this.reminderId = reminderId;
		this.schedule = schedule;
	}

	public Reminder() {
		super();
	}

	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule() {
		LocalDateTime dateTime = LocalDateTime.now();
		Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(i);
		this.schedule =date;
	}


}
