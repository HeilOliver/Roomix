package at.fhv.roomix.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.common
 * MapperTest
 * 09/05/2018 Oliver
 * <p>
 * Enter Description here
 */
class MapperTest {

    private class A {
        String a;
    }

    private class B {
        Integer a;
    }

    @Test
    void addMapType() {
        Mapper.getInstance().addMapType((MapType<B, A>) (source, destination, mapper) -> {
            destination.a = source.a.toString();
        },B.class, A.class);

        B b = new B();
        b.a = 10;

        A a = new A();
        Mapper.getInstance().map(b,a);

        assertEquals(b.a.toString(), a.a);
    }

}