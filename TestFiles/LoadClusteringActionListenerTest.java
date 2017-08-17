package action;

import static org.junit.Assert.*;

import javax.swing.JTree;

import org.junit.Test;

import ui.GUI;

public class LoadClusteringActionListenerTest {
	
	GUI gui = new GUI();
	JTree tree = new JTree();

	@Test
	public void testActionPerformed() {
		
new OpenDSMActionListener(gui,tree).actionPerformed(null); //titan.dsm 열기
		
		new LoadClusteringActionListener(gui,tree).actionPerformed(null); //titan_DRH+Bunch.clsx 열기
		
		System.out.println(gui.getRoot().getChildAt(0).toString());
		
		assertEquals("L0",gui.getRoot().getChildAt(0).toString());
		
		assertEquals("edu.drexel.cs.rise.titan.util.IconFactory", gui.getRoot().getChildAt(0).getChildAt(0).toString());
		
		assertEquals("edu.drexel.cs.rise.titan.util.IconFactory", gui.getRoot().getChildAt(0).getChildAt(0).getChildAt(0).toString());
		
		assertEquals("L1",gui.getRoot().getChildAt(1).toString());
		
	}

}
