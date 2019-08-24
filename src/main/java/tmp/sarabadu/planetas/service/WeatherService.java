package tmp.sarabadu.planetas.service;

import org.springframework.stereotype.Service;

import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.SolarSystem;

@Service
public class WeatherService {

	public WeatherEnum getWeather(SolarSystem solarSystem) {
		if (sunAndPlanetsAligned(solarSystem)) {
			return WeatherEnum.SEQUIA;
		}
		
		return WeatherEnum.NORMAL;
	}

	private boolean sunAndPlanetsAligned(SolarSystem solarSystem) {
		
		return solarSystem.getSun().isCollinear(solarSystem.getPlanets());
	}

}
