package com.techmojo.web.automation.util;

import java.util.Date;

public final class DateUtils {
	
	private DateUtils() {
		super();
	}
	
	public static String getTimeStamp() {
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}
}