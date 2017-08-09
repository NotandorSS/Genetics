package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import enums.BUILD;
import enums.COLOR;
import enums.POINT;
import enums.SHADE;
import enums.TABBY;

public class CreateView extends JPanel {
	private JLabel catLabel = new JLabel("Cat ID: ");
	private JLabel genderLabel = new JLabel("Gender: ");
	private JLabel buildLabel = new JLabel("Build: ");
	private JLabel colorLabel = new JLabel("Color: ");
	private JLabel shadingLabel = new JLabel("Shading: ");
	private JLabel tabbyLabel = new JLabel("Tabby: ");
	private JLabel pointsLabel = new JLabel("Points: ");
	private JLabel careLabel = new JLabel("Don't care about...");
	private JTextField catID = new JTextField();
	private ButtonGroup genderGroup = new ButtonGroup();
	private JRadioButton female = new JRadioButton("Female");
	private JRadioButton male = new JRadioButton("Male");
	private JComboBox<BUILD> build = new JComboBox<BUILD>(BUILD.values());
	private JComboBox<COLOR> color = new JComboBox<COLOR>(COLOR.values());
	private JComboBox<SHADE> shading = new JComboBox<SHADE>(SHADE.values());
	private JComboBox<TABBY> tabby = new JComboBox<TABBY>(TABBY.values());
	private JComboBox<POINT> points = new JComboBox<POINT>(POINT.values());
	private JCheckBox torti = new JCheckBox("tortoiseshell");
	private JCheckBox white = new JCheckBox("white");
	private JCheckBox silver = new JCheckBox("silver");
	private JCheckBox charcoal = new JCheckBox("charcoal");
	private JCheckBox genderCheck = new JCheckBox("Gender");
	private JCheckBox buildCheck = new JCheckBox("Build");
	private JCheckBox colorCheck = new JCheckBox("Color");
	private JCheckBox tortiCheck = new JCheckBox("Tortoiseshell");
	private JCheckBox whiteCheck = new JCheckBox("White");
	private JCheckBox shadingCheck = new JCheckBox("Shading");
	private JCheckBox tabbyCheck = new JCheckBox("Tabby");
	private JCheckBox silverCheck = new JCheckBox("Silver");
	private JCheckBox charcoalCheck = new JCheckBox("Charcoal");
	private JCheckBox pointsCheck = new JCheckBox("Points");
	private JButton createButton = new JButton("Create!");

	private boolean bTortiCheck = true;
	private boolean bTColor = true;
	
	private boolean bShading = true;
	private boolean bTabby = true;
	private boolean bSilverCheck = true;

	public CreateView() {
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, View.frameWidth, View.frameHeight);
		genderGroup.add(female);
		genderGroup.add(male);
		female.setSelected(true);
		points.setSelectedIndex(3);
		catID.setToolTipText("Only use when filling in blank ids.");
		addItemListeners();

		addComponents();
		
	}
	
	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2,0,2,0);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0;
		this.add(careLabel, c);
		c.gridy = 1;
		this.add(genderCheck, c);
		c.gridy = 2;
		this.add(buildCheck, c);
		c.gridy = 3;
		this.add(colorCheck, c);
		c.gridy = 5;
		this.add(shadingCheck, c);
		c.gridy = 6;
		this.add(tabbyCheck, c);
		c.gridy = 8;
		this.add(pointsCheck, c);
		c.gridwidth = 1;
		c.gridy = 4;
		this.add(tortiCheck, c);
		c.gridy = 7;
		this.add(silverCheck, c);
		c.gridx = 1;
		c.gridy = 4;
		this.add(whiteCheck, c);
		c.gridy = 7;
		this.add(charcoalCheck, c);
		
		c.gridx = 2;
		c.gridy = 0;
		this.add(catLabel, c);
		c.gridy = 1;
		this.add(genderLabel, c);
		c.gridy = 2;
		this.add(buildLabel, c);
		c.gridy = 3;
		this.add(colorLabel, c);
		c.gridy = 5;
		this.add(shadingLabel, c);
		c.gridy = 6;
		this.add(tabbyLabel, c);
		c.gridy = 8;
		this.add(pointsLabel, c);
		
		
		c.gridy = 0;
		c.gridx = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(catID, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 2;
		this.add(build, c);
		c.gridy = 3;
		this.add(color, c);
		c.gridy = 5;
		this.add(shading, c);
		c.gridy = 6;
		this.add(tabby, c);
		c.gridy = 8;
		this.add(points, c);
		
		c.gridwidth = 1;
		c.gridy = 1;
		this.add(female, c);
		c.gridy = 4;
		this.add(torti, c);
		c.gridy = 7;
		this.add(silver, c);
		c.gridx = 4;
		c.gridy = 1;
		this.add(male, c);
		c.gridy = 4;
		this.add(white, c);
		c.gridy = 7;
		this.add(charcoal, c);
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 9;
		c.gridwidth = 3;
		c.gridx = 2;
		this.add(createButton, c);
	}

	/**
	 * adds itemListeners to components that change the state of another component
	 */
	private void addItemListeners() {
		
		color.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				colorItemStateChanged(evt);
			}
		});
		
		shading.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				shadingItemStateChanged(evt);
			}
		});
		
		tabby.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				tabbyItemStateChanged(evt);
			}
		});
		
		white.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				whiteItemStateChanged(evt);
			}
		});

		genderCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				genderCheckItemStateChanged(evt);
			}
		});

		buildCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				buildCheckItemStateChanged(evt);
			}
		});

		colorCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				colorCheckItemStateChanged(evt);
			}
		});

		tortiCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				tortiCheckItemStateChanged(evt);
			}
		});

		whiteCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				whiteCheckItemStateChanged(evt);
			}
		});

		shadingCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				shadingCheckItemStateChanged(evt);
			}
		});

		tabbyCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				tabbyCheckItemStateChanged(evt);
			}
		});

		silverCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				silverCheckItemStateChanged(evt);
			}
		});

		charcoalCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				charcoalCheckItemStateChanged(evt);
			}
		});

		pointsCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				pointsCheckItemStateChanged(evt);
			}
		});
	}

	protected void colorItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			switch ((COLOR) evt.getItem()) {
			case ORANGE:
				bTColor = false;
				break;
			case CREAM:
				bTColor = false;
				break;
			case APRI:
				bTColor = false;
				break;
			default:
				bTColor = true;
			}
			torti.setEnabled(bTortiCheck && bTColor);
		}

	}
	
	protected void shadingItemStateChanged(ItemEvent evt) {
		if (evt.getItem().equals(SHADE.NA)) {
			bShading = evt.getStateChange() == ItemEvent.SELECTED;
			silver.setEnabled(bSilverCheck && bShading && bTabby);
		}
	}
	
	protected void tabbyItemStateChanged(ItemEvent evt) {
		if (evt.getItem().equals(TABBY.SELF)) {
			bTabby = evt.getStateChange() == ItemEvent.DESELECTED;
			silver.setEnabled(bSilverCheck && bShading && bTabby);
		}
	}
	
	protected void whiteItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			torti.setText("calico");
			tortiCheck.setText("Calico");
		} else {
			torti.setText("tortoiseshell");
			tortiCheck.setText("Tortoiseshell");
		}
	}

	protected void genderCheckItemStateChanged(ItemEvent evt) {
		female.setEnabled(!female.isEnabled());
		male.setEnabled(!male.isEnabled());
	}

	protected void buildCheckItemStateChanged(ItemEvent evt) {
		build.setEnabled(!build.isEnabled());
	}

	protected void colorCheckItemStateChanged(ItemEvent evt) {
		color.setEnabled(!color.isEnabled());
	}

	protected void tortiCheckItemStateChanged(ItemEvent evt) {
		bTortiCheck = !bTortiCheck;
		torti.setEnabled(bTortiCheck && bTColor);
	}

	protected void whiteCheckItemStateChanged(ItemEvent evt) {
		white.setEnabled(!white.isEnabled());
	}

	protected void shadingCheckItemStateChanged(ItemEvent evt) {
		shading.setEnabled(!shading.isEnabled());
	}

	protected void tabbyCheckItemStateChanged(ItemEvent evt) {
		tabby.setEnabled(!tabby.isEnabled());
	}

	protected void silverCheckItemStateChanged(ItemEvent evt) {
		bSilverCheck = !bSilverCheck;
		silver.setEnabled(bSilverCheck && bShading && bTabby);
	}

	protected void charcoalCheckItemStateChanged(ItemEvent evt) {
		charcoal.setEnabled(!charcoal.isEnabled());
	}

	protected void pointsCheckItemStateChanged(ItemEvent evt) {
		points.setEnabled(!points.isEnabled());
	}
	
	public void setCreateButtonListener(ActionListener listener) {
		createButton.addActionListener(listener);
	}
	
	public char getGenderSelected(){
		return (genderCheck.isSelected() || female.isSelected()) ? 'f' : 'm';
	}

	public String getCatIDText() {
		return catID.getText();
	}

}