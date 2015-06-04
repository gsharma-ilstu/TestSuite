package isu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.http.entity.mime.content.InputStreamBody;

import oracle.jdbc.OracleDriver;

public class DBUtil {

	
	
	Statement stmt = null;
    ResultSet rs = null;
    PropertiesUtil propUtil;
    InputStream input;
	public ResultSet getUserNameSet(String sql) throws IOException{

		 try {
			    
			    propUtil = new PropertiesUtil();
				input= propUtil.getPropertyFile("database.properties");
		        DriverManager.registerDriver(new OracleDriver());
		        //Connection conn = DriverManager.getConnection(propUtil.getPropertyValue("URL", input),propUtil.getPropertyValue("USER", input),propUtil.getPropertyValue("PASS", input));
		        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//atoraiam01-t.at.illinoisstate.edu:1521/iamtest_svc","svc_account_self_svc","noXmas4U");
		        stmt = conn.createStatement();
		        rs = stmt.executeQuery(sql);
		        if(rs.equals(null))
		        {
		        	throw new SQLException("Empty Result set");
		        }
		        else{
	            return rs;
		        }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
		
	
		
	}
	
	
}
