package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 13.07.13
 * Time: 0:48
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Vector extends HashMap<Integer, Integer> { //Имхо, наследовать было рационально, ибо создавать класс с 1 полем и оперировать им - не круто.


	public static Vector toVector(String str){
		while(str.contains("  ")){
			str = str.replace("  ", " ");
		}
		return toVector(str.split(" "));
	}

	protected static Vector toVector(String[] str){
		Vector vector = new Vector();
		ArrayList<String> array = new ArrayList<>();
		array.addAll(Arrays.asList(str)); //еще не определился, как лучше - так, или в конатрукторе сразу привести к такому виду.
		HashSet<String> set = new HashSet<>(array);
		set.remove("");
		for(String i : set){
			vector.put(SQLQuery.getIdWord(i), Collections.frequency(array, i));
		}
		vector.remove(-1);
		return vector;
	}

	public Integer at(Integer i){
		Integer res = this.get(i);
		if(res != null)
			return res;
		return 0;
	}


	public static double distance(Vector a, Vector b) { //I'm not sure about this speed. But this is only for tests.
		java.util.HashSet<Integer> set = new java.util.HashSet<Integer>();
		set.addAll(a.keySet());
		set.addAll(b.keySet());
		int sumA = 0;
		int sumB = 0;
		int dotProduct = 0;
		for(Integer i : set){
			sumA += Math.pow(a.at(i), 2);
			sumB += Math.pow(b.at(i), 2);
			dotProduct += a.at(i)*b.at(i);
		}
		return Math.acos(dotProduct/(Math.sqrt(sumA)*Math.sqrt(sumB)))/(Math.PI/2); //so, this function return double in [0;1]
	}

}
