import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListRefactTest extends MyLinkedList {

    @Test
    public void sizeTest(){
        MyLinkedListRefact list=new MyLinkedListRefact();

        for (int i = 10; i > 0; i--) {
            list.add(i);
        }

        int size= list.getSize();
        Assertions.assertEquals(size,10);

        for (int i = 10; i > 0; i--) {
            list.add(i);
        }

        int size2= list.getSize();
        Assertions.assertEquals(size2,20);
    }

    @Test
    void containsTest() {
        MyLinkedListRefact list=new MyLinkedListRefact();
        for (int i = 10; i > 0; i--) {
            list.add(i);
        }

        Assertions.assertFalse(list.contains(100));
        Assertions.assertTrue(list.contains(3));
    }

    @Test
    void popLastTest(){
        MyLinkedListRefact list=new MyLinkedListRefact();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 10-1; i >= 0; i--) {
            int val= list.popLast();
            Assertions.assertEquals(i,val);
        }
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            list.popLast();
        });

    }

    @Test
    void reversedTest(){
        MyLinkedListRefact list=new MyLinkedListRefact();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        MyLinkedListRefact reversed= list.reversed();

        for (int i = 0; i < 10; i++) {
            int val = reversed.popLast();
            Assertions.assertEquals(i, val);
        }

    }
}