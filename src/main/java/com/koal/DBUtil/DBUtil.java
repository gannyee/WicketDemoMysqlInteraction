package com.koal.DBUtil;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdiy.core.Dao;
import org.jdiy.core.JDiyContext;
import org.jdiy.util.Fs;

/**
 * 数据库操作
 * @author gannyee
 *
 */
public class DBUtil implements Serializable{
	//用户名
	
	/**
	 * 获得数据的连接
	 * @return 无
	 * @throws MalformedURLException 数据库连接抛出异常
	 */
	public Dao getDAO() throws MalformedURLException{
		URL xmlLocation = Fs.getResource("jdiy.xml");
		String rootPath = Fs.getResource("G:/wicket/student/src/main/java/koal/test/jdiy.xml").getPath();
		JDiyContext jdc = JDiyContext.newInstance(xmlLocation, rootPath);
		Dao dao = jdc.getDao();
		return dao;
	}
}
