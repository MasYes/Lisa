
/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 12.07.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
package lisa;

import java.nio.file.Files;

public class ArticleCPS extends ArticleAbstract {

	private int section;

	public ArticleCPS(String[] str){ //С этим парсером не работает; лучше пользоваться Питоновским для таких случаев.
		int i = 0;
		while(str[i].equals(""))
			i++;
		setAuthor(str[i++]);
		String university = "";
		while(!str[i].equals("")){
			university+=str[i++] + " ";
		}
		while(str[i].equals(""))
			i++;
		String title = "";
		while(!str[i].equals("")){
			title+=str[i++] + " ";
		}
		String lit = "Литература";
		String body = "";
		String references = "";
		while(!str[i].contains(lit) && i < str.length){
			body += str[i++] + " ";
		}
		while(i < str.length){
			references+=str[i++] + " ";
		}
		setBody(body);
		setTitle(title);
		setUniversity(university);
		setReferences(references);
	}


	public ArticleCPS(String str){
		section = Integer.parseInt(str.substring(str.indexOf("<section>") + 9, str.indexOf("</section>")));
		setAuthor(str.substring(str.indexOf("<author>") + 8, str.indexOf("</author>")));
		setUniversity(str.substring(str.indexOf("<university>") + 12, str.indexOf("</university>")));
		setTitle(str.substring(str.indexOf("<title>") + 7, str.indexOf("</title>")));
		setBody(str.substring(str.indexOf("<body>") + 6, str.indexOf("</body>")));
		setReferences(str.substring(str.indexOf("<references>") + 12, str.indexOf("</references>")));
	}


}
