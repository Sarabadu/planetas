package tmp.sarabadu.planetas.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import tmp.sarabadu.planetas.enums.WeatherEnum;

@Data
public class TotalReport {
	
	private Map<WeatherEnum ,List<List<DayReport>>> periods;

	
	private Long maxLluviaDay;
	private Double  maxLluviaIndex;

	public TotalReport(WeatherEnum[] weatherEnums) {
		super();
		this.periods = new EnumMap<>(WeatherEnum.class);
		
		
		for (WeatherEnum weatherEnum : weatherEnums) {
			this.periods.put(weatherEnum, new ArrayList<>());
		}
	}

}

