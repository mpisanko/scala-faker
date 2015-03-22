trait RandomInt {

  def random(max: Int) = {
    util.Random.nextInt(max)
  }
}