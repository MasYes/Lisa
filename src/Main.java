//import lisa.*;
import de.intarsys.pdf.example.extract.text.ExtractText;
import lisa.ArticleCPS;
import lisa.Dictionary;
import lisa.Lemmer;

import java.nio.file.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.lang.ref.*;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 13.07.13
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 * \\p{Punct}\n]
 */
public class Main {
	public static void main(String[] args){

		File dir = new File("D:\\stxt");
		File [] files = dir.listFiles();
		for(File i : files){
			try{
				System.gc();
				Scanner file = new Scanner(i);
				String str = "";
				while(file.hasNext()){
					str += file.nextLine();
				}
				System.out.println(i);
				System.out.println(str.length());
				str = str.replaceAll("Ё", "Е");
				str = str.replaceAll("ё", "е");
				ArticleCPS art = new ArticleCPS(str);
				str = art.getSense();
				while(str.contains("  ")){
					str = str.replace("  ", " ");
				}
				Dictionary.addToDictionary(Lemmer.lemmer(str));
				file.close();
			} catch (Exception e){
				lisa.Common.createLog(e);
			}
		}
	//	Dictionary.saveDictionary();
	}
}




/*		String str = lisa.ExtractText.parse("D:\\example.pdf");
		File dir = new File("D:\\stxt");
		File [] files = dir.listFiles();
		try(FileWriter logs = new FileWriter("D:\\example.txt", false)){
			logs.write(str);
		} catch (IOException e){
			System.out.println("Все очень плохо...");
		}
		lisa.ArticleCPS art = new ArticleCPS(str.split("\n"));
		System.out.println(art.getTitle());
		*/