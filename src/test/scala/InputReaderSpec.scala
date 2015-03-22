import org.scalatest._

trait MockRandomInt extends RandomInt {
  override def random(max: Int) = 0
}
class InputReaderT(input: String) extends InputReader(input) with MockRandomInt {}

class InputReaderSpec extends FunSpec with Matchers {
  
  describe("randomText") {
    it("gets the first sentence from file with non random") {

      val reader = new InputReaderT("article.txt")
      reader.randomText should be (" estate agents say Melbourne's property market has turned, with the eastern suburbs now experiencing boom sales conditions")
    }
  }
}