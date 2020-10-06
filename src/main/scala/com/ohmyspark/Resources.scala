package com.ohmyspark

import com.codahale.metrics.annotation.Timed
import com.ohmyspark.kafka.Producer
import javax.ws.rs._
import javax.ws.rs.core.MediaType

object Resources {
  @Path("/events")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  class EventsResource {
    @POST
    @Timed def processMessage(message: String): Unit = {
      Producer.sendMessage(topic = "events", message)
    }
  }

  @Path("/pricing")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  class PricingResource {
    @POST
    @Timed def processMessage(message: String): Unit = {
      Producer.sendMessage(topic = "pricing", message)
    }
  }
}
