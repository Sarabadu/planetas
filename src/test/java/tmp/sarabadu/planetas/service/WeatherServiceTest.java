package tmp.sarabadu.planetas.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tmp.sarabadu.planetas.enums.WeatherEnum;
import tmp.sarabadu.planetas.model.Planet;
import tmp.sarabadu.planetas.model.SolarSystem;
import tmp.sarabadu.planetas.model.SpaceObject;
import tmp.sarabadu.planetas.model.Star;

/**
 * Test del servicio de clima Se espera que el servicio de clima se encargue de
 * tomar un sistema solar(en un estado dado) y nos permita obtener el clima de
 * ese momento
 * 
 * {@link tmp.sarabadu.planetas.service.WeatherService}
 * 
 * @author Juan Pablo Garcia Ripa
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {

	@Autowired
	WeatherService serv;

	@Test
	public void test_sun_and_planets_aligned() {

		// sol en el centro del eje
		SpaceObject sun = new Star(0., 0.);

		// los planetas con un mismo angulo estaran alineados con respecto al sol
		Planet planet1 = new Planet(sun, 0, 500, 0);
		Planet planet2 = new Planet(sun, 0, 1000, 0);
		Planet planet3 = new Planet(sun, 0, 2000, 0);

		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);

		SolarSystem ssys = new SolarSystem(sun, planetas);

		assertEquals(WeatherEnum.SEQUIA, serv.getWeather(ssys));

	}

	@Test
	public void test_sun_and_planets_not_aligned() {

		SpaceObject sun = new Star(0., 0.);

		Planet planet1 = new Planet(sun, 0, 500, 0);
		Planet planet2 = new Planet(sun, 0, 1000, 0);
		Planet planet3 = new Planet(sun, 1, 2000, 0);

		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);

		SolarSystem ssys = new SolarSystem(sun, planetas);

		assertEquals(WeatherEnum.NORMAL, serv.getWeather(ssys));

	}

	@Test
	public void test_only_planets_aligned() {
		/**
		 * busco una orbita adecuada para que un planeta este a 45 grados, otro a 90 y
		 * otro a 135 si el de 90 grados esta a una distacia de 2
		 * 
		 * los otros 2 tienen que estar a una distacia de h**2 = 2**2 + 2**2 por lo que
		 * h = sqr(8)
		 */

		SpaceObject sun = new Star(0., 0.);

		Planet planet1 = new Planet(sun, 135, Math.sqrt(8), 0);
		Planet planet2 = new Planet(sun, 90, 2, 0);
		Planet planet3 = new Planet(sun, 45, Math.sqrt(8), 0);

		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);

		SolarSystem ssys = new SolarSystem(sun, planetas);

		assertEquals(WeatherEnum.OPTIMO, serv.getWeather(ssys));

	}

	@Test
	public void test_sun_inside() {
		/**
		 * se prueba con angulos donde seguro el sol debe estan dentro del triangulo
		 */

		SpaceObject sun = new Star(0., 0.);

		Planet planet1 = new Planet(sun, 0, 500, 0);
		Planet planet2 = new Planet(sun, 90, 2000, 0);
		Planet planet3 = new Planet(sun, 180, 1500, 0);

		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);

		SolarSystem ssys = new SolarSystem(sun, planetas);

		assertEquals(WeatherEnum.LLUVIA, serv.getWeather(ssys));

	}

	@Test
	public void test_sun_not_inside() {

		SpaceObject sun = new Star(0., 0.);

		Planet planet1 = new Planet(sun, 0, 500, 0);
		Planet planet2 = new Planet(sun, 90, 2000, 0);
		Planet planet3 = new Planet(sun, 179, 1500, 0);

		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);

		SolarSystem ssys = new SolarSystem(sun, planetas);

		assertEquals(WeatherEnum.NORMAL, serv.getWeather(ssys));

	}

}
