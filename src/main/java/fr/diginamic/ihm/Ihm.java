package fr.diginamic.ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ihm extends JFrame {
    public static final String IBOOF_LOGO = "./design/logo-no-background.png";

    public Ihm() throws IOException {
        super("IBOOF - Immersive Best open FOOd Facts Advisor");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);   /*Pour centrer la fenêtre*/

        //Création du contener principal
        JPanel contentPane = (JPanel) this.getContentPane();

        //Ajout des widgets enfants

        //Présentation de l'application dans la zone Nord du container principal
        JPanel northPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        BufferedImage IBOOF_logo = ImageIO.read(new File(IBOOF_LOGO));
        JLabel pic = new JLabel(new ImageIcon(IBOOF_logo));
        northPanel.add(pic);
        JButton btnRegenerateDatabase = new JButton("Recharger la base de données");
        northPanel.add(btnRegenerateDatabase);
        contentPane.add(northPanel, BorderLayout.NORTH);

        //Création d'une barre de status dans la zone SUD
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblStatus1 = new JLabel("message1");
        lblStatus1.setPreferredSize(new Dimension(100, 30));
        statusBar.add(lblStatus1);
        contentPane.add(statusBar, BorderLayout.SOUTH);

        //La zone ouest de la fenêtre contiendra les réponses des requêtes
        JPanel panelLeft = new JPanel(new GridLayout(7, 1, 5, 5));
        JLabel lblSearch = new JLabel("Rechercher");
        lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
        panelLeft.add(lblSearch);
        JButton btn1 = new JButton("N meilleurs produits\npour une Marque donnée");
        btn1.setPreferredSize(new Dimension(50, 0));
        panelLeft.add(btn1);
        panelLeft.add(new JButton("N meilleurs produits pour une Catégorie donnée"));
        panelLeft.add(new JButton("N meilleurs produits par Marque et par Catégorie"));
        panelLeft.add(new JButton("N ingrédients les plus courants"));
        panelLeft.add(new JButton("N allergènes les plus courants"));
        panelLeft.add(new JButton("N additifs les plus courants"));
        contentPane.add(panelLeft, BorderLayout.WEST);

        //Zone centrale
        JTextArea textContent = new JTextArea((""));
        JScrollPane scrContent = new JScrollPane(textContent);
        contentPane.add(textContent);
    }

    public static Ihm display() throws UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        Ihm ihm = new Ihm();
        ihm.setVisible(true);
        return ihm;
    }

}