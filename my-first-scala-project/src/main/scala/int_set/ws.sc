package int_set

object ws {
  val set = NonEmpty(7, Empty, Empty)             //> set  : int_set.NonEmpty = {.7.}
  
  val newSet = set include 5 include 12           //> newSet  : int_set.IntSet = {{.5.}7{.12.}}
  
  newSet.height                                   //> res0: Int = 2
  newSet.size                                     //> res1: Int = 3
  
  set.height                                      //> res2: Int = 1
  set.size                                        //> res3: Int = 1
  
  val includedSet = newSet union NonEmpty(6, Empty, Empty)
                                                  //> includedSet  : int_set.IntSet = {{.5{.6.}}7{.12.}}
  includedSet                                     //> res4: int_set.IntSet = {{.5{.6.}}7{.12.}}
  includedSet.toString() == "{{.5{.6.}}7{.12.}}"  //> res5: Boolean = true
  
  val set3 = Empty.include(7).include(5).include(12).include(9).include(15)
                                                  //> set3  : int_set.IntSet = {{.5.}7{{.9.}12{.15.}}}
  
  val set4 = set3 map {x => if (x > 7) -x else x} //> set4  : int_set.IntSet = {{{{.-15.}-12{.-9.}}5.}7.}
  set4.toString() == "{{{{.-15.}-12{.-9.}}5.}7.}" //> res6: Boolean = true
  
  set4.isBalanced == false                        //> res7: Boolean = true
  set3.isBalanced == true                         //> res8: Boolean = true
}