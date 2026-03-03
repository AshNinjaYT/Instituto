namespace EAC1_Gestor_de_Finanzas
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            textBox1 = new TextBox();
            radioButton1 = new RadioButton();
            radioButton2 = new RadioButton();
            textBox2 = new TextBox();
            textBox3 = new TextBox();
            numericUpDown1 = new NumericUpDown();
            comboBox1 = new ComboBox();
            textBox4 = new TextBox();
            button2 = new Button();
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            label4 = new Label();
            label5 = new Label();
            label6 = new Label();
            button1 = new Button();
            userControl11 = new UserControl1();
            label7 = new Label();
            ((System.ComponentModel.ISupportInitialize)numericUpDown1).BeginInit();
            SuspendLayout();
            // 
            // textBox1
            // 
            textBox1.Location = new Point(141, 6);
            textBox1.Name = "textBox1";
            textBox1.Size = new Size(100, 23);
            textBox1.TabIndex = 0;
            // 
            // radioButton1
            // 
            radioButton1.AutoSize = true;
            radioButton1.Location = new Point(13, 35);
            radioButton1.Name = "radioButton1";
            radioButton1.Size = new Size(101, 19);
            radioButton1.TabIndex = 1;
            radioButton1.TabStop = true;
            radioButton1.Text = "Media jornada";
            radioButton1.UseVisualStyleBackColor = true;
            radioButton1.CheckedChanged += radioButton1_CheckedChanged;
            // 
            // radioButton2
            // 
            radioButton2.AutoSize = true;
            radioButton2.Location = new Point(120, 35);
            radioButton2.Name = "radioButton2";
            radioButton2.Size = new Size(121, 19);
            radioButton2.TabIndex = 2;
            radioButton2.TabStop = true;
            radioButton2.Text = "Jornada Completa";
            radioButton2.UseVisualStyleBackColor = true;
            radioButton2.CheckedChanged += radioButton2_CheckedChanged;
            // 
            // textBox2
            // 
            textBox2.Location = new Point(141, 60);
            textBox2.Name = "textBox2";
            textBox2.Size = new Size(100, 23);
            textBox2.TabIndex = 3;
            // 
            // textBox3
            // 
            textBox3.Location = new Point(141, 89);
            textBox3.Name = "textBox3";
            textBox3.ReadOnly = true;
            textBox3.Size = new Size(100, 23);
            textBox3.TabIndex = 4;
            // 
            // numericUpDown1
            // 
            numericUpDown1.Location = new Point(141, 118);
            numericUpDown1.Maximum = new decimal(new int[] { 20, 0, 0, 0 });
            numericUpDown1.Name = "numericUpDown1";
            numericUpDown1.Size = new Size(100, 23);
            numericUpDown1.TabIndex = 5;
            // 
            // comboBox1
            // 
            comboBox1.FormattingEnabled = true;
            comboBox1.Items.AddRange(new object[] { "2%", "10%", "18%" });
            comboBox1.Location = new Point(141, 147);
            comboBox1.Name = "comboBox1";
            comboBox1.Size = new Size(100, 23);
            comboBox1.TabIndex = 6;
            // 
            // textBox4
            // 
            textBox4.Location = new Point(141, 176);
            textBox4.Name = "textBox4";
            textBox4.ReadOnly = true;
            textBox4.Size = new Size(100, 23);
            textBox4.TabIndex = 7;
            // 
            // button2
            // 
            button2.Location = new Point(141, 206);
            button2.Name = "button2";
            button2.Size = new Size(75, 23);
            button2.TabIndex = 8;
            button2.Text = "Limpiar";
            button2.UseVisualStyleBackColor = true;
            button2.Click += button2_Click;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(12, 9);
            label1.Name = "label1";
            label1.Size = new Size(105, 15);
            label1.TabIndex = 10;
            label1.Text = "Nombre y apellido";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(12, 63);
            label2.Name = "label2";
            label2.Size = new Size(123, 15);
            label2.TabIndex = 11;
            label2.Text = "Sueldo mensual bruto";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(12, 92);
            label3.Name = "label3";
            label3.Size = new Size(117, 15);
            label3.TabIndex = 12;
            label3.Text = "Precio de horas extra";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(13, 150);
            label4.Name = "label4";
            label4.Size = new Size(104, 15);
            label4.TabIndex = 14;
            label4.Text = "Seleccione su IRPF";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(12, 120);
            label5.Name = "label5";
            label5.Size = new Size(128, 15);
            label5.TabIndex = 13;
            label5.Text = "Numero de horas extra";
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Location = new Point(12, 179);
            label6.Name = "label6";
            label6.Size = new Size(118, 15);
            label6.TabIndex = 15;
            label6.Text = "Sueldo mensual neto";
            // 
            // button1
            // 
            button1.Location = new Point(60, 206);
            button1.Name = "button1";
            button1.Size = new Size(75, 23);
            button1.TabIndex = 16;
            button1.Text = "Calcular";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // userControl11
            // 
            userControl11.BackColor = SystemColors.ActiveBorder;
            userControl11.Location = new Point(544, 35);
            userControl11.Name = "userControl11";
            userControl11.Size = new Size(244, 294);
            userControl11.TabIndex = 17;
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Location = new Point(551, 12);
            label7.Name = "label7";
            label7.Size = new Size(70, 15);
            label7.TabIndex = 18;
            label7.Text = "Calculadora";
            // 
            // Form2
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(label7);
            Controls.Add(userControl11);
            Controls.Add(button1);
            Controls.Add(label6);
            Controls.Add(label4);
            Controls.Add(label5);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(button2);
            Controls.Add(textBox4);
            Controls.Add(comboBox1);
            Controls.Add(numericUpDown1);
            Controls.Add(textBox3);
            Controls.Add(textBox2);
            Controls.Add(radioButton2);
            Controls.Add(radioButton1);
            Controls.Add(textBox1);
            Name = "Form2";
            Text = "Form2";
            ((System.ComponentModel.ISupportInitialize)numericUpDown1).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox textBox1;
        private RadioButton radioButton1;
        private RadioButton radioButton2;
        private TextBox textBox2;
        private TextBox textBox3;
        private NumericUpDown numericUpDown1;
        private ComboBox comboBox1;
        private TextBox textBox4;
        private Button button2;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private Label label5;
        private Label label6;
        private Button button1;
        private UserControl1 userControl11;
        private Label label7;
    }
}