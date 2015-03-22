import org.scalatest._

class InputParserSpec extends FunSpec with Matchers {
  describe("words") {
    it("returns words of the sentence") {
      val words = new InputParser(" some unfinished, sentence, ").words
      words should be (Array("some", "unfinished", "sentence").toStream)
    }

    it("returns all the words without punctuation") {
      val parser = new InputParser("some sentence which is semi-long, doesn't have much sense, ")
      val words = parser.words
      words should have size 10
      words.foreach((word: String) => parser.NonWord.r.unanchored.findFirstIn(word) shouldBe empty )
    }
  }
}