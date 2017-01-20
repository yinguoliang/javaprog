package com.elva.h2db.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class IndexTest {
    private static String jdbc_url = "jdbc:h2:D:/h2/test";
    static void createTable() throws Exception{
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(jdbc_url,"sa","");
        Statement stm = conn.createStatement();
        //drop table if exists
        stm.executeUpdate("drop table if exists big_table_index");
        //create table
        stm.executeUpdate("create table big_table_index(id int AUTO_INCREMENT,name varchar(20),create_date date)");
        stm.close();
    }
    static void crateIndex() throws Exception{
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(jdbc_url,"sa","");
        Statement stm = conn.createStatement();
        //create table
        stm.executeUpdate("create index idx_big_table_id on big_table_index(id)");
        stm.close();
    
    }
    static void insertData() throws Exception{
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(jdbc_url,"sa","");
        Statement stm = conn.createStatement();
        for(int i=0;i<1000000;i++)
        stm.execute("insert into big_table_index(name,create_date) values ('xxxx"+i+"',CURRENT_DATE())");
        stm.close();
    }
    static void queryData() throws Exception{
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(jdbc_url,"sa","");
        Statement stm = conn.createStatement();
        long startx = System.currentTimeMillis();
        ResultSet countRs = stm.executeQuery("select count(*) from big_table_index");
        while(countRs.next()){
            System.out.println("total count "+countRs.getInt(1));
        }
        System.out.println("cost time :"+(System.currentTimeMillis()-startx));
        countRs.close();
        long start = System.currentTimeMillis();
        ResultSet rs = stm.executeQuery("select * from big_table_index where id =  19999 ");
        while(rs.next()){
            System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getDate(3));
        }
        System.out.println("cost time :"+(System.currentTimeMillis()-start));
        System.out.println("--------------explain----------------");
        rs = stm.executeQuery("explain select * from big_table_index where id between 10 and 100 ");
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        
        rs.close();
        stm.close();
    }
    public static void main(String args[]) throws Exception {
//        createTable();
//        crateIndex();
//        insertData();
        queryData();
    }
}
