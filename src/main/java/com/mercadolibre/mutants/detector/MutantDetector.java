package com.mercadolibre.mutants.detector;

import com.mercadolibre.mutants.exceptions.DNAException;

import java.util.List;

//A esta clase se le asigno la responsabilidad de analizar si el sujeto es mutante.
public class MutantDetector {

    public boolean mutantDetector(List<String> ADN) throws DNAException {

        boolean result;
        /*
        Llamo a validatorADN para verificar que la matriz sea cuadrada y que no presente caracteres invalidos
        En caso de que no cumpla con esas condiciones lanza una excepcion
         */
        validatorADN(ADN);

        //Contador utilizado para la cantidad de secuencias encontradas
        int contador = 0;

        /*
        Empezamos a recorrer la matriz
        Cada metodo a excepcion del primero contiene un "if" que evalua si el
        contador ya es igual a 2 para que el algoritmo no recorra la matriz en vano
        */

        contador = RecorrerDiagonal(ADN, contador);
        contador = RecorrerVertical(ADN, contador);
        contador = RecorrerHorizontal(ADN, contador);
        contador = RecorrerContraDiagonal(ADN, contador);

        if (contador >= 2) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private void validatorADN(List<String> adn) throws DNAException {
        for (int i = 0; i < adn.size(); i++) {
            if (adn.get(i).length() != adn.size()) {
                throw new DNAException("El arreglo no es cuadrado");
            }
            for (int j = 0; j < adn.get(0).length(); j++) {
                if (!(adn.get(i).charAt(j) == 'A' || adn.get(i).charAt(j) == 'G' || adn.get(i).charAt(j) == 'C' || adn.get(i).charAt(j) == 'T')) {
                    throw new DNAException("El arreglo no presenta caracteres validos");
                }
            }
        }
    }

    private boolean CompararLetras(char A, char B, char C, char D) {
        return A == B && A == C && A == D;
    }

    public int RecorrerDiagonal(List<String> adn, int contador) {
        // recorrer diagonales
        for (int i = 0; i < adn.size() - 3; i++) {
            buscar2:
            for (int j = 0; j < adn.get(i).length() - 3; j++) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j + 1), adn.get(i + 2).charAt(j + 2)
                        , adn.get(i + 3).charAt(j + 3))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    return contador;
                }
            }
        }
        return contador;
    }

    public int RecorrerVertical(List<String> adn, int contador) {
        if (contador >= 2) {
            return contador;
        }
        for (int i = 0; i < adn.size() - 3; i++) {
            buscar2:
            for (int j = 0; j < adn.get(i).length(); j++) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j), adn.get(i + 2).charAt(j)
                        , adn.get(i + 3).charAt(j))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    return contador;
                }
            }
        }
        return contador;
    }

    public int RecorrerHorizontal(List<String> adn, int contador) {
        if (contador >= 2) {
            return contador;
        }
        for (int i = 0; i < adn.size(); i++) {
            buscar2:
            for (int j = 0; j < adn.get(i).length() - 3; j++) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i).charAt(j + 1), adn.get(i).charAt(j + 2)
                        , adn.get(i).charAt(j + 3))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    return contador;
                }
            }
        }
        return contador;
    }

    public int RecorrerContraDiagonal(List<String> adn, int contador) {
        if (contador >= 2) {
            return contador;
        }
        for (int i = 0; i < adn.size() - 3; i++) {
            buscar2:
            for (int j = adn.get(i).length() - 1; j > adn.get(i).length() - 2; j--) {
                if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j - 1), adn.get(i + 2).charAt(j - 2),
                        adn.get(i + 3).charAt(j - 3))) {
                    contador++;
                    break buscar2;
                }
                if (contador == 2) {
                    return contador;
                }
            }
        }
        return contador;
    }
}

