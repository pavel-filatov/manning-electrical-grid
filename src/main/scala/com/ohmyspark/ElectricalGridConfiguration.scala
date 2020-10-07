package com.ohmyspark

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class ElectricalGridConfiguration(
    @NotEmpty template: String,
    @NotEmpty defaultName: String = "Stranger"
) extends Configuration {
  @JsonProperty def getTemplate: String = template

  @JsonProperty def getDefaultName: String = defaultName
}
