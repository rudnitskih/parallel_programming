package example

object assignment_1 {
  // it is my first solution, not oprtimal but work :(
  def pascal(c: Int, r: Int): Int = {
    def pascalIt(previousRow: List[Int],
                 renamedRows: Int): Int = {
      if (renamedRows == 0) {
        previousRow(c);
      } else {
        val row = List.tabulate(previousRow.length + 1)(columnNumber => {
          if (columnNumber == 0 || columnNumber == previousRow.length) {
            1
          } else {
            previousRow(columnNumber - 1) + previousRow(columnNumber)
          }
        });

        pascalIt(row, renamedRows - 1);
      }
    }

    pascalIt(List(1, 1), r - 1);
  }                                               //> pascal: (c: Int, r: Int)Int

  pascal(0, 2) == 1                               //> res0: Boolean = true
  pascal(1, 2) == 2                               //> res1: Boolean = true
  pascal(1, 3) == 3                               //> res2: Boolean = true
  pascal(3, 3) == 1                               //> res3: Boolean = true

  // find optimal solution on github https://gist.github.com/ngocdaothanh/3764694
  // and analyzed it
  def printPascalTriangle() {
    println("Pascal's Triangle")
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) 1
      else pascal(c - 1, r - 1) + pascal(c, r - 1)
    }

    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }                                               //> printPascalTriangle: ()Unit

  printPascalTriangle()                           //> Pascal's Triangle
                                                  //| 1 
                                                  //| 1 1 
                                                  //| 1 2 1 
                                                  //| 1 3 3 1 
                                                  //| 1 4 6 4 1 
                                                  //| 1 5 10 10 5 1 
                                                  //| 1 6 15 20 15 6 1 
                                                  //| 1 7 21 35 35 21 7 1 
                                                  //| 1 8 28 56 70 56 28 8 1 
                                                  //| 1 9 36 84 126 126 84 36 9 1 
                                                  //| 1 10 45 120 210 252 210 120 45 10 1 

  // spied in https://gist.github.com/ngocdaothanh/3764694
  def balance(chars: List[Char]): Boolean = {
    def balanceIt(chars: List[Char], numberOpens: Int): Boolean = {
      if (chars.isEmpty) {
        numberOpens == 0
      } else {
        val currentChar = chars.head

        val newNumberOpens = {
          if (currentChar == '(') {
            numberOpens + 1
          } else if (currentChar == ')') {
            numberOpens - 1
          } else {
            numberOpens
          }
        }

        if (newNumberOpens >= 0) balanceIt(chars.tail, newNumberOpens)
        else false
      }
    }

    balanceIt(chars, 0);
  }                                               //> balance: (chars: List[Char])Boolean

  balance(":-)".toList) == false                  //> res4: Boolean = true
  balance("())(".toList) == false                 //> res5: Boolean = true
  balance("ось ще однин ( тут ( та й тут ) i може) (тут) є збалансованими".toList) == true
                                                  //> res6: Boolean = true
  balance("(щось тут ( та й тут ) i може ( ще й тут) є)".toList) == true
                                                  //> res7: Boolean = true

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money > 0 && !coins.isEmpty) {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    } else {
      0
    }
  }                                               //> countChange: (money: Int, coins: List[Int])Int

  countChange(4, List(5, 2, 1)) == 3              //> res8: Boolean = true
  countChange(6, List(1, 2, 5)) == 5              //> res9: Boolean = true
  countChange(8, List(1, 2, 5)) == 7              //> res10: Boolean = true
  countChange(3, List(3)) == 1                    //> res11: Boolean = true
  countChange(6, List(2, 3)) == 2                 //> res12: Boolean = true
  countChange(5, List(3, 4)) == 0                 //> res13: Boolean = true
}