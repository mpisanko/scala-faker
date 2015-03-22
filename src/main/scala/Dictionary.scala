class Dictionary(input: Stream[String]) {
  val words = input

  def build = {
    //  as we build triples of words - we have to offset them by one twice - to get all combinations
    val emptyDictionary = Map[Tuple2[String, String], Set[String]]()
    (0 to 2).foldLeft(emptyDictionary)(createPartial(words, _, _))
  }

  private def createPartial(words: Stream[String], initial: Map[Tuple2[String, String], Set[String]], offset: Int) = {
    words.drop(offset).grouped(3).toStream.foldLeft(initial)(dictionaryReducer)
  }

  private def dictionaryReducer(dictionary: Map[Tuple2[String, String], Set[String]], triplet: Stream[String]) = {
    triplet.length match {
      case 3 => {
        val pair = (triplet.head, triplet.tail.head)
        dictionary + (pair -> (dictionary.getOrElse(pair, Set.empty) + triplet.last))
      }
      case 2 => {
        val pair = (triplet.head, triplet.tail.head)
        dictionary + (pair -> dictionary.getOrElse(pair, Set.empty)) 
      }
      case 1 => dictionary
    }
  }
}