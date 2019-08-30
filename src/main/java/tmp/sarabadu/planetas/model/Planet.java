package tmp.sarabadu.planetas.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Planet extends SpaceObject {
	private int angle;

	@ToString.Exclude
	private int moves;

	@ToString.Exclude
	private SpaceObject parentStar;
	private Double distanceFromParent;

	public Planet(SpaceObject parentStar, int initialAngle, int distanceFromParent, int moves) {
		this(parentStar, initialAngle, Double.valueOf(distanceFromParent), moves);
	}

	public Planet(SpaceObject parentStar, int initialAngle, Double distanceFromParent, int moves) {

		this.x = parentStar.getX() + distanceFromParent * Math.cos(Math.toRadians(initialAngle));
		this.y = parentStar.getY() + distanceFromParent * Math.sin(Math.toRadians(initialAngle));
		this.parentStar = parentStar;
		this.distanceFromParent = distanceFromParent;
		this.moves = moves;
		this.angle = initialAngle;
	}

	public Planet advance(int days) {

		return new Planet(this.parentStar, newAngle(days), this.distanceFromParent, this.moves);
	}

	private int newAngle(int days) {

		int newAngle = angle + (moves * days);

		return normalize(newAngle);
	}

	private int normalize(int newAngle) {
		newAngle %= 360;

		return newAngle >= 0 ? newAngle : (newAngle + 360);
	}

}
