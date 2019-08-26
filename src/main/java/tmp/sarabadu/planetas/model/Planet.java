package tmp.sarabadu.planetas.model;

public class Planet extends SpaceObject{
	int angle;
	
	public Planet(SpaceObject parentStar, int initialAngle, int distanceFromParent) {
		this(parentStar,initialAngle,Double.valueOf(distanceFromParent));
	}
	
	public Planet(SpaceObject parentStar, int initialAngle, Double distanceFromParent) {
		this.x = parentStar.x + distanceFromParent * Math.cos(Math.toRadians( initialAngle));
		this.y = parentStar.y + distanceFromParent * Math.sin(Math.toRadians( initialAngle));
		this.angle = initialAngle;
	}

}
