package tmp.sarabadu.planetas.service;

import org.springframework.stereotype.Service;

import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.SolarSystem;

@Service
public class WeatherService {

	public WeatherEnum getWeather(SolarSystem ssys) {
		// TODO Auto-generated method stub
		return WeatherEnum.SEQUIA;
	}

}
