package com.ohmyspark.kafka

import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.common.serialization.{Serde, Serdes}
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import org.apache.kafka.streams.kstream.{Consumed, KStream, Produced}
import org.apache.log4j.{Level, Logger}

object Consumer extends App {


  val APP_NAME = "yelling_app_id"

  val logger = Logger.getLogger(APP_NAME)

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, APP_NAME)
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    p
  }

  val stringSerde: Serde[String] = Serdes.String()

  val builder = new StreamsBuilder

  val simpleFirstStream: KStream[String, String] =
    builder.stream("src-topic", Consumed.`with`(stringSerde, stringSerde))
  val upperCasedStream: KStream[String, String] = simpleFirstStream.mapValues(_.toUpperCase)

  upperCasedStream.to("out-stream", Produced.`with`(stringSerde, stringSerde))

  val streams : KafkaStreams = new KafkaStreams(builder.build(), props)

  logger.info(s"Starting Kafka Streams application: $APP_NAME")
  streams.start()
  Thread.sleep(35000)
  logger.info(s"Shutting down application: $APP_NAME")
  streams.close()

}
