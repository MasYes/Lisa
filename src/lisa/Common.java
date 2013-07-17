package lisa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileWriter;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 16.07.13
 * Time: 1:44
 * To change this template use File | Settings | File Templates.
 */
public class Common {
	private Common(){}
	protected static final int countThreads = 1;

	protected static void createLog(Exception e){
		String log = "";
		for(StackTraceElement elem : e.getStackTrace()){
			log += elem.toString() + "\n";
		}
		createLog(e.toString() + log);
	}

	protected synchronized static void createLog(String str){
		try(FileWriter logs = new FileWriter("logs.txt", true);){
			logs.write("[" + new Date() + "]\n" + str + "\n\n");
		} catch (IOException e){
			System.out.println("Всё очень плохо...");
		}
	}

}
