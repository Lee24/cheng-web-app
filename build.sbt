organization  := "com.cheng"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-servlet" % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    //"org.specs2" % "specs2_2.10" % "2.4.15" % "test",
    "org.specs2" % "specs2-core_2.11" % "2.5" % "test",
    "org.eclipse.jetty" % "jetty-webapp" % "9.3.11.v20160721",
    "mysql" % "mysql-connector-java" % "6.0.3",
    "org.slf4j" % "slf4j-api" % "1.7.21"
  )
}

//enablePlugins(JettyPlugin)

jetty(port = 80)
