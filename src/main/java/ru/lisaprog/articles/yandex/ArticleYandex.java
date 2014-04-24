package main.java.ru.lisaprog.articles.yandex;

import main.java.ru.lisaprog.articles.ArticleAbstract;
import main.java.ru.lisaprog.objects.Vector;

/**
 * Created by Юлиан on 12.04.14.
 */
public class ArticleYandex extends ArticleAbstract {

	public String udc;
	public String rank;
	public Vector vector;

	public ArticleYandex(String udc, String rank, Vector vector){
		this.udc = udc;
		this.rank = rank;
		this.vector = vector;
	}



}
