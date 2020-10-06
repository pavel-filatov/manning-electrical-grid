package com.ohmyspark.kafka

import java.util.Properties

object Utils {

  implicit class PropertiesSyntax(m: Map[String, String]) {
     def toProperties: Properties =
      m.foldLeft(new Properties()) {
        case (p, (key, value)) =>
          p.put(key, value)
          p
      }
  }

}
