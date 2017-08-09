package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import enums.ALLELE;
import enums.BUILD;
import enums.COLOR;
import enums.MUTATION;
import enums.POINT;
import enums.SHADE;
import enums.TABBY;
import model.Cat;
import model.World;
import view.ImportView;

public class ImportController implements ActionListener {
	private ImportView importView = new ImportView();
	private World world;

	public ImportController(World world) {
		this.world = world;
		importView.setImportButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Cat c = null;
		try {
			int id = Integer.parseInt(importView.iDBox.getText());
			if (id <= 0 || id > Cat.getPopulation() || world.getCat(id) != null) {
				throw new NumberFormatException();
			}
			c = new Cat(id);
		} catch (NumberFormatException e) {
			return;
		} // try catch
		c.setGender(importView.genderBox.isSelected());

		if (importView.buildLoci.isSelected()) {
			ALLELE[] a = new ALLELE[8];
			for (int i = 0; i < 8; i++) {
				a[i] = (ALLELE) importView.builds.get(i).getSelectedItem();
			}
			c.setBuild(a);
		} else if (importView.buildCheck.isSelected()) {
			c.randomBuild();
		} else {
			c.setBuild((BUILD) importView.buildBox.getSelectedItem());
		} // if build

		if (importView.colorLoci.isSelected()) {
			ALLELE[] a = new ALLELE[10];
			a[0] = (ALLELE) importView.color1.getSelectedItem();
			a[1] = (ALLELE) importView.color2.getSelectedItem();
			a[2] = (ALLELE) importView.tanning1.getSelectedItem();
			a[3] = (ALLELE) importView.tanning2.getSelectedItem();
			a[4] = (ALLELE) importView.dilute1.getSelectedItem();
			a[5] = (ALLELE) importView.dilute2.getSelectedItem();
			a[6] = (ALLELE) importView.bleaching1.getSelectedItem();
			a[7] = (ALLELE) importView.bleaching2.getSelectedItem();
			a[8] = (ALLELE) importView.amber1.getSelectedItem();
			a[9] = (ALLELE) importView.amber2.getSelectedItem();
			c.setColor(a);
		} else {
			c.setColor((COLOR) importView.colorBox.getSelectedItem());
		} // if color

		if (importView.tortiBox.isSelected()) {
			c.setTorti();
		} // if torti

		if (importView.shadingLoci.isSelected()) {
			ALLELE[] a = new ALLELE[4];
			a[0] = (ALLELE) importView.shading1.getSelectedItem();
			a[1] = (ALLELE) importView.shading2.getSelectedItem();
			a[2] = (ALLELE) importView.golden1.getSelectedItem();
			a[3] = (ALLELE) importView.golden2.getSelectedItem();
			c.setShading(a);
		} else {
			c.setShading((SHADE) importView.shadingBox.getSelectedItem(), importView.silverBox.isSelected());
		} // if shaded

		if (importView.tabbyLoci.isSelected()) {
			ALLELE[] a = new ALLELE[10];
			a[0] = (ALLELE) importView.tabby1.getSelectedItem();
			a[1] = (ALLELE) importView.tabby2.getSelectedItem();
			a[2] = (ALLELE) importView.tick1.getSelectedItem();
			a[3] = (ALLELE) importView.tick2.getSelectedItem();
			a[4] = (ALLELE) importView.spot1.getSelectedItem();
			a[5] = (ALLELE) importView.spot2.getSelectedItem();
			a[6] = (ALLELE) importView.mack1.getSelectedItem();
			a[7] = (ALLELE) importView.mack2.getSelectedItem();
			a[8] = (ALLELE) importView.rose1.getSelectedItem();
			a[9] = (ALLELE) importView.rose2.getSelectedItem();
			c.setTabby(a);
		} else {
			c.setTabby((TABBY) importView.tabbyBox.getSelectedItem(), importView.charcoalBox.isSelected());
		} // if tabby
		if (importView.pointLoci.isSelected()) {
			ALLELE[] a = new ALLELE[2];
			a[0] = (ALLELE) importView.point1.getSelectedItem();
			a[1] = (ALLELE) importView.point2.getSelectedItem();
			c.setPoints(a);
		} else {
			c.setPoints((POINT) importView.pointsBox.getSelectedItem());
		} // if point
		if (importView.whiteLoci.isSelected()) {
			ALLELE[] a = new ALLELE[2];
			a[0] = (ALLELE) importView.white1.getSelectedItem();
			a[1] = (ALLELE) importView.white2.getSelectedItem();
			c.setWhite(a);
		} else {
			c.setWhite(importView.whiteBox.isSelected());
		} // if white

		if (importView.bunnyBox.isSelected()) {
			c.giveMutation(MUTATION.BUNNY);
		}
		if (importView.lionBox.isSelected()) {
			c.giveMutation(MUTATION.LION);
		}
		if (importView.chimBox.isSelected()) {
			c.giveMutation(MUTATION.CHIM);
		} // if mutations
		
		world.addCat(c);
	}

	public ImportView getImportView() {
		return importView;
	}

}
