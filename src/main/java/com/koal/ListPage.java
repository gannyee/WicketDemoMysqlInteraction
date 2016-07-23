package com.koal;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jdiy.core.Args;
import org.jdiy.core.Dao;
import org.jdiy.core.Ls;
import org.jdiy.core.Rs;

import com.koal.DBUtil.DBUtil;
import com.koal.course.CourInfoModPage;
import com.koal.course.Course;
import com.koal.score.Score;
import com.koal.score.ScoreInfoModPage;
import com.koal.student.StuInfoModPage;
import com.koal.student.Student;

public class ListPage extends WebPage implements Serializable {

	
	private static List students = new ArrayList();
	private static List courses = new ArrayList();
	private static List scores = new ArrayList();
	private static DBUtil dbUtil = new DBUtil();	
	private TextField idAdd;
	private TextField nameAdd;
	private TextField genderAdd;
	private TextField ageAdd;
	private TextField courseIdAdd;
	private TextField courseNameAdd;
	private TextField coursePropertyAdd;
	private TextField creditAdd;
	private TextField periodAdd;
	private TextField scoreIdAdd;
	private TextField stuIdAdd;
	private TextField stuNamAdd;
	private TextField couNOAdd;
	private TextField couNamAdd;
	private TextField couScoreAdd;
	public ListPage() throws MalformedURLException{
		super();
		getStudentData();
		getCourseData();
		getScoreData();
	}
	
	public void getStudentData() throws MalformedURLException{
		students = getStudentDb();
		final PageableListView listView = new PageableListView("students",students,10) {

			protected void populateItem(ListItem items) {
				final Student student = (Student) items.getModelObject();
				items.add(new Label("id",student.getId()));
				items.add(new Label("name",student.getName()));
				items.add(new Label("gender",student.getGender()));
				items.add(new Label("age",student.getAge()));
				items.add(new Link("edit") {
					@Override
					public void onClick() {
						System.out.println("edit: " + student.getId());
						PageParameters parameters = new PageParameters();
						parameters.add("studentId",student.getId());
						setResponsePage(StuInfoModPage.class,parameters);
						
					}
				});
				items.add(new Link("delete") {
					@Override
					public void onClick() {
						System.out.println("delete: " + student.getId());
						try {
							Dao dao = dbUtil.getDAO();
							dao.del(new Args("student","studentID="+ student.getId()) );
							setResponsePage(ListPage.class);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			
		};
		
		final Form form = new Form("form") {
			protected void onSubmit() {
				System.out.println("add new: ");
				Rs rs = new Rs("student");
				// 修改学号
				rs.set("studentID", idAdd.getModelObject().toString());
				// 修改姓名
				rs.set("studentName", nameAdd.getDefaultModelObjectAsString());
				// 修改性别
				rs.set("gender", genderAdd.getDefaultModelObjectAsString());
				// 修改年龄
				rs.set("age", ageAdd.getDefaultModelObjectAsString());
				// 保存修改的数据到数据库
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
		this.add(form);
		idAdd = new TextField("idAdd",Model.of(""));
		nameAdd = new TextField("nameAdd",Model.of(""));
		genderAdd = new TextField("genderAdd",Model.of(""));
		ageAdd = new TextField("ageAdd",Model.of(""));
		form.add(idAdd);
		form.add(nameAdd);
		form.add(genderAdd);
		form.add(ageAdd);
		form.add(listView);
		form.add(new PagingNavigator("navigator",listView));
		
	}
	
	public void  getCourseData() throws MalformedURLException{
		courses = getCourseDb();
		final PageableListView listView = new PageableListView("courses",courses,10) {

			protected void populateItem(ListItem items) {
				final Course course = (Course) items.getModelObject();
				items.add(new Label("courseNO",course.getCourseNO()));
				items.add(new Label("courseName",course.getCourseName()));
				items.add(new Label("property",course.getProperty()));
				items.add(new Label("credit",course.getCredit()));
				items.add(new Label("period",course.getPeriod()));
				items.add(new Link("editCorse") {
					@Override
					public void onClick() {
						System.out.println("editCorse: " + course.getCourseNO());
						PageParameters parameters = new PageParameters();
						parameters.add("courseId",course.getCourseNO());
						setResponsePage(CourInfoModPage.class,parameters);
						
					}
				});
				items.add(new Link("deleteCourse") {
					@Override
					public void onClick() {
						System.out.println("delete: " + course.getCourseNO());
						try {
							Dao dao = dbUtil.getDAO();
							dao.del(new Args("course","courseNO="+ course.getCourseNO()) );
							setResponsePage(ListPage.class);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			
		};
		
		final Form form = new Form("courseForm") {
			protected void onSubmit() {
				System.out.println("add new: ");
				Rs rs = new Rs("course");
				// 修改课程号
				rs.set("courseNO", courseIdAdd.getModelObject().toString());
				// 修改课程名
				rs.set("courseName", courseNameAdd.getDefaultModelObjectAsString());
				// 修改课程属性
				rs.set("property", coursePropertyAdd.getDefaultModelObjectAsString());
				// 修改课程学分
				rs.set("credit", creditAdd.getDefaultModelObjectAsString());
				//修改课程学时
				rs.set("period", periodAdd.getDefaultModelObjectAsString());
				// 保存修改的数据到数据库
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
		this.add(form);
		courseIdAdd = new TextField("courseIdAdd",Model.of(""));
		courseNameAdd = new TextField("courseNameAdd",Model.of(""));
		coursePropertyAdd = new TextField("coursePropertyAdd",Model.of(""));
		creditAdd = new TextField("creditAdd",Model.of(""));
		periodAdd = new TextField("periodAdd",Model.of(""));
		form.add(courseIdAdd);
		form.add(courseNameAdd);
		form.add(coursePropertyAdd);
		form.add(creditAdd);
		form.add(periodAdd);
		form.add(listView);
		form.add(new PagingNavigator("courseNavigator",listView));
	}
	
	public void  getScoreData() throws MalformedURLException{
		scores = getScoreDb();
		final PageableListView listView = new PageableListView("scores",scores,10) {

			protected void populateItem(ListItem items) {
				final Score score = (Score) items.getModelObject();
				items.add(new Label("scoreId",score.getId()));
				items.add(new Label("stuId",score.getStudentID()));
				items.add(new Label("stuNam",score.getStudentName()));
				items.add(new Label("couNO",score.getCourseNO()));
				items.add(new Label("couNam",score.getCourseName()));
				items.add(new Label("couScore",score.getScore()));
				items.add(new Link("editScore") {
					@Override
					public void onClick() {
						System.out.println("editCorse: " + score.getId());
						PageParameters parameters = new PageParameters();
						parameters.add("id",score.getId());
						setResponsePage(ScoreInfoModPage.class,parameters);
						
					}
				});
				items.add(new Link("deleteScore") {
					@Override
					public void onClick() {
						System.out.println("delete: " + score.getId());
						try {
							Dao dao = dbUtil.getDAO();
							dao.del(new Args("score","id="+score.getId()) );
							setResponsePage(ListPage.class);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			
		};
		
		final Form form = new Form("scoreForm") {
			protected void onSubmit() {
				System.out.println("add new: ");
				Rs rs = new Rs("score");
				// 成绩序号
				rs.set("id", scoreIdAdd.getModelObject().toString());
				// 学号
				rs.set("studentID", stuIdAdd.getDefaultModelObjectAsString());
				// 课程编号
				rs.set("courseNO", couNOAdd.getDefaultModelObjectAsString());
				// 成绩
				rs.set("score", couScoreAdd.getDefaultModelObjectAsString());
				// 保存修改的数据到数据库
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
		this.add(form);
		scoreIdAdd = new TextField("scoreIdAdd",Model.of(""));
		stuIdAdd = new TextField("stuIdAdd",Model.of(""));
		stuNamAdd = new TextField("stuNamAdd",Model.of(""));
		couNOAdd = new TextField("couNOAdd",Model.of(""));
		couNamAdd = new TextField("couNamAdd",Model.of(""));
		couScoreAdd =  new TextField("couScoreAdd",Model.of(""));
		form.add(scoreIdAdd);
		form.add(stuIdAdd);
		form.add(stuNamAdd);
		form.add(couNOAdd);
		form.add(couNamAdd);
		form.add(couScoreAdd);
		form.add(listView);
		form.add(new PagingNavigator("scoreNavigator",listView));
	}
	
	public List getStudentDb() throws MalformedURLException{
		Dao dao = dbUtil.getDAO();
//		Rs rs = new Rs("student");
		Ls ls = dao.ls(new Args("student"));
		List students = new ArrayList();
		for(Rs item:ls.getItems()){
			Student student = new Student();
			student.setId(Integer.parseInt(item.get("studentID")));
			student.setName(item.get("studentName"));
			student.setGender(item.get("gender"));
			student.setAge(Integer.parseInt(item.get("age")));
			students.add(student);
		}
		return students;
	}
	
	public List getCourseDb() throws MalformedURLException{
		Dao dao = dbUtil.getDAO();
//		Rs rs = new Rs("course");
		Ls ls = dao.ls(new Args("course"));
		List courses = new ArrayList();
		for(Rs item:ls.getItems()){
			Course course = new Course();
			course.setCourseNO(Integer.parseInt(item.get("courseNO")));
			course.setCourseName(item.get("courseName"));
			course.setProperty(item.get("property"));
			course.setCredit(Integer.parseInt(item.get("credit")));
			course.setPeriod(Integer.parseInt(item.get("period")));
			courses.add(course);
		}
		return courses;
	}
	
	public List getScoreDb() throws MalformedURLException{
		Dao dao = dbUtil.getDAO();
//		Rs rs = new Rs("score");
		Ls ls = dao.ls(new Args("score"));
		List scores = new ArrayList();
		for(Rs item:ls.getItems()){
			Score score = new Score();
			score.setId(Integer.parseInt(item.get("id")));
			score.setCourseNO(Integer.parseInt(item.get("courseNO")));
			score.setStudentID(Integer.parseInt(item.get("studentID")));
			Ls lsStudent = dao.ls(new Args("student","studentID="+Integer.parseInt(item.get("studentID"))));
			Ls lsCourse = dao.ls(new Args("course","courseNO=" +Integer.parseInt(item.get("courseNO"))));
			score.setCourseName(lsCourse.getItems()[0].get("courseName"));
			score.setStudentName(lsStudent.getItems()[0].get("studentName"));
			score.setScore(Integer.parseInt(item.get("score")));
			scores.add(score);
		}
		return scores;
	}
	
	public List getStudentAllId() throws MalformedURLException{
		Dao dao = dbUtil.getDAO();
		Ls ls = dao.ls(new Args("student","studentID"));
		List studentId = new ArrayList();
		for(Rs item:ls.getItems()){
			studentId.add(Integer.parseInt(item.get("studentID")));
		}
		return studentId;
	}
}
