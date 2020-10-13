package com.ohmyspark.dao

import com.ohmyspark.parsing.EventParser.EventRepr
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery

@RegisterRowMapper(classOf[EventMapper])
trait EventDAO {

  @SqlQuery("""
    SELECT device_id,
    charging_source,
    charging,
    current_capacity,
    processor1_temp,
    processor2_temp,
    processor3_temp,
    processor4_temp,
    inverter_state,
    "moduleL_temp",
    "moduleR_temp",
    "SoC_regulator"
    FROM events
    WHERE device_id = :deviceId
  """)
  def selectEvents(@Bind("deviceId") deviceID: String): Array[EventRepr]

}
