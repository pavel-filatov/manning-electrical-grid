package com.ohmyspark

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class HelloWorldConfiguration extends Configuration {
  @NotEmpty private var template: String = _
  @NotEmpty private var defaultName: String = "Stranger"

  @JsonProperty def getTemplate: String = template

  @JsonProperty def setTemplate(template: String): Unit = {
    this.template = template
  }

  @JsonProperty def getDefaultName: String = defaultName

  @JsonProperty def setDefaultName(name: String): Unit = {
    this.defaultName = name
  }
}

