package com.ohmyspark

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid
import javax.validation.constraints.NotNull

class ElectricalGridConfiguration(
    @Valid @NotNull @JsonProperty("database") database: DataSourceFactory
) extends Configuration {

  @JsonProperty("database")
  def getDataSourceFactory: DataSourceFactory = database
}
