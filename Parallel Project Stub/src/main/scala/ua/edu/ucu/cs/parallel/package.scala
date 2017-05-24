package ua.edu.ucu.cs

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask
import java.util.concurrent.ForkJoinWorkerThread

package object parallel {

  import java.util.concurrent._

  // The fork/join framework uses a work-stealing algorithm
  // http://supertech.csail.mit.edu/papers/steal.pdf
  val forkJoinPool = new ForkJoinPool

  def task[T](computation: => T): RecursiveTask[T] = {
    val t = new RecursiveTask[T] {
      def compute = computation
    }

    Thread.currentThread match {
      case wt: ForkJoinWorkerThread =>
        t.fork() // schedule for execution
      case _ =>
        forkJoinPool.execute(t)
    }

    t
  }

  def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
    val right = task { taskB }
    val left = taskA

    (left, right.join())
  }

  def parallel[A, B, C, D](taskA: => A, taskB: => B, taskC: => C, taskD: => D): (A, B, C, D) = {
    val ta = task { taskA }
    val tb = task { taskB }
    val tc = task { taskC }
    val td = taskD
    (ta.join(), tb.join(), tc.join(), td)
  }

}