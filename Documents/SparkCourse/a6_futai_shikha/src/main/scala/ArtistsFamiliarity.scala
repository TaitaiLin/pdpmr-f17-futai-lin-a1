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
    val pairFamiliarArtist = familiarArtist.aggregateByKey((0.0, 0))((acc, value) => (acc._1 + value, acc._2 + 1),
      (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    pairFamiliarArtist.collect()

    for (x <- pairFamiliarArtist) {
      val key = x._1
      val count = x._2
      println(s"$key: $count")
    }
  }
}
