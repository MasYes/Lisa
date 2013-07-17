package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 16.07.13
 * Time: 2:19
 * To change this template use File | Settings | File Templates.
 * У меня такое подозрение, что сделано это фиговенько, ибо без нотифаев и вейтов. Да и, возможно, с get-методами в SQLQuery накосячил.
 * И то, что код заработал сразу, тоже наводит на мысли 0о
 * И тесты не показывают особого прироста производительности от количества потоков. Мб слова такие, сб синхронизированные методы тормозят. Но 0,5 секунды это классно ^^
 * Проверить все еще раз и, возможно, убрать распоточивание. Все стукается либо в синхронайз блоки, либо (скорее) в SQL запросы.
 * Или, как вариант, при возможности, сделать по 1 БД для каждого потока и чтобы каждый работал со своей.
 * С 1 потоком, вроде, даже чуть быстрее.
 * И почему-то ИНТЕГРАЛУ и ИНТИГРАЛУ обрабатывается примерно за равное время 0о
 */
public class Lemmer implements Runnable{

	private String[] words;
	private int num;

	private Lemmer(String[] str, int i){
		words = str;
		num = i;
	}

	public static String[] lemmer(String str){
		return lemmatization((str.toUpperCase()).split(" "));
	}

	private static String[] lemmatization(String[] str){
		Thread[] threads = new Thread[Common.countThreads];
		for(int i = 0; i < Common.countThreads;i++){
			threads[i] = new Thread(new Lemmer(str, i));
			threads[i].start();
		}
		try{
			for(int i = 0; i < Common.countThreads;i++){
				threads[i].join();
			}
		}catch(InterruptedException e){
			Common.createLog(e);
		}
		return str;
	}

	public void run(){
		int curr = num;
		while(curr < words.length){
			String word = words[curr];
			String work = words[curr];
			words[curr] = "";
			while(!work.equals("")){
				String end = SQLQuery.getEnd(work);
				if(end != null){
					String[] ends = end.split(" ");
					for(int i  = 0; i < ends.length && !work.equals(""); i++){
						String str = SQLQuery.getEnd(Integer.parseInt(ends[i]));
						if(str.contains(word.substring(work.length()))){
							System.out.println(work);
							words[curr] = work + str.substring(1, str.indexOf("%", 1));
							work = "";
						}
					}
				}
				if(work.length() > 0)
					work = work.substring(0, work.length() - 1);
			}
			curr += Common.countThreads;
		}

	}


}
