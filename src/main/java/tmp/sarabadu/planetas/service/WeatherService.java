package tmp.sarabadu.planetas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.Planet;
import tmp.sarabadu.planetas.model.SolarSystem;

@Service
public class WeatherService {

	public WeatherEnum getWeather(SolarSystem solarSystem) {
		if (sunAndPlanetsAligned(solarSystem)) {
			return WeatherEnum.SEQUIA;
		}
		
		if (planetsAligned(solarSystem)) {
			return WeatherEnum.OPTIMO;
		}
		
		if (sunInsidePlanetsTriangle(solarSystem)) {
			return WeatherEnum.LLUVIA;
		}
		
		return WeatherEnum.NORMAL;
	}

	private boolean sunInsidePlanetsTriangle(SolarSystem solarSystem) {
		
		return solarSystem.getSun().isInsideArea(solarSystem.getPlanets().get(0),solarSystem.getPlanets().get(1),solarSystem.getPlanets().get(2));
	}

	private boolean planetsAligned(SolarSystem solarSystem) {
		Planet head = solarSystem.getPlanets().get(0);
		List<Planet> planetas =solarSystem.getPlanets().subList(1, solarSystem.getPlanets().size());
		
		return head.isCollinear(planetas);
	}

	private boolean sunAndPlanetsAligned(SolarSystem solarSystem) {
		
		return solarSystem.getSun().isCollinear(solarSystem.getPlanets());
	}

}
