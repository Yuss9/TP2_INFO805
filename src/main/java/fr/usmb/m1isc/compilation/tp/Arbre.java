package fr.usmb.m1isc.compilation.tp;
import java.util.ArrayList;

import static fr.usmb.m1isc.compilation.tp.TypeNode.*;

import static java.util.Objects.isNull;

public class Arbre {
//    private TypeNode type;
//    private String racine;
//    private Arbre fg,fd;
//
//    public Arbre(TypeNode type, String racine, Arbre fg, Arbre fd) {
//        this.type = type;
//        this.racine = racine;
//        this.fg = fg;
//        this.fd = fd;
//    }
//    public Arbre(TypeNode type, String racine) {
//        this.type = type;
//        this.racine = racine;
//    }
//    public Arbre() {}
//
//    public String getRacine() {
//        return this.racine;
//    }
//
//    public TypeNode getType() {
//        return this.type;
//    }
//
//    public Arbre getFg() {
//        return this.fg;
//    }
//
//    public Arbre getFd() {
//        return this.fd;
//    }
//
//    public void setRacine(String racine) {
//        this.racine = racine;
//    }
//
//    public void setType(TypeNode type) {
//        this.type = type;
//    }
//
//    public void setFg(Arbre fg) {
//        this.fg = fg;
//    }
//
//    public void setFd(Arbre fd) {
//        this.fd = fd;
//    }
//
//
//    public String toString() {
//        StringBuilder resultat = new StringBuilder();
//        if (this.type == OPERATEUR || this.type == LET) {
//            resultat.append("(").append(this.racine);
//        } else {
//            resultat.append(" ").append(this.racine);
//        }
//        if (!isNull(this.fg)) {
//            resultat.append(this.fg.toString());
//        }
//        if (!isNull(this.fd)) {
//            resultat.append(this.fd.toString());
//        }
//        if (this.type == OPERATEUR || this.type == LET) {
//            resultat.append(")");
//        }
//        return resultat.toString();
//
//
//    }
//
//    public void genererDonnees(ArrayList<String> listDonnees) {
//        if (fg != null) {
//            fg.genererDonnees(listDonnees);
//        }
//        if (fd != null) {
//            fd.genererDonnees(listDonnees);
//        }
//        if (type == IDENT && !listDonnees.contains(racine)) {
//            listDonnees.add(racine);
//        }
//    }
//
//    public String genCode() {
//        if (isNull(this)) {
//            return "";
//        }
//        String resultat = "";
//        if (!isNull(this.fg)) {
//            resultat += this.fg.genCode();
//        }
//        if (!isNull(this.fd)) {
//            resultat += this.fd.genCode();
//        }
//
//        switch (this.type) {
//            case ENTIER:
//                resultat += genCodeEntier();
//                break;
//            case OPERATEUR:
//                resultat += genCodeOperateur();
//                break;
//            case LET:
//                resultat += genCodeLet();
//                break;
//            default:
//                throw new RuntimeException("Type de noeud inconnu: " + this.type);
//        }
//
//        return resultat;
//    }
//
//    private String genCodeEntier() {
//        String resultat = "\tpush eax\n";
//        resultat += "\tmov eax, " + this.racine + "\n";
//        return resultat;
//    }
//
//    private String genCodeOperateur() {
//        String resultat = "";
//        String op = this.racine;
//        resultat += "\tpop ebx\n";
//        if (op.equals("+")) {
//            resultat += "\tadd eax, ebx\n";
//        } else if (op.equals("-")) {
//            resultat += "\tsub ebx, eax\n";
//            resultat += "\tmov eax, ebx\n";
//        } else if (op.equals("*")) {
//            resultat += "\tmul eax, ebx\n";
//        } else if (op.equals("/")) {
//            resultat += "\tdiv ebx, eax\n";
//            resultat += "\tmov eax, ebx\n";
//        }
//
//        return resultat;
//    }
//
//    private String genCodeLet() {
//        String resultat = "";
//        resultat += "\tmov "+this.fg.racine+",eax \n";
//        resultat += "\tmov eax, "+this.fg.racine+" \n";
//        return resultat;
//    }
//
//    public String generation() {
//        String resultat = "";
//        resultat += generationData();
//        resultat += generationCode();
//        return resultat;
//    }
//
//    private String generationData() {
//        String resultat = "DATA SEGMENT\n";
//        ArrayList<String> listData = new ArrayList<>();
//        this.genererDonnees(listData);
//        for (int i = 0; i < listData.size(); i++) {
//            resultat += "\t " + listData.get(i) + " DD\n";
//        }
//        resultat += "DATA ENDS\n";
//        return resultat;
//    }
//
//    private String generationCode() {
//        String resultat = "CODE SEGMENT\n";
//        resultat += this.genCode();
//        resultat += "CODE ENDS";
//        return resultat;
//    }


    private TypeNode type;
    private String racine;
    private Arbre fg,fd;

    public Arbre(TypeNode type, String racine, Arbre fg, Arbre fd) {
        this.type = type;
        this.racine = racine;
        this.fg = fg;
        this.fd = fd;
    }

    public Arbre(TypeNode type, String racine) {
        this.type = type;
        this.racine = racine;
    }

    public Arbre() {}

    public String getRacine() {
        return this.racine;
    }

    public String toString() {
        String resultat = "";
        if ((this.type == OPERATEUR) || (this.type == LET)) {
            resultat += "(" + this.racine;
        } else {
            resultat += " " + this.racine;
        }
        if (!isNull(this.fg)) {
            resultat += this.fg.toString();
        }
        if (!isNull(this.fd)) {
            resultat += this.fd.toString();
        }
        if ((this.type == OPERATEUR) || (this.type == LET)) {
            resultat += ")";
        }
        return resultat;
    }

    public void genData(ArrayList<String> listData) {
        if (!isNull(this.fg)) {
            this.fg.genData(listData);
        }
        if (!isNull(this.fd)) {
            this.fd.genData(listData);
        }
        if ((this.type == IDENT) && (!listData.contains(this.racine))) {
            listData.add(this.racine);
        }
    }

    public String genCode() {
        String resultat = "";
        if (!isNull(this.fg)) {
            resultat += this.fg.genCode();
        }
        if (!isNull(this.fd)) {
            resultat += this.fd.genCode();
        }
        if (this.type == ENTIER) {
            resultat += "\tpush eax\n";
            resultat += "\tmov eax, " + this.racine + "\n";
        } else if (this.type == OPERATEUR) {
            if (this.racine.equals("+")) {
                resultat += "\tpop ebx\n";
                resultat += "\tadd eax, ebx\n";
            } else if (this.racine.equals("-")) {
                resultat += "\tpop ebx\n";
                resultat += "\tsub ebx, eax\n";
                resultat += "\tmov eax, ebx\n";
            } else if (this.racine.equals("*")) {
                resultat += "\tpop ebx\n";
                resultat += "\tmul eax, ebx\n";
            } else if (this.racine.equals("/")) {
                resultat += "\tpop ebx\n";
                resultat += "\tdiv ebx, eax\n";
                resultat += "\tmov eax, ebx\n";
            }
        } else if (this.type == LET) {
            resultat += "\tmov "+this.fg.racine+",eax \n";
            resultat += "\tmov eax, "+this.fg.racine+" \n";
        }
        return resultat;
    }

    public String generation() {
        String resultat = "DATA SEGMENT\n";
        ArrayList<String> listData = new ArrayList<>();
        this.genData(listData);
        for(int i = 0; i < listData.size(); i++){
            resultat += "\t " + listData.get(i) + " DD\n";
        }
        resultat += "DATA ENDS\n";
        resultat += "CODE SEGMENT\n";
        resultat += this.genCode();
        resultat += "CODE ENDS";
        return resultat;
    }


}
