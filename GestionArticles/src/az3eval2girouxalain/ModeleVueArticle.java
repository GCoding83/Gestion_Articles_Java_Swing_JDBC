/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az3eval2girouxalain;

import com.sun.istack.internal.logging.Logger;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.logging.Level;
import sun.util.logging.PlatformLogger;

public class ModeleVueArticle extends Observable {

    private static String hote = "jdbc:derby://localhost:1527/TP2";
    private static String usager = "alain";
    private static String passwd = "rosemont";

    private Connection con = null;
    private String sqlc = "Select * from Article";
    private Statement stmt;
    private ResultSet rS;
    private static int positionRSActuelle;


    public static int getPositionRSActuelle() {
        return positionRSActuelle;
    }

    //Pour se connecter seulement
    public Connection getConnexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(hote, usager, passwd);
            System.out.println("Connexion obtenue");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

    public void getFirstArticle() {
        try {
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);
            rS.first();

            int codeA = rS.getInt(1);
            String nameA = rS.getString(2);
            int codeC = rS.getInt(3);
            double prixA = rS.getDouble(4);

            positionRSActuelle = rS.getRow();

            //Creer objet
            Article premierArticle = new Article(codeA, nameA, codeC, prixA);
            setChanged();
            this.notifyObservers(premierArticle);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getLastArticle() {
        try {
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);
            rS.last();

            int codeA = rS.getInt(1);
            String nameA = rS.getString(2);
            int codeC = rS.getInt(3);
            double prixA = rS.getDouble(4);

            positionRSActuelle = rS.getRow();

            //Creer objet
            Article dernierArticle = new Article(codeA, nameA, codeC, prixA);
            setChanged();
            this.notifyObservers(dernierArticle);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getNextArticle(int codeAActuel) {
        try {

            //Aller chercher tous les articles pour notre resultset
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);

            //Parcourir le resultset
            while (rS.next()) {
                int codeCurseur = rS.getInt(1);
                //Si cette ligne est celle qui se trouve presentement dans l'interface
                if (codeCurseur == codeAActuel) {
                    //Voir si on peut aller chercher la ligne suivante. Si oui, la retourner.
                    if (rS.next()) {
                        //Aller chercher les infos pour creer l'objet a envoyer
                        int codeA = rS.getInt(1);
                        String nameA = rS.getString(2);
                        int codeC = rS.getInt(3);
                        double prixA = rS.getDouble(4);

                        positionRSActuelle = rS.getRow();

                        //Creer objet a envoyer
                        Article articleSuivant = new Article(codeA, nameA, codeC, prixA);
                        setChanged();

                        //Envoyer l'objet
                        this.notifyObservers(articleSuivant);

                    } else {
                        setChanged();
                        this.notifyObservers();
                    }
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void getPreviousArticle(int codeAActuel) {
        try {

            //Aller chercher tous les articles pour notre resultset
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);

            //Parcourir le resultset
            while (rS.next()) {
                int codeCurseur = rS.getInt(1);
                //Si cette ligne est celle qui se trouve presentement dans l'interface
                if (codeCurseur == codeAActuel) {
                    //Voir si on peut aller chercher la ligne precedent. Si oui, la retourner.
                    if (rS.previous()) {
                        //Aller chercher les infos pour creer l'objet a envoyer
                        int codeA = rS.getInt(1);
                        String nameA = rS.getString(2);
                        int codeC = rS.getInt(3);
                        double prixA = rS.getDouble(4);

                        positionRSActuelle = rS.getRow();

                        //Creer objet a envoyer
                        Article articleSuivant = new Article(codeA, nameA, codeC, prixA);
                        setChanged();

                        //Envoyer l'objet
                        this.notifyObservers(articleSuivant);

                        //Si non, envoyer une erreur.
                    } else {
                        setChanged();
                        this.notifyObservers();
                    }
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addArticle(int codeA, String nameA, int codeC, double prixA) {
        try {
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);
            rS.moveToInsertRow();
            rS.updateInt(1, codeA);
            rS.updateString(2, nameA);
            rS.updateInt(3, codeC);
            rS.updateDouble(4, prixA);
            rS.insertRow();

            positionRSActuelle = rS.getRow();

            Article article = new Article(codeA, nameA, codeC, prixA);
            setChanged();
            this.notifyObservers(article);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void removeArticle(int code) {
        try {
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);
            rS.next();

            //Parcourir le resultset
            while (rS.next()) {
                int codeCurseur = rS.getInt(1);
                //Si cette ligne est celle qui se trouve presentement dans l'interface, alors l'effacer
                if (codeCurseur == code) {
                    rS.deleteRow();
                    Article article = new Article(code);
                    setChanged();
                    rS.first();
                    positionRSActuelle = 1;
                    //this.notifyObservers(article);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void changeArticle(int codeA, String nameA, int codeC, double prixA) {
        try {

            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);
            rS.next();

            //Parcourir le resultset
            while (rS.next()) {
                int codeCurseur = rS.getInt(1);
                //Si cette ligne est celle qui se trouve presentement dans l'interface
                if (codeCurseur == codeA) {
                    rS.updateInt(1, codeA);
                    rS.updateString(2, nameA);
                    rS.updateInt(3, codeC);
                    rS.updateDouble(4, prixA);
                    rS.updateRow();

                    positionRSActuelle = rS.getRow();

                    Article article = new Article(codeA, nameA, codeC, prixA);
                    setChanged();
                    this.notifyObservers(article);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void backToEarlierArticle(int positionCurseurPasse) {
        try {

            //Aller chercher tous les articles pour notre resultset
            con = DriverManager.getConnection(hote, usager, passwd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rS = stmt.executeQuery(sqlc);

            //Parcourir le resultset
            while (rS.next()) {

                if (rS.getRow() == positionCurseurPasse) {

                    //Aller chercher les infos pour creer l'objet a envoyer
                    int codeA = rS.getInt(1);
                    String nameA = rS.getString(2);
                    int codeC = rS.getInt(3);
                    double prixA = rS.getDouble(4);

                    //Creer objet a envoyer
                    Article articleAncien = new Article(codeA, nameA, codeC, prixA);
                    setChanged();

                    //Envoyer l'objet
                    this.notifyObservers(articleAncien);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //rS.close();
                stmt.close();
                //con.close();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ModeleVueArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
