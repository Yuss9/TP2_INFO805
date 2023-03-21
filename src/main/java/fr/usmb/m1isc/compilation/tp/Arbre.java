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

    /*
    exemple de fichier source de l'analyseur
    12 + 5;             *//* ceci est un commentaire *//*
    10 / 2 - 3;  99;    *//* le point-virgule sépare les expressions à évaluer *//*
     *//*
     l'évaluation donne toujours un nombre entier *//*
    ((30 * 1) + 4) mod 2; *//* opérateurs binaires *//*
     3 * -4;
    *//* attention à l'opérateur unaire *//*

    let prixHt = 200;   *//* une variable prend valeur lors de sa déclaration *//*
    let prixTtc =  prixHt * 119 / 100;
    prixTtc + 100.
    */
}