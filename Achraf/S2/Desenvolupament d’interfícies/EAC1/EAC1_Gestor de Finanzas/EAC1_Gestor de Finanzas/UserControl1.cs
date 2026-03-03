using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace EAC1_Gestor_de_Finanzas
{
    public partial class UserControl1 : UserControl
    {
        public UserControl1()
        {
            InitializeComponent();
        }

        // Botones numéricos
        private void button0_Click(object sender, EventArgs e) { textBoxCalc.Text += "0"; }
        private void button1_Click(object sender, EventArgs e) { textBoxCalc.Text += "1"; }
        private void button2_Click(object sender, EventArgs e) { textBoxCalc.Text += "2"; }
        private void button3_Click(object sender, EventArgs e) { textBoxCalc.Text += "3"; }
        private void button4_Click(object sender, EventArgs e) { textBoxCalc.Text += "4"; }
        private void button5_Click(object sender, EventArgs e) { textBoxCalc.Text += "5"; }
        private void button6_Click(object sender, EventArgs e) { textBoxCalc.Text += "6"; }
        private void button7_Click(object sender, EventArgs e) { textBoxCalc.Text += "7"; }
        private void button8_Click(object sender, EventArgs e) { textBoxCalc.Text += "8"; }
        private void button9_Click(object sender, EventArgs e) { textBoxCalc.Text += "9"; }

        // Boton de punto
        private void buttonPunto_Click(object sender, EventArgs e) { textBoxCalc.Text += "."; }

        // Botones de operación
        private void buttonSuma_Click(object sender, EventArgs e) { textBoxCalc.Text += "+"; }
        private void buttonResta_Click(object sender, EventArgs e) { textBoxCalc.Text += "-"; }
        private void buttonMulti_Click(object sender, EventArgs e) { textBoxCalc.Text += "*"; }
        private void buttonDiv_Click(object sender, EventArgs e) { textBoxCalc.Text += "/"; }

        // Botón cambio de signo
        private void buttonSigno_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(textBoxCalc.Text))
            {
                if (textBoxCalc.Text.StartsWith("-"))
                    textBoxCalc.Text = textBoxCalc.Text.Substring(1);
                else
                    textBoxCalc.Text = "-" + textBoxCalc.Text;
            }
        }

        // Botón borrar todo
        private void buttonC_Click(object sender, EventArgs e) { textBoxCalc.Text = ""; }

        // Botón retroceso
        private void buttonBack_Click(object sender, EventArgs e)
        {
            if (textBoxCalc.Text.Length > 0)
                textBoxCalc.Text = textBoxCalc.Text.Substring(0, textBoxCalc.Text.Length - 1);
        }

        // Botón igual
        private void buttonIgual_Click(object sender, EventArgs e)
        {
            MessageBox.Show(textBoxCalc.Text, "Resultado");
        }
    }
}
