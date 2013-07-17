
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
	protected ArticleCPS(String str){
		this.setTemplate(TemplateStyle.CPS);
		this.setTitle(str);
	}

	public ArticleCPS(int id, String title, String body){ //only for tests, delete this :)
		setId(id);
		setTitle(title);
		setBody(body);
		setTemplate(TemplateStyle.CPS);
	}

/*	public static ArticleCPS toArticle(Files inFile){
		return new ArticleCPS();
	}*/

}
