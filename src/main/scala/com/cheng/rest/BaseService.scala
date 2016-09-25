package com.cheng.rest

import akka.actor.Actor
import com.cheng.Home.{HomeService, UploadFile}
import org.json4s.DefaultFormats
import spray.httpx.Json4sSupport
import spray.routing._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class BaseServiceActor extends Actor with BaseService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)

  override implicit def json4sFormats = DefaultFormats
}


// this trait defines our service behavior independently from the service actor
trait BaseService extends HttpService with Json4sSupport {

  val myRoute =
    path("uploadfile") {
      post {
        entity(as[UploadFile]) {
          para => detach() {
            complete {
              (new HomeService).process(para)
            }
          }
        }
      }
    }
}
