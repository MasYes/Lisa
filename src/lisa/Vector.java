/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 13.07.13
 * Time: 0:48
 * To change this template use File | Settings | File Templates.
 */

package lisa;
public class Vector {
	private java.util.HashMap<Integer, Integer> dict;
	protected Vector(){}

	public static double distance(Vector a, Vector b) { //I'm not sure about this speed. But this is only for tests.
		java.util.HashSet<Integer> set = new java.util.HashSet<Integer>();
		set.addAll(a.dict.keySet());
		set.addAll(b.dict.keySet());
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

	public Vector(java.util.HashMap<Integer, Integer> vect){ //for tests
		dict = vect;
	}

	protected static Vector toVector(String str){
		Vector vector = new Vector();
		//magic
		return vector;
	}

	public Integer at(Integer i){
		Integer res =  dict.get(i);
		if(res != null)
			return res;
		return 0;
	}



}
