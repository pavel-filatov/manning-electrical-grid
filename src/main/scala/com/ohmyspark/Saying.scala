package com.ohmyspark

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

// Jackson deserialization
class Saying() {
  private var id: Long = _
  @Length(max = 3) private var content: String = _

  def this(id: Long, content: String) {
    this()
    this.id = id
    this.content = content
  }

  @JsonProperty def getId: Long = id

  @JsonProperty def getContent: String = content
}

