package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import java.io.*;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;

import controller.ClusterLoadDSM;
import controller.ClusterSaveDSM;
import controller.ClusterUtilities;
import model.Matrix;
import model.MatrixModel;
import model.Tree;
import action.GroupActionListener;
import action.LoadClusteringActionListener;
import action.NewClusteringActionListener;
import action.NewDSMActionListener;
import action.OpenDSMActionListener;
import action.RedrawActionListener;
import action.RenameActionListener;
import action.SaveClusteringActionListener;
import action.SaveClusteringAsActionListener;
import action.SaveDSMActionListener;
import ui.GUI;

/**
 *
 * @author caucse
 */
public class GUI extends javax.swing.JFrame {

	private Matrix Original=new Matrix();
	private Matrix ForTable=new Matrix();
	private ClusterLoadDSM ClsLoad = new ClusterLoadDSM();
	private ClusterSaveDSM ClsSave = new ClusterSaveDSM();
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("$root");
	private File DSMFile;
	private File CLSFile;
	private MatrixModel MM;
	private MatrixModel M2;

	/**
	 * 
	 * Creates new form GUI
	 */
	public GUI() {
		initComponents();
		setTitle("T I T A N");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jMenuBar2 = new javax.swing.JMenuBar();
		jMenu6 = new javax.swing.JMenu();
		jMenu7 = new javax.swing.JMenu();
		jPopupMenu1 = new javax.swing.JPopupMenu();
		jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
		jPopupMenu2 = new javax.swing.JPopupMenu();
		jMenuBar3 = new javax.swing.JMenuBar();
		jMenu8 = new javax.swing.JMenu();
		jMenu9 = new javax.swing.JMenu();
		jMenuBar4 = new javax.swing.JMenuBar();
		jMenu10 = new javax.swing.JMenu();
		jMenu11 = new javax.swing.JMenu();
		jMenuBar5 = new javax.swing.JMenuBar();
		jMenu12 = new javax.swing.JMenu();
		jMenu13 = new javax.swing.JMenu();
		jMenu14 = new javax.swing.JMenu();
		jMenu15 = new javax.swing.JMenu();
		jDialog1 = new javax.swing.JDialog();
		jPanel1 = new javax.swing.JPanel();
		jFrame1 = new javax.swing.JFrame();
		jToolBar2 = new javax.swing.JToolBar();
		jButton1 = new javax.swing.JButton();
		jButton1.setToolTipText("Open DSM");
		jButton2 = new javax.swing.JButton();
		jButton2.setEnabled(false);
		jButton2.setToolTipText("Redraw");
		jButton3 = new javax.swing.JButton();
		jButton3.setToolTipText("New Clustering");
		jButton3.setEnabled(false);
		jButton4 = new javax.swing.JButton();
		jButton4.setToolTipText("Load Clustering");
		jButton4.setEnabled(false);
		jButton5 = new javax.swing.JButton();
		jButton5.setToolTipText("Save Clustering");
		jButton5.setEnabled(false);
		jButton6 = new javax.swing.JButton();
		jButton6.setToolTipText("Save Clustering as");
		jButton6.setEnabled(false);
		jToolBar1 = new javax.swing.JToolBar();
		openTree = new javax.swing.JButton();
		openTree.setToolTipText("Expand");
		treeMenu = new JPopupMenu();
		Rename = new JMenuItem("Rename");
		Sort = new JMenuItem("Sort");
		Duplicate = new JMenuItem("Duplicate");
		Edit = new JMenuItem("Edit");
		treeMenu.add(Rename);
		treeMenu.add(Sort);
		treeMenu.add(Duplicate);
		treeMenu.add(Edit);
		Rename.addActionListener(new RenameActionListener(this));
		Sort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TreePath path = ((JTree)treeMenu.getInvoker()).getSelectionPath();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				System.out.println(node.toString());  	
				if(!node.isLeaf()){
					int indexI = ForTable.itemIndex(node.getFirstLeaf().toString());
					int indexJ = ForTable.itemIndex(node.getLastLeaf().toString());
					System.out.println("Sort!!"+ indexI+" "+indexJ);
					ForTable.Sort(indexI, indexJ);
				}

				new ClusterUtilities().clusterSort(node);
				DefaultTreeModel model = (DefaultTreeModel)(jTree1.getModel());
				model.reload(node);
			}
		});
		Duplicate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GUI gui = new GUI();
				TreePath path = ((JTree)treeMenu.getInvoker()).getSelectionPath();
				DefaultMutableTreeNode Dupnode = (DefaultMutableTreeNode) path.getLastPathComponent();
				
				if(Dupnode.isLeaf()){
					Dupnode = (DefaultMutableTreeNode)Dupnode.getParent();
				}
				
				System.out.println("Selected is " + Dupnode.toString());
				DefaultMutableTreeNode tempnode = Dupnode;

				while(!tempnode.isLeaf())
					tempnode = tempnode.getNextNode();

				int a;
				for(a=0; a<ForTable.getSize(); a++)
					if(ForTable.getNameList().get(a).equals(tempnode.toString()))
						break;

				System.out.println("A is " + tempnode.toString());

				tempnode = Dupnode.getNextNode();
				DefaultMutableTreeNode tempnode2 = null;

				while(Dupnode.getLevel() < tempnode.getLevel()) {
					tempnode2 = tempnode;
					tempnode = tempnode.getNextNode();

					if(tempnode == null){
						tempnode = tempnode2;
						break;
					}
				}

				if(Dupnode.getLevel() >= tempnode.getLevel())
					tempnode = tempnode2;

				int b;
				for(b=0; b<ForTable.getSize(); b++)
					if(ForTable.getNameList().get(b).equals(tempnode.toString()))
						break;

				System.out.println("B is " + tempnode.toString());

				Matrix TempTable = ForTable.clone();
				System.out.println("Count is " + a);
				System.out.println("Count is " + b);
				TempTable.RemoveFromTo(b+1, ForTable.getSize()-1);
				TempTable.RemoveFromTo(0, a-1);
				TempTable.ArrangeCM();

				gui.setJTree(jTree1.clone(Dupnode));
				gui.setTable(TempTable);
				System.out.println(gui.getJTree().getRoot());
				gui.setRoot(gui.getJTree().getRoot());
				System.out.println(jTree1.getRoot());
				gui.getMyModel().setDataVector(gui.getTable());
				gui.setButtonsEnable();
				gui.TreeAdd();
				gui.setVisible(true);
				gui.setDefaultCloseOperation(1);
				gui.jScrollPane1.setViewportView(gui.getJTree());
				DefaultTreeModel model = (DefaultTreeModel)(gui.getJTree().getModel());
				model.reload();
			}
		});

		openTree.setEnabled(false);
		openTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("openTree");
				for(int i=0;i<=jTree1.getRowCount();i++) jTree1.expandRow(i);
			}
		});
		closeTree = new javax.swing.JButton();
		closeTree.setToolTipText("Fold");
		closeTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("closeTree");
				for(int i=0;i<=jTree1.getRowCount();i++) jTree1.collapseRow(i);
			}
		});
		jButton9 = new javax.swing.JButton();
		jButton9.setToolTipText("Group");
		jButton10 = new javax.swing.JButton();
		jButton10.setToolTipText("UnGroup");
		jButton11 = new javax.swing.JButton();
		jButton11.setToolTipText("Up");
		jButton12 = new javax.swing.JButton();
		jButton12.setToolTipText("Down");
		jButton13 = new javax.swing.JButton();
		jButton13.setToolTipText("Delete");
		jButton14 = new javax.swing.JButton();
		jButton14.setToolTipText("AddItem");
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem1.setIcon(new ImageIcon(GUI.class.getResource("/se/image/open-clsx.png")));
		jMenuItem6 = new javax.swing.JMenuItem();
		jMenuItem6.setIcon(new ImageIcon(GUI.class.getResource("/se/image/save-clsx.png")));
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem2.setIcon(new ImageIcon(GUI.class.getResource("/se/image/new-clsx.png")));
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem3.setIcon(new ImageIcon(GUI.class.getResource("/se/image/open-clsx.png")));
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenuItem4.setIcon(new ImageIcon(GUI.class.getResource("/se/image/save-clsx.png")));
		jMenuItem5 = new javax.swing.JMenuItem();
		jMenuItem5.setIcon(new ImageIcon(GUI.class.getResource("/se/image/save-clsx-as.png")));
		jMenuItem7 = new javax.swing.JMenuItem();
		jMenuItem7.setIcon(new ImageIcon(GUI.class.getResource("/se/image/new-clsx.png")));
		jMenu3 = new javax.swing.JMenu();
		jMenuItem8 = new javax.swing.JMenuItem();
		jMenuItem9 = new javax.swing.JMenuItem();
		jMenuItem10 = new javax.swing.JCheckBoxMenuItem("Show Row Labels", true);
		jMenu4 = new javax.swing.JMenu();
		jMenuItem12 = new javax.swing.JMenuItem();

		jMenu6.setText("File");
		jMenuBar2.add(jMenu6);

		jMenu7.setText("Edit");
		jMenuBar2.add(jMenu7);

		jRadioButtonMenuItem1.setSelected(true);
		jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

		jMenu8.setText("File");
		jMenuBar3.add(jMenu8);

		jMenu9.setText("Edit");
		jMenuBar3.add(jMenu9);

		jMenu10.setText("File");
		jMenuBar4.add(jMenu10);

		jMenu11.setText("Edit");
		jMenuBar4.add(jMenu11);

		jMenu12.setText("File");
		jMenuBar5.add(jMenu12);

		jMenu13.setText("Edit");
		jMenuBar5.add(jMenu13);

		jMenu14.setText("jMenu14");

		jMenu15.setText("jMenu15");

		javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
		jDialog1.getContentPane().setLayout(jDialog1Layout);
		jDialog1Layout.setHorizontalGroup(
				jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE)
				);
		jDialog1Layout.setVerticalGroup(
				jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
				);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE)
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 360, Short.MAX_VALUE)
				);

		javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
		jFrame1.getContentPane().setLayout(jFrame1Layout);
		jFrame1Layout.setHorizontalGroup(
				jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE)
				);
		jFrame1Layout.setVerticalGroup(
				jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
				);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jToolBar2.setRollover(true);

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/open-dsm.png"))); // NOI18N
		jButton1.setFocusable(false);
		jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar2.add(jButton1);

		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/redraw.png"))); // NOI18N
		jButton2.setFocusable(false);
		jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton2.addActionListener(new RedrawActionListener(this));
		jToolBar2.add(jButton2);

		jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/new-clsx.png"))); // NOI18N
		jButton3.setFocusable(false);
		jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton3.addActionListener(new NewClusteringActionListener(this));
		jToolBar2.add(jButton3);

		jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/open-clsx.png"))); // NOI18N
		jButton4.setFocusable(false);
		jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar2.add(jButton4);

		jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/save-clsx.png"))); // NOI18N
		jButton5.setFocusable(false);
		jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton5.addActionListener(new SaveClusteringActionListener(this));
		jToolBar2.add(jButton5);

		jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/save-clsx-as.png"))); // NOI18N
		jButton6.setFocusable(false);
		jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton6.addActionListener(new SaveClusteringAsActionListener(this));
		jToolBar2.add(jButton6);

		jToolBar1.setRollover(true);

		openTree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/expand.png"))); // NOI18N
		openTree.setFocusable(false);
		openTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		openTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		jToolBar1.add(openTree);

		closeTree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/collapse.png"))); // NOI18N
		closeTree.setFocusable(false);
		closeTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		closeTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(closeTree);

		jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/group.png"))); // NOI18N
		jButton9.setFocusable(false);
		jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton9.addActionListener(new GroupActionListener(this){
		});

		jToolBar1.add(jButton9);

		jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/ungroup.png"))); // NOI18N
		jButton10.setFocusable(false);
		jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton10.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
				TreePath paths[] = jTree1.getSelectionPaths();
				TreePath path = paths[0];
				if (path == null) {return;}
				if (paths.length > 0) { 
					System.out.println("You selected Several Node.");
					return;
				}

				DefaultMutableTreeNode Parent = (DefaultMutableTreeNode)path.getLastPathComponent();
				System.out.println("I'm " + Parent.toString());
				DefaultMutableTreeNode Grandpa = (DefaultMutableTreeNode)Parent.getParent();
				System.out.println("I'm " + Grandpa.toString());
				int place = Grandpa.getIndex(Parent);
				int childcount = Parent.getChildCount();
				System.out.println(Parent.toString() + " is in " + place);
				MutableTreeNode node[]=new MutableTreeNode[childcount];

				for(int i=0; i<childcount; i++) {
					node[i] =(MutableTreeNode)Parent.getChildAt(0);
					System.out.println("UnGroup");
					DefaultTreeModel treeModel = (DefaultTreeModel) jTree1.getModel();
					treeModel.insertNodeInto(node[i], Grandpa, i+place);
					System.out.println("I'll move " + node[i].toString() + " to " + Grandpa.toString() + " in " + (i+place));
					treeModel.nodeStructureChanged(Grandpa);
					treeModel.nodeChanged(node[i]);

					if( i == childcount-1 ){
						System.out.println("I'll remove " + ((DefaultMutableTreeNode)Grandpa.getChildAt(place+i+1)).toString());
						treeModel.removeNodeFromParent((MutableTreeNode)Grandpa.getChildAt(place+i+1));
					}
				}
			}
		});
		jToolBar1.add(jButton10);

		jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/up.png"))); // NOI18N
		jButton11.setFocusable(false);
		jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jButton11.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
				TreePath path[] = jTree1.getSelectionPaths();
				if (path == null) {return;}
				int selectedNodeIndex[]= new int[path.length];
				MutableTreeNode node[]=new MutableTreeNode[path.length];

				for(int i=0; i<path.length; i++){
					node[i] =(MutableTreeNode) path[i].getLastPathComponent();
					selectedNodeIndex[i] = node[i].getParent().getIndex(node[i]);
					if(selectedNodeIndex[i] == 0){
						jTree1.setSelectionPaths(path);
						return;
					}
				}

				for(int i=0; i<path.length; i++){
					for(int j=i+1; j<path.length; j++){
						if(selectedNodeIndex[i] > selectedNodeIndex[j]){
							MutableTreeNode tmpnode= node[i];
							int tmp = selectedNodeIndex[i];
							selectedNodeIndex[i] = selectedNodeIndex[j];
							selectedNodeIndex[j] = tmp;
							node[i] = node[j];
							node[j] = tmpnode;
						}
					}
				}	

				for(int i=0; i<path.length; i++) {
					DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node[i].getParent();
					System.out.println("Moving UP");
					DefaultTreeModel treeModel = (DefaultTreeModel) jTree1.getModel();
					treeModel.insertNodeInto(node[i], parentNode, selectedNodeIndex[i] - 1);
					treeModel.nodeStructureChanged(parentNode);
					treeModel.nodeChanged(node[i]);	
					ForTable.MatrixSwap(ForTable.Whereis(node[i].toString()), ForTable.Whereis(node[i].toString())-1);
				}

				jTree1.setSelectionPaths(path);
			}
		});
		jToolBar1.add(jButton11);

		jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/down.png"))); // NOI18N
		jButton12.setFocusable(false);
		jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		//DOWN CLICKDED
		jButton12.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
				TreePath path[] = jTree1.getSelectionPaths();
				if (path == null) {return;}
				int selectedNodeIndex[]= new int[path.length];
				MutableTreeNode node[]=new MutableTreeNode[path.length];
				for(int i=0; i<path.length; i++){
					node[i] =(MutableTreeNode) path[i].getLastPathComponent();
					selectedNodeIndex[i] = node[i].getParent().getIndex(node[i]);
					if(selectedNodeIndex[i] == node[i].getParent().getChildCount()-1){
						jTree1.setSelectionPaths(path);
						return;
					}
				}

				for(int i=0; i<path.length; i++){
					for(int j=i+1; j<path.length; j++){
						if(selectedNodeIndex[i] > selectedNodeIndex[j]){
							MutableTreeNode tmpnode= node[i];
							int tmp = selectedNodeIndex[i];
							selectedNodeIndex[i] = selectedNodeIndex[j];
							selectedNodeIndex[j] = tmp;
							node[i] = node[j];
							node[j] = tmpnode;
						}
					}
				}	

				for(int i=path.length-1; i>=0; i--){
					DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node[i].getParent();
					System.out.println("Moving DOWN");
					DefaultTreeModel treeModel = (DefaultTreeModel) jTree1.getModel();
					treeModel.insertNodeInto(node[i], parentNode, selectedNodeIndex[i] + 1);
					treeModel.nodeStructureChanged(parentNode);
					treeModel.nodeChanged(node[i]);	
					ForTable.MatrixSwap(ForTable.Whereis(node[i].toString()), ForTable.Whereis(node[i].toString())+1);
				}  
				jTree1.setSelectionPaths(path);
			}
		});
		jToolBar1.add(jButton12);

		jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/se/image/delete.png"))); // NOI18N
		jButton13.setFocusable(false);
		jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		jButton13.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {

				TreePath path[] = jTree1.getSelectionPaths();
				if(path == null) return;

				int selectedNodeIndex[]= new int[path.length];
				MutableTreeNode node[]=new MutableTreeNode[path.length];
				for(int i=0; i<path.length; i++){
					node[i] =(MutableTreeNode) path[i].getLastPathComponent();
					selectedNodeIndex[i] = node[i].getParent().getIndex(node[i]);
				}

				for(int i=0; i<path.length; i++){
					for(int j=i+1; j<path.length; j++){
						if(((DefaultMutableTreeNode)node[i]).isNodeAncestor(node[j]) || ((DefaultMutableTreeNode)node[i]).isNodeChild(node[j]) ){
							System.out.println("Selection Error");
							return;
						}
					}
				}

				for(int i=0; i<path.length; i++)
					System.out.println(node[i].toString());

				for(int i=0; i<path.length; i++) {
					int Place = ForTable.Whereis(node[i].toString());
					((DefaultTreeModel)jTree1.getModel()).removeNodeFromParent(node[i]);

					if(node[i].isLeaf()){
						System.out.println("This is Leaf " + Place);
						System.out.println("So I erased " + node[i].toString());
						ForTable.RemoveAt(Place);
					} else {
						DefaultMutableTreeNode temp = ((DefaultMutableTreeNode)node[i]).getNextNode();
						while(!temp.isLeaf())
							temp = temp.getNextNode();

						Place = ForTable.Whereis(temp.toString());
						System.out.println("This is Folder " + Place);
						System.out.println("So I erased all node under" + node[i].toString());
						int count = ((DefaultMutableTreeNode)node[i]).getLeafCount();
						for(int j=0; j<count; j++)
							ForTable.RemoveAt(Place);
					}
					ForTable.ArrangeCM();
				}
			}
		});

		jToolBar1.add(jButton13);

		jButton14.setIcon(new ImageIcon(GUI.class.getResource("/se/image/dsm.png"))); // NOI18N
		jButton14.setFocusable(false);
		jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		jButton14.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {

				TreePath path = jTree1.getSelectionPath();

				String name = JOptionPane.showInputDialog(null, "New DSM Row");
				if(path == null){ return; }
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				DefaultMutableTreeNode lastNode;
				if(node.isLeaf()){
					node = (DefaultMutableTreeNode)node.getParent();
					lastNode = node.getLastLeaf();
					node.add(new DefaultMutableTreeNode(name));
				}else{
					jTree1.expandPath(path);
					lastNode = node.getLastLeaf();
					node.add(new DefaultMutableTreeNode(name));
				}
				DefaultTreeModel treeModel = (DefaultTreeModel)(jTree1.getModel());
				treeModel.reload(node);
				jTree1.setSelectionPath(path);
				int index = ForTable.itemIndex(lastNode.toString());
				ForTable.insertDependency(name, index);
			}
		});

		jToolBar1.add(jButton14);

		Vector<Vector<String>> dm = new Vector<Vector<String>>(4);
		dm.add(new Vector<String>(1));
		dm.get(0).add(" ");
		Vector<String> cm = new Vector<String>(1);
		cm.add("1");

		MM = new MatrixModel(dm, cm);

		jMenu1.setText("File");
		jMenu1.addContainerListener(new java.awt.event.ContainerAdapter() {
			public void componentAdded(java.awt.event.ContainerEvent evt) {
				jMenu1ComponentAdded(evt);
			}
		});
		jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem1.setText("Open DSM");
		jMenu1.add(jMenuItem1);

		jMenuItem6.setText("Save DSM");
		jMenuItem6.addActionListener(new SaveDSMActionListener(this, MM));
		jMenu1.add(jMenuItem6);

		jMenuItem2.setText("New Clustering");
		jMenuItem2.addActionListener(new NewClusteringActionListener(this));

		separator = new JSeparator();
		jMenu1.add(separator);
		jMenu1.add(jMenuItem2);

		jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem3.setText("Load Clustering");
		jMenu1.add(jMenuItem3);

		jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem4.setText("Save Clustering");
		jMenuItem4.addActionListener(new SaveClusteringActionListener(this));
		jMenu1.add(jMenuItem4);

		jMenuItem5.setText("Save Clustering as");
		jMenuItem5.addActionListener(new SaveClusteringAsActionListener(this));
		jMenu1.add(jMenuItem5);

		jMenuItem7.setText("Make new DSM");
		jMenuItem7.addActionListener(new NewDSMActionListener(this));
		jMenu1.add(jMenuItem7);

		jMenuBar1.add(jMenu1);

		separator_1 = new JSeparator();
		jMenu1.add(separator_1);

		mntmExit = new JMenuItem("Exit");
		jMenu1.add(mntmExit);

		jMenu3.setText("View");

		jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
		jMenuItem8.setText("Redraw");
		jMenuItem8.addActionListener(new RedrawActionListener(this));
		jMenu3.add(jMenuItem8);


		jMenu3.add(jMenuItem10);

	

		jMenuBar1.add(jMenu3);

		jMenu4.setText("Help");

		jMenuItem12.setText("About");

		jMenuItem12.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				new JOptionPane().showMessageDialog(null,"This is DSM Analysis program by Team 5. \n\n Copyright Team 5 All rights reserved.");  
			}
		});
		
		jMenu4.add(jMenuItem12);

		jMenuBar1.add(jMenu4);

		setJMenuBar(jMenuBar1);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(350);
		jScrollPane2 = new javax.swing.JScrollPane();
		splitPane.setRightComponent(jScrollPane2);
		jTable1 = new javax.swing.JTable();
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		jTable1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if((e.getModifiers() & java.awt.event.InputEvent.BUTTON1_MASK) == java.awt.event.InputEvent.BUTTON1_MASK){
					javax.swing.JTable jt = (javax.swing.JTable)e.getSource();
					if(jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn()).equals(" "))
						jt.setValueAt("x",jt.getSelectedRow(), jt.getSelectedColumn());
					else
						jt.setValueAt(" ",jt.getSelectedRow(), jt.getSelectedColumn());

					System.out.println("Dependency ~~");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		jScrollPane2.setViewportView(jTable1);

		table = new JTable();

		jScrollPane2.setRowHeaderView(table);
		jScrollPane1 = new javax.swing.JScrollPane();
		splitPane.setLeftComponent(jScrollPane1);
		jTree1 = new Tree(root);
		jScrollPane1.setViewportView(jTree1);

		jButton1.addActionListener(new OpenDSMActionListener(this, jTree1));
		jButton4.addActionListener(new LoadClusteringActionListener(this, jTree1));
		jMenuItem1.addActionListener(new OpenDSMActionListener(this, jTree1));
		jMenuItem3.addActionListener(new LoadClusteringActionListener(this, jTree1));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(0)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(jToolBar1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(jToolBar2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1246, Short.MAX_VALUE)
														.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1246, Short.MAX_VALUE))
														.addGap(24))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(jToolBar2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jToolBar1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(splitPane)
						.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);

		pack();
	}// </editor-fold>//GEN-END:initComponents
	
	

	private void jMenu1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jMenu1ComponentAdded
		// TODO add your handling code here:
	}//GEN-LAST:event_jMenu1ComponentAdded

	public Matrix getOrigin(){
		return Original;
	}

	public Matrix getTable(){
		return ForTable;
	}

	public void setTable(Matrix replace){
		this.ForTable = replace;
	}

	public void setOriginal(Matrix replace){ //AnJaeHeokÀÌ Ãß°¡ÇÑ°Å
		this.Original = replace;
	}

	public File getDSMFile() {
		return DSMFile;
	}

	public File getCLSFile() {
		return CLSFile;
	}

	public void setDSMFile(File input) {
		this.DSMFile = input;
	}

	public void setCLSFile(File input) {
		this.CLSFile = input;
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public void setRoot(DefaultMutableTreeNode replace) {
		replace.removeFromParent();
		root = replace;
	}
	public Tree getJTree(){
		return jTree1;
	}

	public void setJTree(Tree mytree){
		jTree1 = mytree;
	}

	public MatrixModel getMyModel() {
		return MM;
	}
	public void DeleteTable() {
		table.setModel(new MatrixModel());
		jTable1.setModel(new MatrixModel());
	}

	public void DeleteFileMenu(){
		jMenu1.setVisible(false);
	}

	public void redraw(Matrix DSMTable) {
		System.out.println("111!!" + DSMTable);
		jScrollPane1.setViewportView(jTree1);
		MM.setDataVector(DSMTable);
		MM.setColorInfo(jTree1.getColorInfo());
		jTable1.setModel(MM);

		Vector<Vector<String>>dm = new Vector<Vector<String>>();
		for(int i=0; i < DSMTable.getSize(); i++){
			dm.add(new Vector<String>());
			if(jMenuItem10.isSelected()){
				dm.get(i).add(((i+1)+ "  ") + DSMTable.getNameList().get(i));
			}
			else{
				dm.get(i).add((i+1)+"");
			}
				
			

		}

		Vector<String> cm = new Vector<String>(1);
		cm.add("1");

		M2 = new MatrixModel(dm, cm);
		table.setModel(M2);
		TableRender tableRender = new TableRender(getJTree());

		tableRender.setColColor(jTable1);

	}

	public void ReTree() {
		jTree1 = new Tree(root);
		jScrollPane1.setViewportView(jTree1);
		TreeAdd();
	}

	public void TreeAdd() {
		jTree1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//TODO Auto-generated method stub
				TreePath path = jTree1.getPathForLocation(e.getX(), e.getY());  
				if (path == null) {
					System.out.println("tree null");
					return;
				}

				if (e.getButton() == 3) {
					jTree1.setSelectionPath(path);
					treeMenu.show(jTree1, e.getX(), e.getY());
				}
			}
		});
	}

	public ClusterSaveDSM getSaveCluster() {
		return ClsSave;
	}


	public ClusterLoadDSM getLoadCluster() {
		return ClsLoad;
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton13;
	private javax.swing.JButton jButton14;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton openTree;
	private javax.swing.JButton closeTree;
	private javax.swing.JButton jButton9;
	private javax.swing.JDialog jDialog1;
	private javax.swing.JFrame jFrame1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu10;
	private javax.swing.JMenu jMenu11;
	private javax.swing.JMenu jMenu12;
	private javax.swing.JMenu jMenu13;
	private javax.swing.JMenu jMenu14;
	private javax.swing.JMenu jMenu15;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenu jMenu6;
	private javax.swing.JMenu jMenu7;
	private javax.swing.JMenu jMenu8;
	private javax.swing.JMenu jMenu9;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuBar jMenuBar2;
	private javax.swing.JMenuBar jMenuBar3;
	private javax.swing.JMenuBar jMenuBar4;
	private javax.swing.JMenuBar jMenuBar5;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JCheckBoxMenuItem jMenuItem10;
	private javax.swing.JMenuItem jMenuItem12;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JMenuItem jMenuItem5;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JMenuItem jMenuItem8;
	private javax.swing.JMenuItem jMenuItem9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPopupMenu jPopupMenu1;
	private javax.swing.JPopupMenu jPopupMenu2;
	private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JToolBar jToolBar2;
	private Tree jTree1;
	private JMenuItem mntmExit;
	private JSeparator separator;
	private JSeparator separator_1;
	private JPanel panel;
	private JTable table;
	private JPopupMenu treeMenu;
	private JMenuItem Rename;
	private JMenuItem Sort;
	private JMenuItem Duplicate;
	private JMenuItem Edit;

	// End of variables declaration//GEN-END:variables

	//button enable 
	public void setButtonsEnable() {
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);
		jButton4.setEnabled(true);
		jButton5.setEnabled(true);
		jButton6.setEnabled(true);
		openTree.setEnabled(true);
		closeTree.setEnabled(true);
	}
}

