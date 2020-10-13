package com.ohmyspark.parsing

import com.ohmyspark.energy.avro.Event
import io.circe.generic.auto._
import io.circe.parser._

import scala.beans.BeanProperty

object EventParser {

  def parseEvent(json: String): Either[io.circe.Error, EventRepr] =
    decode[EventRepr](json)

  case class EventRepr(
      @BeanProperty val device_id: String,
      @BeanProperty val charging_source: String,
      @BeanProperty val charging: Int,
      @BeanProperty val current_capacity: Int,
      @BeanProperty val processor1_temp: Int,
      @BeanProperty val processor2_temp: Int,
      @BeanProperty val processor3_temp: Int,
      @BeanProperty val processor4_temp: Int,
      @BeanProperty val inverter_state: Int,
      @BeanProperty val moduleL_temp: Int,
      @BeanProperty val moduleR_temp: Int,
      @BeanProperty val SoC_regulator: Double
  ) {
    def toEvent: Event =
      Event
        .newBuilder()
        .setDeviceId(device_id)
        .setChargingSource(charging_source)
        .setCharging(charging)
        .setCurrentCapacity(current_capacity)
        .setProcessor1Temp(processor1_temp)
        .setProcessor2Temp(processor2_temp)
        .setProcessor3Temp(processor3_temp)
        .setProcessor4Temp(processor4_temp)
        .setInverterState(inverter_state)
        .setModuleLTemp(moduleL_temp)
        .setModuleRTemp(moduleR_temp)
        .setSoCRegulator(SoC_regulator)
        .build()
  }
}
