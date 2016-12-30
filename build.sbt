import scala.io

name := "pyspark-cassandra"

version := io.Source.fromFile("version.txt").mkString.trim

organization := "TargetHolding"

scalaVersion := "2.11.8"

credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

licenses += "Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")

libraryDependencies ++= Seq(
  "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.0-M3"
)

spName := "TargetHolding/pyspark-cassandra"

sparkVersion := "2.0.0"

sparkComponents ++= Seq("streaming", "sql")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions ++= Seq("-Xmax-classfile-name", "73")


assemblyOption in assembly := (assemblyOption in assembly).value.copy(
	includeScala = false
)

assemblyMergeStrategy in assembly <<= (assemblyMergeStrategy in assembly) {
  (old) => {
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case PathList("META-INF", xs @ _*) => MergeStrategy.last
	case x => MergeStrategy.last
  }
}

EclipseKeys.withSource := true
