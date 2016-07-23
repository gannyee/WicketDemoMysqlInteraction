package com.koal.course;

import java.io.Serializable;

public class Course implements Serializable{
	//课程编号
	private int courseNO;
	//课程名称
	private String courseName;
	//课程属性 选修 / 限选 / 必需
	private String property;
	//课程学分
	private int credit;
	//课程学时
	private int period;
	/**
	 * @return the courseNO
	 */
	public int getCourseNO() {
		return courseNO;
	}
	/**
	 * @param courseNO the courseNO to set
	 */
	public void setCourseNO(int courseNO) {
		this.courseNO = courseNO;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	
	
}
