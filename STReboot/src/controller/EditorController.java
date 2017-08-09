package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import model.World;
import view.EditorView;

public class EditorController implements ActionListener {
	private EditorView editorView = new EditorView();
	private World world;

	public EditorController(World world) {
		this.world = world;
		editorView.setButtonListeners(this);
	}

	public EditorView getEditorView() {
		return editorView;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		if ("Add Premade".equals(command)) {
			world.addPremade();
		} else if ("Remove".equals(command)) {
			try {
				world.removeCat(Integer.parseInt(editorView.getRemoveText()));
			} catch (NumberFormatException e) {
				return;
			}
		} else if ("Sort ascending".equals(command)) {
			world.sortUp();
		} else if ("Breed".equals(command)) {
			if (checkIDs()) {
				int dam = Integer.parseInt(editorView.getDamText());
				int sire = Integer.parseInt(editorView.getSireText());
				world.breedCat(world.getCat(dam), world.getCat(sire), editorView.getFromInt(), editorView.getToInt());
			}
		}
		editorView.updateList(world.toString());
	}

	/**
	 * checks to make sure ids are valid cats in the world
	 * 
	 * @return
	 */
	private boolean checkIDs() {
		try {
			int dam = Integer.parseInt(editorView.getDamText());
			int sire = Integer.parseInt(editorView.getSireText());
			if (world.getCat(dam) == null || world.getCat(sire) == null) {
				throw new NumberFormatException();
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
