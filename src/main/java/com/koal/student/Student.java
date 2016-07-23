package com.koal.student;

import java.io.Serializable;

public class Student implements Serializable{
	//学号
	private int studentID;
	//姓名
	private String studentName;
	//性别
	private String gender;
	//年龄
	private int age;
	/**
	 * @return the id
	 */
	public int getId() {
		return studentID;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.studentID = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return studentName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.studentName = name;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
