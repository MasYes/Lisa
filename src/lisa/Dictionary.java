package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 18.07.13
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 *
 *CREATE TABLE lisa.dict(id int AUTO_INCREMENT primary key, word char(32) unique, units int, freq int, meas double);
 *
 * НЕ ЗАБЫВАТЬ МЕНЯТЬ Ё НА Е
 */

import java.io.File;
import java.util.*;


public class Dictionary {
	protected static HashMap<String, Term> dict = new HashMap<>();

	private static void addToDictionary(String[] str){
		ArrayList<String> array = new ArrayList<>();
		array.addAll(Arrays.asList(str)); //еще не определился, как лучше - так, или в конатрукторе сразу привести к такому виду.
		HashSet<String> set = new HashSet<>(array);
		set.remove("");
		for(String i : set){
			if(dict.containsKey(i)){
				dict.get(i).incrementUnits();
				dict.get(i).addToFrequency(Collections.frequency(array, i));
			}
			else{
				dict.put(i, new Term(i, Collections.frequency(array, i) ));
			}
		}
	}

	public static void saveDictionary(){
		for(String key: dict.keySet()){
			Term term = dict.get(key);
			term.computeMeasure();
			if(term.getMeasure() < 0.0002)
				SQLQuery.saveIntoDict(term);
		}
	}

	public static void createDict(String path){
		File dir = new File(path);
		File [] files = dir.listFiles();
		for(File i : files){
			try{
				System.gc();
				Scanner file = new Scanner(i);
				String str = "";
				while(file.hasNext()){
					str += file.nextLine() + " ";
				}
				System.out.println(i);
				str = str.replaceAll("-", "");
				str = str.replaceAll("Ё", "Е");
				str = str.replaceAll("ё", "е");
				ArticleCPS art = new ArticleCPS(str);
				str = art.getSense();
				while(str.contains("  ")){
					str = str.replace("  ", " ");
				}
				Dictionary.addToDictionary(Lemmer.lemmer(str));
				file.close();
			} catch (Exception e){
				lisa.Common.createLog(e);
			}
		}
		Dictionary.saveDictionary();
	}
}

