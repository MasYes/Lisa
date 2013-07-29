package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 24.07.13
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class Term {

	public String word;
	protected int units;
	protected int frequency;
	protected double measure;
	protected Term (String str, int freq){
		word = str;
		units = 1;
		frequency = freq;
		measure = 1;
	}
	protected Term(String str){
		word = str;
		units = 0;
		frequency = 0;
		measure = 1;
	}
	protected Term(){
		word = "";
		units = 0;
		frequency = 0;
		measure = 1;
	}
	protected Term(int freq){
		word = "";
		units = 1;
		frequency = freq;
		measure = 1;
	}
	protected Term (String str, int fr, int un, double ms){
		word = str;
		units =un;
		frequency = fr;
		measure = ms;
	}
}



