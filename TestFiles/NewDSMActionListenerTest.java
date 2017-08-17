package action;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

import ui.GUI;

public class NewDSMActionListenerTest {
	
	
	GUI gui = new GUI();
	JButton button;
	
	@Test
	public void testActionPerformed() {
		
new NewDSMActionListener(gui).actionPerformed(null);
		
		assertEquals("item0",gui.getOrigin().getNameList().get(0));
		
		assertEquals("item0",(gui.getRoot().getChildAt(0).toString()));
		
		assertEquals("item0",gui.getTable().getNameList().get(0));
		
		assertEquals("item0",gui.getMyModel().getDSM().getNameList().get(0));
		
	}

}
