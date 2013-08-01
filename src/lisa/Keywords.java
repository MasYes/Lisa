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
 *term.frequency/term.units>2 && term.units < count*0.1 && term.frequency < height && term.units < width
 */
package lisa;

import java.math.BigInteger;


public class Keywords{ //implements Runnable {

	private static double probability = 1e-18; // Если meas меньше данного значения то слово обзываем кллючевым.

	private Keywords(){}

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


	//@Deprecated // лучше расписать случай, когда слова нет, ибо мб ключевое
	//В условиях суровых нынешних реалий, поскольку я откинул все "неинтересные слова", то
	//слово, встречающееся часто в какой-то статье может быне не ключевым, а скучным.
	protected static String[] getKeywords(Vector vect) {
		int count = 1000; // искусственно ограничиваю количество возможных ключевых слов на 1 статью.
		int i = 0;
		String[] keywords = new String[count];
		for(Integer key : vect.keySet()){
			if(i == count)	break;
			Term term = SQLQuery.getWordData(key);
			/*некоторые пробелмы с таким определением ключевых слов могут возникнуть
			К примеру, если кто-то укажет однажды во введении, что он рассматривает задачу
			информационного поиска. В таком случае ИП - ключевое. С другой стороны, это
			помогает от "случайного" упоминания слова, которое подозревается в ключеватости.

			А еще может быть случай, когда во многих статьях случайно упоминается слово "символ".
			Тогда оно может не стать ключевым для статьи, посвещенной различным символам.
			Мб включать информацию о слове в том случае, когда оно упоминается в ней >1 раза?
			Надо будет поэкспериментировать.

			К тому же нужно будет попробовать анализировать "ключеватьсть" слова,
			основываясь на его положении в тексте.
			 */
			if(term.getMeasure() < probability && term.getFrequency()/term.getUnits()>2 &&
					term.getUnits() < SQLQuery.getCountOfArticles()*0.1 && vect.get(key)*vect.getNorm() > 1){
				keywords[i] = term.getWord();
				i++;
			}
		}
		return keywords;
	}
}
