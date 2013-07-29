import lisa.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 13.07.13
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 * \\p{Punct}\n]
 *
 * В целом, как я и говорил, вся проблема с парсером... Углы очень большие.
 * Да, между похожими они меньше, но всё равно... И с леммером надо разобраться.
 * Но так, если в целом, то всё даже работает....
 */
public class Main {
	public static void main(String[] args){
		long time = System.currentTimeMillis();
		lisa.Article art = new Article("D:\\example.pdf", true);
		for(String i : art.keywords()){
			if(i != null)
				System.out.println(i);
		}
		System.out.println("_____________________________");
		for(Integer i : art.nearest(6)){
			if(i != null)
				System.out.println(SQLQuery.getArticleTitle(i));
		}
		System.out.println(System.currentTimeMillis() - time);
	}
}