package com.koal.student;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.StringValidator;
import org.jdiy.core.Args;
import org.jdiy.core.Dao;
import org.jdiy.core.Rs;

import com.koal.ListPage;
import com.koal.DBUtil.DBUtil;
import com.koal.student.Student;

public class StuInfoModPage extends WebPage implements Serializable {
	//获得数据库资源
	DBUtil dbUtil = new DBUtil();
	//学生学号用于修改时的索引
	private int studentId;
	TextField studentID;
	//姓名
	TextField studentName;
	//性别
	TextField gender ;
	//年龄
	TextField age;
	/**
	 * 构造函数
	 * @param pageParameters 通过url传递过来的参数
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public StuInfoModPage(PageParameters pageParameters) throws MalformedURLException{
		super(pageParameters);
		//获得StudentListPage传递过来的参数studentId:学生学号
		studentId = pageParameters.get("studentId").toInt();
		//获取学生详细信息
		StudentDetail(studentId);
	}
	
	/**
	 * 修改见面下获取特定学生的信息
	 * @param studentId 学生学号
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public void StudentDetail(int studentId) throws MalformedURLException{
		//获得数据库连接
		Dao dao  = dbUtil.getDAO();
		//从数据库查询特定学号的学生信息
		Rs rs = dao.rs(new Args("student","studentID="+studentId));
		//初始化学生对象
		Student student = new Student();
		//表单输入反馈信息
		this.add(new FeedbackPanel("feedback"));
		
		//初始化表单
		Form form = new Form("form",new CompoundPropertyModel(student)){
			protected void onSubmit(){
				Rs rs = new Rs("student");
				//修改学号
				rs.set("studentID", studentID.getDefaultModelObjectAsString());
				//修改姓名
				rs.set("studentName", studentName.getDefaultModelObjectAsString());
				//修改性别
				rs.set("gender",gender.getDefaultModelObjectAsString());
				//修改年龄
				rs.set("age", age.getDefaultModelObjectAsString());
				//保存修改的数据到数据库
				Dao dao;
				try {
					dao = dbUtil.getDAO();
					dao.save(rs);
					setResponsePage(ListPage.class);
				} catch (MalformedURLException e) {
					System.out.println("数据库连接出错");
				}
			}
		};
		//设置表单的输入信息
		form.setMultiPart(true);
		form.setMaxSize(Bytes.kilobytes(1000));
		this.add(form);
		//学号控件 内容初始化
		studentID = (TextField) new TextField("studentID",Model.of(rs.get("studentID"))).setEnabled(false);
		//姓名控件 内容初始化
		studentName = new TextField("studentName",Model.of(rs.get("studentName")));
		//性别控件 内容初始化
		gender = new TextField("gender",Model.of(rs.get("gender")));
		//年龄控件 内容初始化
		age = new TextField("age",Model.of(rs.get("age")));
		form.add(studentID);
		form.add(studentName);
		form.add(gender);
		form.add(age);
	}

}
