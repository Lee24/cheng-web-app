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

    override  def receive: Receive = runRoute (
      path("") {
//        get {
//          respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
//            complete {
              getFromResource("home.html")
//            }
//          }
//        }
      }~
      pathPrefix("base") {
        BaseService ! _
      }
    )
  }

  val serviceActor = system.actorOf(Props(new GeneralActor))

}
