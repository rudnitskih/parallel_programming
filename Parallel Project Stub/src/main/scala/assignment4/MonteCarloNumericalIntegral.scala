package assignment4

import scala.util.Random

import org.scalameter._
import ua.edu.ucu.cs.parallel._

object MonteCarloNumericalIntegral {

  def getRandomBeetwen(start: Double, end: Double): Double = start + (new Random().nextDouble() * (end - start));

  def integral(func: Double => Double, start: Double, end: Double, numberPoints: Int): Double =
    (end - start) / numberPoints * sumValues(func, start, end, numberPoints)

  def sumValues(func: Double => Double, start: Double, end: Double, numberPoints: Int): Double = {
    def it(currentSquare: Double, pointsGenerated: Int): Double = {
      if (pointsGenerated < numberPoints) {
        it(
          currentSquare + func(getRandomBeetwen(start, end)),
          pointsGenerated + 1)
      } else {
        currentSquare
      }
    }

    it(0, 0)
  }

  def integralParTwo(func: Double => Double, start: Double, end: Double, numberPoints: Int): Double = {
    val (res1, res2) = parallel(
      integral(func, start, end, numberPoints / 2),
      integral(func, start, end, numberPoints / 2))

    (res1 + res2) / 2
  }

  def integralParFour(func: Double => Double, start: Double, end: Double, numberPoints: Int): Double = {
    val (res1, res2, res3, res4) = parallel(
      integral(func, start, end, numberPoints / 4),
      integral(func, start, end, numberPoints / 4),
      integral(func, start, end, numberPoints / 4),
      integral(func, start, end, numberPoints / 4))

    (res1 + res2 + res3 + res4) / 4
  }

  def integralParEight(func: Double => Double, start: Double, end: Double, numberPoints: Int): Double = {
    val ((res1, res2, res3, res4), (res5, res6, res7, res8)) = parallel(
      parallel(
        integral(func, start, end, numberPoints / 8),
        integral(func, start, end, numberPoints / 8),
        integral(func, start, end, numberPoints / 8),
        integral(func, start, end, numberPoints / 8)),
      parallel(
        integral(func, start, end, numberPoints / 8),
        integral(func, start, end, numberPoints / 8),
        integral(func, start, end, numberPoints / 8),
        integral(func, start, end, numberPoints / 8)))

    (res1 + res2 + res3 + res4 + res5 + res6 + res7 + res8) / 8
  }

  //  def findBestParalellFactor(maxThreads: Int): collection.mutable.Map[Int, Double] = {
  //    val totalNumberOfPoints = 100000
  //    val start = 0
  //    val end = 1
  //    
  //    val standardConfig = config(
  //      Key.exec.minWarmupRuns -> 10,
  //      Key.exec.maxWarmupRuns -> 100,
  //      Key.exec.benchRuns -> 50,
  //      Key.verbose -> true) withWarmer (new Warmer.Default)
  //      
  //    var currentFactor = 1
  //
  //    val results = collection.mutable.Map[Int, Double]()
  //
  //    val seqtime = standardConfig measure {
  //      integral(func, start, end, totalNumberOfPoints)
  //    }
  //    
  //    while (currentFactor <= maxThreads) {
  //      
  //    }
  //
  //    results
  //  }

  def main(args: Array[String]): Unit = {
    val totalNumberOfPoints = 100000
    val start = 0
    val end = 1

    def func(x: Double): Double = Math.cos(x)

    val standardConfig = config(
      Key.exec.minWarmupRuns -> 10,
      Key.exec.maxWarmupRuns -> 100,
      Key.exec.benchRuns -> 50,
      Key.verbose -> true) withWarmer (new Warmer.Default)

    val seqtime = standardConfig measure {
      integral(func, start, end, totalNumberOfPoints)
    }

    val partimeTwo = standardConfig measure {
      integralParTwo(func, start, end, totalNumberOfPoints)
    }

    val partimeFour = standardConfig measure {
      integralParFour(func, start, end, totalNumberOfPoints)
    }

    val partimeEight = standardConfig measure {
      integralParEight(func, start, end, totalNumberOfPoints)
    }

    println(s"sequntial time $seqtime")
    println(s"parallel two time $partimeTwo")
    println(s"parallel four time $partimeFour")
    println(s"parallel eight time $partimeEight")

    println(s"speedup ${seqtime.value / partimeTwo.value}")
    println(s"speedup ${seqtime.value / partimeFour.value}")
    println(s"speedup ${seqtime.value / partimeEight.value}")

    val seqResult = integral(func, start, end, totalNumberOfPoints)
    val parResult = integralParFour(func, start, end, totalNumberOfPoints)

    println(s"seqResult $seqResult")
    println(s"parResult $parResult")
  }
}