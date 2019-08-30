package tmp.sarabadu.planetas.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetTest {

	@Test
	public void test_planet_advance_1_day() throws Exception {

		Star sun = new Star(0.0, 0.0);

		Planet PlanetOnDay0 = new Planet(sun, 0, 2, 1);
		Planet PlanetOnDay1 = PlanetOnDay0.advance(1);

		assertEquals(1, PlanetOnDay1.getAngle());
		assertThat(PlanetOnDay1.y, greaterThan(0.0));
		assertThat(PlanetOnDay1.x, lessThan(2.0));

	}

	@Test
	public void test_planet_advance_multiple_day() throws Exception {

		Star sun = new Star(0.0, 0.0);

		Planet PlanetOnDay0 = new Planet(sun, 0, 2, 2);
		Planet PlanetOnDay45 = PlanetOnDay0.advance(45);

		assertEquals(90, PlanetOnDay45.getAngle());
		assertThat(PlanetOnDay45.y, closeTo(2.0, 0.000000009));
		assertThat(PlanetOnDay45.x, closeTo(0.0, 0.000000009));

		Planet PlanetOnDay90 = PlanetOnDay45.advance(45);

		assertEquals(180, PlanetOnDay90.getAngle());
		assertThat(PlanetOnDay90.y, closeTo(0.0, 0.000000009));
		assertThat(PlanetOnDay90.x, closeTo(-2.0, 0.000000009));

	}

	@Test
	public void test_planet_advance_backwards() throws Exception {

		Star sun = new Star(0.0, 0.0);

		Planet PlanetOnDay0 = new Planet(sun, 0, 2, -2);
		Planet PlanetOnDay45 = PlanetOnDay0.advance(45);

		assertEquals(270, PlanetOnDay45.getAngle());
		assertThat(PlanetOnDay45.y, closeTo(-2.0, 0.000000009));
		assertThat(PlanetOnDay45.x, closeTo(0.0, 0.000000009));

		Planet PlanetOnDay90 = PlanetOnDay45.advance(45);

		assertEquals(180, PlanetOnDay90.getAngle());
		assertThat(PlanetOnDay90.y, closeTo(0.0, 0.000000009));
		assertThat(PlanetOnDay90.x, closeTo(-2.0, 0.000000009));

	}
}
