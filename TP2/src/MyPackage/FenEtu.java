package MyPackage;
//  TP2 ASPE 2019
//  Laurent Jérémy

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenEtu extends JFrame implements WindowListener
{
    /*Déclaration des composants*/
    private JLabel lbl_etatCivil, lbl_nom, lbl_prenom, lbl_annee;
    private JLabel lbl_loisirs, lbl_commentaire, lbl_semestre;
    private JLabel lbl_image_profil;

    private JComboBox cbb_etatCivil;

    private JTextField tf_nom, tf_prenom;

    private JRadioButton rb_1A, rb_2A,rb_S1,rb_S2,rb_S3,rb_S4;

    private ButtonGroup groupAnnee = new ButtonGroup();
    private ButtonGroup groupSem = new ButtonGroup();

    private JCheckBox ckb_cinema, ckb_sport, ckb_lecture;

    private JTextArea zone_commentaire;

    private JButton bt_ajouter, bt_modifier, bt_suppr;
    /*Déclaration de la fenetre*/
    JFrame frame = new JFrame("FrameDemo");

    /*Déclaration des panneaux*/
    private JPanel pannECivil, pannNomAnLoisir, pannComm, pannBoutons;

    /*Déclaration des sous panneaux*/
    private JPanel pannName, pannAnnee, pannLoisir;

    /*Constructeurs*/
    public FenEtu()
    {
        super();
        initComponents();
    }

    @SuppressWarnings("Convert2Lambda")

    /*Initialisation des composants*/
    private void initComponents() {
        /*Définition des composants*/
        lbl_etatCivil = new JLabel("Etat civil:");
        lbl_nom = new JLabel("Nom:");
        lbl_prenom = new JLabel("Prénom:");

        lbl_image_profil = new JLabel(new ImageIcon("src/gif/homme.png"));

        lbl_annee = new JLabel("Année:");
        lbl_semestre = new JLabel("Semestre:");
        lbl_semestre.setVisible(false); // cf
        lbl_loisirs = new JLabel("Loisirs:");
        lbl_commentaire = new JLabel("Commentaire:");

        zone_commentaire = new JTextArea(5, 15);

        cbb_etatCivil = new JComboBox();
        cbb_etatCivil.addItem("M.");
        cbb_etatCivil.addItem("Mme");

        /*Choix de l'image selon le genre*/
        cbb_etatCivil.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cbb_etatCivil.getSelectedItem() == "Mme") {
                    lbl_image_profil.setIcon(new ImageIcon("src/gif/girl.jpg"));
                } else {
                    lbl_image_profil.setIcon(new ImageIcon("src/gif/homme.png"));
                }
            }
        });

        tf_nom = new JTextField(12);
        tf_prenom = new JTextField(12);

        ckb_cinema = new JCheckBox("Netflix");
        ckb_sport = new JCheckBox("Sport");
        ckb_lecture = new JCheckBox("Spotify");

        /*Choix de l'année, apparition du semestre correspondant*/
        rb_1A = new JRadioButton("1 Année");
        rb_1A.addActionListener(new RdbListener());
        rb_2A = new JRadioButton("2 Année");
        rb_2A.addActionListener(new RdbListener());

        rb_S1 = new JRadioButton("S1");
        rb_S1.setVisible(false);
        //false pour ne pas être visible si S1 non sélectionné
        rb_S2 = new JRadioButton("S2");
        rb_S2.setVisible(false);
        rb_S3 = new JRadioButton("S3");
        rb_S3.setVisible(false);
        rb_S4 = new JRadioButton("S4");
        rb_S4.setVisible(false);

        /*regroupement des boutons par groupe*/
        groupAnnee.add(rb_1A);
        groupAnnee.add(rb_2A);
        groupSem.add(rb_S1);
        groupSem.add(rb_S2);
        groupSem.add(rb_S3);
        groupSem.add(rb_S4);

        /*Bouton en bas de page*/
        bt_ajouter = new JButton("Ajouter");
        bt_modifier = new JButton("Modifier");
        bt_suppr = new JButton("Supprimé");
        /*Ajout de l'écoute*/
        bt_ajouter.addMouseListener(new ButtonListener());
        bt_modifier.addMouseListener(new ButtonListener());
        bt_suppr.addMouseListener(new ButtonListener());

        /*Paramétrage de la fenetre*/
        frame.addWindowListener(this);
        frame.setTitle("Saisie Etudiant");
        frame.setSize(500, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*Grille de 4 Lignes et 1 colonne*/
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new GridLayout(4, 1));

        Def4Pann();

        /*ajout des panneaux au content pane de la fenetre, sur le gridlayout*/
        contentPane.add(pannECivil);
        contentPane.add(pannNomAnLoisir);
        contentPane.add(pannComm);
        contentPane.add(pannBoutons);
    }
        /*Définition des 4 panneaux*/
        private void Def4Pann()
    {
            /*Placement par défaut*/
            LayoutManager MyLayout = new FlowLayout(FlowLayout.LEFT);
            /*Paramétrages des panneaux*/
            pannECivil = new JPanel();
            pannECivil.add(lbl_image_profil);
            pannECivil.add(lbl_etatCivil);
            pannECivil.add(cbb_etatCivil);

            pannNomAnLoisir = new JPanel(new GridLayout(3,1));

            /*Création des panneaux*/
            pannName = new JPanel(MyLayout);
            pannName.add(lbl_nom);
            pannName.add(tf_nom);
            pannName.add(lbl_prenom);
            pannName.add(tf_prenom);

            pannAnnee = new JPanel(MyLayout);
            pannAnnee.add(lbl_annee);
            pannAnnee.add(rb_1A);
            pannAnnee.add(rb_2A);
            pannAnnee.add(lbl_semestre);
            pannAnnee.add(rb_S1);
            pannAnnee.add(rb_S2);
            pannAnnee.add(rb_S3);
            pannAnnee.add(rb_S4);

            pannLoisir = new JPanel(MyLayout);
            pannLoisir.add(lbl_loisirs);
            pannLoisir.add(ckb_cinema);
            pannLoisir.add(ckb_lecture);
            pannLoisir.add(ckb_sport);

            pannNomAnLoisir.add(pannName);
            pannNomAnLoisir.add(pannAnnee);
            pannNomAnLoisir.add(pannLoisir);

            pannComm= new JPanel(MyLayout);
            pannComm.add(lbl_commentaire);
            pannComm.add(zone_commentaire);

            pannBoutons= new JPanel();
            pannBoutons.add(bt_suppr);
            pannBoutons.add(bt_modifier);
            pannBoutons.add(bt_ajouter);
    }

    class ButtonListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == bt_suppr) {
                tf_nom.setText("");
                tf_prenom.setText("");
                zone_commentaire.setText("");
                ckb_cinema.setSelected(false);
                ckb_sport.setSelected(false);
                ckb_lecture.setSelected(false);
                lbl_semestre.setVisible(false);
                rb_S1.setVisible(false);
                rb_S2.setVisible(false);
                rb_S3.setVisible(false);
                rb_S4.setVisible(false);
                cbb_etatCivil.setSelectedIndex(0);
            }
            else if (e.getSource() == bt_ajouter) {
                String C, A, S, NET, SP, LEC;
                if (cbb_etatCivil.getSelectedIndex() == 0) {
                    C = "M.";
                }
                else {
                    C = "Mme";
                }

                if (rb_1A.isSelected()) {
                    A = "1ere";
                    if (rb_S1.isSelected())
                        S = "S1";
                    else
                        S = "S2";
                } else {
                    A = "2eme";
                    if (rb_S3.isSelected())
                        S = "S3";
                    else
                        S = "S4";
                }
                if (ckb_cinema.isSelected())
                    NET = "Netflix\n";
                else
                    NET = "";
                if (ckb_sport.isSelected())
                    SP = "Spotify\n";
                else
                    SP = "";
                if (ckb_lecture.isSelected())
                    LEC = "La lecture\n";
                else
                    LEC = "";

                JOptionPane.showMessageDialog(frame, C
                        + " "
                        + tf_nom.getText()
                        + " "
                        + tf_prenom.getText()
                        + " est inscrit(e) en "
                        + A
                        + " année en semestre "
                        + S
                        + " et a comme loisirs:\n"
                        + NET
                        + SP
                        + LEC
                        + "Autres informations: "
                        + zone_commentaire.getText(), "Etudiant(e) ajouté(e)", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (e.getSource() == bt_modifier) {
                JOptionPane.showMessageDialog(frame, "Il faut directement modifier les champs dans la ");
            }

        }
        /*Redéfition des méthodes pour un affichage dans la console*/
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Bouton pressé");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Bouton relâché");
        }

        /*passage en surbrillance rouge des boutons*/
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == bt_suppr) {
                bt_suppr.setForeground(Color.red);
                bt_suppr.setText("En cours de selection");
            }
            if (e.getSource() == bt_modifier) {
                bt_modifier.setForeground(Color.red);
                bt_modifier.setText("En cours de selection");
            }
        }

        /*Passage en surbrillance noir des boutons*/
        @Override
        public void mouseExited(MouseEvent e)
        {
            if (e.getSource() == bt_suppr)
            {
                bt_suppr.setForeground(Color.black);
                bt_suppr.setText("Supprimer");
            }
            if (e.getSource() == bt_modifier)
            {
                bt_modifier.setForeground(Color.black);
                bt_modifier.setText("Modifier");
            }
        }
    }
    /*Pour gérer les écoutes de boutons*/
    class RdbListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (rb_1A.isSelected()){
                lbl_semestre.setVisible(true);
                rb_S1.setVisible(true);
                rb_S2.setVisible(true);
                rb_S3.setVisible(false);
                rb_S4.setVisible(false);
            }
            if (rb_2A.isSelected()){
                lbl_semestre.setVisible(true);
                rb_S3.setVisible(true);
                rb_S4.setVisible(true);
                rb_S1.setVisible(false);
                rb_S2.setVisible(false);
            }
        }
    }
   static class ConfirmDialogInFrame extends JFrame {

       public ConfirmDialogInFrame() {
           getContentPane().setBackground(Color.DARK_GRAY);
           setTitle("Confirm Dialog in Frame");
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setVisible(true);
           setResizable(false);
           setSize(400, 300);
           getContentPane().setLayout(null);
       }
   }

    public static void main(String[] args)
    {
            FenEtu maFenetre = new FenEtu();
    }

    @Override
    public void windowClosing(WindowEvent e){
        int input = JOptionPane.showConfirmDialog(new ConfirmDialogInFrame(),"Tu es sûr de vouloir quitter ?","", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        // 0=ok, 2=cancel
        if (input==0) {
            System.out.println(input);
            System.out.println("Application Fermée");
            frame.dispose();
            System.exit(0);

            {
                System.out.println("Application Fermée");
            }
        }
        else{
            System.out.println("Je sais pas comment on fait pour pas fermer tout de même la fenetre...");
        }
    }

    @Override
    public void windowOpened(WindowEvent e){
        frame.setTitle(frame.getTitle()+" de Lyon");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Window Closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Window Icinified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Window Deiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Application ouverte");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Window Deactivated");
    }
}


