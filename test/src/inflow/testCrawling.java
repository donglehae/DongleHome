package inflow;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class testCrawling {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		while(true) {
			SimpleDateFormat format = new SimpleDateFormat ("HH:mm");
			Date time = new Date();
			String curtime = format.format(time);
			
			String kospi_url = "https://finance.naver.com/sise/sise_index.nhn?code=KOSPI";
	        Document kospi_doc;
			kospi_doc = Jsoup.connect(kospi_url).get();
			Elements kospi_element = kospi_doc.select("em#now_value");
			String kospi_quote = kospi_element.text();
	        
	        String kosdaq_url = "https://finance.naver.com/sise/sise_index.nhn?code=KOSDAQ";
	        Document kosdaq_doc = Jsoup.connect(kosdaq_url).get();
	        Elements kosdaq_element = kosdaq_doc.select("em#now_value");
			String kosdaq_quote = kosdaq_element.text();
	        
	        String fut_url = "https://finance.naver.com/sise/sise_index.nhn?code=FUT";
	        Document fut_doc = Jsoup.connect(fut_url).get();
	        Elements fut_element = fut_doc.select("td#now_value");
	        String fut_quote = fut_element.text();
	       
	        System.out.println(curtime + " " + kospi_quote);
	        System.out.println(curtime + " " + kosdaq_quote);
	        System.out.println(curtime + " " + fut_quote);	 
	        
	        try {
				Class.forName("org.mariadb.jdbc.Driver");
				Connection con = DriverManager.getConnection( "jdbc:mariadb://127.0.0.1:3306/financedb", "donghae", "787849");
				if(con != null) { System.out.println("DB 접속 성공"); }
				PreparedStatement kospi_pstmt = con.prepareStatement("INSERT INTO kospi(time, quote) VALUES(?, ?)");
				PreparedStatement kosdaq_pstmt = con.prepareStatement("INSERT INTO kosdaq(time, quote) VALUES(?, ?)");
				PreparedStatement fut_pstmt = con.prepareStatement("INSERT INTO fut(time, quote) VALUES(?, ?)");
				kospi_pstmt.setNString(1, curtime);
				kospi_pstmt.setNString(2, kospi_quote);
				kosdaq_pstmt.setNString(1, curtime);
				kosdaq_pstmt.setNString(2, kosdaq_quote);
				fut_pstmt.setNString(1, curtime);
				fut_pstmt.setNString(2, fut_quote);
				kospi_pstmt.executeLargeUpdate();
				kosdaq_pstmt.executeLargeUpdate();
				fut_pstmt.executeLargeUpdate();
			} catch (ClassNotFoundException e) { 
				System.out.println("드라이버 로드 실패"); 
			} catch (SQLException e) {
				System.out.println("DB 접속 실패");
	            e.printStackTrace();
			}
	        Thread.sleep(60000);
		}
	}

}
