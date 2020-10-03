package com.ohmyspark

import io.dropwizard.Application
import io.dropwizard.setup.{Bootstrap, Environment}

object HelloWorldApplication extends Application[HelloWorldConfiguration] {
  def main(args: Array[String]): Unit = {
    run("server", "config.yaml")
  }


  override def getName = "hello-world"

  override def initialize(bootstrap: Bootstrap[HelloWorldConfiguration]): Unit = {
    // nothing to do yet
  }

  def run(configuration: HelloWorldConfiguration, environment: Environment): Unit = {
    val resource = new HelloWorldResource(configuration.getTemplate, configuration.getDefaultName)
    environment.jersey.register(resource)
  }
}

