package com.MS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Day2 {
    private List<Integer> example1 = Arrays.asList(1,0,0,0,99);
    private List<Integer> example2 = Arrays.asList(2,3,0,3,99);
    private List<Integer> example3 = Arrays.asList(2,4,4,5,99,0);
    private List<Integer> example4 = Arrays.asList(1,1,1,4,99,5,6,0,99);

    private List<Integer> example1After = Arrays.asList(2,0,0,0,99);
    private List<Integer> example2After = Arrays.asList(2,3,0,6,99);
    private List<Integer> example3After = Arrays.asList(2,4,4,5,99,9801);
    private List<Integer> example4After = Arrays.asList(30,1,1,4,2,5,6,0,99);

    private List<Integer> gravityAssistProgram = Arrays.asList(1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,9,19,23,2,23,13,27,1,27,9,31,2,31,6,35,1,5,35,39,1,10,39,43,2,43,6,47,1,10,47,51,2,6,51,55,1,5,55,59,1,59,9,63,1,13,63,67,2,6,67,71,1,5,71,75,2,6,75,79,2,79,6,83,1,13,83,87,1,9,87,91,1,9,91,95,1,5,95,99,1,5,99,103,2,13,103,107,1,6,107,111,1,9,111,115,2,6,115,119,1,13,119,123,1,123,6,127,1,127,5,131,2,10,131,135,2,135,10,139,1,13,139,143,1,10,143,147,1,2,147,151,1,6,151,0,99,2,14,0,0);

    @Test
    public void Day2Part1() throws Exception {

        List<Integer> intcode = gravityAssistProgram;
        List<Integer> shifted1201Intcode = new ArrayList<>(gravityAssistProgram);

        ComputeAndStoreIntcode(intcode);
        int resultWithoutShift = getValueAtZeroIndex(intcode);
        System.out.println("Result without 1202 shift: " + resultWithoutShift);
        Assert.assertEquals(234699, resultWithoutShift, 0);

        shifted1201Intcode.set(1, 12);
        shifted1201Intcode.set(2, 2);

        ComputeAndStoreIntcode(shifted1201Intcode);
        int resultAfter1202shift = getValueAtZeroIndex(shifted1201Intcode);

        System.out.println("Result with 1202 shift: " + resultAfter1202shift);

        Assert.assertEquals(3306701, resultAfter1202shift, 0);
    }

    @Test
    public void Day2Part2() throws Exception {

        int noun = 0;
        int verb = 0;

        int wantedResult = 19690720;
        int resultAfter1202shift = 0;

        while(wantedResult != resultAfter1202shift) {
            List<Integer> shifted1201Intcode = new ArrayList<>(gravityAssistProgram);

            noun = (int) (Math.random() * (100));
            verb = (int) (Math.random() * (100));


            System.out.println("Shuffled Noun: " + noun);
            System.out.println("Shuffled Verb: " + verb);

            shifted1201Intcode.set(1, noun);
            shifted1201Intcode.set(2, verb);

            ComputeAndStoreIntcode(shifted1201Intcode);
            resultAfter1202shift = getValueAtZeroIndex(shifted1201Intcode);

            System.out.println("Result: " + resultAfter1202shift);
        }

        System.out.println("Final Noun: " + noun);
        System.out.println("Final Verb: " + verb);

        //Result: 19690720
        //Final Noun: 76
        //Final Verb: 21

        System.out.println("100 * Noun + Verb: " + 100*noun+verb);
        //7621

    }

    @Test
    public void Day2Part2Verification() throws Exception {
        List<Integer> shifted1201Intcode = new ArrayList<>(gravityAssistProgram);

        int noun = 76;
        int verb = 21;

        shifted1201Intcode.set(1, noun);
        shifted1201Intcode.set(2, verb);

        ComputeAndStoreIntcode(shifted1201Intcode);
        int resultAfter1202shift = getValueAtZeroIndex(shifted1201Intcode);

        System.out.println("Result with 1202 shift: " + resultAfter1202shift);

        Assert.assertEquals(19690720, resultAfter1202shift, 0);

        System.out.println("100 * Noun + Verb: " + (100*noun+verb));

        Assert.assertEquals(7621, (100*noun+verb), 0);
    }

    @Test
    public void Day2Part1Example1() throws Exception {

        List<Integer> intcode = example1;

        int resultOfLastOperation = ComputeAndStoreIntcode(intcode);
        int result = getValueAtZeroIndex(intcode);

        Assert.assertEquals(2, resultOfLastOperation, 0);
        Assert.assertEquals(2, result, 0);
        Assert.assertEquals(example1After, intcode);
    }

    @Test
    public void Day2Part1Example2() throws Exception {

        List<Integer> intcode = example2;

        int resultOfLastOperation = ComputeAndStoreIntcode(intcode);

        int result = getValueAtZeroIndex(intcode);

        Assert.assertEquals(example2After, example2);
        Assert.assertEquals(6, resultOfLastOperation, 0);
        Assert.assertEquals(2, result, 0);
    }

    @Test
    public void Day2Part1Example3() throws Exception {

        List<Integer> intcode = example3;

        int resultOfLastOperation = ComputeAndStoreIntcode(intcode);

        int result = getValueAtZeroIndex(intcode);

        Assert.assertEquals(example3After, example3);
        Assert.assertEquals(9801, resultOfLastOperation, 0);
        Assert.assertEquals(2, result, 0);
    }

    @Test
    public void Day2Part1Example4() throws Exception {

        List<Integer> intcode = example4;

        int resultOfLastOperation = ComputeAndStoreIntcode(intcode);

        int result = getValueAtZeroIndex(intcode);

        Assert.assertEquals(example4After, example4);
        Assert.assertEquals(30, resultOfLastOperation, 0);
        Assert.assertEquals(30, result, 0);
    }


    private int ComputeAndStoreIntcode(List<Integer> intcode) throws Exception {
        int index = 0;
        int opcode = intcode.get(0);
        int resultOfLastOperation = 0;

        while (opcode != 99){
            switch (opcode){
                case 1:
                    resultOfLastOperation = calculateAndStoreSum(index, intcode);
                    index += 4;
                    opcode = intcode.get(index);
                    break;
                case 2:
                    resultOfLastOperation = calculateAndStoreMultiplication(index, intcode);
                    index += 4;
                    opcode = intcode.get(index);
                    break;
                default:
                    throw new Exception("Incorrect opcode: " + opcode);
            }
        }
        return resultOfLastOperation;
    }

    private int getValueAtZeroIndex(List<Integer> intcode) {
        return intcode.get(0);
    }

    private int calculateAndStoreMultiplication(int index, List<Integer> intcode) {
        int resultM = intcode.get(intcode.get(index+1)) * intcode.get(intcode.get(index+2));
        storeResultOfTheOperation(index, resultM, intcode);

//        System.out.println("Result of Multiplication: " + resultM);
        return resultM;
    }

    private int calculateAndStoreSum(int index, List<Integer> intcode) {
        int resultS = intcode.get(intcode.get(index + 1)) + intcode.get(intcode.get(index + 2));
        storeResultOfTheOperation(index, resultS, intcode);

//        System.out.println("Result of a sum: " + resultS);
        return resultS;
    }

    private void storeResultOfTheOperation(int index, int result, List<Integer> intcode) {
        intcode.set(getIndexOfWhereResultShouldBeStored(index, intcode), result);
    }

    private Integer getIndexOfWhereResultShouldBeStored(int index, List<Integer> intcode) {
        return intcode.get(index + 3);
    }
}