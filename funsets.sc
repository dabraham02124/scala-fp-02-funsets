object funsets {
  type Set = Int => Boolean
  
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: funsets.Set, elem: Int)Boolean
  
  def singletonSet(y: Int): Int => Boolean = x => y == x
                                                  //> singletonSet: (y: Int)Int => Boolean
  def union(x: Set, y: Set): Set = elem => x(elem) || y(elem)
                                                  //> union: (x: funsets.Set, y: funsets.Set)funsets.Set
  def intersect(x: Set, y: Set): Set = elem => x(elem) && y(elem)
                                                  //> intersect: (x: funsets.Set, y: funsets.Set)funsets.Set
  def diff(x: Set, y: Set): Set = elem => x(elem) && ! y(elem)
                                                  //> diff: (x: funsets.Set, y: funsets.Set)funsets.Set
                                                  
  def filter(s: Set, p: Int => Boolean): Set = elem => p(elem) && s(elem)
                                                  //> filter: (s: funsets.Set, p: Int => Boolean)funsets.Set
  
  
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def inforall(s: Set, p: Int => Boolean, i: Int): Boolean =
      if (i > 1000) true
      else if (contains(s, i)) p(i) && inforall(s,p,i+1)
      else inforall(s,p,i+1)
  
    inforall(s,p,-1000)
  }                                               //> forall: (s: funsets.Set, p: Int => Boolean)Boolean
  
  def exists(s: Set, p: Int => Boolean): Boolean = forall(p, s)
                                                  //> exists: (s: funsets.Set, p: Int => Boolean)Boolean
  
  def map(s: Set, f: Int => Int): Set = elem => exists(s, x => f(x) == elem)
                                                  //> map: (s: funsets.Set, f: Int => Int)funsets.Set

  map(singletonSet(5), x => x*2)                  //> res0: funsets.Set = <function1>
  assert(contains(map(singletonSet(5), x => x*2), 5) == false)
                                                  //> java.lang.AssertionError: assertion failed
                                                  //| 	at scala.Predef$.assert(Predef.scala:156)
                                                  //| 	at funsets$$anonfun$main$1.apply$mcV$sp(funsets.scala:28)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at funsets$.main(funsets.scala:2)
                                                  //| 	at funsets.main(funsets.scala)
  contains(map(union(singletonSet(5), singletonSet(3)), x => x*2), 5)
  contains(map(union(singletonSet(5), singletonSet(3)), x => x*2), 6)
  contains(map(singletonSet(5), x => x*2), 6)
  contains(map(singletonSet(5), x => x*2), 10)

  forall(singletonSet(6), x => x == 5)
  
  contains(singletonSet(5),6)
  contains(singletonSet(5),5)
  contains(union(singletonSet(5),singletonSet(6)),5)
  contains(union(singletonSet(5),singletonSet(6)),6)
  contains(union(singletonSet(5),singletonSet(6)),7)


  contains(intersect(singletonSet(5),singletonSet(6)),5)
  contains(intersect(singletonSet(5),singletonSet(6)),6)
  contains(intersect(singletonSet(5),singletonSet(6)),7)
  contains(intersect(singletonSet(5),singletonSet(5)),5)


  contains(diff(singletonSet(5),singletonSet(6)),5)
  contains(diff(singletonSet(5),singletonSet(6)),6)
  contains(diff(singletonSet(5),singletonSet(6)),7)

}