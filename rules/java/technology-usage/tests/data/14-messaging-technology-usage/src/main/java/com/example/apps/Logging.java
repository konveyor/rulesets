package com.example.apps;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.DailyRollingFileAppender;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SocketHandler;

public class Logging {
	
	public class MyLogbackFileAppender extends ch.qos.logback.core.FileAppender{}
	public class MyRollingFileAppender extends RollingFileAppender{}
	public class MyDailyRollingFileAppender extends DailyRollingFileAppender{}
	public class MyFileHandler extends FileHandler{
		public MyFileHandler() throws IOException {
			super();
		}
	}
	public class MySocketHandler extends SocketHandler {
		public MySocketHandler() throws IOException {
			super("host", 1);
		}
	}
	
	public static void main(String argv[]) {
		try {
			RollingFileAppender rfa = new RollingFileAppender();
			DailyRollingFileAppender drfa = new DailyRollingFileAppender();
			FileHandler fh = new FileHandler();
			SocketHandler sh = new SocketHandler("host", 1);
		} catch (IOException e) {
			// Ignore
		}
	}

}
