package com.ohmyspark

import io.dropwizard.Application
import io.dropwizard.setup.{Bootstrap, Environment}

object ElectricalGridWebApp extends Application[ElectricalGridConfiguration] {
  def main(args: Array[String]): Unit = {
    run(args: _*)
  }


  override def getName = "hello-world"

  override def initialize(bootstrap: Bootstrap[ElectricalGridConfiguration]): Unit = {
    // nothing to do yet
  }

  def run(configuration: ElectricalGridConfiguration, environment: Environment): Unit = {
    environment.jersey.register(new Resources.EventsResource)
    environment.jersey.register(new Resources.PricingResource)
  }
}

