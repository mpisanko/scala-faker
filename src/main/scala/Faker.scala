class Faker(input: Stream[String]) {
  val words = input
  if (words.size < 3) throw new IllegalStateException("Not enough words to make a sentence.")
  val MaxLength = 50
  lazy val dictionary = new Dictionary(words).build
  
  def fake = {
    val start = randomPair
    start._1 #:: start._2 #:: nextWord(MaxLength, start)
  }

  private def nextWord(remaining: Int, pair: Tuple2[String, String]): Stream[String] = {
    dictionary.get(pair) match {
      case Some(found) => {  
        if(remaining == 0 || found.isEmpty) {
          Stream.empty
        } else {
          val word = randomWord(found)
          Stream.cons(word, nextWord(remaining - 1, (pair._2, word)))  
        }
      }
      case None => Stream.empty
    }
  }

  private def randomWord(words: Set[String]) = {
    val wordsSeq = words.toSeq
    wordsSeq(util.Random.nextInt(wordsSeq.size))
  }

  @annotation.tailrec
  private def randomPair: Tuple2[String, String] = {
    val pair = words.drop(util.Random.nextInt(words.size)).take(2)
    pair.length match {
      case 2 => (pair.head, pair.tail.head)
      case _ => randomPair
    }
  }

}