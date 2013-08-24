package lisa;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Юлиан
 * Date: 23.08.13
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
public class Nearest {
	private static double diffrence = 1.53; //1.54
	private static int MAXcount = 300; //300
	private static int count = 234;
	private static double step = 0.175/10; //Примерно 10 градусов //10
	private static String[][] mass = new String[MAXcount][(int)(1.57/step) + 2];
	private static double closeness = 1.23;

	public static void createBasis(){
		//Возможно потребуется переписать
		Vector[] basis = new Vector[MAXcount];
		int next = 1;
		basis[0] = SQLQuery.getArticleVector(1);
		for(int i = 2; i <= SQLQuery.getCountOfArticles(); i++){
			Vector curr = SQLQuery.getArticleVector(i);
			for(int j = 0; j < next; j++){
				if(curr.angle(basis[j]) < diffrence)
					break;
				if(j == (next - 1)){
					basis[next++]=curr;
					break;
				}
			}
			if(next == MAXcount)
				break;
		}
		int count = next;
		System.out.println(count);

		Vector vect;
		double angle;
		int place;


		for(int i = 1; i <= SQLQuery.getCountOfArticles(); i++){
			vect = SQLQuery.getArticleVector(i);
			for(int j = 0; j < count; j++){
				angle = vect.angle(basis[j]);
				place =(int)(angle/step);
				if(mass[j][place] == null)
					mass[j][place] = i + ";";
				else
					mass[j][place] += i + ";";
			}
		}

		for(int i = 0; i < count; i++){
			String res = "";
			for(int j = 0; j < (int)(1.57/step)-1; j++){
				if(mass[i][j]!=null)
					res += mass[i][j];
			}
			SQLQuery.saveGroup(res, basis[i]);
		}
	}


	protected static Integer[] findClose(Vector vect){
		Integer[] res = new Integer[100];
		int min = 0;
		double value = 1.6;
		double curr;
		for(int i = 0; i < count; i++){
			curr = vect.angle(SQLQuery.getGroupVector(i));
			if(curr < value){
				min = i;
				value = curr;
			}
		}
		int count = 0;
		for(String i : SQLQuery.getGroupArticles(min).split(";")){
			if(vect.angle(SQLQuery.getArticleVector(Integer.parseInt(i))) < closeness){
				res[count++] = Integer.parseInt(i);
			}
		}
		return res;
	}
}
