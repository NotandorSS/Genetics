package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Cat;
import model.World;
import view.CreateView;

public class CreateController implements ActionListener{
	private CreateView createView = new CreateView();
	private World world;
	public CreateController(World world) {
		this.world = world;
		createView.setCreateButtonListener(this);
	}
	
	/**
	 * actionPerformed for the createButton in CreateMode.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Cat c = null;
		try {
			int id = Integer.parseInt(createView.getCatIDText());
			if (id <= 0 || id >= Cat.getPopulation() ||world.getCat(id) != null) {
				throw new NumberFormatException();
			}
			c = new Cat(id);
		} catch (NumberFormatException e) {
			int userInput = JOptionPane.showConfirmDialog(createView,
                    "One of three things happened: \n"
                    + "--You entered something Integer couldn't parse,\n"
                    + "--You entered an integer not in an acceptable range, \n"
                    + "--You entered the id of a cat that already exists in the database.\n"
                    + "Would you like to continue with a program chosen id?", "ID Problems",
                    JOptionPane.YES_NO_OPTION);
			if (userInput != JOptionPane.YES_OPTION) {
				return;
			}
		}
		//TODO proceed with actually creating a cat from view stuff
		if (c == null) {
			c = new Cat();
		}
	}

	public CreateView getCreateView() {
		return createView;
	}

}
