/**
  * Created by futailin on 10/23/17.
  */

import org.apache.spark.{SparkConf, SparkContext}

object NumDistinct {

  def getIndex(line: String): Map[String, Int] = {
    val index = Map("artist_id" -> 0, "song_id" -> 0, "release" -> 0)
    if (line.startsWith("track_id")) {
      val words = line.split(';')
      val indexArtistId = words.indexOf("artist_id")
      val indexSongId = words.indexOf("song_id")
      val indexAlbum = words.indexOf("release")
      val index = Map("artist_id" -> indexArtistId, "song_id" -> indexSongId, "release" -> indexAlbum)
      return index
    }
    return index
  }

  def parseAlbum(line: String) = {
    val iterators = line.split(';')
    val artistId = iterators(16)
    //val songId = iterators(23)
    val AlbumName = iterators(22)
    (artistId, AlbumName)
  }

  def parseArtist(line: String) = {
    val iterators = line.split(';')
    val artistId = iterators(16)
    //val songId = iterators(23)
    artistId
  }

  def parseSong(line: String) = {
    val iterators = line.split(';')
    val songId = iterators(23)
    songId
  }


  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local").setAppName("NumDistinct")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("MillionSongSubset/song_info.csv")

    val rdd1 =lines.map(parseAlbum)
    val distinctNumAlbum = rdd1.map(x => (x, 1)).reduceByKey((x, y) => x +y)

    val rdd2 = lines.map(parseSong)
    val distinctNumSong = rdd2.map(x => (x, 1)).reduceByKey((x, y) => x +y)

    val rdd3 = lines.map(parseArtist)
    val distinctNumArtist = rdd3.map(x => (x, 1)).reduceByKey((x, y) => x +y)

    for (x <- distinctNumSong) {
      val key = x._1
      val count = x._2
      println(s"$key: $count")
    }
  }
}
