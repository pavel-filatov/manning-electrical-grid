package com.ohmyspark

import com.codahale.metrics.annotation.Timed
import com.ohmyspark.energy.avro.Event
import com.ohmyspark.kafka.GenericProducer
import javax.ws.rs._
import javax.ws.rs.core.MediaType
import org.apache.kafka.clients.producer.ProducerRecord

object Resources {
  @Path("/events/{device-id}")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  class EventsResource {
    val producer: GenericProducer[String, Event] = GenericProducer[String, Event]

    @POST
    @Timed def processMessage(@PathParam("device-id") deviceId: String, jsons: String): Unit = {
      jsons.split("\n") foreach { json =>
        val event = parsing.EventParser.parseEvent(json).map(_.toEvent)
        event match {
          case Right(e) =>
            producer.sendMessage(topic = "events", key = deviceId, message = e)
          case _ =>
            println(s"Cannot parse event: $json")
        }
      }
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
