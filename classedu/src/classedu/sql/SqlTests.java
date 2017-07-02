package classedu.sql;

import org.junit.Test;

public class SqlTests {
	
//	@Test
//	public void sqlTest(){
//		Sql sql = new Sql();
//		String status = sql.login("133037", "13307");
//		System.out.println(status);
//	}
	
	@Test
	public void test1(){
		String status = test();
		String a = "success";
		String b = new String("success");
		System.out.println(status);
		System.out.println("success" == status);
		System.out.println("success" == a);
		System.out.println("success" == b);
	}
	
	public String test (){
		return "success";
	}
	
	public static void addOne(StringBuffer x, StringBuffer y) {
		x.append("00");
		y.append("00");
	}
	
	public static void main(String[] args) {
		StringBuffer x1 = new StringBuffer("10");
		StringBuffer y1 = new StringBuffer("20");
		System.out.println(x1+"**"+y1);
		addOne(x1,y1);
		System.out.println(x1+"**"+y1);
	}

}
