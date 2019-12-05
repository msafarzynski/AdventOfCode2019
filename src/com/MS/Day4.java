package com.MS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class Day4 {

    @Test
    public void Day4Part1() {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 108457; i <= 562041; i++) {

            String iS = String.valueOf(i);
            int i0 = Integer.parseInt(iS.substring(0, 1));
            int i1 = Integer.parseInt(iS.substring(1, 2));
            int i2 = Integer.parseInt(iS.substring(2, 3));
            int i3 = Integer.parseInt(iS.substring(3, 4));
            int i4 = Integer.parseInt(iS.substring(4, 5));
            int i5 = Integer.parseInt(iS.substring(5, 6));

            if (i0 <= i1 && i1 <= i2 && i2 <= i3 && i3 <= i4 && i4 <= i5) {
                if (isThereADouble(new Integer[] { i0, i1, i2, i3, i4, i5 })) {

                    resultList.add(i);
                }
            }
        }

        System.out.println(resultList.size());
        System.out.println(resultList);
    }

    @Test
    public void Day4Part2() {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 108457; i <= 562041; i++) {

            String iS = String.valueOf(i);
            Integer i0 = Integer.parseInt(iS.substring(0, 1));
            Integer i1 = Integer.parseInt(iS.substring(1, 2));
            Integer i2 = Integer.parseInt(iS.substring(2, 3));
            Integer i3 = Integer.parseInt(iS.substring(3, 4));
            Integer i4 = Integer.parseInt(iS.substring(4, 5));
            Integer i5 = Integer.parseInt(iS.substring(5, 6));

            Integer[] passwordArray = { i0, i1, i2, i3, i4, i5 };

            if (i0 <= i1 && i1 <= i2 && i2 <= i3 && i3 <= i4 && i4 <= i5) {
                if (!isThereA5InARow(passwordArray)) {

                    if (isThereAQuadruple(passwordArray) && isOnly2DifferentDigit(passwordArray)) {
                        resultList.add(i);
                    } else if (!isThereAQuadruple(passwordArray)) {
                        if (isThereATriple(passwordArray) && isOnly3DifferentDigit(passwordArray)) {
                            resultList.add(i);
                        } else if (!isThereATriple(passwordArray)) {
                            if(isThereADouble(passwordArray)){
                                resultList.add(i);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(resultList.size());
        System.out.println(resultList);
        //1972
    }

    private boolean isThereADouble(Integer[] intAr) {

        if (intAr[0] == intAr[1]) {
            return true;
        }
        if (intAr[1] == intAr[2]) {
            return true;
        }
        if (intAr[2] == intAr[3]) {
            return true;
        }
        if (intAr[3] == intAr[4]) {
            return true;
        }
        if (intAr[4] == intAr[5]) {
            return true;
        }

        return false;
    }

    private boolean isThereATriple(Integer[] intAr) {
        if (intAr[0] == intAr[1] && intAr[1] == intAr[2]) {
            return true;
        }
        if (intAr[1] == intAr[2] && intAr[2] == intAr[3]) {
            return true;
        }
        if (intAr[2] == intAr[3] && intAr[3] == intAr[4]) {
            return true;
        }
        if (intAr[3] == intAr[4] && intAr[4] == intAr[5]) {
            return true;
        }

        return false;
    }

    private boolean isThereAQuadruple(Integer[] intAr) {
        if (intAr[0] == intAr[1] && intAr[1] == intAr[2] && intAr[2] == intAr[3]) {
            return true;
        }
        if (intAr[1] == intAr[2] && intAr[2] == intAr[3] && intAr[3] == intAr[4]) {
            return true;
        }
        if (intAr[2] == intAr[3] && intAr[3] == intAr[4] && intAr[4] == intAr[5]) {
            return true;
        }

        return false;
    }

    private boolean isThereA5InARow(Integer[] intAr) {
        if (intAr[0] == intAr[1] && intAr[1] == intAr[2] && intAr[2] == intAr[3] && intAr[3] == intAr[4]) {
            return true;
        }
        if (intAr[1] == intAr[2] && intAr[2] == intAr[3] && intAr[3] == intAr[4] && intAr[4] == intAr[5]) {
            return true;
        }

        return false;
    }

    private boolean isOnly2DifferentDigit(Integer[] intAr) {
        List<Integer> intL = Arrays.asList(intAr);

        if (intL.stream().distinct().collect(Collectors.toList()).size() == 2) {
            return true;
        }

        return false;
    }

    private boolean isOnly3DifferentDigit(Integer[] intAr) {
        List<Integer> intL = Arrays.asList(intAr);

        if (intL.stream().distinct().collect(Collectors.toList()).size() == 3) {
            return true;
        }

        return false;
    }
}