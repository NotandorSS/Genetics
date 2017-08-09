package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditorView extends JPanel {
	private JButton breedButton = new JButton("Breed");
	private JLabel damLabel = new JLabel("Dam: ");
	private JLabel sireLabel = new JLabel("Sire: ");
	private JLabel fromLabel = new JLabel("From: ");
	private JLabel toLabel = new JLabel("To: ");
	private JTextField damField = new JTextField();
	private JTextField sireField = new JTextField();
	private JComboBox<Integer> fromBox;
	private JComboBox<Integer> toBox;
	private JButton premadeButton = new JButton("Add Premade");
	private JButton removeButton = new JButton("Remove");
	private JTextField removeField = new JTextField();
	private JButton sortUpButton = new JButton("Sort ascending");
	private JButton sortDownButton = new JButton ("Sort descending");
	private JTextArea list = new JTextArea();
	private JScrollPane scroll = new JScrollPane (list);
	
	public EditorView(){
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, View.frameWidth, View.frameHeight);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        Integer [] range = {new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6)};
        fromBox = new JComboBox<Integer>(range);
        toBox = new JComboBox<Integer>(range);
        
		addComponents();
		
	}

	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2,0,2,0);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 11;
		c.ipadx = 200;
		c.ipady = 100;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.weightx = .3;
		this.add(scroll, c);
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.fill = GridBagConstraints.NONE;
		c.weighty = 0;
		c.gridy = 1;
		this.add(breedButton, c);
		c.gridx = 1;
		this.add(damLabel, c);
		c.gridx = 3;
		this.add(sireLabel, c);
		c.gridx = 5;
		this.add(premadeButton, c);
		c.gridx = 6;
		this.add(removeButton, c);
		c.gridx = 8;
		this.add(sortUpButton, c);
		c.gridx = 9;
		this.add(sortDownButton, c);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		this.add(damField, c);
		c.gridx = 4;
		this.add(sireField, c);
		c.gridx = 7;
		this.add(removeField, c);
		c.gridy = 2;
		c.gridx = 2;
		this.add(fromBox, c);
		c.gridx = 4;
		this.add(toBox, c);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		this.add(fromLabel, c);
		c.gridx = 3;
		this.add(toLabel, c);
	}
	
	public void setButtonListeners(ActionListener listener) {
		premadeButton.addActionListener(listener);
		breedButton.addActionListener(listener);
		removeButton.addActionListener(listener);
		sortUpButton.addActionListener(listener);
	}
	
	public void updateList(String stuff){
		list.setText(stuff);
	}
	
	public String getDamText(){
		return damField.getText();
	}
	
	public String getSireText(){
		return sireField.getText();
	}
	
	public String getRemoveText(){
		return removeField.getText();
	}
	
	public int getFromInt() {
		return (Integer) fromBox.getSelectedItem();
	}
	
	public int getToInt() {
		return (Integer) toBox.getSelectedItem();
	}
}
