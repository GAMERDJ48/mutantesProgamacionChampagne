package com.mercadolibre.mutants.detector;

import com.mercadolibre.mutants.exceptions.DNAException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//A esta clase se le asigno la responsabilidad de analizar si el sujeto es mutante.
public class MutantDetector {

    //Contador usado para llevar la cuenta de cuantas coincidencias de mutante hay, si es 2 o más, es mutante
    public int contador;

    //Metodo encargado de validar si el ADN es cuadrado y si presenta caracteres validos
    private void validatorADN(List<String> adn) throws DNAException {
        for (int i = 0; i < adn.size(); i++) {
            if (adn.get(i).length() != adn.size()) {
                throw new DNAException("El arreglo no es cuadrado");
            }
            for (int j = 0; j < adn.get(i).length(); j++) {
                char c = adn.get(i).charAt(j);
                if (!(c == 'A' || c == 'G' || c == 'C' || c == 'T')) {
                    throw new DNAException("El arreglo no presenta caracteres validos");
                }
            }
        }
    }

    //Metodo que recibe el adn y se encarga de llamar al validador de ADN
    //y tambien se encarga de llamar al detector de mutantes
    //para comprobar si hay mutantes en las horizontales, llamamos a TestHorizontal
    //para comprobar si hay mutantes en las verticales, llamamos al metodo getColumns
    //y luego llamamos a testHorizontal con el resultado de getColumns
    //despues llamamos a getDiagonals y luego llamamos a testHorizontal con el resultado
    //de getDiagonals como argumento, por ultimo, para comprobar las diagonales
    //principales, llamamos a mirror con el adn, luego llamamos a getDiagonals pasandole
    //el resultado de mirror y ahí luego llamamos a testHorizontal.
    //Cabe resaltar que por el uso de cortocircuito, con el primer testHorizontal que devuelva true
    //el metodo devuelve true. Si ninguno es verdadero, el metodo deveulve faltse
    public boolean mutantDetector(List<String> dna) throws DNAException {
        validatorADN(dna);
        this.contador = 0;
        if (testHorizontal(dna)
                || testHorizontal(getColumns(dna))
                || testHorizontal(getDiagonals(dna))
                || testHorizontal(getDiagonals(mirror(dna)))) {
            return true;
        }
        return false;
    }

    //Este metodo lo que hace es recibir una matriz, y comprobar
    //que cada fila tenga contenida cualquiera de las cuatro secuencias
    //que se especifican en el array condicion, por cada fila que
    //contenga esa secuencia, incrementamos la variable contador en 1
    //Si contador fuera mayor o igual a 2, devolvemos true
    public boolean testHorizontal(List<String> dna) {
        String[] condicion = {"AAAA", "TTTT", "CCCC", "GGGG"};
        for (String fila : dna) {
            if (contador >= 2) {
                return true;
            }
            for (String c : condicion) {
                if (fila.contains(c)) {
                    contador++;
                    break;
                }
            }
        }
        return false;
    }

    //Este metodo obtiene cada columna de la "matriz", la convierte a String
    //y crea una nueva matriz donde cada "columna" de la matriz original
    //es una fila de esta nueva matriz
    public List<String> getColumns(List<String> dna) {
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < dna.size(); i++) {
            int finalI = i;
            List<Character> buff = dna.stream().map(row -> row.charAt(finalI)).collect(Collectors.toList());
            String column = buff.toString()
                    .substring(1, 3 * buff.size() - 1)
                    .replaceAll(", ", "");
            columns.add(column);
        }
        return columns;
    }

    //Este metodo obtiene cada diagonal inversa del adn, la convierte
    //en string y luego agrega cada diagonal como fila de una nueva
    //matriz que creamos y devolvemos.
    public List<String> getDiagonals(List<String> dna) {
        List<String> diagonals = new ArrayList<>();
        String s;
        for (int k = 0; k < dna.size() * 2; k++) {
            s = "";
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < dna.size() && j < dna.size()) {
                    s += dna.get(i).charAt(j);
                }
            }
            diagonals.add(s);
        }
        return diagonals.stream().filter(row -> row.length() >= 4).collect(Collectors.toList());
    }

    //Este metodo hace una reflexion del adn respecto al eje vertical
    //por ej: la primera columna pasa a ser la ultima, y la última será la primera
    public List<String> mirror(List<String> dna) {
        List<String> mirrored = new ArrayList<>();
        String mirrorString;
        for (String s : dna) {
            mirrorString = "";
            for (int j = s.length() - 1; j >= 0; j--) {
                mirrorString += s.charAt(j);
            }
            mirrored.add(mirrorString);
        }
        return mirrored;
    }

}

