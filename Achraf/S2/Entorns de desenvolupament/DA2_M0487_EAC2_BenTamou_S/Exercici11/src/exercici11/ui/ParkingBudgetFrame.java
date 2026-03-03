package ej;


import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class EJ11 extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEdat;
    private JComboBox<String> cmbTipus;
    private JComboBox<String> cmbPadro;
    private JButton btnValidar;
    private JTextField txtMatricula;
    private JTextField txtLlargada;
    private JButton btnEsborrar;
    private JButton btnCalcular;
    private JTextField txtBase;
    private JTextField txtBaseAmbDte;
    private JTextField txtIVA;
    private JTextField txtDescompte;
    private JTextField txtTotal;
    private JTextArea txtErrors;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EJ11 frame = new EJ11();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EJ11() {
        setSize(new Dimension(480, 520));
        setResizable(false);
        setTitle("Lloguer PARQUING Profe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ---------------------- DADES CLIENT ----------------------
        JPanel Dades_client = new JPanel();
        Dades_client.setToolTipText("Dades Client");
        Dades_client.setBounds(10, 11, 400, 145);
        Dades_client.setLayout(null);
        contentPane.add(Dades_client);

        JLabel lblEdat = new JLabel("Edat:");
        lblEdat.setBounds(20, 36, 60, 22);
        Dades_client.add(lblEdat);

        txtEdat = new JTextField();
        txtEdat.setToolTipText("Introdueixi l'edat: enter entre 18 i 100");
        txtEdat.setBounds(90, 36, 65, 22);
        Dades_client.add(txtEdat);

        JLabel lblAnys = new JLabel("anys");
        lblAnys.setBounds(165, 36, 40, 22);
        Dades_client.add(lblAnys);

        JLabel lblTipus = new JLabel("Tipus de:");
        lblTipus.setBounds(20, 65, 60, 22);
        Dades_client.add(lblTipus);

        cmbTipus = new JComboBox<>();
        cmbTipus.addItem("-- Selecciona --");
        cmbTipus.addItem("Matins");
        cmbTipus.addItem("Tardes");
        cmbTipus.addItem("Nits");
        cmbTipus.addItem("24 hores");
        cmbTipus.setToolTipText("Trieu el tipus de lloguer");
        cmbTipus.setBounds(90, 65, 150, 22);
        Dades_client.add(cmbTipus);

        JLabel lblPadro = new JLabel("Padró:");
        lblPadro.setBounds(20, 97, 60, 22);
        Dades_client.add(lblPadro);

        cmbPadro = new JComboBox<>();
        cmbPadro.addItem("-- Selecciona --");
        cmbPadro.addItem("Sí");
        cmbPadro.addItem("No");
        cmbPadro.setToolTipText("Està donat d'alta al padró municipal?");
        cmbPadro.setBounds(90, 97, 150, 22);
        Dades_client.add(cmbPadro);

        btnValidar = new JButton("VALIDAR");
        btnValidar.setToolTipText("Validar dades del client");
        btnValidar.setBounds(264, 64, 100, 25);
        Dades_client.add(btnValidar);
        
        lblNewLabel_3 = new JLabel("Dades Client");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(10, 11, 100, 14);
        Dades_client.add(lblNewLabel_3);

        // ---------------------- DADES COTXE ----------------------
        JPanel Dades_Cotxe = new JPanel();
        Dades_Cotxe.setToolTipText("Dades Cotxe");
        Dades_Cotxe.setBounds(10, 167, 400, 90);
        Dades_Cotxe.setLayout(null);
        contentPane.add(Dades_Cotxe);

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(34, 21, 80, 22);
        Dades_Cotxe.add(lblMatricula);

        txtMatricula = new JTextField();
        txtMatricula.setToolTipText("4 números i 3 lletres no vocals (ex: 1234BCD)");
        txtMatricula.setEnabled(false);
        txtMatricula.setBounds(98, 21, 100, 22);
        Dades_Cotxe.add(txtMatricula);

        JLabel lblLlargada = new JLabel("Llargada:");
        lblLlargada.setBounds(217, 21, 80, 22);
        Dades_Cotxe.add(lblLlargada);

        txtLlargada = new JTextField();
        txtLlargada.setToolTipText("Llargada del cotxe en metres, p.ex. 3.75 (entre 2.5 i 6)");
        txtLlargada.setEnabled(false);
        txtLlargada.setBounds(269, 21, 100, 22);
        Dades_Cotxe.add(txtLlargada);

        btnEsborrar = new JButton("ESBORRAR TOT");
        btnEsborrar.setEnabled(false);
        btnEsborrar.setBounds(68, 54, 130, 25);
        Dades_Cotxe.add(btnEsborrar);

        btnCalcular = new JButton("CALCULAR");
        btnCalcular.setEnabled(false);
        btnCalcular.setBounds(237, 54, 120, 25);
        Dades_Cotxe.add(btnCalcular);
        
        lblNewLabel_2 = new JLabel("Dades Cotxe");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(10, 0, 136, 14);
        Dades_Cotxe.add(lblNewLabel_2);

        // ---------------------- PREUS ----------------------
        JPanel Preus = new JPanel();
        Preus.setToolTipText("Preus");
        Preus.setLayout(null);
        Preus.setBounds(10, 268, 400, 110);
        contentPane.add(Preus);

        JLabel lblBase = new JLabel("Base");
        lblBase.setBounds(30, 10, 60, 20);
        Preus.add(lblBase);

        txtBase = new JTextField();
        txtBase.setEditable(false);
        txtBase.setBounds(30, 30, 80, 22);
        Preus.add(txtBase);

        JLabel lblDescompte = new JLabel("Descompte");
        lblDescompte.setBounds(130, 10, 80, 20);
        Preus.add(lblDescompte);

        txtDescompte = new JTextField();
        txtDescompte.setEditable(false);
        txtDescompte.setBounds(130, 30, 80, 22);
        Preus.add(txtDescompte);

        JLabel lblBaseAmbDte = new JLabel("Base amb Dte.");
        lblBaseAmbDte.setBounds(30, 63, 100, 20);
        Preus.add(lblBaseAmbDte);

        txtBaseAmbDte = new JTextField();
        txtBaseAmbDte.setEditable(false);
        txtBaseAmbDte.setBounds(30, 83, 80, 22);
        Preus.add(txtBaseAmbDte);

        JLabel lblIVA = new JLabel("IVA");
        lblIVA.setBounds(130, 63, 60, 20);
        Preus.add(lblIVA);

        txtIVA = new JTextField();
        txtIVA.setEditable(false);
        txtIVA.setBounds(130, 83, 80, 22);
        Preus.add(txtIVA);

        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setBounds(219, 52, 60, 20);
        Preus.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        txtTotal.setBounds(259, 51, 80, 22);
        Preus.add(txtTotal);
        
        lblNewLabel_1 = new JLabel("Preus");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 0, 46, 14);
        Preus.add(lblNewLabel_1);

        // ---------------------- ERRORS I ALERTES ----------------------
        JPanel Errors_i_alertes = new JPanel();
        Errors_i_alertes.setToolTipText("Errors i alertes");
        Errors_i_alertes.setLayout(null);
        Errors_i_alertes.setBounds(10, 389, 400, 81);
        contentPane.add(Errors_i_alertes);

        txtErrors = new JTextArea();
        txtErrors.setEditable(false);
        txtErrors.setToolTipText("Aquí s'indiquen errors i alertes");
        txtErrors.setBounds(10, 43, 367, 27);
        Errors_i_alertes.add(txtErrors);
        
        lblNewLabel = new JLabel("Errors i alertes");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 11, 97, 14);
        Errors_i_alertes.add(lblNewLabel);

        // ---------------------- ACCIONES ----------------------
        btnValidar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarDadesClient();
            }
        });

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularPreu();
            }
        });

        btnEsborrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                esborrarTot();
            }
        });
    }

    // ---------------------- VALIDAR CLIENT ----------------------
    private void validarDadesClient() {
        txtErrors.setText("");
        try {
            int edat = Integer.parseInt(txtEdat.getText().trim());
            if (edat < 18 || edat > 100) {
                txtErrors.setText("⚠️ L'edat ha de ser entre 18 i 100 anys.");
                return;
            }
        } catch (NumberFormatException e) {
            txtErrors.setText("⚠️ Introdueixi una edat vàlida (nombre enter).");
            return;
        }

        if (cmbTipus.getSelectedIndex() == 0) {
            txtErrors.setText("⚠️ Seleccioni un tipus de lloguer.");
            return;
        }

        if (cmbPadro.getSelectedIndex() == 0) {
            txtErrors.setText("⚠️ Indiqui si està donat d'alta al padró.");
            return;
        }

        txtErrors.setText("✅ Dades del client validades correctament.");
        txtMatricula.setEnabled(true);
        txtLlargada.setEnabled(true);
        btnCalcular.setEnabled(true);
        btnEsborrar.setEnabled(true);
    }

    // ---------------------- CALCULAR PREU ----------------------
    private void calcularPreu() {
        txtErrors.setText("");

        String matricula = txtMatricula.getText().trim();
        if (!matricula.matches("\\d{4}[B-DF-HJ-NP-TV-Z]{3}")) {
            txtErrors.setText("⚠️ Matrícula invàlida. Format: 1234BCD");
            return;
        }

        double llargada;
        try {
            llargada = Double.parseDouble(txtLlargada.getText().trim());
            if (llargada < 2.5 || llargada > 6.0) {
                txtErrors.setText("⚠️ Llargada fora de rang (2.5 - 6.0 m).");
                return;
            }
        } catch (NumberFormatException e) {
            txtErrors.setText("⚠️ Introdueixi una llargada vàlida (nombre decimal).");
            return;
        }

        // Base segons tipus
        double base = 0;
        switch (cmbTipus.getSelectedIndex()) {
            case 1: base = 45; break; // Matins
            case 2: base = 55; break; // Tardes
            case 3: base = 60; break; // Nits
            case 4: base = 70; break; // 24h
        }

        double descompte = 0;
        if (cmbPadro.getSelectedItem().equals("Sí")) descompte = base * 0.15;

        double baseAmbDte = base - descompte;
        double iva = baseAmbDte * 0.21;
        double total = baseAmbDte + iva;

        txtBase.setText(String.format("%.2f €", base));
        txtDescompte.setText(String.format("-%.2f €", descompte));
        txtBaseAmbDte.setText(String.format("%.2f €", baseAmbDte));
        txtIVA.setText(String.format("%.2f €", iva));
        txtTotal.setText(String.format("%.2f €", total));

        txtErrors.setText("✅ Càlcul realitzat correctament.");
    }

    // ---------------------- ESBORRAR TOT ----------------------
    private void esborrarTot() {
        txtEdat.setText("");
        cmbTipus.setSelectedIndex(0);
        cmbPadro.setSelectedIndex(0);
        txtMatricula.setText("");
        txtLlargada.setText("");
        txtBase.setText("");
        txtDescompte.setText("");
        txtBaseAmbDte.setText("");
        txtIVA.setText("");
        txtTotal.setText("");
        txtMatricula.setEnabled(false);
        txtLlargada.setEnabled(false);
        btnCalcular.setEnabled(false);
        btnEsborrar.setEnabled(false);
        txtErrors.setText("🧹 Dades esborrades.");
    }
}
