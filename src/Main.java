import lisa.*;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 13.07.13
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 * \\p{Punct}\n]
 *
 * В целом, как я и говорил, вся проблема с парсером... Углы очень большие.
 * Да, между похожими они меньше, но всё равно... И с леммером надо разобраться.
 * Но так, если в целом, то всё даже работает....
 */
public class Main {
	public static void main(String[] args){

		Common.addArticles();

//		System.out.println(SQLQuery.getArticleTitle(123));
		/*System.out.println(new File("D:\\example.pdf").length());
		System.out.println(new File("D:\\example2.pdf").length());
		System.out.println(new File("D:\\example3.pdf").length());
		System.out.println(new File("D:\\example4.pdf").length());
		System.out.println(new File("D:\\example5.pdf").length());

		Article art = new Article("D:\\example4.pdf");
		for(String i : art.keywords()){
			if(i!=null)
				System.out.println(i);
		}
		for(Integer i : art.findClose()){
			if(i!=null)
				System.out.println(SQLQuery.getArticleTitle(i));
		}*/
	}
}



/*		String path = "A:\\articles\\CPS\\stxt";
			File dir = new File(path);
			File [] files = dir.listFiles();
			int count = 0;
			for(File i : files){
				try{
					if (i.toString().contains(".txt")){
						String command = "A:\\mystem.exe -w -e utf-8 -l -n " +
								 i.toString() + " " + i.toString() + ".lemm";
						java.lang.Runtime.getRuntime().exec(command).waitFor();
						count++;
					}
				} catch (IOException|InterruptedException e){
					e.printStackTrace();
				}
			}*/