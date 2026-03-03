using System.Globalization;
using System.Speech.Recognition;
using System.Speech.Synthesis;
using System.Windows.Forms;

namespace Reconocimiento
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        SpeechRecognitionEngine rec = new SpeechRecognitionEngine();

        private void Form1_Load(object sender, EventArgs e)
        {
            button1.Visible = false;
            button2.Visible = false;
            button3.Visible = false;
            Choices lista = new Choices();

            lista.Add(new string[] { "amarillo", "azul", "rojo", "todos", "ninguno", "salir" });


            Grammar gramatica = new Grammar(new GrammarBuilder(lista));

            try
            {
                rec.SetInputToDefaultAudioDevice();
                rec.LoadGrammar(gramatica);
                rec.SpeechRecognized += reconocimiento;
                rec.RecognizeAsync(RecognizeMode.Multiple);
            }
            catch (Exception)
            {

                throw;
            }
        }
        void reconocimiento(object sender, SpeechRecognizedEventArgs e)
        {
            if (e.Result.Text=="amarillo")
            {
                button1.Visible = true;
            }
            else if (e.Result.Text == "azul")
            {
                button2.Visible = true;
            }
            else if (e.Result.Text == "rojo")
            {
                button3.Visible = true;
            }
            else if (e.Result.Text == "todos")
            {
                button1.Visible = true;
                button2.Visible = true;
                button3.Visible = true;
            }
            else if (e.Result.Text == "ninguno")
            {
                button1.Visible = false;
                button2.Visible = false;
                button3.Visible = false;
            }
            else if (e.Result.Text == "salir")
            {
                Application.Exit();
            }
        }
    }
}

using System;
using System.Windows.Forms;
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

        // --- MOTORES DE VOZ ---
        SpeechRecognitionEngine rec; // Lo inicializaremos según el idioma
        SpeechSynthesizer synth = new SpeechSynthesizer();
        Choices lista = new Choices();

        private void Form1_Load(object sender, EventArgs e)
        {
            // 1. Configuración visual inicial
            progressBar1.Visible = false;

            // Configurar ComboBox de idiomas (Ejemplo básico)
            comboBox1.Items.AddRange(new string[] { "es-ES", "en-US" });
            comboBox2.Items.AddRange(new string[] { "es-ES", "en-US" });
            comboBox1.SelectedIndex = 0; // Idioma Reconocimiento por defecto
            comboBox2.SelectedIndex = 0; // Idioma Síntesis por defecto

            // 2. Configurar el motor de Síntesis
            synth.SpeakCompleted += Synth_SpeakCompleted; // Evento para cuando termine de hablar solo

            // 3. Cargar palabras iniciales
            listBox1.Items.Add("obrir");
            listBox1.Items.Add("guardar");
            listBox1.Items.Add("tancar"); // "cerrar" en catalán, según el requisito
            listBox1.Items.Add("cerrar"); // Mantenemos "cerrar" por compatibilidad con tu ejemplo

            // 4. Inicializar gramática (sin cargar el motor aún)
            RegenerarGramatica();
        }

        // --------------------------------------------------------
        // SECCIÓN 1: RECONOCIMIENTO DE VOZ (Escuchar)
        // --------------------------------------------------------

        private void Escuchando(bool activar)
        {
            if (activar)
            {
                try
                {
                    // A. Configurar el motor con el idioma seleccionado en ComboBox1
                    string culturaSeleccionada = comboBox1.Text;

                    // Si el motor no existe o el idioma cambió, lo creamos de nuevo
                    if (rec == null || rec.RecognizerInfo.Culture.Name != culturaSeleccionada)
                    {
                        if (rec != null) rec.Dispose(); // Limpiar el anterior
                        rec = new SpeechRecognitionEngine(new CultureInfo(culturaSeleccionada));
                        rec.SetInputToDefaultAudioDevice();
                        rec.SpeechRecognized += Reconocimiento;

                        // Cargamos la gramática en el nuevo motor
                        Grammar gramatica = new Grammar(new GrammarBuilder(lista));
                        rec.LoadGrammar(gramatica);
                    }

                    // B. Iniciar escucha
                    rec.RecognizeAsync(RecognizeMode.Multiple);
                    progressBar1.Visible = true; // REQUISITO: Barra de progreso activa
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error al iniciar micrófono: " + ex.Message);
                }
            }
            else
            {
                // C. Detener escucha
                if (rec != null)
                {
                    rec.RecognizeAsyncCancel();
                }
                progressBar1.Visible = false;
            }
        }

        private void Reconocimiento(object sender, SpeechRecognizedEventArgs e)
        {
            string palabra = e.Result.Text;
            float confianza = e.Result.Confidence * 100; // REQUISITO: Porcentaje

            // Formato de salida: "palabra (85,30%)"
            string resultadoTexto = $"{palabra} ({confianza:N2}%)";

            // REQUISITO ESPECIAL: "cerrar" o "tancar"
            if (palabra == "cerrar" || palabra == "tancar")
            {
                listBox2.Items.Add(resultadoTexto);

                // REQUISITO: Diálogo de confirmación en Catalán
                DialogResult respuesta = MessageBox.Show(
                    "Estàs segur que vols tancar l'aplicació?",
                    "Confirmació de tancament",
                    MessageBoxButtons.YesNo,
                    MessageBoxIcon.Warning);

                if (respuesta == DialogResult.Yes)
                {
                    Application.Exit();
                }
            }
            else
            {
                // Resto de palabras de la lista
                listBox2.Items.Add(resultadoTexto);
            }
        }

       private void RegenerarGramatica()
{
    // Guardamos el estado actual
    bool estabaEscuchando = (button3.Text == "Deixar d'escoltar");

    // Si estaba escuchando, pausamos un momento
    if (estabaEscuchando) Escuchando(false);

    // 1. Creamos la lista nueva de opciones
    lista = new Choices();

    // 2. La rellenamos usando los datos del ListBox
    if (listBox1.Items.Count > 0)
    {
        foreach (var item in listBox1.Items)
        {
            lista.Add(item.ToString());
        }
    }

    // 3. Gestionamos el motor de reconocimiento
    if (rec != null)
    {
        // Siempre descargamos la gramática anterior para limpiar
        rec.UnloadAllGrammars();

        // 4. SOLO cargamos la nueva gramática si hay palabras en la lista
        // (Si intentamos cargar una gramática vacía, el programa daría error)
        if (listBox1.Items.Count > 0)
        {
            Grammar gramatica = new Grammar(new GrammarBuilder(lista));
            rec.LoadGrammar(gramatica);
        }
    }

    // Si estaba escuchando antes, lo volvemos a activar
    if (estabaEscuchando) Escuchando(true);
}


        // --------------------------------------------------------
        // SECCIÓN 2: SÍNTESIS DE VOZ (Hablar / Text-to-Speech)
        // --------------------------------------------------------

        private void button4_Click(object sender, EventArgs e) // ASUME QUE ESTE ES TU NUEVO BOTÓN
        {
            if (button4.Text == "Començar a parlar")
            {
                // 1. Configurar idioma según ComboBox2
                try
                {
                    synth.SelectVoiceByHints(VoiceGender.Neutral, VoiceAge.Adult, 0, new CultureInfo(comboBox2.Text));
                }
                catch
                {
                    // Si falla el idioma, usa el por defecto
                }

                // 2. Iniciar lectura asíncrona
                string textoLeer = textBox2.Text; // ASUME QUE ESTE ES TU NUEVO TEXTBOX
                if (!string.IsNullOrEmpty(textoLeer))
                {
                    synth.SpeakAsync(textoLeer);
                    button4.Text = "Deixar de parlar";
                }
            }
            else
            {
                // 3. Detener lectura inmediatamente
                synth.SpeakAsyncCancelAll();
                button4.Text = "Començar a parlar";
            }
        }

        // Evento que salta cuando la voz termina de leer sola
        private void Synth_SpeakCompleted(object sender, SpeakCompletedEventArgs e)
        {
            button4.Text = "Començar a parlar";
        }

        // --------------------------------------------------------
        // BOTONES DE INTERFAZ EXISTENTES
        // --------------------------------------------------------

        private void button1_Click(object sender, EventArgs e) // AÑADIR
        {
            string newTexto = textBox1.Text;
            if (!string.IsNullOrWhiteSpace(newTexto) && !listBox1.Items.Contains(newTexto))
            {
                listBox1.Items.Add(newTexto);
                RegenerarGramatica();
                textBox1.Clear();
            }
            else
            {
                MessageBox.Show("La paraula ja existeix o és buida.", "Avís");
            }
        }

        private void button2_Click(object sender, EventArgs e) // ELIMINAR
        {
            if (listBox1.SelectedIndex != -1)
            {
                listBox1.Items.RemoveAt(listBox1.SelectedIndex);
                RegenerarGramatica();
            }
            else
            {
                MessageBox.Show("Selecciona una paraula de la llista.", "Avís");
            }
        }

        private void button3_Click(object sender, EventArgs e) // LISTEN (ESCOLTAR)
        {
            if (button3.Text == "Començar a escoltar") // Ajusta el texto según tu botón inicial en el diseñador
            {
                button3.Text = "Deixar d'escoltar";
                Escuchando(true);
            }
            else
            {
                button3.Text = "Començar a escoltar";
                Escuchando(false);
            }
        }
    }
}
