package tmp.sarabadu.planetas.model;

import java.util.List;

import lombok.Data;

@Data
public class SolarSystem {
	
	private SpaceObject sun;
	private List<Planet> planets;
	
	public SolarSystem(SpaceObject sun, List<Planet> planets) {
		this.sun = sun;
		this.planets = planets;
	}

}
