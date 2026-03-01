using System.Speech.Recognition;
using System.Speech.Synthesis;
using System.Globalization;

namespace EAC3
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        SpeechRecognitionEngine rec = new SpeechRecognitionEngine();
        SpeechSynthesizer voz = new SpeechSynthesizer();
        Choices lista = new Choices();

        private void Form1_Load(object sender, EventArgs e)
        {

            // Carga manual de idiomas disponibles
            comboBox1.Items.Add(new CultureInfo("es-ES"));
            comboBox1.Items.Add(new CultureInfo("en-US"));
            comboBox1.SelectedIndex = 0;

            comboBox2.Items.Add(new CultureInfo("es-ES"));
            comboBox2.Items.Add(new CultureInfo("en-US"));
            comboBox2.SelectedIndex = 0;

            voz.SpeakCompleted += new EventHandler<SpeakCompletedEventArgs>(Fin_hablar);


            RegenerarGramatica();

            try
            {
                rec.SetInputToDefaultAudioDevice();
                rec.SpeechRecognized += Reconocimiento;

                Grammar gramatica = new Grammar(new GrammarBuilder(lista));
                rec.LoadGrammar(gramatica);
            }
            catch (Exception)
            {
                throw;
            }
        }

        private void Escuchando(bool activo)  // Controla el estado del reconocimiento
        {
            if (activo)
            {
                try
                {
                    rec.RecognizeAsync(RecognizeMode.Multiple);
                }
                catch (Exception)
                {

                    throw;
                }
            }
            else
            {
                rec.RecognizeAsyncCancel();
            }

        }

        private void Reconocimiento(object sender, SpeechRecognizedEventArgs e)  // Procesa la palabra detectada, gestiona el cierre y muestra la confianza
        {
            string palabra = e.Result.Text;
            float confianza = e.Result.Confidence * 100;
            string rPalabra = $"{palabra} ({confianza:N2}%)";


            if (e.Result.Text == "cerrar")
            {
                listBox2.Items.Add(rPalabra);
                // Mostramos el Pop Up con botones de Sí y No
                DialogResult respuesta = MessageBox.Show("¿Estás seguro de que quieres cerrar la aplicación?", "Confirmar cierre",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Warning
                );

                // Si el usuario pulsa Sí, cerramos la app
                if (respuesta == DialogResult.Yes)
                {
                    Application.Exit();
                }
            }
            else
            {
                listBox2.Items.Add(rPalabra);
            }
        }

        private void RegenerarGramatica() // Recarga el motor con las palabras actuales del ListBox
        {
            bool estabaActivo = (button3.Text == "Stop listening");

            //Apagamos el reconocimiento para poder modificar la gramática
            Escuchando(false);
            rec.UnloadAllGrammars();
            lista = new Choices();

            if (listBox1.Items.Count > 0)
            {
                foreach (var item in listBox1.Items)
                {
                    lista.Add(item.ToString());
                }

                //Cargamos la nueva gramática
                Grammar gramatica = new Grammar(new GrammarBuilder(lista));
                rec.LoadGrammar(gramatica);
            }

            if (estabaActivo)
            {
                Escuchando(true);
            }
        }

        private void ConfiguararIdioma() // Reinicia el motor de reconocimiento si cambia el idioma seleccionado
        {
            CultureInfo culturaElegida = (CultureInfo)comboBox1.SelectedItem;

            if (rec.RecognizerInfo.Culture.Name != culturaElegida.Name)
            {
                rec.Dispose(); // Eliminamos el motor anterior
                rec = new SpeechRecognitionEngine(culturaElegida); // Creamos uno nuevo con el idioma
                rec.SetInputToDefaultAudioDevice();
                rec.SpeechRecognized += Reconocimiento;
                RegenerarGramatica(); // Recarga de palabras en el nuevo idioma
            }
        }

        private void button1_Click(object sender, EventArgs e) //Añadir palabra
        {
            string newTexto = textBox1.Text;

            if (listBox1.Items.Contains(newTexto))
            {
                //Si existe, se muestra un Pop Up
                MessageBox.Show("Esta palabra ya existe en la lista.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            else if (newTexto == null || newTexto == string.Empty)
            {
                //Si no pone nada, se muestra un Pop Up
                MessageBox.Show("Por favor, añade texto.", "Aviso");
            }
            else
            {
                //Si no existe, se añade
                listBox1.Items.Add(newTexto);
                RegenerarGramatica();
            }
        }

        private void button2_Click(object sender, EventArgs e) //Quitar palabra
        {
            if (listBox1.SelectedIndex != -1)
            {
                //Elimina el texto
                listBox1.Items.RemoveAt(listBox1.SelectedIndex);
                RegenerarGramatica();
            }
            else
            {
                //Si no seleciona nada, se muestra un Pop Up
                MessageBox.Show("Por favor, selecciona primero una palabra de la lista.", "Aviso");
            }
        }

        private void button3_Click(object sender, EventArgs e) //Escuchar
        {

            if (button3.Text == "Start listening")
            {
                ConfiguararIdioma();

                button3.Text = "Stop listening";
                progressBar1.Visible = true;
                Escuchando(true);
            }
            else
            {
                button3.Text = "Start listening";
                progressBar1.Visible = false;
                Escuchando(false);
            }
        } 

        private void Fin_hablar(object sender, SpeakCompletedEventArgs e) // Restablece el texto del botón cuando termina la locución
        {
            // Invoke necesario porque el evento viene de otro hilo
            BeginInvoke((MethodInvoker)delegate
            {
                button4.Text = "Speak";
            });
        }

        private void button4_Click(object sender, EventArgs e) //Hablar
        {    
            if (button4.Text == "Speak")
            {
                if (string.IsNullOrEmpty(textBox2.Text)) return;

                //Configurar Idioma seleccionado
                CultureInfo idiomaSel = (CultureInfo)comboBox2.SelectedItem;
                voz.SelectVoiceByHints(VoiceGender.Neutral, VoiceAge.Adult, 0, idiomaSel);

                button4.Text = "Stop";
                voz.SpeakAsync(textBox2.Text);
            }
            else
            {
                voz.SpeakAsyncCancelAll();
                button4.Text = "Speak";
            }
        }
    }
}
