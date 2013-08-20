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

		 //UDC.computeUDCTerms();
		//Common.addArticles();
		//UDC.computeUDCVectors();
		//UDC.computeUDCTerms();
//		Vector vect = SQLQuery.getArticleVector(3001);
		Article art = new Article("A:\\example.pdf");
		System.out.println(UDC.findCloseUDCTerm(art.vector));

		/*Article art1 = new Article("A:\\example2.pdf");
		System.out.println(UDC.findCloseUDCTerm(art1.vector));
		Article art2 = new Article("A:\\example3.pdf");
		System.out.println(UDC.findCloseUDCTerm(art2.vector));
		Article art3 = new Article("A:\\example4.pdf");
		System.out.println(UDC.findCloseUDCTerm(art3.vector));
		Article art4 = new Article("A:\\example5.pdf");
		System.out.println(UDC.findCloseUDCTerm(art4.vector));
		Article art5 = new Article("A:\\example6.pdf");
		System.out.println(UDC.findCloseUDCTerm(art5.vector));
		Article art6 = new Article("A:\\example7.pdf");
		System.out.println(UDC.findCloseUDCTerm(art6.vector));*/
	}


	private static void Test(){
		String[] main = new String[]{"", "2", "3", "4", "5", "6", "7", "8"};
		for(String i : main){
			System.out.println("___________\n" + i);
			Article art = new Article("A:\\example" + i + ".pdf");
			System.out.println(art.findUDC());
		}
	}
}

