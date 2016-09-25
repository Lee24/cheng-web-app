package com.cheng.common

import java.sql.{DriverManager, ResultSet}

/**
  * Created by lee on 16/9/19.
  */
object TestApp extends App {
  println("lo")
  Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
  val conn = DriverManager.getConnection("jdbc:mysql://chenglee.picp.io:3306/mysql?user=root&password=Cheng@1220")
  val statement = conn.createStatement()
  val result: ResultSet = statement.executeQuery("select * from user")

  while(result.next()){
    println("hello")
    val userName = result.getString("user")
    println(userName)
  }
//  println(result)
//  new Hello().hello()
}

class Hello {
  def hello() = {
    println("hello")
  }
}
