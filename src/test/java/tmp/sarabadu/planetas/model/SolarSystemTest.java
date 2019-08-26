package tmp.sarabadu.planetas.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SolarSystemTest {

	@Test
	public void test_advance() throws Exception {
		
		Star sun = new Star(0.0, 0.0);
		
		Planet Planet1OnDay0 = new Planet(sun,0,2,2);
		Planet Planet2OnDay0 = new Planet(sun,0,2,-1);
		Planet Planet3OnDay0 = new Planet(sun,0,2,1);
		
		List<Planet> planets = new ArrayList<Planet>();
		
		planets.add(Planet1OnDay0);
		planets.add(Planet2OnDay0);
		planets.add(Planet3OnDay0);
		
		SolarSystem solarSys = new SolarSystem(sun, planets );
		SolarSystem solarSysDay2 = solarSys.advance(2);
		
		
		assertEquals(4, solarSysDay2.getPlanets().get(0).getAngle());
		assertEquals(358, solarSysDay2.getPlanets().get(1).getAngle());
		assertEquals(2, solarSysDay2.getPlanets().get(2).getAngle());
		
		
		
	}

}
