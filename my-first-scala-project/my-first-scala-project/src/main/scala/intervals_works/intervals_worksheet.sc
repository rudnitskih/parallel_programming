package intervals_works

object intervals_worksheet {
  new Interval(1, 0.5)                            //> java.lang.IllegalArgumentException: requirement failed: Left should be less t
                                                  //| han right
                                                  //| 	at scala.Predef$.require(Predef.scala:224)
                                                  //| 	at intervals_works.Interval.<init>(interval.scala:4)
                                                  //| 	at intervals_works.intervals_worksheet$$anonfun$main$1.apply$mcV$sp(inte
                                                  //| rvals_works.intervals_worksheet.scala:4)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at intervals_works.intervals_worksheet$.main(intervals_works.intervals_w
                                                  //| orksheet.scala:3)
                                                  //| 	at intervals_works.intervals_worksheet.main(intervals_works.intervals_wo
                                                  //| rksheet.scala)
}