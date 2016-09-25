package com.cheng.Home

import java.io.{File, FileOutputStream}
/**
  * Created by 10183507 on 2016/9/25.
  */
case class UploadFile(name: String, data: String)

class HomeService {
  val fileDir = "/data/uploadfiles"
  def process(file: UploadFile) = {
    try{
      val f = new File(s"$fileDir/${file.name}")
      val out = new FileOutputStream(f)
      out.write(file.data.getBytes())
      out.close()
      "success"
    }catch{
      case _: Throwable => "fail"
    }

  }
}
