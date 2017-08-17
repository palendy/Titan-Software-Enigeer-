package model;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testSetSize() {
		Matrix x = new Matrix();
		x.setSize(2);
	}

	@Test
	public void testSort() {
		
		Matrix b = new Matrix();
		b.setSize(4);
		for(int i=0; i<b.getSize(); i++){
			b.getDependency().add(new Vector<String>());
			b.getcm().add(i+1+"");
			for(int j=0; j < b.getSize(); j++)
				b.getDependency().get(i).add("X");
		}
		for(int i=0; i<b.getSize(); i++){
			b.getNameList().add("item"+(3-i));
		}
		
		b.Sort(0, 3);
		
		
		assertEquals("item1", b.getNameList().get(0));
		
	}

}
