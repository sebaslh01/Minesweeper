package uo.mp.minesweeper.util.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import uo.mp.minesweeper.util.Console;

public class FileLogger implements SimpleLogger{
	private static final boolean APPEND = true;
	private String filePath;
	public FileLogger(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public void log(Exception ex) {
		try {
		FileOutputStream file = new FileOutputStream(this.filePath, APPEND);
		PrintStream out = new PrintStream(file);		
		out.print( getDate() );
		out.println(ex);
		out.close();
		}catch(FileNotFoundException fne) {
			Console.printError("Something went wrong with logger");
		}
		
	}
	
	private String getDate() {
		Date date = new Date() ;
		String dateStr = new SimpleDateFormat("dd/MM/yy").format(date);
		String timeStr = new SimpleDateFormat("HH:mm:ss").format(date);
		dateStr = String.format("[%1$s - %2$s]: ", dateStr, timeStr);
		return dateStr;
	}

}


