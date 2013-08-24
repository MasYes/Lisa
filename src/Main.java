import lisa.*;
import java.util.HashSet;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

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
		Article art = new Article("A:\\example10.pdf");
		long time = System.currentTimeMillis();
		Integer[] res = art.findClose();
		System.out.println(System.currentTimeMillis() - time);
		for(Integer i : res){
			if(i == null)
				break;
			System.out.println(art.vector.angle(SQLQuery.getArticleVector(i)));
			System.out.println(SQLQuery.getArticleTitle(i));
			System.out.println("-----------------------");
		}

	}
}

