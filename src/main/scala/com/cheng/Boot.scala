package com.cheng

import javax.swing.text.html.HTMLDocument

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRefFactory, ActorSystem, Props}
import com.cheng.rest.BaseServiceActor
import org.w3c.dom.html.HTMLElement
import spray.routing.HttpService
import spray.servlet.WebBoot
import spray.http.MediaTypes._


// this class is instantiated by the servlet initializer
// it needs to have a default constructor and implement
// the spray.servlet.WebBoot trait
class Boot extends WebBoot {

  // we need an ActorSystem to host our application in
  val system = ActorSystem("cheng-lee-app")

  // the service actor replies to incoming HttpRequests
  val BaseService = system.actorOf(Props[BaseServiceActor])

  class GeneralActor extends Actor with HttpService {
    def actorRefFactory: ActorRefFactory = context

    override def receive: Receive = runRoute(
      path("") {
        //        get {
        //          respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
        //            complete {
        getFromResource("home.html")
        //            }
        //          }
        //        }
      } ~
        path("test") {
          get {
            respondWithMediaType(`text/html`) {
              // XML is marshalled to `text/xml` by default, so we simply override here
              complete {
                "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <title>Cheng</title>\n    <link href=\"lib/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n    <link href=\"css/common-style.css\" rel=\"stylesheet\">\n</head>\n<body class=\"bodyBackground\">\n<div class=\"container\" style=\"margin-top:10px\">\n    <div class=\"panelBackground\">\n        <nav class=\"navbar navbar-default\" role=\"navigation\">\n            <div class=\"container-fluid\">\n                <div class=\"navbar-header\">\n                    <a id=\"upload\" class=\"navbar-brand\" href=\"#\">\n                        <span>文件上传</span>\n                    </a>\n                    <input type=\"file\" name=\"files\" id=\"fileupload_input\" style=\"display: none\"/>\n                </div>\n            </div>\n        </nav>\n    </div>\n</div>\n</body>\n<script src=\"lib/jquery/jQuery 3.1.1.min.js\" type=\"text/javascript\"></script>\n<script src=\"lib/bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n<script src=\"lib/jQuery-File-Upload/js/jquery.fileupload-jquery-ui.js\" type=\"text/javascript\"></script>\n<script src=\"lib/jQuery-File-Upload/js/jquery.iframe-transport.js\" type=\"text/javascript\"></script>\n<script src=\"lib/jQuery-File-Upload/js/jquery.fileupload.js\" type=\"text/javascript\"></script>\n<script src=\"js/home/home.js\" type=\"text/javascript\"></script>\n\n</html>"
                //            }
              }
            }
          }
        } ~
        pathPrefix("base") {
          BaseService ! _
        }
    )
  }

  val serviceActor = system.actorOf(Props(new GeneralActor))

}
