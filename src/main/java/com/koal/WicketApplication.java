package com.koal;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.koal.Start#main(String[])
 */
public class WicketApplication extends WebApplication implements Serializable
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
//		return HomePage.class;
		return ListPage.class;
//		return StuInfoModPage.class;
//		return ScoreIndex.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getMarkupSettings().setDefaultMarkupEncoding("utf-8");
		// add your configuration here
	}
}
