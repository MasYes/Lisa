//import lisa.*;
import de.intarsys.pdf.example.extract.text.ExtractText;
import lisa.Lemmer;

import java.nio.file.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 13.07.13
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
	public static void main(String[] args){
		System.out.println(toStr(Lemmer.lemmer("потенциальноёрш")));
		/*long timeout = System.currentTimeMillis();
		String str1 = lisa.ExtractText.parse("D:\\example.pdf").replaceAll("[\\p{Punct}\n]+", " ");
		String str = toStr(Lemmer.lemmer(str1)).toLowerCase();
		System.out.println(System.currentTimeMillis() - timeout);
		try(FileWriter logs = new FileWriter("D:\\example.txt", false)){
			logs.write(str);
		} catch (IOException e){
			System.out.println("Всё очень плохо...");
		}*/
	}


public static String toStr(String[] str){
	String res = "";
	for(String i: str){
		if(i.length() > 1){
			res +=" " + i;
		}
	}
	return res;
}


}