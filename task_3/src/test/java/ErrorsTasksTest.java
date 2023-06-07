package test.java;

import main.java.ErrorsTasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorsTasksTest {

    @Test
    public void sum2d_NumberFormatException() {
        ErrorsTasks errorsTasks=new ErrorsTasks();

        Assertions.assertThrows(NumberFormatException.class,() ->
                errorsTasks.sum2d(new String[][]{{"asd"},{"ds"}})   );
    }

    @Test
    public void sum2d_NullPointerException() {
        ErrorsTasks errorsTasks=new ErrorsTasks();

        Assertions.assertThrows(NullPointerException.class,() ->
                errorsTasks.sum2d(null)   );
    }
    @Test
    public void sum2d_ClassCastException() {
        ErrorsTasks errorsTasks=new ErrorsTasks();

        Assertions.assertThrows(ClassCastException.class,() ->
                errorsTasks.sum2d((String[][]) new Object())   );
    }

    @Test
    public void sum2d_ArrayIndexOutOfBoundsException() {
        ErrorsTasks errorsTasks=new ErrorsTasks();

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() ->
                errorsTasks.sum2d(new String[][]{{"2147483647"},{"2"}})   );
    }
}