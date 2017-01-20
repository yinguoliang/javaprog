package com.elva.derby.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DerbyBasicTest {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    static String jdbc_url = "jdbc:derby:D:/embeddedDB/derby/firstDB;create=true";
    static void createDB() throws Exception{
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(jdbc_url);
        Statement stmt = conn.createStatement();
        stmt.execute("create table test(id int,name varchar(20),create_date date)");
        stmt.close();
    }
    
    static void insertData() throws Exception{
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(jdbc_url);
        Statement stmt = conn.createStatement();
        for(int i=0;i<100000;i++){
            stmt.executeUpdate(String.format("insert into test(id,name,create_date) values(%s,'%s',CURRENT_DATE)",
                    i+"",i+"ZZZZ"));
        }
        stmt.close();
    
    }
    
    static void queryDB() throws Exception{
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(jdbc_url);
        Statement stmt = conn.createStatement();
        long start1 = System.currentTimeMillis();
        ResultSet rs = stmt.executeQuery("select count(*) from test");
        while(rs.next()){
            System.out.println("totla count "+rs.getString(1));
        }
        System.out.println("cost "+(System.currentTimeMillis()-start1));
        start1 = System.currentTimeMillis();
        ResultSet rs2 = stmt.executeQuery("select * from test where id = 12323");
        while(rs2.next()){
            System.out.println(rs2.getString(1)+","+rs2.getString(2)+","+rs2.getDate(3));
        }
        System.out.println("cost "+(System.currentTimeMillis()-start1));
        stmt.close();
    }
    public static void main(String args[]) throws Exception {
//        insertData();
        queryDB();
    }
}
