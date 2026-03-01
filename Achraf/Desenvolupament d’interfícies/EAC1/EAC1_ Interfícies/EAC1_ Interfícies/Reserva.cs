namespace EAC1__Interfícies
{
    public partial class Reserva : Form
    {
        public Reserva()
        {
            InitializeComponent();
        }

        private void backgroundWorker1_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void fileSystemWatcher1_Changed(object sender, FileSystemEventArgs e)
        {

        }

        private void label1_Click_1(object sender, EventArgs e)
        {

        }

        private void label1_Click_2(object sender, EventArgs e)
        {

        }

        private void textBox5_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void dataGridView1_CellContentClick_1(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void tabPage2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Vuelos nuevoForm = new Vuelos(); // Form2 es el nombre de tu segundo formulario
            nuevoForm.Show(); // Abre el formulario como ventana aparte
        }
        private void button1_MouseEnter(object sender, EventArgs e)
        {
            button1.BackColor = Color.IndianRed;
        }
        private void button1_MouseLeave(object sender, EventArgs e)
        {
            button1.BackColor = SystemColors.Control;
        }
        private void textBox1_Validating(object sender, System.ComponentModel.CancelEventArgs e)
        {
            if (!textBox1.Text.Contains("@"))
            {
                MessageBox.Show("Correo no válido. Debe contener '@'.");
                e.Cancel = true; // cancela perder el foco si error
            }
        }
        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            MessageBox.Show("Destino seleccionado: " + comboBox2.SelectedItem.ToString());
        }


    }
}
