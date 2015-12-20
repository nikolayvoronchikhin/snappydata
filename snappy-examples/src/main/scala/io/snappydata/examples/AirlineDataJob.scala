package io.snappydata.examples

import com.typesafe.config.Config
import spark.jobserver.{SparkJobValid, SparkJobValidation}

import org.apache.spark.sql.{DataFrame, SnappySQLJob}


/**
  * Created by swati on 2/12/15.
  */
object AirlineDataJob extends SnappySQLJob {

  override def runJob(snc: C, jobConfig: Config): Any = {

    val colTableName = "airline"
    val rowTableName = "airlineref"

    // Get the tables that were created using sql scripts via snappy-shell
    val airlineDF: DataFrame = snc.table(colTableName)

    // Row table
    val airlineCodeDF : DataFrame = snc.table(rowTableName)


    // Schema for column table
    val result1 = airlineDF.schema

    // Column table entry count
    val result2 = airlineDF.count

    // Schema for Row table
    val result3 = airlineCodeDF.schema

    // Row table entry count
    val result4 = airlineCodeDF.count

    Map("Airline table schema" -> result1,
      "Airline table count" -> result2,
      "AirlineRef table schema" -> result3,
      "AirlineRef count" -> result4)

  }

  override def validate(sc: C, config: Config): SparkJobValidation = {
    SparkJobValid
  }
}