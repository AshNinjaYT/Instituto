namespace EAC3
{
    partial class Form1
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
            button1 = new Button();
            button2 = new Button();
            button3 = new Button();
            button4 = new Button();
            label1 = new Label();
            label2 = new Label();
            listBox1 = new ListBox();
            textBox1 = new TextBox();
            textBox2 = new TextBox();
            listBox2 = new ListBox();
            comboBox1 = new ComboBox();
            comboBox2 = new ComboBox();
            progressBar1 = new ProgressBar();
            SuspendLayout();
            // 
            // button1
            // 
            button1.Location = new Point(239, 27);
            button1.Name = "button1";
            button1.Size = new Size(127, 22);
            button1.TabIndex = 0;
            button1.Text = "Add command";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // button2
            // 
            button2.Location = new Point(239, 55);
            button2.Name = "button2";
            button2.Size = new Size(127, 23);
            button2.TabIndex = 1;
            button2.Text = "Remove command";
            button2.UseVisualStyleBackColor = true;
            button2.Click += button2_Click;
            // 
            // button3
            // 
            button3.Location = new Point(239, 155);
            button3.Name = "button3";
            button3.Size = new Size(127, 23);
            button3.TabIndex = 2;
            button3.Text = "Start listening";
            button3.UseVisualStyleBackColor = true;
            button3.Click += button3_Click;
            // 
            // button4
            // 
            button4.Location = new Point(239, 260);
            button4.Name = "button4";
            button4.Size = new Size(127, 23);
            button4.TabIndex = 3;
            button4.Text = "Speak";
            button4.UseVisualStyleBackColor = true;
            button4.Click += button4_Click;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(239, 210);
            label1.Name = "label1";
            label1.Size = new Size(46, 15);
            label1.TabIndex = 4;
            label1.Text = "Culture";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(239, 292);
            label2.Name = "label2";
            label2.Size = new Size(46, 15);
            label2.TabIndex = 5;
            label2.Text = "Culture";
            // 
            // listBox1
            // 
            listBox1.FormattingEnabled = true;
            listBox1.ItemHeight = 15;
            listBox1.Items.AddRange(new object[] { "nuevo", "abrir", "guardar", "cerrar" });
            listBox1.Location = new Point(12, 55);
            listBox1.Name = "listBox1";
            listBox1.ScrollAlwaysVisible = true;
            listBox1.Size = new Size(193, 94);
            listBox1.TabIndex = 6;
            // 
            // textBox1
            // 
            textBox1.Location = new Point(12, 26);
            textBox1.Name = "textBox1";
            textBox1.Size = new Size(193, 23);
            textBox1.TabIndex = 7;
            // 
            // textBox2
            // 
            textBox2.Location = new Point(12, 260);
            textBox2.Multiline = true;
            textBox2.Name = "textBox2";
            textBox2.Size = new Size(193, 85);
            textBox2.TabIndex = 8;
            // 
            // listBox2
            // 
            listBox2.BackColor = SystemColors.ScrollBar;
            listBox2.Enabled = false;
            listBox2.FormattingEnabled = true;
            listBox2.ItemHeight = 15;
            listBox2.Location = new Point(12, 155);
            listBox2.Name = "listBox2";
            listBox2.ScrollAlwaysVisible = true;
            listBox2.Size = new Size(193, 94);
            listBox2.TabIndex = 9;
            // 
            // comboBox1
            // 
            comboBox1.FormattingEnabled = true;
            comboBox1.Location = new Point(291, 207);
            comboBox1.Name = "comboBox1";
            comboBox1.Size = new Size(75, 23);
            comboBox1.TabIndex = 10;
            // 
            // comboBox2
            // 
            comboBox2.FormattingEnabled = true;
            comboBox2.Location = new Point(291, 289);
            comboBox2.Name = "comboBox2";
            comboBox2.Size = new Size(75, 23);
            comboBox2.TabIndex = 11;
            // 
            // progressBar1
            // 
            progressBar1.Location = new Point(239, 184);
            progressBar1.Name = "progressBar1";
            progressBar1.Size = new Size(127, 17);
            progressBar1.TabIndex = 12;
            progressBar1.Value = 25;
            progressBar1.Visible = false;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(397, 367);
            Controls.Add(progressBar1);
            Controls.Add(comboBox2);
            Controls.Add(comboBox1);
            Controls.Add(listBox2);
            Controls.Add(textBox2);
            Controls.Add(textBox1);
            Controls.Add(listBox1);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(button4);
            Controls.Add(button3);
            Controls.Add(button2);
            Controls.Add(button1);
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button button1;
        private Button button2;
        private Button button3;
        private Button button4;
        private Label label1;
        private Label label2;
        private ListBox listBox1;
        private TextBox textBox1;
        private TextBox textBox2;
        private ListBox listBox2;
        private ComboBox comboBox1;
        private ComboBox comboBox2;
        private ProgressBar progressBar1;
    }
}
