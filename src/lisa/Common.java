package lisa;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 16.07.13
 * Time: 1:44
 * To change this template use File | Settings | File Templates.
 */
public class Common {
	private Common(){}
	protected final static int COUNT_THREADS = 1;

	public static void createLog(Exception e){
		System.out.println("LOG");
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

	public static void addArticles(String path){
		File dir = new File(path);
		File [] files = dir.listFiles();
		for(File i : files){
			try{
				Scanner file = new Scanner(i);
				String str = "";
				while(file.hasNext()){
					str += file.nextLine() + " ";
				}
				System.out.println(i);
				str = str.replaceAll("Ё", "Е");
				str = str.replaceAll("ё", "е");
				ArticleCPS art = new ArticleCPS(str);
				SQLQuery.saveArticle(art);
				file.close();
			} catch (IOException e){
				Common.createLog(e);
			}
		}
	}

	protected static void computeMeasures(int id){
		int last = SQLQuery.getCountOfWords();
		for(int i = id; i <= last; i++){
			System.out.println(i);
			Term term = SQLQuery.getWordData(i);
			term.computeMeasure();
			SQLQuery.updateWord(term);
		}
	}

	public static void computeMeasures(){
		computeMeasures(1);
	}




}
