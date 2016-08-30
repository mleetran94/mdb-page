import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;


@SuppressWarnings("serial")
public class MDBProj2 extends JFrame {

	//swing types
	private JPanel contentPane;
	private JTable table;
	private JTextField txtDBLoad;
	private JLabel lblDBDir;
	private JLabel lblFolderSaveDir;
	private JTextField txtSaveDir;
	private JButton btnLoadTable;
	private JTextField txtLabelNameCount;
	private JTextField txtSelectBLOB;
	
	private String[] namingOfFile = new String[3];//string array of the column names specfied by user from combo boxes
	private String nameOfFile;//file name of the browser loaded database file
	private int columnLblCount;//number of column labels that the user specified to be in the naming convention (size of namingOfFile array)
	private String LBDColName;//specified column of blob data
	//column name & type name arrays are initialized with nothing in them because combo boxes throw error if loaded with null items
	private String[] columnNames = new String[0];//meta data for column names
	private String[] columnTypeNames = new String[0];//meta data for column type names
	private String[] columnBlobNames = new String[0];//meta data for column names containing blob type data
	private boolean successfulLoad = false;//true upon successful loading and modeling of table. only used as a marker for loading labels into combo boxes one time
	private boolean successfulLoad2 = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MDBProj2 frame = new MDBProj2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
	
	/**
	 * Create the frame.
	 */
	public MDBProj2() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 888);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnExportFiles = new JButton("Export...");	
		btnExportFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				
				//connection belongs to java.sql.Connection class
				Connection myConn = null;
				Statement myStmnt = null;
				ResultSet rs = null;
				
				InputStream input = null;
				FileOutputStream output = null;
				
				String filePath = txtDBLoad.getText();
				//System.out.println("File Path Location:" + filePath);
				String folderPath = txtSaveDir.getText();
				//System.out.println("Folder Path Directory:" + folderPath);
				
				try {
					myConn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
					
					//query to execute
					myStmnt = myConn.createStatement();
					String query = "select * from " + nameOfFile;
					rs = myStmnt.executeQuery(query);
					
					//Setup a handle to the file
					while(rs.next()){
						if (rs.getBinaryStream(LBDColName) != null) {
							String conventionName = "";
							
							for(int i = 0; i < columnLblCount; i++){
								if(namingOfFile[i] != null){
									conventionName = conventionName + rs.getString(namingOfFile[i]) + "_";
								}
							}
							conventionName = conventionName.substring(0, conventionName.length()-1)	;//removes last _
							//String imgName = "" + rs.getString("key") + "_" + rs.getString("f_name") + "_" + rs.getString("l_name");
							File theFile = new File("" + folderPath + "\\" + conventionName + ".jpg");
							output = new FileOutputStream(theFile);
							input = rs.getBinaryStream(LBDColName);
							//System.out.println("Reading id from database...");
							//System.out.println(query);
							
							byte[] buffer = new byte[1024];
							while (input.read(buffer) > 0){
								output.write(buffer);
							}
							//System.out.println("\nSaved file: " + theFile.getAbsolutePath());
							//System.out.println("\nCompleted successfully at counter: " + rs.getRow() + " !");
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (output != null) {
						try {
							output.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		});
		btnExportFiles.setBounds(825, 565, 150, 23);
		contentPane.add(btnExportFiles);
		
		JScrollPane SPaneModelTable = new JScrollPane();
		SPaneModelTable.setBounds(45, 259, 930, 290);
		contentPane.add(SPaneModelTable);
		
		table = new JTable();
		SPaneModelTable.setViewportView(table);
		
		txtDBLoad = new JTextField();
		txtDBLoad.setBackground(Color.WHITE);
		txtDBLoad.setEditable(false);
		txtDBLoad.setBounds(45, 80, 597, 20);
		contentPane.add(txtDBLoad);
		txtDBLoad.setColumns(10);
		
		lblDBDir = new JLabel("Database loaded directory:");
		lblDBDir.setBounds(171, 42, 219, 14);
		contentPane.add(lblDBDir);
		
		lblFolderSaveDir = new JLabel("Folder saved directory:");
		lblFolderSaveDir.setBounds(210, 129, 219, 14);
		contentPane.add(lblFolderSaveDir);
		
		txtSaveDir = new JTextField();
		txtSaveDir.setBackground(Color.WHITE);
		txtSaveDir.setEditable(false);
		txtSaveDir.setBounds(45, 165, 597, 20);
		contentPane.add(txtSaveDir);
		txtSaveDir.setColumns(10);
		
		
		//Create a file load chooser
		final JFileChooser fcLoad = new JFileChooser();
		
		JButton btnImportDb = new JButton("Import DB");
		btnImportDb.setBackground(UIManager.getColor("Button.background"));
		btnImportDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fcLoad.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fcLoad.getSelectedFile();
					String path = file.getPath();
					nameOfFile = FilenameUtils.removeExtension(file.getName());
					
					txtDBLoad.setText(path);
				} else {
					//nothing happens yet
					txtDBLoad.setText("No path");
				}
			}
		});
		btnImportDb.setBounds(45, 35, 115, 29);
		contentPane.add(btnImportDb);
		
		//Create a file save chooser
		final JFileChooser fcSave = new JFileChooser();
		
		JButton btnSaveLocation = new JButton("Save location");
		btnSaveLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				fcSave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if(fcSave.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					String path = fcSave.getSelectedFile().toString();
					
					txtSaveDir.setText(path);
				} else {
					//nothing happens yet
					txtSaveDir.setText("No path");
				}
			}
		});
		btnSaveLocation.setBounds(45, 122, 150, 29);
		contentPane.add(btnSaveLocation);
		
		
		//button collects meta data, and models table into j scroll pane for user to reference when deciding input
		btnLoadTable = new JButton("Load Table");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//connection belongs to java.sql.Connection class
				Connection myConn = null;
				Statement myStmnt = null;
				ResultSet rs = null;
				ResultSetMetaData mrs = null;
				
				//data collection variables
				String[] colNames2;
				String[] colTypes2;
				int blobCount = 0;
				
				String filePath = txtDBLoad.getText();
				//System.out.println("File Path Location:" + filePath);
				
				try {
					myConn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
					
					//query to execute
					myStmnt = myConn.createStatement();
					String query = "select * from " + nameOfFile;
					rs = myStmnt.executeQuery(query);
					
					//obtains column information for user to specification later in program 
					mrs = rs.getMetaData();
					colNames2 = new String[mrs.getColumnCount()];
					colTypes2 = new String[mrs.getColumnCount()];
					for(int i = 1; i <= mrs.getColumnCount(); i++){
						colNames2[i-1] = mrs.getColumnName(i);
						colTypes2[i-1] = mrs.getColumnTypeName(i);
						if(mrs.getColumnType(i) == 2004){//2004 is sql int reference for BLOB data type
							blobCount++;
						}
						//System.out.println(i +") Column Name:" + mrs.getColumnName(i) + " || Column Type:" + mrs.getColumnTypeName(i));
					}
					
					//recording information for later use
					columnNames = colNames2;
					columnTypeNames = colTypes2;
					successfulLoad = true;
					successfulLoad2 = true;
					
					//tells user if blob data is not present in the table, else it builds columnBlobNames array
					if(blobCount == 0) {
						JOptionPane.showMessageDialog(null,
							    "warning: could not find blob data",
							    "idiot",
							    JOptionPane.WARNING_MESSAGE);
					} else {
						columnBlobNames = new String[blobCount];
						int blobIndex = 0;
						for(int i = 0; i < mrs.getColumnCount(); i ++){
							if(columnTypeNames[i].equals("BLOB")){//could also use the same method before to check if mrs.getColumnType(i) == 2004
								columnBlobNames[blobIndex] = columnNames[i];
								blobIndex++;
							}
						}
						//System.out.println("columnBlobNames.length:" + columnBlobNames.length);
					}
					
					//models table
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e2) {
					e2.printStackTrace();
				} 
			}
		});
		btnLoadTable.setBounds(45, 217, 115, 29);
		contentPane.add(btnLoadTable);
		
		
		
		/* removed generic jcombobox raw type error warning
		 * windowbuilder does not support
		JComboBox<String> cBoxColLbl1 = new JComboBox<>(columnNames);
		cBoxColLbl1.setEnabled(false);
		cBoxColLbl1.setToolTipText("First label in name");
		cBoxColLbl1.setBounds(45, 659, 300, 26);
		contentPane.add(cBoxColLbl1);
		
		JComboBox<String> cBoxColLbl2 = new JComboBox<>(columnNames);
		cBoxColLbl2.setEnabled(false);
		cBoxColLbl2.setToolTipText("Second label in name");
		cBoxColLbl2.setBounds(360, 659, 300, 26);
		contentPane.add(cBoxColLbl2);
		
		JComboBox<String> cBoxColLbl3 = new JComboBox<>(columnNames);
		cBoxColLbl3.setEnabled(false);
		cBoxColLbl3.setToolTipText("Third label in name");
		cBoxColLbl3.setBounds(675, 659, 300, 26);
		contentPane.add(cBoxColLbl3);
		*/
		//combo boxes for label names that specify naming convention of output files
		//*************************
		JComboBox cBoxColLbl1 = new JComboBox();
		cBoxColLbl1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent colLbl1) {
				JComboBox cb = (JComboBox)colLbl1.getSource();
				String name1 = (String)cb.getSelectedItem();
				if(name1 != "" && name1 != null){
					namingOfFile[0] = name1;
				}
			}
		});
		cBoxColLbl1.setEnabled(false);
		cBoxColLbl1.setToolTipText("First label in name");
		cBoxColLbl1.setBounds(45, 659, 300, 26);
		contentPane.add(cBoxColLbl1);
		
		JComboBox cBoxColLbl2 = new JComboBox();
		cBoxColLbl2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent colLbl2) {
				JComboBox cb = (JComboBox)colLbl2.getSource();
				String name2 = (String)cb.getSelectedItem();
				if(name2 != "" && name2 != null){
					namingOfFile[1] = name2;
				}
			}
		});
		cBoxColLbl2.setEnabled(false);
		cBoxColLbl2.setToolTipText("Second label in name");
		cBoxColLbl2.setBounds(360, 659, 300, 26);
		contentPane.add(cBoxColLbl2);
		
		JComboBox cBoxColLbl3 = new JComboBox();
		cBoxColLbl3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent colLbl3) {
				JComboBox cb = (JComboBox)colLbl3.getSource();
				String name3 = (String)cb.getSelectedItem();
				if(name3 != "" && name3 != null){
					namingOfFile[2] = name3;
				}
			}
		});
		cBoxColLbl3.setEnabled(false);
		cBoxColLbl3.setToolTipText("Third label in name");
		cBoxColLbl3.setBounds(675, 659, 300, 26);
		contentPane.add(cBoxColLbl3);
		//*************************
		
		//Combo box for number of name labels combo boxes to enable
		txtLabelNameCount = new JTextField();
		txtLabelNameCount.setToolTipText("Number of labels that will have corresponding data in that cell to be named in the saved file.");
		txtLabelNameCount.setEditable(false);
		txtLabelNameCount.setText("Label name count:");
		txtLabelNameCount.setBounds(45, 605, 146, 26);
		contentPane.add(txtLabelNameCount);
		txtLabelNameCount.setColumns(10);
		
		String[] lblCount = {"1","2","3"};
		JComboBox cBoxLblCount = new JComboBox(lblCount);
		cBoxLblCount.setEnabled(false);
		cBoxLblCount.setToolTipText("Number of labels that will have corresponding data in that cell to be named in the saved file.");
		//updates label name jcomboboxes items upon clicking labelcount
		//columnNames array values are not initialized upon startup and need to be updated, which is what this action event does
		cBoxLblCount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		//enables or disables number column label combo boxes based on user input (enables left to right, disables right to left)
		cBoxLblCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String selectedCount = (String)cb.getSelectedItem();
				if(selectedCount == "1"){
					cBoxColLbl1.setEnabled(true);
					cBoxColLbl2.setEnabled(false);
					cBoxColLbl3.setEnabled(false);
				} else if (selectedCount == "2") {
					cBoxColLbl1.setEnabled(true);
					cBoxColLbl2.setEnabled(true);
					cBoxColLbl3.setEnabled(false);
				} else if (selectedCount == "3") {
					cBoxColLbl1.setEnabled(true);
					cBoxColLbl2.setEnabled(true);
					cBoxColLbl3.setEnabled(true);
				}
				columnLblCount = Integer.parseInt(selectedCount);
				//System.out.println("selected number of labels:" + columnLblCount);
				//namingOfFile = new String[columnLblCount];
				
				if(columnNames.length > 0 && successfulLoad){
					cBoxColLbl1.removeAllItems();
					cBoxColLbl2.removeAllItems();
					cBoxColLbl3.removeAllItems();
					for(String s:columnNames){
						cBoxColLbl1.addItem(s);
						cBoxColLbl2.addItem(s);
						cBoxColLbl3.addItem(s);
					}
					
				}
				successfulLoad = false;//prevents combo box items from being added more than once after a table is loaded
			}
		});
		cBoxLblCount.setBounds(195, 605, 102, 26);
		contentPane.add(cBoxLblCount);
		
		//select blob column that the input byte stream will output file using
				txtSelectBLOB = new JTextField();
				txtSelectBLOB.setEditable(false);
				txtSelectBLOB.setText("Select Long Binary Data column (BLOB):");
				txtSelectBLOB.setBounds(45, 563, 300, 26);
				contentPane.add(txtSelectBLOB);
				txtSelectBLOB.setColumns(10);
				
				JComboBox cBoxSelectBlob = new JComboBox();
				cBoxSelectBlob.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent eBlob) {
						if(columnBlobNames.length > 0 && successfulLoad2){
							cBoxSelectBlob.removeAllItems();
							for(String s:columnBlobNames){
								cBoxSelectBlob.addItem(s);
							}
						}
						successfulLoad2 = false;
					}
				});
				cBoxSelectBlob.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent selectBlob) {
						JComboBox cb = (JComboBox)selectBlob.getSource();
						String blobName = (String)cb.getSelectedItem();
						LBDColName = blobName;
						if (LBDColName.length() != 0){
							cBoxLblCount.setEnabled(true);
						}
						cBoxLblCount.setEnabled(true);
					}
				});
				cBoxSelectBlob.setToolTipText("Select the column with chosen blob data to use for outputing image");
				cBoxSelectBlob.setBounds(360, 563, 300, 26);
		
		contentPane.add(cBoxSelectBlob);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnImportDb, btnSaveLocation, btnLoadTable, cBoxColLbl1, cBoxColLbl2, cBoxColLbl3, btnExportFiles}));
	}
}
