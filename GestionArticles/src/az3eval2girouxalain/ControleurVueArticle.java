/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az3eval2girouxalain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author girou
 */
public class ControleurVueArticle {

    private VueArticle vue = new VueArticle();
    private ModeleVueArticle modele = new ModeleVueArticle();

    public ControleurVueArticle() {

        modele.addObserver(vue);

        vue.getBtnNouveau().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNouveauActionPerformed(e);
            }
        });

        vue.getBtnAjouter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAjouterActionPerformed(e);
            }
        });

        vue.getBtnSupprimer().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSupprimerActionPerformed(e);
            }
        });

        vue.getBtnModifier().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnModifierActionPerformed(e);
            }
        });

        vue.getBtnAnnuler().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAnnulerActionPerformed(e);
            }
        });

        vue.getBtnPremier().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPremierActionPerformed(e);
            }
        });

        vue.getBtnDernier().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDernierActionPerformed(e);
            }
        });

        vue.getBtnSuivant().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSuivantActionPerformed(e);
            }
        });

        vue.getBtnPrecedent().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPrecedentActionPerformed(e);
            }
        });

        vue.setVisible(true);

    }

    public void btnNouveauActionPerformed(ActionEvent e) {
        try {
            vue.getBtnNouveau().setEnabled(false);
            vue.getBtnModifier().setEnabled(false);
            vue.getBtnSupprimer().setEnabled(false);
            vue.getBtnDernier().setEnabled(false);
            vue.getBtnPrecedent().setEnabled(false);
            vue.getBtnPremier().setEnabled(false);
            vue.getBtnSuivant().setEnabled(false);
            vue.getBtnAjouter().setEnabled(true);
            vue.getBtnAnnuler().setEnabled(true);

            vue.getTxtCodeA().setText(null);
            vue.getTxtCodeC().setText(null);
            vue.getTxtDesignation().setText(null);
            vue.getTxtPrix().setText(null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }

    }

    public void btnAnnulerActionPerformed(ActionEvent e) {
        try {

            int positionCurseur = ModeleVueArticle.getPositionRSActuelle();
            modele.backToEarlierArticle(positionCurseur);

            vue.getBtnNouveau().setEnabled(true);
            vue.getBtnModifier().setEnabled(true);
            vue.getBtnSupprimer().setEnabled(true);
            vue.getBtnDernier().setEnabled(true);
            vue.getBtnPrecedent().setEnabled(true);
            vue.getBtnPremier().setEnabled(true);
            vue.getBtnSuivant().setEnabled(true);
            vue.getBtnAjouter().setEnabled(false);
            vue.getBtnAnnuler().setEnabled(false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }

    }

    public void btnAjouterActionPerformed(ActionEvent e) {
        try {

            int codeA = Integer.parseInt((vue.getTxtCodeA().getText()));
            String nameA = vue.getTxtDesignation().getText();
            int codeC = Integer.parseInt((vue.getTxtCodeC().getText()));
            double prixA = Double.parseDouble(vue.getTxtPrix().getText());

            modele.addArticle(codeA, nameA, codeC, prixA);

            vue.getBtnNouveau().setEnabled(true);
            vue.getBtnModifier().setEnabled(true);
            vue.getBtnSupprimer().setEnabled(true);
            vue.getBtnDernier().setEnabled(true);
            vue.getBtnPrecedent().setEnabled(true);
            vue.getBtnPremier().setEnabled(true);
            vue.getBtnSuivant().setEnabled(true);
            vue.getBtnAjouter().setEnabled(false);
            vue.getBtnAnnuler().setEnabled(false);

            JOptionPane.showMessageDialog(vue, "Ajoute avec succes!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

    public void btnSupprimerActionPerformed(ActionEvent e) {
        try {

            int codeA = Integer.parseInt((vue.getTxtCodeA().getText()));

            modele.removeArticle(codeA);
            vue.getTxtCodeA().setText(null);
            vue.getTxtDesignation().setText(null);
            vue.getTxtCodeC().setText(null);
            vue.getTxtPrix().setText(null);
            
            JOptionPane.showMessageDialog(vue, "Supprimez avec succes!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

    public void btnModifierActionPerformed(ActionEvent e) {
        try {

            int codeA = Integer.parseInt((vue.getTxtCodeA().getText()));
            String nameA = vue.getTxtDesignation().getText();
            int codeC = Integer.parseInt((vue.getTxtCodeC().getText()));
            double prixA = Double.parseDouble(vue.getTxtPrix().getText());

            modele.changeArticle(codeA, nameA, codeC, prixA);

            JOptionPane.showMessageDialog(vue, "Modification effectuee avec succes!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

    public void btnDernierActionPerformed(ActionEvent e) {
        try {

            modele.getLastArticle();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

    public void btnPremierActionPerformed(ActionEvent e) {
        try {

            modele.getFirstArticle();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

    public void btnSuivantActionPerformed(ActionEvent e) {
        try {
            int codeAActuel = Integer.parseInt((vue.getTxtCodeA().getText()));
            modele.getNextArticle(codeAActuel);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

    public void btnPrecedentActionPerformed(ActionEvent e) {
        try {
            int codeAActuel = Integer.parseInt((vue.getTxtCodeA().getText()));
            modele.getPreviousArticle(codeAActuel);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Corrigez erreur de format!");
        }
    }

}
