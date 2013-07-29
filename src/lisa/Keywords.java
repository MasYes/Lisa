/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 12.07.13
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 * Распоточить по возможности.
 *
 * Внимание! Расчет мер нужно производить ПОСЛЕ добавления коллекции статей, ибо
 * используется количество статей коллекции.
 * Проверить ключевые слова, ибо результаты меня не впечатляют 0о Мб просто маленькая коллекция.
 * PS Пока сделано обязательным условием чтобы быть ключевым - чтобы freq/units>2 && units < countOfArticles*0.1;
 * В общем, философия метода такова, что все найденные слова несут какой-то "смысл". В этом плане
 * он верен. Второй шаг - найти "ключевые" слова из всех, что несут какой-то "смысл".
 * А еще надо попинать леммер, ибо бесит ><
 *
 * А вообще, в будущем надо использовать этот метод не для поиска ключевых, а для выделения "значимых слов", что заметно
 * уменьшит словарь => все будет работать быстрее)
 *
 *
 */
package lisa;

import java.math.BigInteger;


public class Keywords{ //implements Runnable {
	private static BigInteger[][] snsk;
	private static int height = 1150; //1150
	private static int width = 200; //200
	private static double probability = 1e-18; // Если meas меньше данного значения то слово обзываем кллючевым.

	private Keywords(){}

	private static void initsnsk(){
		snsk = new BigInteger[height][];
		for(int i = 0; i < height; i++){
			snsk[i] = new BigInteger[width];
		}
		snsk[0][0] = BigInteger.ONE;
		for(int i = 1; i < width; i++){
			snsk[0][i] = BigInteger.ZERO;
		}
		for(int i = 1; i < height; i++){
			for(int j = 0; j < width; j++){
				snsk[i][j] = get(i-1, j-1).add( get(i-1, j).multiply(BigInteger.valueOf(j)) ); // OMG, ай хейт джава, все дела. Перегрузка операторов ><
			}
		}
	}

	private static BigInteger get(int i, int j){
		if(i < 0 || j < 0)
			return BigInteger.ZERO;
		return snsk[i][j];
	}

	protected static void computeMeasures(int id){
		initsnsk();
		int count = SQLQuery.getCountOfArticles();
		int last = SQLQuery.getMaxID();
		for(int i = id; i <= last; i++){
			System.out.println(i);
			Term term = SQLQuery.getWordData(i);
			term.measure = 1;
			if(term.frequency/term.units>2 && term.units < count*0.1 && term.frequency < height && term.units < width){
				BigInteger res = BigInteger.ZERO;
				for(int n = 1; n <= term.units; n++){
					res = res.add(multiplyFromTo(count - n, count).multiply(get(term.frequency, n)));
				}
				term.measure = division(res, BigInteger.valueOf(count).pow(term.frequency));
			}
			SQLQuery.updateWord(term);
		}
	}

	public static void computeMeasures(){
		computeMeasures(1);
	}

	private static BigInteger multiplyFromTo(int from, int to){ //перемножает все числа от from+1 до (to); Если from == to, то единицу
		BigInteger res = BigInteger.ONE;
		while(from < to){
			from++;
			res = res.multiply(BigInteger.valueOf(from));
		}
		return res;
	}

	private static double division(BigInteger dividend, BigInteger divider){
		if(dividend.compareTo(divider) >= 0)
			return 1;
		double res = 0;
		int pow = 1;
		while(pow < 25){
			dividend = dividend.multiply(BigInteger.TEN);
			int count = 0;
			while(dividend.compareTo(divider) == 1){
				dividend = dividend.subtract(divider);
				count++;
			}
			res+=count*java.lang.Math.pow(0.1, pow);
			pow++;
		}
		return res;
	}

	@Deprecated
	protected static String[] getKeywords(String text) {return new String[]{};}

	protected static String[] getKeywords(Vector vect) {
		int count = 10; // искусственно ограничиваю количество возможных ключевых слов на 1 статью.
		int i = 0;
		String[] keywords = new String[count];
		for(Integer key : vect.keySet()){
			if(i == count)	break;
			Term word = SQLQuery.getWordData(key);
			if(word.measure < probability){
				keywords[i] = word.word;
				i++;
			}
		}
		return keywords;
	}
}
