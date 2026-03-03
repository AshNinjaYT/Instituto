using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace EAC1_Gestor_de_Finanzas
{
    public partial class Form2 : Form
    {
        decimal sueldoBruto = 0;
        decimal precioHoraExtra = 0;
        public Form2()
        {
            InitializeComponent();

        }
        private void CalcularSueldo()
        {
            
            // Sueldo Bruto
            decimal.TryParse(textBox2.Text, out sueldoBruto);
            textBox2.Text = sueldoBruto.ToString("0.00");

            // Horas extra
            int horasExtra = (int)numericUpDown1.Value;

            // IRPF
            decimal irpf = 0;
            if (comboBox1.SelectedItem != null)
            {
                switch (comboBox1.SelectedItem.ToString())
                {
                    case "2%": irpf = 0.02m; break;
                    case "10%": irpf = 0.10m; break;
                    case "18%": irpf = 0.18m; break;
                }
            }

            // Sueldo neto
            decimal totalExtras = horasExtra * precioHoraExtra;
            decimal sueldoFinalBruto = sueldoBruto + totalExtras;
            decimal sueldoNeto = sueldoFinalBruto - (sueldoFinalBruto * irpf);

            textBox4.Text = sueldoNeto.ToString("0.00");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            CalcularSueldo();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            textBox1.Text = "";
            radioButton1.Checked = false;
            radioButton2.Checked = false;
            textBox2.Text = "";
            textBox3.Text = "";
            numericUpDown1.Value = 0;
            comboBox1.SelectedIndex = -1;
            textBox4.Text = "";
        }

        protected override void OnFormClosing(FormClosingEventArgs e)
        {
            DialogResult result = MessageBox.Show(
                "¿Deseas guardar la información antes de salir?",
                "Cerrar aplicación",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Question);

            if (result == DialogResult.No)
            {
                base.OnFormClosing(e);
            }
            else
            {
                e.Cancel = true;
            }
        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            precioHoraExtra = 12;
            textBox3.Text = precioHoraExtra.ToString("0.00");

        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            precioHoraExtra = 18;
            textBox3.Text = precioHoraExtra.ToString("0.00");
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            CalcularSueldo();
        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {
            CalcularSueldo();
        }
    }
}
