package com.ohmyspark

import java.util.Properties

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid
import javax.validation.constraints.NotNull

class ElectricalGridConfiguration(
    @Valid @NotNull @JsonProperty("database") database: DataSourceFactory,
    @Valid @NotNull @JsonProperty("kafka") kafka: Properties
) extends Configuration {
  def getDataSourceFactory: DataSourceFactory = database
  def getKafkaConfig: Properties = kafka
}
