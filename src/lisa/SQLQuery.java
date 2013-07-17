package lisa;

/**
 * Created with IntelliJ IDEA.
 * User: masyes
 * Date: 14.07.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
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

	public static void connect() throws SQLException {

			conn = DriverManager.getConnection(
					"jdbc:mysql://" + url + ":" + port + "/" + DB,
					user, password);
			if (conn == null) {
				System.out.println("Нет соединения с БД!");
			}
			connected = true;

	}

	private SQLQuery(){}


	private static Object deserialize(byte[] array) {
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(array);
			ObjectInputStream objInputStream = new ObjectInputStream(is);
			return objInputStream.readObject();
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
			Common.createLog(e);
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
			Common.createLog(e);
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
		} catch(SQLException e){Common.createLog(e);;}
		return 0;
	}

	public static Object readDump (String id){
		try{
			if(!connected)
				connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ser FROM dumps WHERE id=\'" + id + "\'");
			rs.next();
//			System.out.println(rs.getString("ser"));
			return deserialize(stringToArray(rs.getString("ser")));
		} catch (SQLException e){
			Common.createLog(e);
			return null;
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