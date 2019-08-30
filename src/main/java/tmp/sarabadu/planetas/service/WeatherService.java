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
	
	public Double getLluviaIndex(SolarSystem solarSystem) {
		if (getWeather(solarSystem).equals(WeatherEnum.LLUVIA)) {
			Planet p1 = solarSystem.getPlanets().get(0);
			Planet p2 = solarSystem.getPlanets().get(1);
			Planet p3 = solarSystem.getPlanets().get(2);
			
			return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
		}
		return 0.;

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
