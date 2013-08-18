package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: Юлиан
 * Date: 17.08.13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
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
			keywords = in.nextLine();
			String codes = in.nextLine();
			setMark(-1);
			setVector(Vector.toVector(str));
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
