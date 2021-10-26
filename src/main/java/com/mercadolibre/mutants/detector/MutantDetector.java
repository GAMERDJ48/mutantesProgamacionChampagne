package com.mercadolibre.mutants.detector;

import com.mercadolibre.mutants.entities.Mutant;

import java.util.ArrayList;
import java.util.List;

//A esta clase se le asigno la responsabilidad de analizar si el sujeto es mutante.
public class MutantDetector {

    private boolean CompararLetras(char A, char B, char C, char D) {
        return A == B && A == C && A == D;
    }

    public int RecorrerDiagonal(List<String> adn, int contador) {
        // recorrer diagonales
        buscar:
        for (int i = 0; i < adn.size() - 3; i++) {
            buscar2:
            for (int j = 0; j < adn.get(i).length() - 3; j++) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j + 1), adn.get(i + 2).charAt(j + 2)
                        , adn.get(i + 3).charAt(j + 3))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    break buscar;
                }
            }
        }
        return contador;
    }

    public int RecorrerVertical(List<String> adn, int contador) {
        if (contador >= 2) {
            return contador;
        }
        buscar:
        for (int i = 0; i < adn.size() - 3; i++) {
            buscar2:
            for (int j = 0; j < adn.get(i).length(); j++) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j), adn.get(i + 2).charAt(j)
                        , adn.get(i + 3).charAt(j))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    break buscar;
                }
            }
        }
        return contador;
    }

    public int RecorrerHorizontal(List<String> adn, int contador) {
        if (contador >= 2) {
            return contador;
        }
        buscar:
        for (int i = 0; i < adn.size(); i++) {
            buscar2:
            for (int j = 0; j < adn.get(i).length() - 3; j++) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i).charAt(j + 1), adn.get(i).charAt(j + 2)
                        , adn.get(i).charAt(j + 3))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    break buscar;
                }
            }
        }
        return contador;
    }

    public int RecorrerContraDiagonal(List<String> adn, int contador) {
        if (contador >= 2) {
            return contador;
        }
        buscar:
        for (int i = 0; i < adn.size() - 3; i++) {
            buscar2:
            for (int j = adn.get(i).length() - 1; j > adn.get(i).length() - 2; j--) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j - 1), adn.get(i + 2).charAt(j - 2),
                        adn.get(i + 3).charAt(j - 3))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    break buscar;
                }
            }
        }
        return contador;
    }
}

