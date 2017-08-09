package view;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.CreateController;
import controller.EditorController;
import controller.ImportController;
import model.Cat;
import model.World;

public class View extends JTabbedPane{
	public static final int frameWidth = 1200;
	public static final int frameHeight = 675;
	JFrame frame = new JFrame();
	World world = readIn();
	CreateController createCont = new CreateController(world);
	EditorController editorCont = new EditorController(world);
	ImportController importCont = new ImportController(world);
	
	public View(){
		this.setBounds(0, 0, frameWidth, frameHeight);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this, BorderLayout.CENTER);
		frame.setBounds(500, 300, frameWidth, frameHeight);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	writeOut();
		    }
		});
		this.addTab("Editor", editorCont.getEditorView());
		this.addTab("Create", createCont.getCreateView());
		this.addTab("Import", importCont.getImportView());
		this.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	world.sortUp();
		    	editorCont.getEditorView().updateList(world.toString());
		    }
		});
		editorCont.getEditorView().updateList(world.toString());
		//frame.pack();
		frame.setVisible(true);
	}
	
	private void writeOut() {
		System.out.println("Writing");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("tempdata.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(world);
	        oos.close();
		} catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private World readIn() {
		System.out.println("Reading");
		World world = null;
		try
        {
            FileInputStream fis = new FileInputStream("tempdata.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            world = (World) ois.readObject();
            if (world.getLastID() > 435) {
                Cat.population = world.getLastID();
            }
            ois.close();
        } catch (java.io.InvalidClassException e) {
        	world = new World();
        	System.out.println("RESTARTING");
        }
		catch (Exception e) {
        	e.printStackTrace();
        }
		return world;
	}
	
	public static void main(String[]args) {
		View test = new View();
	}
}