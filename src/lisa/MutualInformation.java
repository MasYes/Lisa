package lisa;

import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Юлиан on 14.04.14.
 */
public class MutualInformation {
/*
	HashMap<String, Vector> vectors;
	HashMap<String, Integer> sizes;
	HashMap<String, Int2DoubleOpenHashMap> mutualInformations;

	public MutualInformation(){
		vectors = new HashMap<>();
	}

	protected void addVector(String udc, Vector vector){
		if(!vectors.containsKey(udc))
			vectors.put(udc, new Vector());
		Vector vector2 = new Vector();
		for(int key : vector.keySet()){
			vector2.put(key, 1);
		}
		vectors.get(udc).add(vector2);
		if(!sizes.containsKey(udc))
			sizes.put(udc, 0);
		sizes.put(udc, sizes.get(udc) + 1);
	}

	protected void calculateMI(){
		mutualInformations = new HashMap<>();
		IntOpenHashSet terms = new IntOpenHashSet();
		for(String i : vectors.keySet()){
			terms.addAll(vectors.get(i).keySet());
		}


		for(int i : terms){
			int to = calculateTermOccurrences(i);
			for(String udc : vectors.keySet()){
				if(!mutualInformations.containsKey(udc))
					mutualInformations.put(udc, new Int2DoubleOpenHashMap());
				int



			}
		}



	}

	private int calculateTermOccurrences(int term){
		int result = 0;
		for(String i : vectors.keySet()){
			result += vectors.get(i).get(term);
		}
		return result;
	}


*/
}
