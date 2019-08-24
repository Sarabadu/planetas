package tmp.sarabadu.planetas.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.Planet;
import tmp.sarabadu.planetas.model.SolarSystem;
import tmp.sarabadu.planetas.model.Star;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {

	@Autowired
	WeatherService serv;
	
	@Test
	public void test() {
		
		
		Star sun = new Star(0,0);
		
		Planet planet1 = new Planet(sun,0,500);
		Planet planet2 = new Planet(sun,0,1000);
		Planet planet3 = new Planet(sun,0,2000);
		
		List<Planet> planetas = Arrays.asList(planet1, planet2, planet3);
		
		SolarSystem ssys = new SolarSystem(sun,planetas);
		
		assertEquals(WeatherEnum.SEQUIA, serv.getWeather(ssys));
		
		
	}

}
