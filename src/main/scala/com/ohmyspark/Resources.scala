package com.ohmyspark

import com.codahale.metrics.annotation.Timed
import com.ohmyspark.kafka.GenericProducer
import javax.ws.rs._
import javax.ws.rs.core.MediaType
import org.apache.kafka.clients.producer.ProducerRecord

object Resources {
  @Path("/events/{event-id}")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  class EventsResource {
    val producer: GenericProducer[String, String] = GenericProducer[String, String]

    @POST
    @Timed def processMessage(@PathParam("event-id") eventId: String, message: String): Unit = {
      producer.sendMessage(topic = "events", key = eventId, message = message)
    }
  }

  @Path("/pricing")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  class PricingResource {
    val producer: GenericProducer[Nothing, String] = GenericProducer[Nothing, String]

    @POST
    @Timed def processMessage(message: String): Unit = {
      val record = new ProducerRecord[Nothing, String]("pricing", message)
      producer.producer.send(record)
    }
  }
}
