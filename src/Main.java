//import lisa.*;
import de.intarsys.pdf.example.extract.text.ExtractText;
import lisa.*;
import lisa.Dictionary;

import java.math.BigDecimal;
import java.math.BigInteger;
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
		File dir = new File("D:\\stxt2");
		File [] files = dir.listFiles();
		for(File i : files){
			try{
				Scanner file = new Scanner(i);
				String str = "";
				while(file.hasNext()){
					str += file.nextLine();
				}
				System.out.println(i);
				str = str.replaceAll("Ё", "Е");
				str = str.replaceAll("ё", "е");
				ArticleCPS art = new ArticleCPS(str);
	//			SQLQuery.saveArticle(art);
				file.close();
				} catch (IOException e){
					Common.createLog(e);
				}
		}
	}
}



















		/*
		lisa.Keywords.initsnsk();
		BigDecimal dd = Keywords.get(1000,150).divide(Keywords.get(1100, 170), 4);
	//	dd = dd.divide(Keywords.get(1000,150));

		System.out.println(dd);
		while(true){}
	}
}*/