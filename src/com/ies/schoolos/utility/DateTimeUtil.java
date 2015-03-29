package com.ies.schoolos.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static String getDDMMYYYY(Date date){
		String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(date);
    	String year = dateStr.substring(6);
    	if(Integer.parseInt(year) > 2500)
    		dateStr = dateStr.replace(year, Integer.parseInt(year)-543+"");
		return dateStr;
	}
	
	public static String getDDMMYYYYBD(Date date){
		String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(date);
    	String year = dateStr.substring(6);
    	if(Integer.parseInt(year) < 2500)
    		dateStr = dateStr.replace(year, Integer.parseInt(year)+543+"");
		return dateStr;
	}
	
	/* กำหนดวัน เป็น dd/MM/YYYY จาก YYYY-MM-dd */
	public static String getDDMMYYYYBD(String date){
		String[] dates = date.split("-");
    	String dateStr = dates[2] + "/" + dates[1] + "/";
    	if(Integer.parseInt(dates[0]) < 2500)
    		dateStr += (Integer.parseInt(dates[0])+543) + "";
    	else
    		dateStr = dates[0];
		return dateStr;
	}
}
