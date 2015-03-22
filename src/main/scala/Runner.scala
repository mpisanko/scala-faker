object Runner {
  def main(args: Array[String]) = {
    val lines = new InputReader(args.head).randomText
    val words = new InputParser(lines).words
    val faker = new Faker(words)
    println("Fake sentence -> " + faker.fake.mkString(" "))
  }
}