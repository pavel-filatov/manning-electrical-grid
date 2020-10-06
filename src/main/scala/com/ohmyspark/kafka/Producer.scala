package com.ohmyspark.kafka

import java.util.Properties

import com.ohmyspark.kafka.Utils.PropertiesSyntax
import org.apache.kafka.clients.producer.{
  KafkaProducer,
  Producer,
  ProducerRecord
}

object Producer {
  val props: Properties = Map(
    "bootstrap.servers" -> "localhost:29092",
    "key.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
    "acks" -> "1",
    "retries" -> "3",
    "key.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
    "value.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
    "compression.type" -> "snappy"
  ).toProperties

  lazy val producer: Producer[Nothing, String] = new KafkaProducer(props)

  def sendMessage(topic: String, message: String): Unit = {
    val record = new ProducerRecord(topic, message)
    producer.send(record)
  }
}
