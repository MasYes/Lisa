package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 14.07.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 * в некоторых функциях можно ретернить не из самого результсет, а сначала сохранить результат в стринги,
 * а потом вызвать rs.close(), что несколько снижает затраты оперативной (вроде).
 * Засунуть все коннекты в "трай витх ресорс"
 *
 * CREATE TABLE lisa.articles(id int primary key auto_increment, author text, title text, vector long binary, UDC text, Template text, link text,
 mark int, language text, info text, publication text);
 *
 */

import java.sql.*;
import java.io.*;



public class SQLQuery {
	private static Connection conn;
	private static String user = "masyes";
	private static String password = "Snoop";
	private static String url = "localhost";
	private static String port = "3306";
	private static String DB = "lisa";
	private static boolean connected = false;

	private static void connect() throws SQLException {

			conn = DriverManager.getConnection(
					"jdbc:mysql://" + url + ":" + port + "/" + DB,
					user, password);
			if (conn == null) {
				System.out.println("Нет соединения с БД!");
			}
			else connected = true;
	}

	protected static void disconnect() throws SQLException {
		//На самом деле я не хотел делать эту функцию, но
		//если иногда не закрывать соединение, то об]ём используемой
		//оперативки приводит к аутофмемори
		conn.close();
		connected = false;
	}

	private SQLQuery(){}


	private static Object deserialize(byte[] array) {
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(array);
			ObjectInputStream objInputStream = new ObjectInputStream(is);
			return objInputStream.readObject(); //Если класс каст эксепшн, то, скорее всего, класс был изменен, а посему восстановить его нет возможности.
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static byte[] serialize(Object obj) { // ПЕРЕНЕСТИ СЕРИАЛИЗАЦИЮ СЮДА!
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try{
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			return os.toByteArray();
		} catch(IOException e){
			Common.createLog(e);
			return null;
		}
	}

	protected static String getEnd(String word){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM words WHERE word=\'" + word + "\'");
			rs.next();
			return rs.getString("ends");
		} catch (SQLException e){
			//Common.createLog(e); и это забивает тоже ><
			return null;
		}
	}

	protected static String getEnd(int id){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ends WHERE id=" + id + ";");
			rs.next();
			return rs.getString("ends");
		} catch (SQLException e){
			//Common.createLog(e); Не загоняем в соммон крейт лог, поскольку они засоряют весь лог файл ><
			return "";
		}
	}

	protected static int createDump(String id, Object obj){
		if(id.length() > 16)
			return -1;
		try{
			if(!connected)
				connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO dumps (id, ser) VALUES (?, ?)");
			ps.setString(1, id);
			ps.setString(2, arrayToString(serialize(obj)));
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e){Common.createLog(e);}
		return 0;
	}

	protected static Object readDump (String id){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ser FROM dumps WHERE id=\'" + id + "\'");
			rs.next();
			return deserialize(stringToArray(rs.getString("ser")));
		} catch (SQLException e){
			Common.createLog(e);
			return null;
		}
	}

	protected static void saveIntoDict(String word, int units, int freq, double meas){
		try{
			if(!connected)
				connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO dict (word, units, freq, meas) VALUES (?, ?, ?, ?)");
			ps.setString(1, word);
			ps.setInt(2, units);
			ps.setInt(3, freq);
			ps.setDouble(4, meas);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Common.createLog(e);
		}
	}

	public static void saveArticle(ArticleAbstract article){
		try{
			if(!connected)
				connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO lisa.articles " +
					"(author, title, vector, UDC, Template," +
					"link, mark, language, info,  publication) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, article.getAuthor());
			ps.setString(2, article.getTitle());
			ps.setString(3, arrayToString(serialize(article.getVector())));
			ps.setString(4, article.getUDC());
			ps.setString(5, article.getTemplate().toString());
			ps.setString(6, article.getLink());
			ps.setInt(7, article.getMark());
			ps.setString(8, article.getLanguage().toString());
			ps.setString(9, article.getInfo());
			ps.setString(10, article.getPublication());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Common.createLog(e);
		}
	}




	protected static void updateWord(Term term){
		try{
			if(!connected)
				connect();
			PreparedStatement ps = conn.prepareStatement("UPDATE lisa.dict SET freq=?, units=?, meas=? WHERE word=?;");
			ps.setInt(1, term.frequency);
			ps.setInt(2, term.units);
			ps.setDouble(3, term.measure);
			ps.setString(4, term.word);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Common.createLog(e);
		}


	}

	public static int getMaxID(){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM lisa.dict");
			rs.next();
			return rs.getInt("MAX(id)");
		} catch (SQLException e){
			Common.createLog(e);
			return -1;
		}
	}

	public static Term getWordData(int i){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lisa.dict WHERE id=" + i);
			rs.next();
			return  new Term(rs.getString("word"), rs.getInt("freq"), rs.getInt("units"), rs.getDouble("meas"));
		} catch (SQLException e){
			Common.createLog(e);
			return null;
		}
	}

	@Deprecated
	protected static ArticleAbstract getArticleData(int i){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lisa.dict WHERE id=" + i);
			rs.next();
			return  new ArticleAbstract();
		} catch (SQLException e){
			Common.createLog(e);
			return null;
		}
	}


	public static Vector getArticleVector(int id){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT vector FROM lisa.articles WHERE id=" + id);
			rs.next();
			return (Vector) deserialize(stringToArray(rs.getString("vector")));
		} catch (SQLException e){
			Common.createLog(e);
			return null;
		}
	}

	public static String getArticleTitle(int id){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT title FROM lisa.articles WHERE id=" + id);
			rs.next();
			return rs.getString("title");
		} catch (SQLException e){
			Common.createLog(e);
			return null;
		}
	}

	public static int getIdWord(String word){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lisa.dict WHERE word=\"" + word + "\"");

			rs.next();
			return  rs.getInt("id");
		} catch (SQLException e){
			//Common.createLog(e); // Если будет забивать логи - убить :D upd убил :D
			return -1;
		}
	}


	protected static int getCountOfArticles(){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM lisa.articles");
			rs.next();
			return rs.getInt("MAX(id)");
		} catch (SQLException e){
			Common.createLog(e);
			return -1;
		}
	}

	private static String arrayToString(byte[] array){
		String res = "\'";
		for(int i = 0; i < array.length; i++){
			if(array[i] <= 15 && array[i]>=0){
				res += "0" + Integer.toHexString(array[i] & 0xFF);
			} else {
				res +=Integer.toHexString(array[i] & 0xFF);
			}
		}
		res += '\'';
		return res;
	}



	private static byte[] stringToArray(String str){
		str = str.substring(1, str.length() - 1);
		byte[] res = new byte[str.length()/2];
		for(int i = 0; i < str.length(); i = i + 2){
			res[i/2] = (byte) (Integer.parseInt("" + str.charAt(i) + str.charAt(i + 1), 16) & 0xFF);
		}
		return res;
	}
}