/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 12.07.13
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
package lisa;
public interface ArticleInterface {
	boolean equals(ArticleAbstract article); // mb if title equals - true?
	String getSense(); //from that we will try to find keywords etc.
	//double closeness(ArticleAbstract article); //distance between two article
	TemplateStyle getTemplate();
	int getId();
	String getTitle();

}
