package daovo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import ui.Alert;
import ui.Professor_Main;

public class ProfessorDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@112.154.60.127:1521/xe";
	String user = "c##user";
	String password = "oma0731";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ProfessorVo pro(String id_txt) {
		try {
			connDB();

			String query = "SELECT * FROM PROFESSOR";
			query += " where id = '" + id_txt + "'";
			System.out.println(query);

			rs = stmt.executeQuery(query);
			rs.last();

			System.out.println(rs.getRow());

			if (rs.getRow() == 0) {
				// Empty
				System.out.println(":: Not Found ::");
			} else {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String college = rs.getString("college");
				String major = rs.getString("major");
				int enroll = rs.getInt("enroll");

				ProfessorVo data = new ProfessorVo(id, name, college, major, enroll);
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ProfessorVo();
	}

	public boolean enroll_pro(String name, String college, String major, int enroll) {
		try {
			String id = "";
			connDB();

			String query = "select * from professor where major = '" + major + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("inquiry SQL : " + query);
			System.out.println(major + "'s professor : " + rs.getRow());

			id = new EtcDao().returnMajor(major);

			if ((rs.getRow() + 1) < 10) {
				id += "0" + (rs.getRow() + 1);
			} else if (rs.getRow() < 100) {
				id += (rs.getRow() + 1);
			} else {
				new Alert("전공당 99명까지 입력이 가능합니다.");
				return false;
			}

			query = "insert into professor values('" + id + "', '" + name + "', '" + college + "', '" + major + "',"
					+ enroll + ")";

			rs = stmt.executeQuery(query);
			System.out.println("Insert Into Professor SQL : " + query);

			query = "insert into member values('" + id + "', '" + name + "', '교수', '1234')";

			rs = stmt.executeQuery(query);
			System.out.println("Insert Into Member SQL : " + query);

		} catch (Exception e) {
			new Alert("해당 교번의 교수가 이미 존재합니다.");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Object[][] allProfessor() {
		try {
			connDB();

			String query = "select * from professor order by id";
			System.out.println("\nSQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			int n = rs.getRow();
			int i = 0;

			Object[][] object = new Object[n][4];

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				object[i][0] = rs.getString("id");
				object[i][1] = rs.getString("name");
				object[i][2] = rs.getString("college");
				object[i][3] = rs.getString("major");
				i++;
			}

			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[][] temp = {};
		return temp;
	}

	public void enterLecture(ProfessorVo vo, String name, String com, String credit, String room, String limit,
			String current) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		String semester = "";

		// 1월 == 0 / 12월 == 11
		if (month < 3) {
			// 1, 2, 3
			// 1학기 과목 등록기간
			semester = "1";
		} else if (month >= 3 && month < 6) {
			// 4, 5, 6
			// 여름계절 과목 등록기간
			semester = "여름계절";
		} else if (month >= 6 && month <= 8) {
			// 7, 8, 9
			// 2학기 과목 등록기간
			semester = "2";
		} else {
			// 10, 11, 12
			// 겨울계절 과목 등록기간
			semester = "겨울계절";
		}

		if (semester.length() == 0) {
			// null
			System.out.println("semester is null");
			return;
		}

		if (limit == null) {
			limit = "";
		}

		try {
			connDB();

			// 학수번호 작업

			// 1. 교수의 전공과 major 테이블의 전공이 일치하는 코드를 찾기
			// 교수의 전공
			String major = vo.getMajor();
			String query = "select * from major where major = '" + major + "'";
			System.out.println("SQL for finding major : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			// 학수번호 코드
			String code = rs.getString("code");
			System.out.println("Major Code : " + code);

			// 2. 과목이름과 코드가 같으면 동일 과목이 이미 존재한다는 오류문 반환 및 return
			// + year and semester
			query = "select * from lecture where major = '" + major + "' and name = '" + name + "' and year = '" + year
					+ "' and semester = '" + semester + "'";
			System.out.println("SQL for checking duplicate : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() != 0) {
				new Alert("이미 존재하는 과목입니다.");
				return;
			}

			// 3. 순서대로 번호부여 000~999
			query = "select * from lecture where major = '" + major + "'";
			System.out.println("SQL for find all major lecture : " + query);
			rs = stmt.executeQuery(query);
			rs.last();

			if (rs.getRow() + 1 < 10) {
				code += "00" + (rs.getRow() + 1);
			} else if (rs.getRow() + 1 < 100) {
				code += "0" + (rs.getRow() + 1);
			} else if (rs.getRow() + 1 < 1000) {
				code += (rs.getRow() + 1);
			} else {
				// out of range
				System.out.println("out of range");
				new Alert("더 이상 과목을 추가할 수 없습니다.");
				return;
			}
			
			System.out.println("final code : " + code);
			
			query = "insert into lecture values ( '" + code + "', '" + com + "', '"+ name +"', "+credit+", '" + room +"', "+year +", '" + semester + "', '" + vo.getCollege() + "', '" + major +"', '" + vo.getName() +"', '"+ limit + "', " + current +")";
			System.out.println("SQL for insert Lecture : " +query);
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		new Professor_Main(vo);
		new Alert("등록하였습니다.");

	}

	public Object[][] inquiryLecture(String pro_name) {
		try {
			connDB();
			
			String query = "select * from lecture where professor = '" + pro_name + "'";
			System.out.println("SQL for get lectures : " + query);
			
			rs = stmt.executeQuery(query);
			
			query = "select * from register where";
			
			while(rs.next()) {
				query += " lec = '" + rs.getString("id") +"' or";
			}
			
			query = query.substring(0, query.length() - 2);
			System.out.println("SQL for get all student : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			
			int n = rs.getRow();
			int i = 0;
			
			Object[][] object = new Object[n][4];
			rs = stmt.executeQuery(query);
			ResultSet rs2;
			
			String query2 = "select ";
			
			while(rs.last()) {
				//// 테이블수정
				// register에 과목명이랑 학생명 같이 넣기
				// 다른 register 접속 다오도 같이 수정하기
				object[i][0] = rs.getString("lec");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Object[][] temp = {};
		return temp;
	}
	
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
