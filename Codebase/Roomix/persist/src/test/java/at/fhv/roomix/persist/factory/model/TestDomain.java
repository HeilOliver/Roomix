package at.fhv.roomix.persist.factory.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class TestDomain {

    /* primitive datatypes */
    private int primitiveInteger;
    private boolean primitiveBoolean;
    private byte primitiveByte;
    private float primitveFloat;
    private double primitiveDouble;
    private long primitveLong;
    private String string;

    /* complex datatypes */
    private Collection<DeepTestDomain> collection;

    /*  */
    private Callable<TestDomain> callable;
    private static int staticInteger;

    /* multidimensional datatypes */
    private char[][] charMap;

    /* Invalid vars */
    private int invalidInteger;

    public int getPrimitiveInteger() {
        return primitiveInteger;
    }

    public void setPrimitiveInteger(int primitiveInteger) {
        this.primitiveInteger = primitiveInteger;
    }

    public boolean isPrimitiveBoolean() {
        return primitiveBoolean;
    }

    public void setPrimitiveBoolean(boolean primitiveBoolean) {
        this.primitiveBoolean = primitiveBoolean;
    }

    public byte getPrimitiveByte() {
        return primitiveByte;
    }

    public void setPrimitiveByte(byte primitiveByte) {
        this.primitiveByte = primitiveByte;
    }

    public float getPrimitveFloat() {
        return primitveFloat;
    }

    public void setPrimitveFloat(float primitveFloat) {
        this.primitveFloat = primitveFloat;
    }

    public double getPrimitiveDouble() {
        return primitiveDouble;
    }

    public void setPrimitiveDouble(double primitiveDouble) {
        this.primitiveDouble = primitiveDouble;
    }

    public long getPrimitveLong() {
        return primitveLong;
    }

    public void setPrimitveLong(long primitveLong) {
        this.primitveLong = primitveLong;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Collection<DeepTestDomain> getCollection() {
        return collection;
    }

    public void setCollection(Collection<DeepTestDomain> collection) {
        this.collection = collection;
    }

    public Callable<TestDomain> getCallable() {
        return callable;
    }

    public void setCallable(Callable<TestDomain> callable) {
        this.callable = callable;
    }

    public static int getStaticInteger() {
        return staticInteger;
    }

    public static void setStaticInteger(int staticInteger) {
        TestDomain.staticInteger = staticInteger;
    }

    public char[][] getCharMap() {
        return charMap;
    }

    public void setCharMap(char[][] charMap) {
        this.charMap = charMap;
    }
    public int getInvalidInteger() {
        return invalidInteger;
    }

    public void setInvalidInteger(int invalidInteger) {
        this.invalidInteger = invalidInteger;
    }


    @Override
    public int hashCode() {
        final int[] result = new int[1];
        long temp;
        result[0] = getPrimitiveInteger();
        result[0] = 31 * result[0] + (isPrimitiveBoolean() ? 1 : 0);
        result[0] = 31 * result[0] + (int) getPrimitiveByte();
        result[0] = 31 * result[0] + (getPrimitveFloat() != +0.0f ? Float.floatToIntBits(getPrimitveFloat()) : 0);
        temp = Double.doubleToLongBits(getPrimitiveDouble());
        result[0] = 31 * result[0] + (int) (temp ^ (temp >>> 32));
        result[0] = 31 * result[0] + (int) (getPrimitveLong() ^ (getPrimitveLong() >>> 32));
        result[0] = 31 * result[0] + getString().hashCode();
        getCollection().forEach(deepTestEntity -> {
            result[0] = 31 * result[0] + deepTestEntity.getDeepID();
        });
        result[0] = 31 * result[0] + getCallable().hashCode();
        result[0] = 31 * result[0] + Arrays.deepHashCode(getCharMap());
        result[0] = 31 * result[0] + getInvalidInteger();
        return result[0];
    }
}
