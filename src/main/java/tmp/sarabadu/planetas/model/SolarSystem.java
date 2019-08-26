package tmp.sarabadu.planetas.model;

import java.util.List;

import java.util.stream.Collectors;

import lombok.Data;

@Data
public class SolarSystem {
	
	private SpaceObject sun;
	private List<Planet> planets;
	
	public SolarSystem(SpaceObject sun, List<Planet> planets) {
		this.setPlanets(planets);
		this.setSun(sun);
	}
	
	public SolarSystem advance(int days) {
		List<Planet> newPlanets = planets.stream()
										  .map(p -> p.advance(days))
										  .collect(Collectors.toList());
		
		
		return new SolarSystem(sun, newPlanets);
		
	}

}
