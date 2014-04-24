package main.java.ru.lisaprog.articles.cyberleninka;

/**
 * Created with IntelliJ IDEA.
 * User: Юлиан
 * Date: 17.08.13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
import main.java.ru.lisaprog.Common;
import main.java.ru.lisaprog.Language;
import main.java.ru.lisaprog.TemplateStyle;
import main.java.ru.lisaprog.articles.ArticleAbstract;
import main.java.ru.lisaprog.lemmer.Lemmer;
import main.java.ru.lisaprog.objects.Vector;

import java.io.*;
import java.util.Scanner;

public class ArticleCyberleninka extends ArticleAbstract {
	private final String path = "A:\\articles\\cyberleninka\\";
	private String keywords;

	public ArticleCyberleninka(int id, String str){
		File file = new File(path + id + ".meta");
		try{
			setLanguage(Language.RU);
			setTemplate(TemplateStyle.CYBERLENINKA);
			setLink("http://cyberleninka.ru/");
			Scanner in = new Scanner(file);
			setPublication(in.nextLine().substring(8));
			setTitle(in.nextLine().substring(5));
			setAuthor(in.nextLine().substring(9));
			keywords = in.nextLine() + "; id = " + id;
			String codes = in.nextLine();
			setMark(-1);
			setVector(Vector.toVector(Lemmer.lemmer(str)));
			if(codes.contains("УДК:")){
				setUDC(codes.substring(codes.indexOf("УДК:") + 4));
			}
		} catch (IOException e){
			Common.createLog(e);
		}
	}

	public String getInfo(){
		return keywords;
	}

}
