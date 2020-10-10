package com.ohmyspark.parsing

import com.ohmyspark.energy.avro.Event
import io.circe.generic.auto._
import io.circe.parser._

object EventParser {

  def parseEvent(json: String): Either[io.circe.Error, EventObject] =
    decode[EventObject](json)

  case class EventObject(
      charging_source: String,
      processor4_temp: Int,
      device_id: String,
      processor2_temp: Int,
      processor1_temp: Int,
      charging: Int,
      current_capacity: Int,
      inverter_state: Int,
      moduleL_temp: Int,
      moduleR_temp: Int,
      processor3_temp: Int,
      SoC_regulator: Double
  ) {
    def toEvent: Event =
      Event
        .newBuilder()
        .setChargingSource(charging_source)
        .setProcessor4Temp(processor4_temp)
        .setDeviceId(device_id)
        .setProcessor2Temp(processor2_temp)
        .setProcessor1Temp(processor1_temp)
        .setCharging(charging)
        .setCurrentCapacity(current_capacity)
        .setInverterState(inverter_state)
        .setModuleLTemp(moduleL_temp)
        .setModuleRTemp(moduleR_temp)
        .setProcessor3Temp(processor3_temp)
        .setSoCRegulator(SoC_regulator)
        .build()
  }

}
