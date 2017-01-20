package com.elva.h2db.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2BasicMain {
    private static String jdbc_url = "jdbc:h2:D:/h2/test";
    public static void main(String args[]) throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(jdbc_url,"sa","");
        Statement stm = conn.createStatement();
        //drop table if exists
        stm.executeUpdate("drop table if exists test");
        //create table
        stm.executeUpdate("create table test(id int AUTO_INCREMENT,name varchar(20),create_date date)");
        //insert data
        stm.execute("insert into test(name,create_date) values ('xxxx',CURRENT_DATE())");
        stm.execute("insert into test(name,create_date) values ('yyyy',CURRENT_DATE())");
        stm.execute("insert into test(name,create_date) values ('zzzz',CURRENT_DATE())");
        //query data
        ResultSet rs = stm.executeQuery("select * from test");
        while(rs.next()){
            System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getDate(3));
        }
        rs.close();
        stm.close();
    }
}
