package com.koal;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jdiy.core.Args;
import org.jdiy.core.Dao;
import org.jdiy.core.Ls;
import org.jdiy.core.Rs;

import com.koal.DBUtil.DBUtil;
import com.koal.score.Score;
import com.koal.score.ScoreInfoModPage;

public class ScoreIndex extends WebPage {
	DBUtil dbUtil = new DBUtil();
	private static List choiceList = new ArrayList();
	TextField text;
	int flag;
	List list = new ArrayList();
	String title = "";
	String result = "";
	String sql = "";
	MultiLineLabel titleLabel;
	MultiLineLabel resultLabel;
	static {
		choiceList.add("按学生学号查询成绩");
		choiceList.add("按课程编号查询成绩");
		choiceList.add("按学生姓名查询成绩");
		choiceList.add("按课程名称查询成绩");
	}

	private String selected = "按学生学号查询成绩";

	public ScoreIndex() {
		// TODO Auto-generated constructor stub
		super();

		Form form = new Form("form") {
			protected void onSubmit() {
				System.out.println(">>>> : " + choiceList.indexOf(selected));
				flag = choiceList.indexOf(selected);
				title = "学号" + " ---------- " + "姓名" + " ---------- " + "课程编号" + " ---------- " + "课程名称" + " ---------- " + "成绩";
				switch (flag) {
				case 0: {

					try {
						sql = "st.studentID=" + (String) text.getModelObject()
								+ " And st.studentID=sc.studentID And sc.courseNO = co.courseNO";
						titleLabel.setDefaultModel(Model.of(title));
						resultLabel.setDefaultModel(Model.of(byStudentID(sql)));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				case 1: {
					try {
						sql = "co.courseNO=" + (String) text.getModelObject()
								+ " And st.studentID=sc.studentID And sc.courseNO = co.courseNO";
						titleLabel.setDefaultModel(Model.of(title));
						resultLabel.setDefaultModel(Model.of(byStudentID(sql)));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				case 2: {
					try {
						sql = "st.studentName='" + (String) text.getModelObject()
						+ "' And st.studentID=sc.studentID And sc.courseNO = co.courseNO";
						titleLabel.setDefaultModel(Model.of(title));
						resultLabel.setDefaultModel(Model.of(byStudentID(sql)));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				case 3: {
					try {
						sql = "co.courseName='" + (String) text.getModelObject()
						+ "' And st.studentID=sc.studentID And sc.courseNO = co.courseNO";
						titleLabel.setDefaultModel(Model.of(title));
						resultLabel.setDefaultModel(Model.of(byStudentID(sql)));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	};
		this.add(form);
		DropDownChoice choice = new DropDownChoice("score", new PropertyModel(this, "selected"), choiceList);
		text = new TextField("textInput", Model.of(""));
		form.add(text);
		form.add(choice);
		choice.setRequired(true);
		choice.setNullValid(true);
		titleLabel = new MultiLineLabel("title", title);
		resultLabel = new MultiLineLabel("result", result);
		form.add(titleLabel);
		form.add(resultLabel);
	}

	public String byStudentID(String sql) throws MalformedURLException {
		Dao dao = dbUtil.getDAO();
		List list = new ArrayList();
		Ls ls = dao.ls(new Args("student st,score sc,course co",sql));
		String result = "";
		for (Rs items : ls.getItems()) {
			result = result + ">>" + items.get("studentID") + " ---------- " + items.get("studentName")
			+ " -------------- " + items.get("courseNO")+ " ------------------ " + items.get("courseName") + " ---------------- " + items.get("score") + "\n";
		}
		return result;
	}
}
