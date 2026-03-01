namespace EAC1__Interfícies
{
    partial class Reserva
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            ComboBox comboBox1;
            fileSystemWatcher1 = new FileSystemWatcher();
            label1 = new Label();
            textBox1 = new TextBox();
            textBox3 = new TextBox();
            label3 = new Label();
            textBox5 = new TextBox();
            label5 = new Label();
            textBox2 = new TextBox();
            label2 = new Label();
            label4 = new Label();
            comboBox2 = new ComboBox();
            dateTimePicker1 = new DateTimePicker();
            label6 = new Label();
            label7 = new Label();
            radioButton1 = new RadioButton();
            radioButton2 = new RadioButton();
            radioButton3 = new RadioButton();
            label9 = new Label();
            groupBox1 = new GroupBox();
            numericUpDown1 = new NumericUpDown();
            label8 = new Label();
            tabControl1 = new TabControl();
            tabPage1 = new TabPage();
            tabPage2 = new TabPage();
            label10 = new Label();
            dateTimePicker3 = new DateTimePicker();
            label11 = new Label();
            dateTimePicker4 = new DateTimePicker();
            groupBox2 = new GroupBox();
            button1 = new Button();
            comboBox1 = new ComboBox();
            ((System.ComponentModel.ISupportInitialize)fileSystemWatcher1).BeginInit();
            groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)numericUpDown1).BeginInit();
            tabControl1.SuspendLayout();
            tabPage1.SuspendLayout();
            tabPage2.SuspendLayout();
            groupBox2.SuspendLayout();
            SuspendLayout();
            // 
            // comboBox1
            // 
            comboBox1.FormattingEnabled = true;
            comboBox1.Items.AddRange(new object[] { "Barcelona", "Madrid", "Paris", "Lisboa", "Londres" });
            comboBox1.Location = new Point(9, 81);
            comboBox1.Name = "comboBox1";
            comboBox1.Size = new Size(121, 23);
            comboBox1.TabIndex = 12;
            comboBox1.Text = "Origen";
            // 
            // fileSystemWatcher1
            // 
            fileSystemWatcher1.EnableRaisingEvents = true;
            fileSystemWatcher1.SynchronizingObject = this;
            fileSystemWatcher1.Changed += fileSystemWatcher1_Changed;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.BackColor = SystemColors.ControlDark;
            label1.Location = new Point(6, 48);
            label1.Name = "label1";
            label1.Size = new Size(105, 15);
            label1.TabIndex = 0;
            label1.Text = "Correo electronico";
            label1.Click += label1_Click_2;
            // 
            // textBox1
            // 
            textBox1.Location = new Point(117, 45);
            textBox1.Name = "textBox1";
            textBox1.Size = new Size(205, 23);
            textBox1.TabIndex = 1;
            textBox1.Validating += textBox1_Validating;
            // 
            // textBox3
            // 
            textBox3.Location = new Point(64, 73);
            textBox3.Name = "textBox3";
            textBox3.Size = new Size(95, 23);
            textBox3.TabIndex = 7;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.BackColor = SystemColors.ControlDark;
            label3.Location = new Point(6, 76);
            label3.Name = "label3";
            label3.RightToLeft = RightToLeft.Yes;
            label3.Size = new Size(52, 15);
            label3.TabIndex = 6;
            label3.Text = "Teléfono";
            label3.Click += label3_Click;
            // 
            // textBox5
            // 
            textBox5.Location = new Point(205, 16);
            textBox5.Name = "textBox5";
            textBox5.Size = new Size(156, 23);
            textBox5.TabIndex = 9;
            textBox5.TextChanged += textBox5_TextChanged;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.BackColor = SystemColors.ControlDark;
            label5.Location = new Point(143, 19);
            label5.Name = "label5";
            label5.Size = new Size(56, 15);
            label5.TabIndex = 8;
            label5.Text = "Apellidos";
            // 
            // textBox2
            // 
            textBox2.Location = new Point(63, 16);
            textBox2.Name = "textBox2";
            textBox2.Size = new Size(74, 23);
            textBox2.TabIndex = 11;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.BackColor = SystemColors.ControlDark;
            label2.Location = new Point(6, 19);
            label2.Name = "label2";
            label2.RightToLeft = RightToLeft.Yes;
            label2.Size = new Size(51, 15);
            label2.TabIndex = 10;
            label2.Text = "Nombre";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.BackColor = Color.Transparent;
            label4.ForeColor = SystemColors.ActiveCaptionText;
            label4.Location = new Point(5, 63);
            label4.Name = "label4";
            label4.Size = new Size(260, 15);
            label4.TabIndex = 13;
            label4.Text = "SELECCIONE EL ORIGEN Y DESTINO DE SU VIAJE";
            // 
            // comboBox2
            // 
            comboBox2.FormattingEnabled = true;
            comboBox2.Items.AddRange(new object[] { "Barcelona", "Madrid", "Paris", "Lisboa", "Londres" });
            comboBox2.Location = new Point(138, 81);
            comboBox2.Name = "comboBox2";
            comboBox2.Size = new Size(121, 23);
            comboBox2.TabIndex = 14;
            comboBox2.Text = "Destino";
            comboBox2.SelectedIndexChanged += comboBox2_SelectedIndexChanged;
            // 
            // dateTimePicker1
            // 
            dateTimePicker1.Format = DateTimePickerFormat.Short;
            dateTimePicker1.ImeMode = ImeMode.NoControl;
            dateTimePicker1.Location = new Point(35, 9);
            dateTimePicker1.MinDate = new DateTime(1753, 1, 6, 0, 0, 0, 0);
            dateTimePicker1.Name = "dateTimePicker1";
            dateTimePicker1.Size = new Size(81, 23);
            dateTimePicker1.TabIndex = 15;
            dateTimePicker1.Value = new DateTime(2025, 10, 1, 0, 0, 0, 0);
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.BackColor = Color.Transparent;
            label6.ForeColor = SystemColors.ActiveCaptionText;
            label6.Location = new Point(9, 107);
            label6.Name = "label6";
            label6.Size = new Size(149, 15);
            label6.TabIndex = 16;
            label6.Text = "QUE DIAS TE IRAS DE VIAJE";
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.BackColor = Color.Transparent;
            label7.Location = new Point(6, 15);
            label7.Name = "label7";
            label7.RightToLeft = RightToLeft.Yes;
            label7.Size = new Size(23, 15);
            label7.TabIndex = 18;
            label7.Text = "Ida";
            // 
            // radioButton1
            // 
            radioButton1.AutoSize = true;
            radioButton1.Location = new Point(11, 37);
            radioButton1.Name = "radioButton1";
            radioButton1.Size = new Size(60, 19);
            radioButton1.TabIndex = 21;
            radioButton1.TabStop = true;
            radioButton1.Text = "Vuelos";
            radioButton1.UseVisualStyleBackColor = true;
            // 
            // radioButton2
            // 
            radioButton2.AutoSize = true;
            radioButton2.Location = new Point(77, 37);
            radioButton2.Name = "radioButton2";
            radioButton2.Size = new Size(65, 19);
            radioButton2.TabIndex = 22;
            radioButton2.TabStop = true;
            radioButton2.Text = "Hoteles";
            radioButton2.UseVisualStyleBackColor = true;
            // 
            // radioButton3
            // 
            radioButton3.AutoSize = true;
            radioButton3.Location = new Point(148, 37);
            radioButton3.Name = "radioButton3";
            radioButton3.Size = new Size(114, 19);
            radioButton3.TabIndex = 23;
            radioButton3.TabStop = true;
            radioButton3.Text = "Vuelos + Hoteles";
            radioButton3.UseVisualStyleBackColor = true;
            // 
            // label9
            // 
            label9.AutoSize = true;
            label9.BackColor = Color.Transparent;
            label9.ForeColor = SystemColors.ActiveCaptionText;
            label9.Location = new Point(6, 19);
            label9.Name = "label9";
            label9.Size = new Size(136, 15);
            label9.TabIndex = 24;
            label9.Text = "QUE SERVICIOS QUIERES";
            // 
            // groupBox1
            // 
            groupBox1.BackColor = SystemColors.ActiveBorder;
            groupBox1.Controls.Add(numericUpDown1);
            groupBox1.Controls.Add(label8);
            groupBox1.Controls.Add(tabControl1);
            groupBox1.Controls.Add(label9);
            groupBox1.Controls.Add(comboBox1);
            groupBox1.Controls.Add(label4);
            groupBox1.Controls.Add(radioButton3);
            groupBox1.Controls.Add(comboBox2);
            groupBox1.Controls.Add(radioButton2);
            groupBox1.Controls.Add(radioButton1);
            groupBox1.Controls.Add(label6);
            groupBox1.Location = new Point(51, 146);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(299, 244);
            groupBox1.TabIndex = 25;
            groupBox1.TabStop = false;
            groupBox1.Text = "Reserva";
            // 
            // numericUpDown1
            // 
            numericUpDown1.Location = new Point(14, 215);
            numericUpDown1.Name = "numericUpDown1";
            numericUpDown1.Size = new Size(30, 23);
            numericUpDown1.TabIndex = 29;
            numericUpDown1.Value = new decimal(new int[] { 1, 0, 0, 0 });
            // 
            // label8
            // 
            label8.AutoSize = true;
            label8.Location = new Point(9, 197);
            label8.Name = "label8";
            label8.Size = new Size(138, 15);
            label8.TabIndex = 28;
            label8.Text = "CUANTOS VAN A VIAJAR";
            // 
            // tabControl1
            // 
            tabControl1.Controls.Add(tabPage1);
            tabControl1.Controls.Add(tabPage2);
            tabControl1.Location = new Point(11, 125);
            tabControl1.Name = "tabControl1";
            tabControl1.SelectedIndex = 0;
            tabControl1.Size = new Size(263, 69);
            tabControl1.TabIndex = 26;
            // 
            // tabPage1
            // 
            tabPage1.BackColor = Color.Transparent;
            tabPage1.Controls.Add(label7);
            tabPage1.Controls.Add(dateTimePicker1);
            tabPage1.Location = new Point(4, 24);
            tabPage1.Name = "tabPage1";
            tabPage1.Padding = new Padding(3);
            tabPage1.Size = new Size(255, 41);
            tabPage1.TabIndex = 0;
            tabPage1.Text = "Ida";
            // 
            // tabPage2
            // 
            tabPage2.Controls.Add(label10);
            tabPage2.Controls.Add(dateTimePicker3);
            tabPage2.Controls.Add(label11);
            tabPage2.Controls.Add(dateTimePicker4);
            tabPage2.Location = new Point(4, 24);
            tabPage2.Name = "tabPage2";
            tabPage2.Padding = new Padding(3);
            tabPage2.Size = new Size(255, 41);
            tabPage2.TabIndex = 1;
            tabPage2.Text = "Ida y Vuelta";
            tabPage2.UseVisualStyleBackColor = true;
            tabPage2.Click += tabPage2_Click;
            // 
            // label10
            // 
            label10.AutoSize = true;
            label10.BackColor = Color.Transparent;
            label10.Location = new Point(6, 15);
            label10.Name = "label10";
            label10.RightToLeft = RightToLeft.Yes;
            label10.Size = new Size(23, 15);
            label10.TabIndex = 22;
            label10.Text = "Ida";
            // 
            // dateTimePicker3
            // 
            dateTimePicker3.Format = DateTimePickerFormat.Short;
            dateTimePicker3.ImeMode = ImeMode.NoControl;
            dateTimePicker3.Location = new Point(168, 9);
            dateTimePicker3.MinDate = new DateTime(1753, 1, 6, 0, 0, 0, 0);
            dateTimePicker3.Name = "dateTimePicker3";
            dateTimePicker3.Size = new Size(81, 23);
            dateTimePicker3.TabIndex = 21;
            dateTimePicker3.Value = new DateTime(2025, 10, 2, 0, 0, 0, 0);
            // 
            // label11
            // 
            label11.AutoSize = true;
            label11.BackColor = Color.Transparent;
            label11.Location = new Point(122, 15);
            label11.Name = "label11";
            label11.RightToLeft = RightToLeft.Yes;
            label11.Size = new Size(40, 15);
            label11.TabIndex = 23;
            label11.Text = "Vuelta";
            // 
            // dateTimePicker4
            // 
            dateTimePicker4.Format = DateTimePickerFormat.Short;
            dateTimePicker4.ImeMode = ImeMode.NoControl;
            dateTimePicker4.Location = new Point(35, 9);
            dateTimePicker4.MinDate = new DateTime(1753, 1, 6, 0, 0, 0, 0);
            dateTimePicker4.Name = "dateTimePicker4";
            dateTimePicker4.Size = new Size(81, 23);
            dateTimePicker4.TabIndex = 20;
            dateTimePicker4.Value = new DateTime(2025, 10, 1, 0, 0, 0, 0);
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(label2);
            groupBox2.Controls.Add(textBox2);
            groupBox2.Controls.Add(label1);
            groupBox2.Controls.Add(textBox1);
            groupBox2.Controls.Add(label3);
            groupBox2.Controls.Add(textBox5);
            groupBox2.Controls.Add(textBox3);
            groupBox2.Controls.Add(label5);
            groupBox2.Location = new Point(51, 23);
            groupBox2.Name = "groupBox2";
            groupBox2.Size = new Size(384, 108);
            groupBox2.TabIndex = 26;
            groupBox2.TabStop = false;
            groupBox2.Text = "Datos de contacto";
            // 
            // button1
            // 
            button1.Location = new Point(51, 408);
            button1.Name = "button1";
            button1.Size = new Size(75, 23);
            button1.TabIndex = 27;
            button1.Text = "Buscar";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            button1.MouseEnter += button1_MouseEnter;
            button1.MouseLeave += button1_MouseLeave;
            // 
            // Reserva
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = SystemColors.AppWorkspace;
            ClientSize = new Size(936, 669);
            Controls.Add(button1);
            Controls.Add(groupBox2);
            Controls.Add(groupBox1);
            Name = "Reserva";
            Text = "Form1";
            Load += Form1_Load;
            ((System.ComponentModel.ISupportInitialize)fileSystemWatcher1).EndInit();
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)numericUpDown1).EndInit();
            tabControl1.ResumeLayout(false);
            tabPage1.ResumeLayout(false);
            tabPage1.PerformLayout();
            tabPage2.ResumeLayout(false);
            tabPage2.PerformLayout();
            groupBox2.ResumeLayout(false);
            groupBox2.PerformLayout();
            ResumeLayout(false);
        }

        #endregion
        private FileSystemWatcher fileSystemWatcher1;
        private Label label1;
        private TextBox textBox1;
        private TextBox textBox5;
        private Label label5;
        private TextBox textBox3;
        private Label label3;
        private TextBox textBox2;
        private Label label2;
        private Label label4;
        private ComboBox comboBox1;
        private Label label6;
        private DateTimePicker dateTimePicker1;
        private ComboBox comboBox2;
        private Label label7;
        private RadioButton radioButton3;
        private RadioButton radioButton2;
        private RadioButton radioButton1;
        private TabControl tabControl1;
        private TabPage tabPage1;
        private TabPage tabPage2;
        private GroupBox groupBox1;
        private Label label9;
        private GroupBox groupBox2;
        private Label label10;
        private DateTimePicker dateTimePicker3;
        private Label label11;
        private DateTimePicker dateTimePicker4;
        private Label label8;
        private NumericUpDown numericUpDown1;
        private Button button1;
    }
}
