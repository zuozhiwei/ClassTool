package classedu.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sql {
	Connection conn = null;

	public void connect() {
		String url = "jdbc:mysql://localhost:3306/classedu?"
				+ "user=root&password=zuozuo&useUnicode=true&characterEncoding=UTF8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String login(String userid, String password) {
		connect();
		String status = "no";
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from user where userid = '" + userid + "'";
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				sql = "select password from user where userid ='" + userid + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString(1).equals(password)) {
						status = "success";
					} else {
						status = "passworderror";
					}
				}
			} else {
				status = "useriderror";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}

		return status;
	}

	public String signin(String userid) {
		connect();
		String status = "no";
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(date);
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from signin where userid = '" + userid + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				sql = "insert into signin(userid,time) values('" + userid + "','" + now + "')";
				int result = stmt.executeUpdate(sql);
				if (result != -1) {
					status = "success";
				} else {
					status = "error";
				}
			} else {
				sql = "update signin set time='" + now + "' where userid='" + userid + "'";
				int result = stmt.executeUpdate(sql);
				if (result != -1) {
					status = "success";
				} else {
					status = "error";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}

	public Map<String, String> getQuestion(String id) {
		connect();
		Map<String, String> question = new HashMap<String, String>();
		question.put("status", "ok");
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from question where id = " + id + "";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				question.put("question", rs.getString("question"));
				question.put("answera", rs.getString("answera"));
				question.put("answerb", rs.getString("answerb"));
				question.put("answerc", rs.getString("answerc"));
				question.put("answerd", rs.getString("answerd"));
				question.put("answer", rs.getString("answer"));
			} else {
				question.put("status", "no");
			}
		} catch (Exception e) {
			e.printStackTrace();
			question.put("status", "no");
		}
		return question;
	}

	public String setMessage(String userid, String message) {
		connect();
		String status = "no";
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(date);
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into message(userid,message,addtime) values('" + userid + "','" + message + "','"
					+ now + "')";
			int result = stmt.executeUpdate(sql);
			if (result != -1) {
				status = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public String setAnswerStatus(String userid, String status) {
		connect();
		String result = "no";
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into answerstatus(userid,status) values('" + userid + "','" + status + "')";
			int rs = stmt.executeUpdate(sql);
			if (rs != -1) {
				result = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, String>> getSignin(String targetClass) {
		connect();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select b.name,b.userclass,a.userid,b.grade,b.major,b.userid,a.time from classedu.user b  "
					+ "left outer join classedu.signin a  on a.userid = b.userid " + "where b.courseid ='" + targetClass
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, String> rowData = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String param = rs.getString(i);
					if (null == (rs.getObject(i))) {
						param = "未签到";
					}
					rowData.put(md.getColumnName(i), param);
				}
				list.add(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, String>> getQuestionList() {
		connect();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from question";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, String> rowData = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String param = rs.getString(i);
					rowData.put(md.getColumnName(i), param);
				}
				list.add(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, String>> getMessageList(String targetClass) {
		connect();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select t1.*,t2.name from message t1 , user t2 where t1.userid=t2.userid and t2.courseid='"
					+ targetClass + "'";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, String> rowData = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String param = rs.getString(i);
					if (null == (rs.getObject(i))) {
						param = "*";
					}
					rowData.put(md.getColumnName(i), param);
				}
				list.add(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String addQuestion(String question, String answera, String answerb, String answerc, String answerd,
			String answer) {
		System.out.println("sql" + question + answer);
		connect();
		String status = "no";
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into question(question,answera,answerb,answerc,answerd,answer) values('" + question
					+ "','" + answera + "','" + answerb + "','" + answerc + "','" + answerd + "','" + answer + "')";
			int result = stmt.executeUpdate(sql);
			if (result != -1) {
				status = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public String teacherLogin(String userid, String password) {
		connect();
		String status = "no";
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from teacher where userid = '" + userid + "'";
			ResultSet result = stmt.executeQuery(sql);
			
			if (result.next()) {
				sql = "select password from teacher where userid ='" + userid + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString(1).equals(password)) {
						status = "success";
					} else {
						status = "passworderror";
					}
				}
			} else {
				status = "useriderror";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}

		return status;
	}
}