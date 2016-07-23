package koal.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdiy.core.Args;
import org.jdiy.core.Dao;
import org.jdiy.core.JDiyContext;
import org.jdiy.core.Ls;
import org.jdiy.core.Rs;
import org.jdiy.util.Fs;

import com.koal.course.Course;

public class Test {

	public static void main(String[] args) throws MalformedURLException {
		URL xmlLocation = Fs.getResource("jdiy.xml");
		String rootPath = Fs.getResource("G:/wicket/student/src/main/java/koal/test/jdiy.xml").getPath();
		JDiyContext jdc = JDiyContext.newInstance(xmlLocation, rootPath);
		Dao dao = jdc.getDao();
		String id = "4";
		String string = "st.studentName=" + "'王刚'" + " And st.studentID=sc.studentID And sc.courseNO = co.courseNO";
		Ls ls = dao.ls(new Args("student st,score sc,course co",string));
		String string2 = "";
		
			for(Rs items: ls.getItems()){
				//System.out.println("id: " + items.get("studentID"));
				/*System.out.print("course: " + items.get("courseName") + "\t");
				System.out.print("name: " + items.get("studentName") + "\t");
				System.out.println("score: " + items.get("score"));*/
				string2 = string2 + items.get("studentID") + "\t" + items.get("studentName") + "\t" + items.get("courseName") + "\t" + items.get("score") + "\n";
		}
		System.out.println("学号： " + "\t" + "姓名" + "\t"  + "课程名" + "\t" + "成绩");
		System.out.println(string2);
	}
}
