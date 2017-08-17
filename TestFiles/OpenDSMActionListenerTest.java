package action;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

import ui.GUI;

public class OpenDSMActionListenerTest {
	GUI gui = new GUI();
	DefaultMutableTreeNode myroot;
	JTree tree =  new JTree();
	
	JButton button;

	@Test
	public void testActionPerformed() {
new OpenDSMActionListener(gui,tree).actionPerformed(null);
		
		assertEquals("edu.drexel.cs.rise.titan.action.cluster.SaveAction",gui.getOrigin().getNameList().get(0));
		
		assertEquals(35,gui.getOrigin().getSize());
		
		assertEquals("edu.drexel.cs.rise.titan.action.cluster.SaveAction",gui.getTable().getNameList().get(0));
		
		assertEquals(35,gui.getTable().getSize());
		
		assertEquals("edu.drexel.cs.rise.titan.action.cluster.SaveAction",gui.getMyModel().getDSM().getNameList().get(0));
		
		assertEquals(35,gui.getMyModel().getDSM().getSize());
		
	}

}
