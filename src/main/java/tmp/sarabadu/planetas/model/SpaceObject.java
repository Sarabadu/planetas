package tmp.sarabadu.planetas.model;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import lombok.Data;

@Data
public class SpaceObject {
	Logger	log = LoggerFactory.getLogger(SpaceObject.class);
	
	//tolerancia asumida por punto flotante
	final double THRESHOLD = .00000000000001;
	
	Double x,y;
	
	public SpaceObject() {
		super();
	}

	public SpaceObject(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public boolean isCollinear(SpaceObject spaceObject1,SpaceObject spaceObject2) {
		
		Double x1 = this.x;
		Double y1 = this.y;
		Double x2 = spaceObject1.x;
		Double y2 = spaceObject1.y;
		Double x3 = spaceObject2.x;
		Double y3 = spaceObject2.y;
		
		
		
		return Math.abs(
					x1 * (y2 - y3) +  
					x2 * (y3 - y1) +  
					x3 * (y1 - y2)
								) < THRESHOLD ;
		
		
		// alternativa (y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2)) 
		
	}

	public boolean isCollinear(List<? extends SpaceObject> spaceObjects) {
		
		
		SpaceObject head = spaceObjects.remove(0);
		boolean isCollinear = true;
		Iterator<? extends SpaceObject> iterator = spaceObjects.iterator();
		
		while (iterator.hasNext() && isCollinear) {
			SpaceObject curr = (SpaceObject) iterator.next();
			isCollinear = this.isCollinear(head, curr);
		}
		
		return isCollinear;
		
	}

}