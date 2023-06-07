package main.java;

import test.java.ErrorsTasksTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ErrorsTasks {
    public static void main(String[] args) {
        ErrorsTasks errorsTasks=new ErrorsTasks();
        errorsTasks.one();
        errorsTasks.two();
        errorsTasks.three();
    }

    /**
     * Реализуйте метод, перемножения целочисленных массивов
     * с извещением пользователя об ошибках размерности
     * с поможью исключений
     */

    public void one(){
        multArrs(new int[]{1,2,3}, new int[]{1,5,3},new int[]{1,2});
    }
    private void multArrs(int[] ...array){
        Map<int[],List<int[]>>chainMultiply=  Arrays.stream(array).collect(
                Collectors.toMap(x->x,y->new ArrayList<>()));
        chainMultiply.keySet().forEach(x->{
            List<int[]> otherArs= chainMultiply.keySet().stream()
                    .filter(el->!x.equals(el)).collect(Collectors.toList());
            chainMultiply.put(x,otherArs);
        });


        List<Map.Entry<int[], Object[]>> results=chainMultiply.entrySet().stream().map(oneVal-> Map.entry(oneVal.getKey(),
                oneVal.getValue().stream().map(anotherArray->{
                    int[]keyArray=oneVal.getKey();

                    if (anotherArray.length!=keyArray.length){
                        String caus="Невозможно перемножить массив "+Arrays.toString(keyArray)+" с массивом "+Arrays.toString(anotherArray);
                        new UnsupportedOperationException(caus).printStackTrace();
                        return caus;}
                    return "уможим на "+Arrays.toString(anotherArray)+" руззельтат - "
                            + IntStream.range(0, anotherArray.length)
                            .mapToObj(i -> String.valueOf( anotherArray[i]* keyArray[i]))
                            .collect(Collectors.joining(" "));
                }).toArray()
        )).collect(Collectors.toList());

        results.forEach(x->{
            System.out.println("Массив "+Arrays.toString(x.getKey())+" для перемножения");
            System.out.println("_____Результаты________");
            Arrays.stream(x.getValue()).forEach(arrsAnoter->
                    System.out.println(arrsAnoter));
            System.out.println("");
        });
    }

    /**
     * Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
     */
    public void two(){
        try {   ((Supplier) () -> 1/0).get(); }catch (ArithmeticException e){e.printStackTrace();}
        try {   ((Supplier) () ->  new ArrayList<>().get(2)).get(); }catch (IndexOutOfBoundsException e){e.printStackTrace();}
        try {   ((Supplier) () -> Integer.parseInt(null)).get(); }catch (NumberFormatException e){e.printStackTrace();}
    }

    /**
     * Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?
     * public static int sum2d(String[][] arr){
     * int sum = 0;
     * for (int i = 0; i < arr.length; i++) {
     *      for (int j = 0; j < 5; j++) {
     *      int val = Integer.parseInt(arr[i][j]); sum += val; }
     * }
     *                          return sum; }
     *
     */

    private void three() {
        new ErrorsTasksTest(); // src/test/java
    }
    public int sum2d(String[][] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(arr[i][j]); sum += val; }
        }
        return sum;
    }

}