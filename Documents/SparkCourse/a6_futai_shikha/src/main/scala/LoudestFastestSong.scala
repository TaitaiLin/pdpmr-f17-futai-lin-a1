import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by futailin on 10/25/17.
  */
object LoudestFastestSong {
  def parseLine(line: String) = {
    val token = line.split(";")
    val songId = token(23)
    val loudness = token(6)
    val tempo = token(7)
    (songId, loudness, tempo)
  }
  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local").setAppName("LoudestFastestSong")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("MillionSongSubset/song_info.csv")

    val filterLines = lines.filter(x => !x.startsWith("track_id"))
    val parsedLines =filterLines.map(parseLine)
    val loudness = parsedLines.map(x => (x._1, x._2))
    val sortLoudness = loudness.map(x => (x._2.toDouble, x._1)).sortByKey(ascending = false)
    val fiveLoudness = sortLoudness.take(5)

    val tempo = parsedLines.map(x => (x._1, x._3))
    val sortFastSongs = tempo.map(x => (x._2.toDouble, x._1)).sortByKey(ascending = false)
    val fiveFastestSongs = sortFastSongs.take(5)

    for (x <- fiveFastestSongs) {
      val key = x._2
      val count = x._1
      println(s"$key: $count")
    }
  }
}
