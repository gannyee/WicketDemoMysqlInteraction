package com.koal.course;

import java.io.Serializable;
import java.net.MalformedURLException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Bytes;
import org.jdiy.core.Args;
import org.jdiy.core.Dao;
import org.jdiy.core.Rs;

import com.koal.ListPage;
import com.koal.DBUtil.DBUtil;
import com.koal.course.Course;

public class CourInfoModPage extends WebPage implements Serializable {
	//获得数据库资源
	DBUtil dbUtil = new DBUtil();
	//课程号用于修改时的索引
	private int courseId;
	//课程号
	TextField courseNO;
	//课程名
	TextField courseName;
	//课程属性
	TextField property ;
	//学分
	TextField credit;
	//学时
	TextField period;
	/**
	 * 构造函数
	 * @param pageParameters 通过url传递过来的参数
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public CourInfoModPage(PageParameters pageParameters) throws MalformedURLException{
		super(pageParameters);
		//获得StudentListPage传递过来的参数studentId:学生学号
		courseId = pageParameters.get("courseId").toInt();
		//获取学生详细信息
		courseDetail(courseId);
	}
	
	/**
	 * 修改见面下获取特定学生的信息
	 * @param studentId 学生学号
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public void courseDetail(int courseId) throws MalformedURLException{
		//获得数据库连接
		Dao dao  = dbUtil.getDAO();
		//从数据库查询特定学号的课程信息
		Rs rs = dao.rs(new Args("course","courseNO="+courseId));
		//初始化课程对象
		Course course = new Course();
		//表单输入反馈信息
		this.add(new FeedbackPanel("feedback"));
		
		//初始化表单
		Form form = new Form("form",new CompoundPropertyModel(course)){
			protected void onSubmit(){
				Rs rs = new Rs("course");
				//修改课程号
				rs.set("courseNO", courseNO.getDefaultModelObjectAsString());
				//修改课程名
				rs.set("courseName", courseName.getDefaultModelObjectAsString());
				//修改属性
				rs.set("property",property.getDefaultModelObjectAsString());
				//修改学分
				rs.set("credit", credit.getDefaultModelObjectAsString());
				//修改学时
				rs.set("period", period.getDefaultModelObjectAsString());
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
		//课程号内容 控件初始化
		courseNO = (TextField) new TextField("courseNO",Model.of(rs.get("courseNO"))).setEnabled(false);
		//课程名控件 内容初始化
		courseName = new TextField("courseName",Model.of(rs.get("courseName")));
		//课程属性控件 内容初始化
		property = new TextField("property",Model.of(rs.get("property")));
		//课程学分控件 内容初始化
		credit = new TextField("credit",Model.of(rs.get("credit")));
		//课程学时控件 内容初始化
		period = new TextField("period",Model.of(rs.get("period")));
		form.add(courseNO);
		form.add(courseName);
		form.add(property);
		form.add(credit);
		form.add(period);
	}

}
