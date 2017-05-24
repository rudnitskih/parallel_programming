package assignment4

import scala.util.Random

import org.scalameter._
import ua.edu.ucu.cs.parallel._

object MonteCarloPiEstimation {

  def pi(totalNumberOfPoints: Int): Double = 4.0 * countPointsInsideCircle(totalNumberOfPoints) / totalNumberOfPoints

  def countPointsInsideCircle(totalNumberOfPoints: Int): Int = {
    var rndX = new Random
    var rndY = new Random

    def simulate(hits: Int, pointsGenerated: Int): Int =
      if (pointsGenerated >= totalNumberOfPoints)
        hits
      else {
        val x = rndX.nextDouble
        val y = rndY.nextDouble

        simulate(hits + (if (x * x + y * y <= 1) 1 else 0), pointsGenerated + 1)
      }

    simulate(0, 0)
  }

  def piPar(totalNumberOfPoints: Int) = {
    val ((pi1, pi2), (p3, p4)) = parallel(parallel(countPointsInsideCircle(totalNumberOfPoints / 4), countPointsInsideCircle(totalNumberOfPoints / 4)),
      parallel(countPointsInsideCircle(totalNumberOfPoints / 4), countPointsInsideCircle(totalNumberOfPoints / 4)))

    val piRes = 4 * (pi1 + pi2 + p3 + p4) / totalNumberOfPoints
    println(piRes)
    piRes
  }

  def main(args: Array[String]): Unit = {
    val totalNumberOfPoints = 100000

    val standardConfig = config(
      Key.exec.minWarmupRuns -> 100,
      Key.exec.maxWarmupRuns -> 200,
      Key.exec.benchRuns -> 100,
      Key.verbose -> true) withWarmer (new Warmer.Default)

    val seqtime = standardConfig measure {
      pi(totalNumberOfPoints)
    }

    val partime = standardConfig measure {
      piPar(totalNumberOfPoints)
    }

    println(s"sequntial time $seqtime ms")

    println(s"parallel time $partime ms")
    println(s"speedup ${seqtime.value / partime.value}")
  }

}