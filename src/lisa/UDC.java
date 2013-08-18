package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: Юлиан
 * Date: 17.08.13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class UDC {
	public String id;
	public String descr;
	public UDC parent;
	public String children;

	@Deprecated
	UDC(int idents, String ids, String descrs, int parents){
	}
}
