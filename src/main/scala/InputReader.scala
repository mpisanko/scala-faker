class InputReader(input: String) extends RandomInt {
  lazy val file = new java.io.File(input)

  def randomText = {
    io.Source.fromFile(file).drop(randomOffset).toStream.dropWhile(notSpace).takeWhile(notEndOfSentence).mkString
  }

  private def randomOffset() = {
    random(file.length.toInt)
  }

  private def notEndOfSentence(c: Char) = {
    c != '.' && c != '!' && c !='?'
  }

  private def notSpace(c: Char) = {
    c != ' '
  }
}