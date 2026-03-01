using System.Text.RegularExpressions;

namespace EAC1_Gestor_de_Finanzas
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string dni = textBox1.Text;
            string password = textBox2.Text;

            Regex dniRegex = new Regex(@"^\d{8}[A-Za-z]$");

            if (!dniRegex.IsMatch(dni))
            {
                MessageBox.Show("El DNI debe tener el formato 99999999X", "Error");
                return;
            }

            if (password != "123")
            {
                MessageBox.Show("Contraseña incorrecta", "Error");
                return;
            }

            Form2 frm2 = new Form2();
            frm2.Show();
            this.Hide();
        }
    }
}
