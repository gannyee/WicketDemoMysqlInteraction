package com.koal.score;

import java.io.Serializable;

public class Score implements Serializable{
	//成绩表序号
	private int id;
	//学生学号
	private int studentID;
	//课程号
	private int courseNO;
	//学生姓名
	private String studentName;
	//课程名
	private String courseName;
	//成绩
	private int score;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
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
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
