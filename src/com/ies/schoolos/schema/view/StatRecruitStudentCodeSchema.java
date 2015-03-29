package com.ies.schoolos.schema.view;

public class StatRecruitStudentCodeSchema implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "stat_recruit_student_code";
	
	public static final String MAX_CODE = "max_code";
	public static final String MIN_CODE = "min_code";
	
	private static StatRecruitStudentCodeSchema instance = null;  

    public static StatRecruitStudentCodeSchema getInstance() {  
        if (instance == null) {  
            instance = new StatRecruitStudentCodeSchema();  
        }  
        return instance;  
    }
}
