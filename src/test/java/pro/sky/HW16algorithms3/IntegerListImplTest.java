package pro.sky.HW16algorithms3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private final IntegerListImpl integerUnderTest = new IntegerListImpl();

    @Test
    void grow () {
         integerUnderTest.add(0,1);
        integerUnderTest.add(1,2);
        integerUnderTest.add(2,3);
        integerUnderTest.add(3,4);
        integerUnderTest.add(4,5);
        integerUnderTest.add(5,6);
        integerUnderTest.add(6,7);
        integerUnderTest.add(7,8);
        integerUnderTest.add(8,9);
        integerUnderTest.add(9,10);
        integerUnderTest.add(10,11);
        integerUnderTest.add(11,12);
        integerUnderTest.add(12,13);

       System.out.println(integerUnderTest.size());

    }
}