/**
 * 
 */
package tmp.sarabadu.planetas.model;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Juan Pablo Garcia Ripa
 *
 */


public class SpaceObjectTest {

	@Test
	public void test_3_space_objects_are_collinears() throws Exception {
		
		SpaceObject so0 = new SpaceObject(0.0,0.0);
		SpaceObject so1 = new SpaceObject(1.0,1.0);
		SpaceObject so2 = new SpaceObject(2.0,2.0);
		
		assertTrue(so0.isCollinear(so1,so2));
		
		
	}
	
	@Test
	public void test_space_objects_are_collinears() throws Exception {
		
		SpaceObject so0 = new SpaceObject(0.0,0.0);
		SpaceObject so1 = new SpaceObject(1.0,1.0);
		SpaceObject so2 = new SpaceObject(2.0,2.0);
		
		List<SpaceObject> rest = new ArrayList<SpaceObject>();
		rest.add(so1);
		rest.add(so2);
		assertTrue(so0.isCollinear(rest));
		
	}
	
	@Test
	public void test_space_objects_are_collinears2() throws Exception {
		SpaceObject so0 = new SpaceObject(0.0,0.0);
		
		List<SpaceObject> spaceObjects = new ArrayList<SpaceObject>();
		spaceObjects.add(new SpaceObject(0.0,1.0));
		spaceObjects.add(new SpaceObject(0.0,2.0));
		spaceObjects.add(new SpaceObject(0.0,3.0));
		spaceObjects.add(new SpaceObject(0.0,4.0));
		spaceObjects.add(new SpaceObject(0.0,5.0));
		spaceObjects.add(new SpaceObject(0.0,6.0));
		spaceObjects.add(new SpaceObject(0.0,7.0));
		spaceObjects.add(new SpaceObject(0.0,8.0));
		spaceObjects.add(new SpaceObject(0.0,9.0));
		assertTrue(so0.isCollinear(spaceObjects));
		
	}
	
	@Test
	public void test_space_objects_are_not_collinears() throws Exception {
		SpaceObject so0 = new SpaceObject(0.0,0.0);
		
		List<SpaceObject> spaceObjects = new ArrayList<SpaceObject>();
		spaceObjects.add(new SpaceObject(0.0,1.0));
		spaceObjects.add(new SpaceObject(0.0,2.0));
		spaceObjects.add(new SpaceObject(0.0,3.0));
		spaceObjects.add(new SpaceObject(0.0,4.0));
		spaceObjects.add(new SpaceObject(0.0,5.0));
		spaceObjects.add(new SpaceObject(0.0,6.0));
		spaceObjects.add(new SpaceObject(0.0,7.0));
		spaceObjects.add(new SpaceObject(0.0,8.0));
		spaceObjects.add(new SpaceObject(0.1,9.0));
		assertFalse(so0.isCollinear(spaceObjects));
		
	}
	
}
