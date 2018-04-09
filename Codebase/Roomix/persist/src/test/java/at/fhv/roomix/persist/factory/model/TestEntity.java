package at.fhv.roomix.persist.factory.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Callable;

public class TestEntity {

    /* Static vars */
    private static int staticInteger;

    static {
        staticInteger = Integer.MAX_VALUE;
    }

    /* primitive datatypes */
    private int primitiveInteger = Integer.MAX_VALUE;
    private boolean primitiveBoolean = Boolean.FALSE;
    private byte primitiveByte = (byte) 0;
    private float primitveFloat = Float.MAX_VALUE;
    private double primitiveDouble = Double.MAX_VALUE;
    private long primitveLong = Long.MAX_VALUE;
    private String string = "Slim Shady";
    /* complex datatypes */
    private Collection<DeepTestEntity> collection = new LinkedList<>();
    /*  */
    private Callable<TestEntity> callable;
    /* multidimensional datatypes */
    private char[][] charMap = new char[][]{
            {'a', 'a'},
            {'a'}
    };
    /* Invalid vars */
    private int invalidInteger;

    public TestEntity() {
        DeepTestEntity deepEntity = new DeepTestEntity();
        deepEntity.setDeepID(Integer.MAX_VALUE);
        collection.add(deepEntity);
        callable = TestEntity::new;
    }

    public static int getStaticInteger() {
        return staticInteger;
    }

    public static void setStaticInteger(int staticInteger) {
        TestEntity.staticInteger = staticInteger;
    }

    public void testMethod() {
    }

    /* Getters/Setters */
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

    public Collection<DeepTestEntity> getCollection() {
        return collection;
    }

    public void setCollection(Collection<DeepTestEntity> collection) {
        this.collection = collection;
    }

    public Callable<TestEntity> getCallable() {
        return callable;
    }

    public void setCallable(Callable<TestEntity> callable) {
        this.callable = callable;
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
