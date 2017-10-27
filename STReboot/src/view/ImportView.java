package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import enums.*;

public class ImportView extends JPanel {
	public JSeparator jSeparator1 = new JSeparator();
	public JSeparator jSeparator2 = new JSeparator();
	public JLabel iDLabel = new JLabel("Cat ID: ");
	public JLabel genderLabel = new JLabel("Gender: ");
	public JCheckBox buildCheck = new JCheckBox("Build: ");
	public JLabel colorLabel = new JLabel("Color: ");
	public JLabel tortiLabel = new JLabel("Tortoiseshell: ");
	public JLabel shadingLabel = new JLabel("Shading: ");
	public JLabel tabbyLabel = new JLabel("Tabby: ");
	public JLabel silverLabel = new JLabel("Silver: ");
	public JCheckBox charcoalCheck = new JCheckBox("Charcoal: ");
	public JLabel pointsLabel = new JLabel("Points: ");
	public JLabel whiteLabel = new JLabel("White: ");
	public JTextField iDBox = new JTextField();
	public JCheckBox genderBox = new JCheckBox("Female");
	public JComboBox<BUILD> buildBox = new JComboBox<BUILD>(BUILD.values());
	public JComboBox<COLOR> colorBox = new JComboBox<COLOR>(COLOR.values());
	public JCheckBox tortiBox = new JCheckBox("Yes");
	public JComboBox<SHADE> shadingBox = new JComboBox<SHADE>(SHADE.values());
	public JComboBox<TABBY> tabbyBox = new JComboBox<TABBY>(TABBY.values());
	public JCheckBox silverBox = new JCheckBox("Yes");
	public JCheckBox charcoalBox = new JCheckBox("Yes");
	public JComboBox<POINT> pointsBox = new JComboBox<POINT>(POINT.values());
	public JCheckBox whiteBox = new JCheckBox("Yes");
	public JCheckBox buildLoci = new JCheckBox();
	public ArrayList<JComboBox<ALLELE>> builds = new ArrayList<JComboBox<ALLELE>>();
	public JCheckBox colorLoci = new JCheckBox();
	public JComboBox<ALLELE> color1;
	public JComboBox<ALLELE> color2;
	public JComboBox<ALLELE> tanning1;
	public JComboBox<ALLELE> tanning2;
	public JComboBox<ALLELE> dilute1;
	public JComboBox<ALLELE> dilute2;
	public JComboBox<ALLELE> bleaching1;
	public JComboBox<ALLELE> bleaching2;
	public JComboBox<ALLELE> amber1;
	public JComboBox<ALLELE> amber2;
	public JCheckBox shadingLoci = new JCheckBox();
	public JComboBox<ALLELE> shading1;
	public JComboBox<ALLELE> shading2;
	public JComboBox<ALLELE> golden1;
	public JComboBox<ALLELE> golden2;
	public JCheckBox tabbyLoci = new JCheckBox();
	public JComboBox<ALLELE> tabby1;
	public JComboBox<ALLELE> tabby2;
	public JComboBox<ALLELE> tick1;
	public JComboBox<ALLELE> tick2;
	public JComboBox<ALLELE> spot1;
	public JComboBox<ALLELE> spot2;
	public JComboBox<ALLELE> mack1;
	public JComboBox<ALLELE> mack2;
	public JComboBox<ALLELE> rose1;
	public JComboBox<ALLELE> rose2;
	public JCheckBox pointLoci = new JCheckBox();
	public JComboBox<ALLELE> point1;
	public JComboBox<ALLELE> point2;
	public JCheckBox whiteLoci = new JCheckBox();
	public JComboBox<ALLELE> white1;
	public JComboBox<ALLELE> white2;
	public JCheckBox bunnyBox = new JCheckBox("Bunny");
	public JCheckBox lionBox = new JCheckBox("Lion");
	public JCheckBox chimBox = new JCheckBox("Chimaeric Tortoiseshell");
	public JCheckBox fangBox = new JCheckBox("Fangs");
	public JButton importButton = new JButton("Import");
	// if bTabby && bCharcoalCheck, charcoalBox is enabled
	private boolean bTabby = true;
	private boolean bCharcoalCheck = true;
	private boolean bShading = true;

	public ImportView() {
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, View.frameWidth, View.frameHeight);
		initComponents();
		addItemListeners();
		addComponents();
	}

	private void initComponents() {
		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
		buildCheck.setSelected(true);
		buildBox.setEnabled(false);
		pointsBox.setSelectedIndex(3);
		ALLELE[] build = { ALLELE.Wi, ALLELE.Co, ALLELE.Or, ALLELE.Su };
		for (int i = 0; i < 8; i++) {
			builds.add(new JComboBox<ALLELE>(build));
			builds.get(i).setEnabled(false);
		}
		ALLELE[] color = { ALLELE.O, ALLELE.o };
		color1 = new JComboBox<ALLELE>(color);
		color1.setEnabled(false);
		color2 = new JComboBox<ALLELE>(color);
		color2.setEnabled(false);
		ALLELE[] tanning = { ALLELE.B, ALLELE.b, ALLELE.bl };
		tanning1 = new JComboBox<ALLELE>(tanning);
		tanning1.setEnabled(false);
		tanning2 = new JComboBox<ALLELE>(tanning);
		tanning2.setEnabled(false);
		ALLELE[] dilute = { ALLELE.D, ALLELE.d };
		dilute1 = new JComboBox<ALLELE>(dilute);
		dilute1.setEnabled(false);
		dilute2 = new JComboBox<ALLELE>(dilute);
		dilute2.setEnabled(false);
		ALLELE[] bleaching = { ALLELE.Dm, ALLELE.dm };
		bleaching1 = new JComboBox<ALLELE>(bleaching);
		bleaching1.setEnabled(false);
		bleaching2 = new JComboBox<ALLELE>(bleaching);
		bleaching2.setEnabled(false);
		ALLELE[] amber = { ALLELE.E, ALLELE.e };
		amber1 = new JComboBox<ALLELE>(amber);
		amber1.setEnabled(false);
		amber2 = new JComboBox<ALLELE>(amber);
		amber2.setEnabled(false);
		ALLELE[] shading = { ALLELE.I, ALLELE.i };
		shading1 = new JComboBox<ALLELE>(shading);
		shading1.setEnabled(false);
		shading2 = new JComboBox<ALLELE>(shading);
		shading2.setEnabled(false);
		ALLELE[] golden = { ALLELE.G, ALLELE.g };
		golden1 = new JComboBox<ALLELE>(golden);
		golden1.setEnabled(false);
		golden2 = new JComboBox<ALLELE>(golden);
		golden2.setEnabled(false);
		ALLELE[] tabby = { ALLELE.A, ALLELE.Ap, ALLELE.a };
		tabby1 = new JComboBox<ALLELE>(tabby);
		tabby1.setEnabled(false);
		tabby2 = new JComboBox<ALLELE>(tabby);
		tabby2.setEnabled(false);
		ALLELE[] ticked = { ALLELE.Ta, ALLELE.t };
		tick1 = new JComboBox<ALLELE>(ticked);
		tick1.setEnabled(false);
		tick2 = new JComboBox<ALLELE>(ticked);
		tick2.setEnabled(false);
		ALLELE[] spotted = { ALLELE.Sp, ALLELE.sp };
		spot1 = new JComboBox<ALLELE>(spotted);
		spot1.setEnabled(false);
		spot2 = new JComboBox<ALLELE>(spotted);
		spot2.setEnabled(false);
		ALLELE[] mackerel = { ALLELE.Mc, ALLELE.mc };
		mack1 = new JComboBox<ALLELE>(mackerel);
		mack1.setEnabled(false);
		mack2 = new JComboBox<ALLELE>(mackerel);
		mack2.setEnabled(false);
		ALLELE[] rosetted = { ALLELE.Ro, ALLELE.ro };
		rose1 = new JComboBox<ALLELE>(rosetted);
		rose1.setEnabled(false);
		rose2 = new JComboBox<ALLELE>(rosetted);
		rose2.setEnabled(false);
		ALLELE[] point = { ALLELE.C, ALLELE.cs, ALLELE.cb };
		point1 = new JComboBox<ALLELE>(point);
		point1.setEnabled(false);
		point2 = new JComboBox<ALLELE>(point);
		point2.setEnabled(false);
		ALLELE[] white = { ALLELE.S, ALLELE.s };
		white1 = new JComboBox<ALLELE>(white);
		white1.setEnabled(false);
		white2 = new JComboBox<ALLELE>(white);
		white2.setEnabled(false);
	}

	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 12;
		c.fill = GridBagConstraints.VERTICAL;
		this.add(jSeparator1, c);
		c.gridx = 9;
		this.add(jSeparator2, c);
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(iDLabel, c);
		c.gridy = 1;
		this.add(genderLabel, c);
		c.gridy = 2;
		this.add(buildCheck, c);
		c.gridy = 3;
		this.add(colorLabel, c);
		c.gridy = 4;
		this.add(tortiLabel, c);
		c.gridy = 5;
		this.add(shadingLabel, c);
		c.gridy = 6;
		this.add(tabbyLabel, c);
		c.gridy = 7;
		this.add(silverLabel, c);
		c.gridy = 8;
		this.add(charcoalCheck, c);
		c.gridy = 9;
		this.add(pointsLabel, c);
		c.gridy = 10;
		this.add(whiteLabel, c);
		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		this.add(iDBox, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		this.add(genderBox, c);
		c.gridy = 2;
		this.add(buildBox, c);
		c.gridy = 3;
		this.add(colorBox, c);
		c.gridy = 4;
		this.add(tortiBox, c);
		c.gridy = 5;
		this.add(shadingBox, c);
		c.gridy = 6;
		this.add(tabbyBox, c);
		c.gridy = 7;
		this.add(silverBox, c);
		c.gridy = 8;
		this.add(charcoalBox, c);
		c.gridy = 9;
		this.add(pointsBox, c);
		c.gridy = 10;
		this.add(whiteBox, c);
		c.gridx = 3;
		c.gridy = 0;
		this.add(buildLoci, c);
		for (int i = 0; i < 8; i++) {
			c.gridy = 1 + i;
			this.add(builds.get(i), c);
		}
		c.gridx = 4;
		c.gridy = 0;
		this.add(colorLoci, c);
		c.gridy = 1;
		this.add(color1, c);
		c.gridy = 2;
		this.add(color2, c);
		c.gridy = 3;
		this.add(tanning1, c);
		c.gridy = 4;
		this.add(tanning2, c);
		c.gridy = 5;
		this.add(dilute1, c);
		c.gridy = 6;
		this.add(dilute2, c);
		c.gridy = 7;
		this.add(bleaching1, c);
		c.gridy = 8;
		this.add(bleaching2, c);
		c.gridy = 9;
		this.add(amber1, c);
		c.gridy = 10;
		this.add(amber2, c);
		c.gridx = 5;
		c.gridy = 0;
		this.add(shadingLoci, c);
		c.gridy = 1;
		this.add(shading1, c);
		c.gridy = 2;
		this.add(shading2, c);
		c.gridy = 3;
		this.add(golden1, c);
		c.gridy = 4;
		this.add(golden2, c);
		c.gridx = 6;
		c.gridy = 0;
		this.add(tabbyLoci, c);
		c.gridy = 1;
		this.add(tabby1, c);
		c.gridy = 2;
		this.add(tabby2, c);
		c.gridy = 3;
		this.add(tick1, c);
		c.gridy = 4;
		this.add(tick2, c);
		c.gridy = 5;
		this.add(spot1, c);
		c.gridy = 6;
		this.add(spot2, c);
		c.gridy = 7;
		this.add(mack1, c);
		c.gridy = 8;
		this.add(mack2, c);
		c.gridy = 9;
		this.add(rose1, c);
		c.gridy = 10;
		this.add(rose2, c);
		c.gridx = 7;
		c.gridy = 0;
		this.add(pointLoci, c);
		c.gridy = 1;
		this.add(point1, c);
		c.gridy = 2;
		this.add(point2, c);
		c.gridx = 8;
		c.gridy = 0;
		this.add(whiteLoci, c);
		c.gridy = 1;
		this.add(white1, c);
		c.gridy = 2;
		this.add(white2, c);
		c.gridx = 10;
		c.gridy = 0;
		this.add(bunnyBox, c);
		c.gridy = 1;
		this.add(lionBox, c);
		c.gridy = 3;
		this.add(fangBox, c);
		c.gridy = 2;
		c.gridwidth = 2;
		this.add(chimBox, c);
		c.gridx = 11;
		c.gridwidth = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(importButton, c);
	}

	private void addItemListeners() {
		buildCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				buildCheckItemStateChanged(evt);
			}
		});

		charcoalCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				charcoalCheckItemStateChanged(evt);
			}
		});

		colorBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				colorBoxItemStateChanged(evt);
			}
		});

		shadingBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				shadingBoxItemStateChanged(evt);
			}
		});

		tabbyBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				tabbyBoxItemStateChanged(evt);
			}
		});

		whiteBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				whiteBoxItemStateChanged(evt);
			}
		});

		buildLoci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				buildLociItemStateChanged(evt);
			}
		});

		colorLoci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				colorLociItemStateChanged(evt);
			}
		});

		shadingLoci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				shadingLociItemStateChanged(evt);
			}
		});

		tabbyLoci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				tabbyLociItemStateChanged(evt);
			}
		});

		pointLoci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				pointLociItemStateChanged(evt);
			}
		});

		whiteLoci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				whiteLociItemStateChanged(evt);
			}
		});
	}

	protected void buildCheckItemStateChanged(ItemEvent evt) {
		buildBox.setEnabled(!buildBox.isEnabled());
	}

	protected void charcoalCheckItemStateChanged(ItemEvent evt) {
		bCharcoalCheck = !bCharcoalCheck;
		charcoalBox.setEnabled(bTabby && bCharcoalCheck);
		if (charcoalBox.isSelected()) {
			charcoalBox.setSelected(false);
		}

	}

	protected void colorBoxItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			if (evt.getItem().equals(COLOR.ORANGE) || evt.getItem().equals(COLOR.CREAM)
					|| evt.getItem().equals(COLOR.APRI)) {
				tortiBox.setEnabled(false);
			} else {
				tortiBox.setEnabled(true);
			}
		}
	}

	protected void shadingBoxItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED && evt.getItem().equals(SHADE.NA)) {
			bShading = true;
		} else {
			bShading = false;
		}
		silverBox.setEnabled(bShading);
	}

	protected void tabbyBoxItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED && evt.getItem().equals(TABBY.SELF)) {
			bTabby = false;
			silverLabel.setText("Smoked: ");
		} else {
			bTabby = true;
			silverLabel.setText("Silver: ");
		}
		charcoalBox.setEnabled(bTabby && bCharcoalCheck);
	}

	protected void whiteBoxItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			tortiLabel.setText("Calico: ");
		} else {
			tortiLabel.setText("Tortoiseshell: ");
		}
	}

	protected void buildLociItemStateChanged(ItemEvent evt) {
		for (int i = 0; i < 8; i++) {
			builds.get(i).setEnabled(!builds.get(i).isEnabled());
		}
	}

	protected void colorLociItemStateChanged(ItemEvent evt) {
		color1.setEnabled(!color1.isEnabled());
		color2.setEnabled(!color2.isEnabled());
		tanning1.setEnabled(!tanning1.isEnabled());
		tanning2.setEnabled(!tanning2.isEnabled());
		dilute1.setEnabled(!dilute1.isEnabled());
		dilute2.setEnabled(!dilute2.isEnabled());
		bleaching1.setEnabled(!bleaching1.isEnabled());
		bleaching2.setEnabled(!bleaching2.isEnabled());
		amber1.setEnabled(!amber1.isEnabled());
		amber2.setEnabled(!amber2.isEnabled());
	}

	protected void shadingLociItemStateChanged(ItemEvent evt) {
		shading1.setEnabled(!shading1.isEnabled());
		shading2.setEnabled(!shading2.isEnabled());
		golden1.setEnabled(!golden1.isEnabled());
		golden2.setEnabled(!golden2.isEnabled());
	}

	protected void tabbyLociItemStateChanged(ItemEvent evt) {
		tabby1.setEnabled(!tabby1.isEnabled());
		tabby2.setEnabled(!tabby2.isEnabled());
		tick1.setEnabled(!tick1.isEnabled());
		tick2.setEnabled(!tick2.isEnabled());
		spot1.setEnabled(!spot1.isEnabled());
		spot2.setEnabled(!spot2.isEnabled());
		mack1.setEnabled(!mack1.isEnabled());
		mack2.setEnabled(!mack2.isEnabled());
		rose1.setEnabled(!rose1.isEnabled());
		rose2.setEnabled(!rose2.isEnabled());
	}

	protected void pointLociItemStateChanged(ItemEvent evt) {
		point1.setEnabled(!point1.isEnabled());
		point2.setEnabled(!point2.isEnabled());
	}

	protected void whiteLociItemStateChanged(ItemEvent evt) {
		white1.setEnabled(!white1.isEnabled());
		white2.setEnabled(!white2.isEnabled());
	}

	public void setImportButtonListener(ActionListener listener) {
		importButton.addActionListener(listener);
	}
}
