import org.scalatest._

class DictionarySpec extends FunSpec with Matchers {

  describe("build") {
    it("creates dictionary consisting of combinations of word pairs - mapped to following word") {
      val dictionary = dictionaryFor("I wish I may I wish I might")
      dictionary should be (Map(
        ("wish", "I") -> Set("may", "might"), 
        ("I", "might") -> Set.empty, 
        ("I", "may") -> Set("I"),
        ("may", "I") -> Set("wish"),
        ("I", "wish") -> Set("I")))
    }

    it("contains all unique consecutive word pair combinations") {
      val dictionary = dictionaryFor("I have a red car")
      dictionary should have size 4
    }
  }

  def dictionaryFor(sentence: String) = {
    val words = sentence.split(" ").toStream
    new Dictionary(words).build
  }

}