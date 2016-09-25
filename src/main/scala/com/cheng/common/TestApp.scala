package com.cheng.common

import java.sql.{DriverManager, ResultSet}

/**
  * Created by lee on 16/9/19.
  */

case class SqlResult(name: List[String], value: List[List[String]])

object TestApp extends App {
  println("lo")
  Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
  val conn = DriverManager.getConnection("jdbc:mysql://chenglee.picp.io:3306/mysql?user=root&password=Cheng@1220")
  val statement = conn.createStatement()
  val result: ResultSet = statement.executeQuery("select * from user")

  val columnCount = result.getMetaData.getColumnCount

  val name = (1 to columnCount).map(result.getMetaData.getColumnName).toList

  var value: List[List[String]] = List()

  while (result.next) {
    val oneLine = (1 to columnCount).map(result.getString).toList
    value = oneLine :: value
  }

  println(name)
  println(value)

}

class Hello {
  def hello() = {
    println("hello")
  }
}
