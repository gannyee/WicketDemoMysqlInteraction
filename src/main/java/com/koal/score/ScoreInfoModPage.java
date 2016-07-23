package com.koal.score;

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
import org.jdiy.core.Ls;
import org.jdiy.core.Rs;

import com.koal.ListPage;
import com.koal.DBUtil.DBUtil;
import com.koal.course.Course;

public class ScoreInfoModPage extends WebPage implements Serializable{
	//获得数据库资源
	DBUtil dbUtil = new DBUtil();
	//成绩序列 用于修改时的索引
	private int id;
	//成绩序号
	TextField scoreId;
	//学号
	TextField studentID;
	//姓名
	TextField studentName;
	//课程号
	TextField courseNO;
	//课程名
	TextField courseName;
	//成绩
	TextField scoreModify;
	/**
	 * 构造函数
	 * @param pageParameters 通过url传递过来的参数
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public ScoreInfoModPage(PageParameters pageParameters) throws MalformedURLException{
		super(pageParameters);
		//获得ListPage传递过来的参数Id:成绩表的序列
		id = pageParameters.get("id").toInt();
		//获取成绩详细信息
		scoreDetail(id);
	}
	
	/**
	 * 获取特定序号的成绩详细信息
	 * @param id 成绩序号
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public void scoreDetail(int id) throws MalformedURLException{
		//获得数据库连接
		Dao dao  = dbUtil.getDAO();
		//从数据库查询特定成绩信息
		Rs rs = dao.rs(new Args("score","id="+id));
		//初始化课程对象
		Score score = new Score();
		//表单输入反馈信息
		this.add(new FeedbackPanel("feedback"));
		
		//初始化表单
		Form form = new Form("form",new CompoundPropertyModel(score)){
			protected void onSubmit(){
				Rs rs = new Rs("score");
				//读取成绩的序号id
				rs.set("id", scoreId.getDefaultModelObjectAsString());
				//读取当前成绩对应的学生学号
				rs.set("studentID", studentID.getDefaultModelObjectAsString());
		/*		//读取当前成绩对应的学生姓名
				rs.set("studentName",studentName.getDefaultModelObjectAsString());*/
				//读取当前成绩对应的课程编号
				rs.set("courseNO", courseNO.getDefaultModelObjectAsString());
				//修改学时
				rs.set("score",Integer.parseInt(scoreModify.getModelObject().toString()));
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
		
		Ls lsScore = dao.ls(new Args("score","id="+id));
		Ls lsStudent = dao.ls(new Args("student","studentID="+Integer.parseInt(lsScore.getItems()[0].get("studentID"))));
		Ls lsCourse = dao.ls(new Args("course","courseNO=" +Integer.parseInt(lsScore.getItems()[0].get("courseNO"))));
		//成绩序号内容 控件初始化
		scoreId = (TextField) new TextField("scoreId",Model.of(id)).setEnabled(false);
		//学号内容 控件初始化
		studentID = (TextField) new TextField("studentID",Model.of(rs.get("studentID"))).setEnabled(false);
		//姓名控件 内容初始化
		studentName = (TextField) new TextField("studentName",Model.of(lsStudent.getItems()[0].get("studentName"))).setEnabled(false);
		//课程属性控件 内容初始化
		courseNO = (TextField) new TextField("courseNO",Model.of(rs.get("courseNO"))).setEnabled(false);
		//课程名控件 内容初始化
		courseName = (TextField) new TextField("courseName",Model.of(lsCourse.getItems()[0].get("courseNO"))).setEnabled(false);
		//成绩控件 内容初始化
		scoreModify =  new TextField("scoreModify",Model.of(rs.get("score")));
		form.add(scoreId);
		form.add(studentID);
		form.add(studentName);
		form.add(courseNO);
		form.add(courseName);
		form.add(scoreModify);
		
	}

}
