package com.ohmyspark.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{
  KafkaProducer,
  Producer,
  ProducerRecord
}

object GenericProducer {
  def apply[A, B](props: Properties): GenericProducer[A, B] = new GenericProducer[A, B] {
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
