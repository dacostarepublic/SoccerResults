import scala.io.Source
import scala.util.matching.Regex
import scala.collection.mutable

object SoccerResults extends App {
  // if (args.length < 1) {
  //   println("Please provide a filename as a command line argument.")
  //   System.exit(1)
  // }

  // val filename = args(0)
  val filename = "../../results.txt"
  val teamPoints = mutable.Map[String, Int]().withDefaultValue(0)
  val pattern = new Regex("(.*) (\\d+), (.*) (\\d+)")

  for (line <- Source.fromFile(filename).getLines()) {
    pattern.findFirstMatchIn(line).foreach { m =>
      val team1 = m.group(1)
      val score1 = m.group(2).toInt
      val team2 = m.group(3)
      val score2 = m.group(4).toInt

      if (score1 > score2) {
        teamPoints(team1) += 3
        teamPoints(team2) += 0
      } else if (score1 < score2) {
        teamPoints(team1) += 0
        teamPoints(team2) += 3
      } else {
        teamPoints(team1) += 1
        teamPoints(team2) += 1
      }
    }
  }

  val sortedTeams = teamPoints.toSeq.sortBy { case (team, points) => (-points, team) }

  for (((team, points), index) <- sortedTeams.zipWithIndex) {
    println(s"${index + 1}. $team, $points pts")
  }
}