package com.ohmyspark.dao

import java.sql.ResultSet

import com.ohmyspark.parsing.EventParser.EventRepr
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement

class EventMapper extends RowMapper[EventRepr] {
  def map(r: ResultSet, ctx: statement.StatementContext): EventRepr = {
    EventRepr(
      device_id = r.getString("device_id"),
      charging_source = r.getString("charging_source"),
      charging = r.getInt("charging"),
      current_capacity = r.getInt("current_capacity"),
      processor1_temp = r.getInt("processor1_temp"),
      processor2_temp = r.getInt("processor2_temp"),
      processor3_temp = r.getInt("processor3_temp"),
      processor4_temp = r.getInt("processor4_temp"),
      inverter_state = r.getInt("inverter_state"),
      moduleL_temp = r.getInt("moduleL_temp"),
      moduleR_temp = r.getInt("moduleR_temp"),
      SoC_regulator = r.getDouble("SoC_regulator")
    )
  }
}
