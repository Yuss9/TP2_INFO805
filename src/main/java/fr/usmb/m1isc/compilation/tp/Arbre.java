package fr.usmb.m1isc.compilation.tp;
import java.util.ArrayList;

import static fr.usmb.m1isc.compilation.tp.TypeNode.*;

import static java.util.Objects.isNull;

public class Arbre {
    private TypeNode type;
    private String racine;
    private Arbre fg, fd;

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

    public Arbre() {
    }

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
        if (this.fg != null) {
            this.fg.genData(listData);
        }
        if (this.fd != null) {
            this.fd.genData(listData);
        }
        if (this.type == IDENT && !listData.contains(this.racine)) {
            listData.add(this.racine);
        }
    }

    public String genCode() {
        StringBuilder resultat = new StringBuilder();

        if (this.type == SEMI) {
            return this.fg.genCode() + this.fd.genCode();
        }

        if (this.type == ENTIER || this.type == IDENT) {
            resultat.append("\tmov eax, ").append(this.racine).append("\n");
        } else if (this.type == OPERATEUR) {
            resultat.append(this.fg.genCode());
            resultat.append("\tpush eax\n");
            resultat.append(this.fd.genCode());
            resultat.append("\tpop ebx\n");
            switch (this.racine) {
                case "+":
                    resultat.append("\tadd eax, ebx\n");
                    break;
                case "-":
                    resultat.append("\tsub ebx, eax\n");
                    resultat.append("\tmov eax, ebx\n");
                    break;
                case "*":
                    resultat.append("\tmul eax, ebx\n");
                    break;
                case "/":
                    resultat.append("\tdiv ebx, eax\n");
                    resultat.append("\tmov eax, ebx\n");
                    break;
                default:
                    break;
            }
        } else if (this.type == LET) {
            resultat.append(this.fd.genCode());
            resultat.append("\tmov ").append(this.fg.racine).append(", eax\n");
        } else if (this.type == INPUT) {
            resultat.append("\tin eax\n");
        } else if (this.type == OUTPUT) {
            resultat.append("\tmov eax, ").append(this.racine).append("\n");
            resultat.append("\tout eax\n");
        }
        return resultat.toString();
    }

    public String generation() {
        StringBuilder resultat = new StringBuilder();
        resultat.append("DATA SEGMENT\n");
        ArrayList<String> listData = new ArrayList<>();
        this.genData(listData);
        for(String data : listData){
            resultat.append("\t ").append(data).append(" DD\n");
        }
        resultat.append("DATA ENDS\n");
        resultat.append("CODE SEGMENT\n");
        resultat.append(this.genCode());
        resultat.append("CODE ENDS");
        return resultat.toString();
    }
}