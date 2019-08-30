package tmp.sarabadu.planetas.model;

import java.util.Iterator;
import java.util.List;

import lombok.Data;


@Data
public class SpaceObject {
	
	

	private static final double THRESHOLD = .0000000001;
	
	protected Double x;
	protected Double y;
	
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
		
		SpaceObject head = spaceObjects.get(0);
		List<? extends SpaceObject> tail =spaceObjects.subList(1, spaceObjects.size());
		

		boolean isCollinear = true;
		Iterator<? extends SpaceObject> iterator = tail.iterator();
		
		while (iterator.hasNext() && isCollinear) {
			SpaceObject curr =  iterator.next();
			isCollinear = this.isCollinear(head, curr);
		}
		
		return isCollinear;
		
	}

	public boolean isInsideArea(SpaceObject so1, SpaceObject so2, SpaceObject so3) {
		Double areaT = area(so1.x,so1.y,so2.x,so2.y,so3.x,so3.y);
		
		Double area1 = area(this.x,this.y,so2.x,so2.y,so3.x,so3.y);
		Double area2 = area(so1.x,so1.y,this.x,this.y,so3.x,so3.y);
		Double area3 = area(so1.x,so1.y,so2.x,so2.y,this.x,this.y);
		
		Double area123 = area1 + area2 + area3;
		
		boolean isGreater =  area123 > areaT -THRESHOLD;
		
		
		
		return !isGreater;
	}

	private Double area(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3) {
		
		return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
	}
	
	public Double distance(SpaceObject so) {
		Double x1 = this.x;
		Double y1 = this.y;
		Double x2 = so.x;
		Double y2 = so.y;
		
		Double cat1 = (x1-x2) * (x1-x2);
		Double cat2 = (y1-y2) * (y1-y2);
		
		return Math.sqrt(cat1+cat2);
	}

}