package com.ohmyspark

import com.ohmyspark.dao.EventDAO
import io.dropwizard.Application
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.{Bootstrap, Environment}
import org.jdbi.v3.core.Jdbi

object ElectricalGridWebApp extends Application[ElectricalGridConfiguration] {
  def main(args: Array[String]): Unit = {
    run(args: _*)
  }

  override def getName = "electrical-grid-app"

  override def initialize(
      bootstrap: Bootstrap[ElectricalGridConfiguration]
  ): Unit = {
    // nothing to do yet
  }

  def run(
      configuration: ElectricalGridConfiguration,
      environment: Environment
  ): Unit = {
    val factory: JdbiFactory = new JdbiFactory()
    val jdbi: Jdbi = factory.build(
      environment,
      configuration.getDataSourceFactory,
      "postgresql"
    )
    val dao: EventDAO = jdbi.onDemand(classOf[EventDAO])

    val props = configuration.getKafkaConfig

    environment.jersey.register(new Resources.EventsResource(dao, props))
    environment.jersey.register(new Resources.PricingResource(props))
  }
}
