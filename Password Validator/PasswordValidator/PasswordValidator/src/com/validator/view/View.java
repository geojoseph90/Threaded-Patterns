package com.validator.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.validator.controller.MainController;

public class View extends JFrame {
	JPanel panel = new JPanel();
	JTextArea textArea = new JTextArea();
	JLabel label = new JLabel("Password: ");
	JLabel resultLabel = new JLabel();
	JTextField inputField = new JTextField();
	JButton button = new JButton("Validate");
	String[] options = { "None", "Offensive word", "Easy guess",
			"Salt password" };
	JLabel option1 = new JLabel("1st filter: ");
	JLabel option2 = new JLabel("2nd filter: ");
	JLabel option3 = new JLabel("3rd filter: ");
	JComboBox optionsList1 = new JComboBox(options);
	JComboBox optionsList2 = new JComboBox(options);
	JComboBox optionsList3 = new JComboBox(options);

	public View(String title) {
		super(title);

		// creating layout manager
		setLayout(new BorderLayout());

		// create swing component
		panel.setLayout(new GridBagLayout());
		getContentPane().add(panel);
		optionsList1.setSelectedItem("Offensive word");
		optionsList2.setSelectedItem("Easy guess");
		optionsList3.setSelectedItem("Salt password");

		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.EAST;

		GridBagConstraints center = new GridBagConstraints();
		center.weightx = 2.0;
		center.fill = GridBagConstraints.HORIZONTAL;
		center.gridwidth = GridBagConstraints.CENTER;

		GridBagConstraints right = new GridBagConstraints();
		right.weightx = 2.0;
		right.fill = GridBagConstraints.HORIZONTAL;
		right.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(label, left);
		panel.add(inputField, center);
		panel.add(resultLabel, right);
		panel.add(option1, left);
		panel.add(optionsList1, right);
		panel.add(option2, left);
		panel.add(optionsList2, right);
		panel.add(option3, left);
		panel.add(optionsList3, right);

		// Add swing components to the content pane
		Container container = getContentPane();
		container.add(button, BorderLayout.SOUTH);
		container.add(panel, BorderLayout.CENTER);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resultLabel.setText("");
				if(inputField.getText()==null || inputField.getText().length()==0){
					warnEmpty();
					return;
				}
					
				MainController controller = new MainController();
				List<String> filterList = new ArrayList<String>();
				if (!optionsList1.getSelectedItem().toString().toLowerCase()
						.equals("none")) {
					if (filterList.contains(optionsList1.getSelectedItem()
							.toString())) {
						warn(optionsList1.getSelectedItem()
							.toString());
						return;

					}
					filterList.add(optionsList1.getSelectedItem().toString());
				}
				if (!optionsList2.getSelectedItem().toString().toLowerCase()
						.equals("none")) {
					if (filterList.contains(optionsList2.getSelectedItem()
							.toString())) {
						warn(optionsList2.getSelectedItem()
								.toString());
						return;

					}
					filterList.add(optionsList2.getSelectedItem().toString());
				}
				if (!optionsList3.getSelectedItem().toString().toLowerCase()
						.equals("none")) {
					if (filterList.contains(optionsList3.getSelectedItem()
							.toString())) {
						warn(optionsList3.getSelectedItem()
								.toString());
						return;
					}
					filterList.add(optionsList3.getSelectedItem().toString());
				}
				String[] filterOrder =filterList.toArray(new String[filterList.size()]);
				String password = inputField.getText();
				controller.validatePassword(password, filterOrder);

			}

			public void warn(String filter) {
				{
					JOptionPane.showMessageDialog(null,
							"Error: filter - "+filter+" cannot be selected multiple times.",
							"Error Massage", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			public void warnEmpty() {
				{
					JOptionPane.showMessageDialog(null,
							"Password is empty. Cannot validate",
							"Error Massage", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		inputField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				clear();
			}

			public void removeUpdate(DocumentEvent e) {
				clear();
			}

			public void insertUpdate(DocumentEvent e) {
				clear();
			}

			public void clear() {
				resultLabel.setText("");
			}
		});

	}

	public void setResultLabel(boolean result) {
		if (result) {
			resultLabel.setText("valid password");
		} else {
			resultLabel.setText("invalid password");
		}
	}

	public JLabel getResultLable() {
		return resultLabel;
	}

}
