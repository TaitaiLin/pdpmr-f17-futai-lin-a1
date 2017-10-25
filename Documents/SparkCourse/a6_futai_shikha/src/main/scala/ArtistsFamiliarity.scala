import LoudestFastestSong.parseLine
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by futailin on 10/25/17.
  */
object ArtistsFamiliarity {
  def parseLine(line: String) = {
    val token = line.split(";")
    val artistId = token(16)
    val familarity = token(19)
    (artistId, familarity)
  }

  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local").setAppName("ArtistsFamiliarity")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("MillionSongSubset/song_info.csv")

    val filterLines = lines.filter(x => !x.startsWith("track_id"))
    val parsedLines = filterLines.map(parseLine)
    val familiarArtist = parsedLines.map(x => (x._1, x._2.toDouble))
    val meanFamiliarityByArtist = familiarArtist.mapValues(x => (x, 1))
      .reduceByKey((x, y) => (x._1 + y._1, x._2 +y._2)).mapValues(y => 1.0 * y._1 / y._2)

    for (x <- meanFamiliarityByArtist) {
      val key = x._1
      val count = x._2
      println(s"$key: $count")
    }
  }
}
