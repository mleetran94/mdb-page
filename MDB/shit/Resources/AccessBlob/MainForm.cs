using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace AccessBlob
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class MainForm : System.Windows.Forms.Form
	{
		private const string ACCESS_FILE_FILTER = "Microsoft Access (*.mdb)|*.*|All Files (*.*)|*.*";
		private const string DATA_FILE_FILTER = "All Files (*.*)|*.*";
		
		private const int ORDER_BY_FILE_NAME = 1;
		private const int ORDER_BY_SIZE = 2;
		private const int ORDER_BY_TYPE = 3;
		private const int ORDER_BY_DATE_UPLOADED = 4;

		private string orderBy;
		
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox AccessFile;
		private System.Windows.Forms.Button BrowseAccessFile;
		private System.Windows.Forms.ListView FileListView;
		private System.Windows.Forms.Button BrowseDataFile;
		private System.Windows.Forms.TextBox DataFile;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Button UploadFile;
		private System.Windows.Forms.Button DownloadFile;
		private System.Windows.Forms.Button Exit;
		private System.Windows.Forms.ColumnHeader FileNameColumn;
		private System.Windows.Forms.ColumnHeader SizeColumn;
		private System.Windows.Forms.ColumnHeader TypeColumn;
		private System.Windows.Forms.ColumnHeader DateColumn;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public MainForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
			Text = Application.ProductName;
			orderBy = "1";
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label1 = new System.Windows.Forms.Label();
			this.AccessFile = new System.Windows.Forms.TextBox();
			this.BrowseAccessFile = new System.Windows.Forms.Button();
			this.FileListView = new System.Windows.Forms.ListView();
			this.FileNameColumn = new System.Windows.Forms.ColumnHeader();
			this.SizeColumn = new System.Windows.Forms.ColumnHeader();
			this.TypeColumn = new System.Windows.Forms.ColumnHeader();
			this.DateColumn = new System.Windows.Forms.ColumnHeader();
			this.BrowseDataFile = new System.Windows.Forms.Button();
			this.DataFile = new System.Windows.Forms.TextBox();
			this.label2 = new System.Windows.Forms.Label();
			this.UploadFile = new System.Windows.Forms.Button();
			this.DownloadFile = new System.Windows.Forms.Button();
			this.Exit = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(8, 8);
			this.label1.Name = "label1";
			this.label1.TabIndex = 0;
			this.label1.Text = "Access File:";
			// 
			// AccessFile
			// 
			this.AccessFile.Location = new System.Drawing.Point(8, 32);
			this.AccessFile.Name = "AccessFile";
			this.AccessFile.Size = new System.Drawing.Size(480, 20);
			this.AccessFile.TabIndex = 1;
			this.AccessFile.Text = "";
			// 
			// BrowseAccessFile
			// 
			this.BrowseAccessFile.Location = new System.Drawing.Point(496, 32);
			this.BrowseAccessFile.Name = "BrowseAccessFile";
			this.BrowseAccessFile.Size = new System.Drawing.Size(32, 23);
			this.BrowseAccessFile.TabIndex = 2;
			this.BrowseAccessFile.Text = "...";
			this.BrowseAccessFile.Click += new System.EventHandler(this.BrowseAccessFile_Click);
			// 
			// FileListView
			// 
			this.FileListView.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
																						   this.FileNameColumn,
																						   this.SizeColumn,
																						   this.TypeColumn,
																						   this.DateColumn});
			this.FileListView.FullRowSelect = true;
			this.FileListView.GridLines = true;
			this.FileListView.Location = new System.Drawing.Point(8, 72);
			this.FileListView.Name = "FileListView";
			this.FileListView.Size = new System.Drawing.Size(520, 232);
			this.FileListView.TabIndex = 3;
			this.FileListView.View = System.Windows.Forms.View.Details;
			this.FileListView.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.FileListView_ColumnClick);
			// 
			// FileNameColumn
			// 
			this.FileNameColumn.Text = "File Name";
			this.FileNameColumn.Width = 203;
			// 
			// SizeColumn
			// 
			this.SizeColumn.Text = "Size";
			this.SizeColumn.Width = 53;
			// 
			// TypeColumn
			// 
			this.TypeColumn.Text = "Type";
			this.TypeColumn.Width = 62;
			// 
			// DateColumn
			// 
			this.DateColumn.Text = "Date Uploaded";
			this.DateColumn.Width = 196;
			// 
			// BrowseDataFile
			// 
			this.BrowseDataFile.Location = new System.Drawing.Point(496, 336);
			this.BrowseDataFile.Name = "BrowseDataFile";
			this.BrowseDataFile.Size = new System.Drawing.Size(32, 23);
			this.BrowseDataFile.TabIndex = 6;
			this.BrowseDataFile.Text = "...";
			this.BrowseDataFile.Click += new System.EventHandler(this.BrowseDataFile_Click);
			// 
			// DataFile
			// 
			this.DataFile.Location = new System.Drawing.Point(8, 336);
			this.DataFile.Name = "DataFile";
			this.DataFile.Size = new System.Drawing.Size(480, 20);
			this.DataFile.TabIndex = 5;
			this.DataFile.Text = "";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(8, 312);
			this.label2.Name = "label2";
			this.label2.TabIndex = 4;
			this.label2.Text = "Data File:";
			// 
			// UploadFile
			// 
			this.UploadFile.Location = new System.Drawing.Point(288, 392);
			this.UploadFile.Name = "UploadFile";
			this.UploadFile.TabIndex = 7;
			this.UploadFile.Text = "Upload...";
			this.UploadFile.Click += new System.EventHandler(this.UploadFile_Click);
			// 
			// DownloadFile
			// 
			this.DownloadFile.Location = new System.Drawing.Point(368, 392);
			this.DownloadFile.Name = "DownloadFile";
			this.DownloadFile.TabIndex = 8;
			this.DownloadFile.Text = "Download...";
			this.DownloadFile.Click += new System.EventHandler(this.DownloadFile_Click);
			// 
			// Exit
			// 
			this.Exit.Location = new System.Drawing.Point(456, 392);
			this.Exit.Name = "Exit";
			this.Exit.TabIndex = 9;
			this.Exit.Text = "Exit";
			this.Exit.Click += new System.EventHandler(this.Exit_Click);
			// 
			// MainForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(536, 422);
			this.Controls.Add(this.Exit);
			this.Controls.Add(this.DownloadFile);
			this.Controls.Add(this.UploadFile);
			this.Controls.Add(this.BrowseDataFile);
			this.Controls.Add(this.DataFile);
			this.Controls.Add(this.AccessFile);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.FileListView);
			this.Controls.Add(this.BrowseAccessFile);
			this.Controls.Add(this.label1);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
			this.MaximizeBox = false;
			this.Name = "MainForm";
			this.Text = "Form1";
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new MainForm());
		}

		private void BrowseAccessFile_Click(object sender, System.EventArgs e) {
			OpenFileDialog dlg = new OpenFileDialog();
			dlg.Filter = ACCESS_FILE_FILTER;
			if(AccessFile.Text.Length > 0) {
				dlg.FileName = AccessFile.Text;
			}

			if(dlg.ShowDialog() == DialogResult.Cancel) {
				return;
			}

			AccessFile.Text = dlg.FileName;
			PopulateListView();
		}

		private void BrowseDataFile_Click(object sender, System.EventArgs e) {
			OpenFileDialog dlg = new OpenFileDialog();
			dlg.Filter = DATA_FILE_FILTER;
			dlg.Title = "Data File";
			if(DataFile.Text.Length > 0) {
				dlg.FileName = DataFile.Text;
			}

			if(dlg.ShowDialog() == DialogResult.Cancel) {
				return;
			}

			DataFile.Text = dlg.FileName;
		}

		private void UploadFile_Click(object sender, System.EventArgs e) {
			if(AccessFile.Text.Length <= 0) {
				MessageBox.Show("You need to specify a Microsoft Access Database.", Text);
				return;
			}

			if(DataFile.Text.Length <= 0) {
				MessageBox.Show("You need to specify the file that you want to upload in the Data File section of this form.", Text);
				return;
			}

			System.Data.OleDb.OleDbConnection conn = null;		// the connection to the database
			System.Data.OleDb.OleDbCommand cmd = null;			// the command
			System.Data.OleDb.OleDbDataReader dr = null;		// the data reader used to determine if the
																// an insert or update command should be used
			System.Data.OleDb.OleDbParameter param = null;		// the parameter used to store the BLOB
			System.IO.FileStream fileStream;					// the file stream for the source file       
			System.IO.BinaryReader reader = null;				// the reader used to read the source file
			byte[] data = null;									// the entire file

			// DataFile.Text is the source file name with full path.  we
			// need to determine the file name and the type.
			string[] arr = DataFile.Text.Split(new char[]{'\\'});
			string fileName = arr[arr.Length - 1];
			arr = DataFile.Text.Split(new char[]{'.'});
			string type = (arr.Length > 1) ? arr[arr.Length - 1] : "";
			// get the time stamp when the file was uploaded
			string dateUploaded = System.DateTime.Now.ToShortDateString() + " " + System.DateTime.Now.ToShortTimeString();
			int numRecords = 0;

			try {
				// load the file that we want to upload into memory
				fileStream = new System.IO.FileStream(DataFile.Text, System.IO.FileMode.Open, System.IO.FileAccess.Read);
				reader = new System.IO.BinaryReader(fileStream);
				data = reader.ReadBytes((int)fileStream.Length);

				// open the connection to the database
				conn = new System.Data.OleDb.OleDbConnection("Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + AccessFile.Text);
				conn.Open();

				// determine if the file already exists.  If the file does not already
				// exist, add the file to the table.
				cmd = new System.Data.OleDb.OleDbCommand("SELECT COUNT(*) FROM [File] WHERE [FileName]='" + fileName + "'",conn);
				dr = cmd.ExecuteReader();
				dr.Read();
				numRecords = dr.GetInt32(0);
				dr.Close();
				if(numRecords == 0) {
					cmd = new System.Data.OleDb.OleDbCommand("INSERT INTO [File] ([FileName],[Size],[Type],[DateUploaded]) VALUES('" + fileName + "'," + data.Length + ",'" + type + "', #"+ dateUploaded + "#)",conn);
					cmd.ExecuteNonQuery();
				}
			
				// update the BLOB data based for the record.
				cmd = new System.Data.OleDb.OleDbCommand("UPDATE [File] SET [File]=@file, [Size]=" + data.Length + ", [DateUploaded]=#" + dateUploaded + "# WHERE [FileName]='" + fileName + "'",conn);
				param = cmd.Parameters.Add("@file", System.Data.OleDb.OleDbType.Binary);
				param.Value = data;
				cmd.ExecuteNonQuery();
			} catch(Exception ex) {
				MessageBox.Show("Could not upload " + DataFile.Text + "." + System.Environment.NewLine + ex.Message + System.Environment.NewLine + ex.StackTrace, Text);
			} finally {
				if(reader != null) {
					reader.Close();
				}

				if(conn != null) {
					conn.Close();
				}
			}

			PopulateListView();
		}

		private void DownloadFile_Click(object sender, System.EventArgs e) {
			if(AccessFile.Text.Length <= 0) {
				MessageBox.Show("You need to specify a Microsoft Access Database.", Text);
				return;
			}

			if(DataFile.Text.Length <= 0) {
				MessageBox.Show("You need to specify the location where you want to save the file in the Data File section of this form.", Text);
				return;
			}

			if(FileListView.SelectedItems.Count <= 0) {
				MessageBox.Show("You must select the file to download.", Text);
				return;			
			}

			System.Data.OleDb.OleDbConnection conn = null;		// the connection to the database
			System.Data.OleDb.OleDbCommand cmd = null;			// the command
			System.Data.OleDb.OleDbDataReader reader = null;	// reads the data
			System.IO.FileStream fileStream = null;				// the file stream for the destination file 
			System.IO.BinaryWriter writer = null;				// the writer used to create the destination file
			int bufferSize = 1000;								// the size of the buffer that is read from the database
																// an written to the file
			byte[] buffer = new byte[bufferSize];				// the buffer for the data being transfered from
																// the database to the file
            long startIndex = 0;								// the start index of the data in the database
			long numberOfBytes = 0;								// the number of bytes read from the database

			
			try {
				// create the connection to the database: AccessFile.Text is the full
				// path to the Access file.
				conn = new System.Data.OleDb.OleDbConnection("Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + AccessFile.Text);
				// create the SQL command: FileListView.SelectedItems[0].Text is the name
				// of the file taken from the List View.
				cmd = new System.Data.OleDb.OleDbCommand("SELECT [File] FROM [File] WHERE [FileName] = '" + FileListView.SelectedItems[0].Text + "'", conn);
				// open up the connection to the database
				conn.Open();

				// create the DataReader that will get the blob one buffer at a time
				reader = cmd.ExecuteReader(CommandBehavior.SequentialAccess);
				// we advance to the first record returned.  for this application we
				// know that there is atleast one record because we got the file name
				// from the List View
				reader.Read();
				// create the file stream that will save the file to DataFile.Text
				fileStream = new System.IO.FileStream(DataFile.Text, System.IO.FileMode.OpenOrCreate, System.IO.FileAccess.Write);
				// create the writer from the file stream
				writer = new System.IO.BinaryWriter(fileStream);

				// read in file from the database one buffer at a time.  when the number
				// of bytes read is zero then we know that we are done.
				do {
					numberOfBytes = reader.GetBytes(0, startIndex, buffer, 0, bufferSize);
					if(numberOfBytes == 0) {
						break;
					}
					writer.Write(buffer, 0, (int) numberOfBytes);
					startIndex += numberOfBytes;
				} while (true);
				writer.Flush();
			} catch(Exception ex) {
				MessageBox.Show("Could not download the file." + System.Environment.NewLine + ex.Message + System.Environment.NewLine + ex.StackTrace, Text);
			} finally {
				if(writer != null) {
					writer.Close();
				}

				if(fileStream != null) {
					fileStream.Close();
				}

				if(reader != null) {
					reader.Close();
				}

				if(conn != null) {
					conn.Close();
				}
			}
		}

		private void Exit_Click(object sender, System.EventArgs e) {
			Application.Exit();
		}

		private void FileListView_ColumnClick(object sender, System.Windows.Forms.ColumnClickEventArgs e) {
			orderBy = ORDER_BY_FILE_NAME.ToString();

			if(e.Column == 1) {
				orderBy = ORDER_BY_SIZE.ToString() + ", " + orderBy;
			} else if(e.Column == 2) {
				orderBy = ORDER_BY_TYPE.ToString() + ", " + orderBy; 
			} else if(e.Column == 3) {
				orderBy = ORDER_BY_DATE_UPLOADED.ToString() + ", " + orderBy;
			}

			PopulateListView();
		}

		private void PopulateListView() {
			System.Data.OleDb.OleDbConnection conn = null;
			System.Data.OleDb.OleDbCommand cmd = null;
			System.Data.OleDb.OleDbDataReader reader = null;
			ListViewItem item = null;

			// clear all items in the list view
			FileListView.Items.Clear();

			try {
				// connect to the data, create the command, and get the reader.
				conn = new System.Data.OleDb.OleDbConnection("Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + AccessFile.Text);
				cmd = new System.Data.OleDb.OleDbCommand("SELECT [FileName], [Size], [Type], [DateUploaded] FROM [File] ORDER BY " + orderBy, conn);
				conn.Open();
				reader = cmd.ExecuteReader();

				// go through all the records and add them to the list view.
				while(reader.Read()) {
					item = new ListViewItem(new string[]{reader.GetString(0), reader.GetInt32(1).ToString(), reader.GetString(2), reader.GetDateTime(3).ToShortDateString() + " " + reader.GetDateTime(3).ToShortTimeString()});
					FileListView.Items.Add(item);
				}

			} catch(Exception ex) {
				MessageBox.Show("Could not connect to the database." + System.Environment.NewLine + ex.Message + System.Environment.NewLine + ex.StackTrace, Text);
			} finally {
				if(reader != null) {
					reader.Close();
				}

				if(conn != null) {
					conn.Close();
				}
			}
		}
	}
}
