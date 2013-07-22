/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 12.07.13
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 * IllegalArgumentException - неверные входящие данные
 */
package lisa;

import java.io.Serializable;

class ArticleAbstract implements Serializable, ArticleInterface{
	private int id;
	private int mark;
	private int year;
	private String link;
	private String author;
	private String university;
	private String title;
	private String body;
	private String references;
	private String topic; //Мб удалить и заменить УДК?
	private String UDC;
	private TemplateStyle template;
	private Language lang;
	private Vector vector;


	String[] keywords(){
		return Keywords.getKeywords(getSense());
	}

	protected void setId(int i){
		this.id = i;
	}

	protected void setMark(int i){
		this.mark = i;
	}

	protected void setYear(int i){
		this.year = i;
	}

	protected void setLink(String str){
		this.link = str;
	}

	protected void setAuthor(String str){
		this.author = str;
	}

	protected void setUniversity(String str){
		this.university = str;
	}

	protected void setTitle(String str){
		this.title = str;
	}

	protected void setBody(String str){
		this.body = str;
	}

	protected void setReferences(String str){
		this.references = str;
	}

	protected void setVector(){
		this.vector = Vector.toVector(getSense());
	}



	protected void setTopic(String str){
		this.topic = str;
	}

	protected void setTemplate(TemplateStyle tmpl){
		this.template = tmpl;
	}

	protected void setLanguage(Language lang){
		this.lang = lang;
	}

	public int getId(){
		return id;
	}

	public int getMark(){
		return mark;
	}

	public String getTitle(){
		return title;
	}

	public String getTopic() {
		return topic;
	}

	public String getBody() {
		return body;
	}

	public TemplateStyle getTemplate(){
		return template;
	}

	public Language getLanguage(){
		return lang;
	}

	public String getSense(){
		return title + " " + body;
	}

	public double closeness(ArticleAbstract article){
		return Vector.distance(this.vector, article.vector);
	}

	public boolean equals(ArticleAbstract article){
		return this.title.equals(article.title);
	}

}
