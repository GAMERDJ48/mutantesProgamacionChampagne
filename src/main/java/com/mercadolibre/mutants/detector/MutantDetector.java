package com.mercadolibre.mutants.detector;

import com.mercadolibre.mutants.exceptions.DNAException;

import java.util.List;

//A esta clase se le asigno la responsabilidad de analizar si el sujeto es mutante.
public class MutantDetector {

    //Metodo encargado de validar si el ADN es cuadrado y si presenta caracteres validos
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

    //Se encarga de comparar las letras para detectar si hay coincidencias de 4 letras iguales en la matriz
    private boolean CompararLetras(char A, char B, char C, char D) {
        return A == B && A == C && A == D;
    }

    public boolean mutantDetector(List<String> adn) throws DNAException{
        //Indican hasta donde se recorre por fila (finI) o columna(finJ)
        int finI, finJ=0;
        /*
        Indica el metodo que se esta usando:
        RecorrerDiagonal --> 0
        RecorrerVertical --> 1
        RecorrerHorizontal --> 2
        RecorrerContraDiagonal --> 3
        */
        int metodo=0;
        //Contabiliza la cantidad de coincidencias
        int contador = 0;
        //Verifica que la lista sea cuadrada y contenga caracteres validos
        validatorADN(adn);
        do{
            //Switch utilizado para inicializar las variables que indican hasta donde recorrer
            switch (metodo){
                case 0:
                    finI = adn.size()-3;
                    finJ = finI;
                    break;
                case 1:
                    finJ=adn.size();
                    finI = finJ-3;
                    break;
                case 2:
                    finI=adn.size();
                    finJ = finI-3;
                    break;
                default:
                    finI = adn.size()-3;
            }
            for (int i = 0; i < finI; i++) {
                if(metodo==0 || metodo==1 || metodo==2){
                    //Etiqueta utilizada para que "i" no aumente en el for
                    buscar2:
                    for (int j = 0; j < finJ; j++) {
                        boolean result = false;
                        if(metodo==0) result = RecorrerDiagonal(adn, i, j);
                        if(metodo==1) result = RecorrerVertical(adn, i, j);
                        if(metodo==2) result = RecorrerHorizontal(adn, i, j);
                        //Si los algun metodo devuelve true suma un uno en el contador
                        if(result){
                            contador++;
                            if (contador == 2) {
                                return true;
                            }
                            break buscar2;
                        }
                    }
                }else{
                    //Etiqueta utilizada para que "i" no aumente en el for
                    buscar2:
                    for (int j = adn.get(i).length() - 1; j > adn.get(i).length() - 2; j--) {
                        if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j - 1), adn.get(i + 2).charAt(j - 2),
                                adn.get(i + 3).charAt(j - 3))) {
                            contador++;
                            if (contador == 2) {
                                return true;
                            }
                            break buscar2;
                        }
                    }
                }
            }
            metodo++;
        }while(metodo<4);

        return false;
    }

    public boolean RecorrerDiagonal(List<String> adn, int i, int j) {
        boolean resultado = (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j + 1), adn.get(i + 2).charAt(j + 2)
                , adn.get(i + 3).charAt(j + 3))) ?  true :  false;
        return resultado;
    }

    public boolean RecorrerVertical(List<String> adn, int i, int j) {
        if (CompararLetras(adn.get(i).charAt(j), adn.get(i + 1).charAt(j), adn.get(i + 2).charAt(j)
                , adn.get(i + 3).charAt(j))) {
            return true;
        }
        return false;
    }

    public boolean RecorrerHorizontal(List<String> adn, int i, int j) {
        if (CompararLetras(adn.get(i).charAt(j), adn.get(i).charAt(j + 1), adn.get(i).charAt(j + 2)
                , adn.get(i).charAt(j + 3))) {
            return true;
        }
        return false;
    }

}

