import lisa.*;
import java.util.HashSet;
import java.io.*;

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
		System.out.println(new File("D:\\example.pdf").length());
		System.out.println(new File("D:\\example2.pdf").length());
		System.out.println(new File("D:\\example3.pdf").length());
		System.out.println(new File("D:\\example4.pdf").length());
		System.out.println(new File("D:\\example5.pdf").length());
		/*
		Article art = new Article("D:\\example4.pdf");
		for(String i : art.keywords()){
			if(i!=null)
				System.out.println(i);
		}
		for(Integer i : art.findClose()){
			if(i!=null)
				System.out.println(SQLQuery.getArticleTitle(i));
		}*/
	}
}