package com.ohmyspark

import com.codahale.metrics.annotation.Timed
import com.ohmyspark.Saying
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import java.util.concurrent.atomic.AtomicLong
import java.util.Optional


@Path("/hello-world")
@Produces(Array(MediaType.APPLICATION_JSON))
class HelloWorldResource(val template: String, val defaultName: String) {
  final private val counter: AtomicLong = new AtomicLong

  @GET
  @Timed def sayHello(@QueryParam("name") name: Optional[String]): Saying = {
    val value = String.format(template, name.orElse(defaultName))
    new Saying(counter.incrementAndGet, value)
  }
}
