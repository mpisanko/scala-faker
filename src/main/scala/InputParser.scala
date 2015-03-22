class InputParser(input: String) {
  val NonWord = "[^a-zA-Z']"
  val sentence = input

  def words = {
    sentence.split(NonWord).filter(nonEmpty).toStream
  }

  private val nonEmpty = (word: String) => {
    word.trim.length > 0
  }

}