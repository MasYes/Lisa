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
	public static void main(String[] args){ //5548
		//UDC.computeUDCTerms();
		Article art = new Article("A:\\example9.pdf");
		System.out.println(art.findUDC());
		for(String str : art.keywords()){
			System.out.println(str);
		}
		/*String main = "510;511;512;514;515.1;517;519.1;519.2;519.6;519.7;519.8;";
		Vector vect;
		for(String i : main.split(";")){
			vect = SQLQuery.getUDCTerms(i);
			if(vect.keySet().size() > 0)
				System.out.println(i + "   ===   " + 1.0*art.vector.crossingSize(vect)/vect.size());
		}*/
		/*
		Vector vect = SQLQuery.getUDCTerms("519.2");
		System.out.println(vect.size());
		for(Integer i : art.vector.keySet()){
			if(vect.keySet().contains(i)){
				System.out.println(SQLQuery.getWordData(i).getWord() + " = " +
						SQLQuery.getWordData(i).getFrequency() + " = " + SQLQuery.getWordData(i).getUnits());
			}
		}*/


	}
}

