package com.ohmyspark.kafka

import java.util.Properties

import com.ohmyspark.kafka.Utils.PropertiesSyntax
import org.apache.kafka.clients.producer.{
  KafkaProducer,
  Producer,
  ProducerRecord
}

object GenericProducer {
  val props: Properties = Map(
    "bootstrap.servers" -> "localhost:29092",
    "schema.registry.url" -> "http://localhost:8090",
    "acks" -> "1",
    "retries" -> "3",
    "key.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
    "value.serializer" -> "io.confluent.kafka.serializers.KafkaAvroSerializer",
    "compression.type" -> "snappy"
  ).toProperties

  implicit def apply[A, B]: GenericProducer[A, B] = new GenericProducer[A, B] {
    override val producer: Producer[A, B] = new KafkaProducer[A, B](props)
  }
}

trait GenericProducer[A, B] {
  val producer: Producer[A, B]

  def sendMessage(topic: String, key: A, message: B): Unit = {
    val record = new ProducerRecord[A, B](topic, key, message)
    producer.send(record)
  }
}
