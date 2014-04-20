package lisa;

/**
 * Created by Юлиан on 12.04.14.
 */
public class ArticleYandex extends ArticleAbstract {

	protected String udc;
	protected String rank;
	protected Vector vector;

	protected ArticleYandex(String udc, String rank, Vector vector){
		this.udc = udc;
		this.rank = rank;
		this.vector = vector;
	}



}
