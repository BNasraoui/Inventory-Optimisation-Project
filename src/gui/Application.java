package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Stock.generateOrderLog;
import dataProcessing.CSVImportExport;
import dataProcessing.ItemProperties;
import dataProcessing.importManifest;
import stock.Item;
import stock.Stock;
import stock.Store;

/**
 * The application Class handles the creation of the GUI and implements
 * functionality to access the back-end classes
 * 
 * @author Alexander Parker
 *
 */
public class Application implements ActionListener {
	private JFrame applicationFrame;
	private JTable table;
	private JTextField logTextField;
	private JFileChooser fileChooser;
	JButton importItemPropertiesButton;
	private JButton loadSalesLogButton;
	private JButton importManifestButton;
	private JButton exportManifestButton;
	private Component errorFrame;
	private JScrollPane tableScrollPane;
	private CSVImportExport csvIO;
	private Store store;
	JLabel currentCapitalLabel;

	/**
	 * The constructor. Creates all components for the application;
	 */
	public Application() {

		// The frame for the application
		applicationFrame = new JFrame();
		applicationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Support pressing X
		applicationFrame.setTitle("Inventory Management Application");
		applicationFrame.getContentPane().setLayout(new BoxLayout(applicationFrame.getContentPane(), BoxLayout.X_AXIS));

		// The main panel
		JPanel applicationPanel = new JPanel();
		applicationFrame.getContentPane().add(applicationPanel);
		GridBagLayout applicationPanelgbl = new GridBagLayout();
		applicationPanelgbl.columnWidths = new int[] { 200, 600, 200 };
		applicationPanelgbl.rowHeights = new int[] { 35, 600, 0, 70, 0 };
		applicationPanelgbl.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		applicationPanelgbl.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		applicationPanel.setLayout(applicationPanelgbl);

		// The capital heading label
		JLabel capitalHeadingLabel = new JLabel("Capital");
		GridBagConstraints capitalHeadingLabelgbc = new GridBagConstraints();
		capitalHeadingLabelgbc.insets = new Insets(0, 0, 5, 5);
		capitalHeadingLabelgbc.gridx = 0;
		capitalHeadingLabelgbc.gridy = 0;
		applicationPanel.add(capitalHeadingLabel, capitalHeadingLabelgbc);

		// The current capital label
		currentCapitalLabel = new JLabel(Double.toString(Store.getInstance().getCapital()));
		GridBagConstraints currentCapitalLabelgbc = new GridBagConstraints();
		currentCapitalLabelgbc.insets = new Insets(0, 0, 5, 0);
		currentCapitalLabelgbc.gridx = 2;
		currentCapitalLabelgbc.gridy = 0;
		applicationPanel.add(currentCapitalLabel, currentCapitalLabelgbc);

		// The table
		table = createTable();

		// Needs to go inside a scroll pane ??? otherwise you don't get headings
		tableScrollPane = new JScrollPane();
		tableScrollPane.setViewportView(table);
		tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints tableScrollPaneConstraints = new GridBagConstraints();
		tableScrollPaneConstraints.gridwidth = 3;
		tableScrollPaneConstraints.insets = new Insets(0, 0, 5, 0);
		tableScrollPaneConstraints.fill = GridBagConstraints.BOTH;
		tableScrollPaneConstraints.gridx = 0;
		tableScrollPaneConstraints.gridy = 1;
		applicationPanel.add(tableScrollPane, tableScrollPaneConstraints);

		// The button panel
		JPanel buttonPanel = new JPanel();
		GridBagConstraints buttonPanelgbc = new GridBagConstraints();
		buttonPanelgbc.insets = new Insets(0, 0, 0, 0);
		buttonPanelgbc.gridx = 1;
		buttonPanelgbc.gridy = 2;
		applicationPanel.add(buttonPanel, buttonPanelgbc);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// The buttons
		importItemPropertiesButton = createButton("Import Item Properties");
		buttonPanel.add(importItemPropertiesButton);
		importItemPropertiesButton.addActionListener(this);
		exportManifestButton = createButton("Export Manifest");
		buttonPanel.add(exportManifestButton);
		exportManifestButton.addActionListener(this);
		exportManifestButton.setEnabled(false);
		importManifestButton = createButton("Import Manifest");
		buttonPanel.add(importManifestButton);
		importManifestButton.addActionListener(this);
		importManifestButton.setEnabled(false);
		loadSalesLogButton = createButton("Load Sales Log");
		buttonPanel.add(loadSalesLogButton);
		loadSalesLogButton.addActionListener(this);
		loadSalesLogButton.setEnabled(false);

		// The log
		logTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 3;
		applicationPanel.add(logTextField, gbc_textField);
		logTextField.setColumns(10);
		logTextField.setEditable(false);

		// Pack the frame
		applicationFrame.pack();

		// Get the size after packing and make it the minimum size allowed so there's no
		// clipping
		applicationFrame.setMinimumSize(new Dimension(applicationFrame.getWidth(), applicationFrame.getHeight()));
	}

	/**
	 * A simple button factory to create uniform buttons easily
	 * 
	 * @param text
	 *            - The text of the button
	 * @return A button with the specified properties
	 */
	private JButton createButton(String text) {
		JButton jbutton = new JButton(text);
		jbutton.setForeground(Color.BLACK);
		jbutton.setBackground(Color.LIGHT_GRAY);
		return jbutton;
	}

	/**
	 * A table factory to create similar looking tables
	 * 
	 * @return A table with either Item Properties or manifests
	 */
	private JTable createTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Quantity");
		model.addColumn("Manufacturing cost ($)");
		model.addColumn("Sell price ($)");
		model.addColumn("Reorder point");
		model.addColumn("Reorder amount");
		model.addColumn("Temperature (°C)");
		JTable jtable = new JTable(model); // Create the table
		jtable.setEnabled(false);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return jtable;
	}

	/**
	 * Initialises the file chooser to only show CSV files and directories
	 */
	public void initialiseFileChooser() {
		fileChooser = new JFileChooser();

		// A file filter to only show directories and CSV files, passed as a variable to
		// fileChooser
		FileFilter csvFilter = new FileFilter() {

			@Override
			public boolean accept(File f) {
				// Always show directories
				if (f.isDirectory()) {
					return true;
				}

				// Only show CSV files
				String extension = Utils.getExtension(f);
				if (extension != null) {
					if (extension.equals(Utils.csv)) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "CSV files";
			}

		};

		// Enable the filter & disable "All files"
		fileChooser.setFileFilter(csvFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}

	/**
	 * Shows the GUI
	 */
	public void showGUI() {
		initialiseFileChooser();
		applicationFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Handle importItemPropertiesButton action
		if (e.getSource() == importItemPropertiesButton) {
			int returnVal = fileChooser.showOpenDialog(applicationFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				try {
					ItemProperties.read(file.getAbsolutePath()); // Load in the properties
					logTextField.setText(logTextField.getText() + "Successfully loaded in "
							+ ItemProperties.ITEM_PROPERTIES.size() + " items. \n");

					// Display them in the table
					for (int i = 0; i < ItemProperties.ITEM_PROPERTIES.size(); i++) {
						String name = ItemProperties.ITEM_PROPERTIES.get(i).getName();
						String quantity = "0";
						String cost = Double.toString(ItemProperties.ITEM_PROPERTIES.get(i).getCost());
						String price = Double.toString(ItemProperties.ITEM_PROPERTIES.get(i).getPrice());
						String reorderpoint = Integer.toString(ItemProperties.ITEM_PROPERTIES.get(i).getReorderPoint());
						String reorderamount = Integer
								.toString(ItemProperties.ITEM_PROPERTIES.get(i).getReorderAmount());
						String temp = Double.toString(ItemProperties.ITEM_PROPERTIES.get(i).getTemperature());
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(new Object[] { name, quantity, cost, price, reorderpoint, reorderamount, temp });
					}

					importItemPropertiesButton.setEnabled(false); // Disable the button and enable the others
					importManifestButton.setEnabled(true);
					exportManifestButton.setEnabled(true);
					loadSalesLogButton.setEnabled(true);

					// Generate the first order log
					Stock firstOrder = new Stock();
					firstOrder.generateOrderLog();

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(errorFrame, "There was an error loading the item properties file");
				}
			}
		}

		// Handle exportManifestButton
		if (e.getSource() == exportManifestButton) {

		}

		// Handle importManifestButton
		if (e.getSource() == importManifestButton) {
			int returnVal = fileChooser.showOpenDialog(applicationFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				try {
					importManifest.read(file);
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					for (Item item : Store.getInstance().getCurrentInventory().getStock().keySet()) {
						for (int i = 0; i < model.getRowCount(); i++) {
							if (model.getValueAt(i, 0) == item.getName()) {
								model.setValueAt(Integer.toString(
										Store.getInstance().getCurrentInventory().getStock().get(item)), i, 1);
								((AbstractTableModel) model).fireTableCellUpdated(i, 1);
							}
						}
					}

					currentCapitalLabel.setText(Double.toString(Store.getInstance().getCapital()));

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(errorFrame, "There was an error loading the manifest");
				}
			}
		}

		// Handle loadSalesLogButton
		if (e.getSource() == loadSalesLogButton) {

		}
	}

	/**
	 * The entry point for the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Open up the application
		Application application = new Application();
		application.showGUI();
	}
}