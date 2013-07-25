package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 18.07.13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 * НЕ ЗАБЫВАТЬ МЕНЯТЬ Ё НА Е
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class Dictionary implements Serializable {
	protected static HashMap<String, Term> dict = new HashMap<>();

	public static void addToDictionary(String[] str){
		ArrayList<String> array = new ArrayList<>();
		array.addAll(Arrays.asList(str)); //еще не определился, как лучше - так, или в конатрукторе сразу привести к такому виду.
		HashSet<String> set = new HashSet<>(array);
		set.remove("");
		for(String i : set){
			if(dict.containsKey(i)){
				dict.get(i).units++;
				dict.get(i).frequency+=Collections.frequency(array, i);
			}
			else{
				dict.put(i, new Term( Collections.frequency(array, i) ));
			}
		}
	}

	public static void saveDictionary(){
		for(String key: dict.keySet()){
			Term data = dict.get(key);
			SQLQuery.saveIntoDict(key, data.units, data.frequency, data.measure);
		}
	}



	public static void ser(){
		try{
			FileOutputStream fos = new FileOutputStream("D:\\dict.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dict);
			oos.flush();
			oos.close();
		}catch(Exception e){
			System.out.println(e);
		}



	}

}
