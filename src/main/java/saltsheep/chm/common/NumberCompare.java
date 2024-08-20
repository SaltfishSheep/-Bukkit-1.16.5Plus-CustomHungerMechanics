package saltsheep.chm.common;

public class NumberCompare {

    private static final boolean lenient = true;
    private static final float floatOffset = lenient? 1e-4f:1e-6f;
    private static final double doubleOffset = lenient? 1e-6f:1e-8;

    public static boolean equals(double numA, double numB){
        double offset = Math.abs(numA-numB);
        return offset <= doubleOffset;
    }

    public static boolean equals(float numA, float numB){
        double offset = Math.abs(numA-numB);
        return offset <= floatOffset;
    }

    public static boolean equals(float numA, double numB){
        if(lenient)
            return equals(numA, (float)numB);
        else
            return equals((double)numA, numB);
    }

    public static boolean equals(double numA, float numB){
        if(lenient)
            return equals((float)numA, numB);
        else
            return equals(numA, (double)numB);
    }

}
