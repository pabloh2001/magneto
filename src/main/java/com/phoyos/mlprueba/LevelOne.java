package com.phoyos.mlprueba;

import java.util.ArrayList;
import java.util.List;

public class LevelOne {
    public static void main(String[] args) {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        System.out.println(isMutant(dna));
    }

    public static boolean isMutant(String[] dna){
       return (horizontal(dna) || vertical(dna)) && (oblique(dna) || vertical(dna));
    }
    public static boolean horizontal(String[] dna){
        for (String value : dna){
            if (doMatchHorizontal(value)) return true;
        }
        return false;
    }
    public static boolean oblique(String[] dna){
        int index;
        String wordAux = "";

        for (String value : dna){
            wordAux = value;
            char[] c = wordAux.toCharArray();

            for(char x : c) {
                int i = 0, count = 1, next = 1;

                for (String text : dna){
                    i++;
                    if (i == 1) continue;

                    index = text.indexOf(x);
                    while (index != -1){ //"ATGCGA","CAGTGC"
                        if (index == next) {
                            count++;
                            next++;
                            break;
                        }
                        index = text.indexOf(x, index+1);
                    }
                    if (index == -1 && count > 0) {
                        count = 0;
                        next = 1;
                    }
                    if (count >= 4) return true;
                }
            }
        }
        return false;
    }
    public static boolean vertical(String[] dna){
        int index, currentIndex;
        String wordAux = "";

        for (String value : dna){
            List<Integer> indices = new ArrayList<>();
            wordAux = value;
            char[] c = wordAux.toCharArray();

            for(char x : c) {
                int i = 0, count = 1;

                currentIndex = wordAux.indexOf(x);
                while (currentIndex != -1){
                    if (!indices.contains(currentIndex)){ indices.add(currentIndex); break;}
                    currentIndex = wordAux.indexOf(x, currentIndex+1);
                }

                for (String text : dna){
                    List<Integer> list = new ArrayList<>();
                    i++;
                    if (i == 1) continue;

                    index = text.indexOf(x);
                    while (index != -1){ //"ATGCGA","CAGTGC"
                        if (index == currentIndex){
                            count++;
                        }
                        list.add(index);
                        index = text.indexOf(x, index+1);
                    }
                    if (!list.contains(currentIndex) && count > 0) count = 0;
                    if (count >= 4) return true;
                }
            }
        }
        return false;
    }
    public static boolean doMatchHorizontal(String v){
        String pattern = "^(A|C|G|T)+\\1{3,}(A|C|G|T)*$";
        return v.matches(pattern);
    }
}
