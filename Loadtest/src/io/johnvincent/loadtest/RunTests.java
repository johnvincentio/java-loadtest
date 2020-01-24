
package io.johnvincent.loadtest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import io.johnvincent.http.AppCookies;
import io.johnvincent.http.AppException;
import io.johnvincent.http.Browser;
import io.johnvincent.http.Form;
import io.johnvincent.http.FormItem;
import io.johnvincent.http.HttpMessage;
import io.johnvincent.http.Receiver;
import io.johnvincent.http.Sender;

import io.johnvincent.file.JVFile;
import io.johnvincent.utils.JVString;

import io.johnvincent.trace.LogHelper;

public class RunTests {
	private AppThreadItem m_appThreadItem;
	private AppThreads m_appThreads;
	private Stages m_stages;
	private File m_logDir;
	private String m_browser;
	public RunTests (AppThreadItem appThreadItem, AppThreads appThreads, Stages stages, File logDir, String browser) {
		m_appThreadItem = appThreadItem;
		m_appThreads = appThreads;
		m_stages = stages;
		m_logDir = logDir;
		m_browser = browser;
	}
	public boolean isThreadHasBeenInstructedToStop() {
		LogHelper.info("if (isThreadHasBeenInstructedToStop()) "+m_appThreadItem.isThreadHasBeenInstructedToStop());
		return m_appThreadItem.isThreadHasBeenInstructedToStop();
	}
	private void handleProgressIndicator() {m_appThreads.handleProgressIndicator();}
	private void addMessage (String msg) {m_appThreads.addMessage(msg);}
	public void runTests (int threadId) {
		AppCookies appCookies = new AppCookies();
		Stage stage;
		Iterator<Stage> iter = m_stages.getItems();
		while (iter.hasNext()) {
			stage = (Stage) iter.next();
			LogHelper.info("---runTests::runTests; stage "+stage);
			if (isThreadHasBeenInstructedToStop()) return;
			addMessage ("("+threadId+") Running test case "+stage.getMessage());
			handleProgressIndicator();
			runTest (appCookies, m_stages.getUrlNoPort(), stage, m_logDir, m_browser);
		}
		addMessage ("("+threadId+") Test cases complete");
	}
	private void runTest(AppCookies appCookies, String url, Stage stage, File logDir, String browser) {
		Sender sender;
		Receiver receiver;
//		String strURL = url + "/" + stage.getUrl();
		String strURL = url + stage.getUrl();
		System.out.println("strURL "+strURL);
		try {
			sender = new Sender(strURL);
			if (stage.isPost())
				sender.setPostMethod();
			else
				sender.setGetMethod();
			Form form = stage.getForm();
			FormItem formItem;
			Iterator<FormItem> iter = form.getItems();
			while (iter.hasNext()) {
				formItem = (FormItem) iter.next();
				sender.addFormItem (formItem.getKey(), formItem.getValue());
			}
	
			LogHelper.info("sender: "+sender.toString());
			receiver = HttpMessage.getReceiverWithRedirects (sender, appCookies);
			LogHelper.info("receiver: "+receiver.toString());

			if (isThreadHasBeenInstructedToStop()) return;
			if (stage.isBrowser()) runBrowser (receiver, logDir, browser);
		}
		catch (AppException appex) {
			LogHelper.info("App exception "+appex.getMessage());
		}
	}

	private void runBrowser (Receiver receiver, File logDir, String browser) {
		System.out.println("runBrowser; logDir "+logDir+" browser "+browser);
		String strFile = "";
		try {
			File file = File.createTempFile ("Load_Testing_", ".html", logDir);
			strFile = file.getAbsolutePath();
		}
		catch (IOException ioex) {
			LogHelper.info("runBrowser::IOException");
		}

		addMessage ("Running browser with file "+strFile);
		handleProgressIndicator();
		
		String[] strCmd = JVString.createStringArrayForExecCmd (browser, strFile);
		LogHelper.info("strCmd.length "+strCmd.length);
		for (String s : strCmd) {
			LogHelper.info("String  :"+s+":");
		}
		try {
			JVFile.writeFile(receiver.getBody().toString(), strFile);
//			String[] cmd = {strCmd, strFile};
			Browser.start(strCmd);
		}
		catch (AppException appex) {
			LogHelper.info("runBrowser::AppException "+appex.getMessage());
			LogHelper.info("cannot run command "+strCmd);
		}
	}
}

/*
public void doEditor(String strFile) {
String[] strCmd = JVString.createStringArrayForExecCmd (m_strEditor, strFile);
LogHelper.info("strCmd.length "+strCmd.length);
for (String s : strCmd) {
	LogHelper.info("String  :"+s+":");
}
try {
	Runtime.getRuntime().exec(strCmd);
}
catch (IOException e) {
	LogHelper.info("Exception; "+e.getMessage());
	LogHelper.info("cannot run command "+strCmd);
}
}
*/
