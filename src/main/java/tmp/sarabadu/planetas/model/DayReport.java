package tmp.sarabadu.planetas.model;

import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import tmp.sarabadu.planetas.enums.WeatherEnum;

@Data
@Builder
//@Entity
public class DayReport {

	@Id
	private long day;

	private WeatherEnum weather;
	
	private SpaceObject ferengi;
	private SpaceObject betasoide;
	private SpaceObject vulcano;
	private Double rainIndex;
	
	
	
	
}
