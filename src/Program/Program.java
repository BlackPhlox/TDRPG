package Program;

import Model.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {
    //Program.Program info
    private static final String PROGRAM_NAME = "Dungeon Crawler";
    private static final String VERSION_NOTATION = "V";
    private static final double VERSION = 0.1;

    //Timestamp related
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    private static final String GENERIC_SEPARATOR = " ";
    private static final String PRINTTYPE_INPUT_SEPARATOR = GENERIC_SEPARATOR + "|" + GENERIC_SEPARATOR;
    private static final String TIMESTAMP_INPUT_SEPARATOR = GENERIC_SEPARATOR + ":" + GENERIC_SEPARATOR;

    public static String getName(){
        return PROGRAM_NAME;
    }
    public static double getVersion(){
        return VERSION;
    }
    public static String getVersionName(){
        return VERSION_NOTATION + getVersion();
    }
    public static String getFullName(){
        return getName() + GENERIC_SEPARATOR + getVersionName();
    }

    private static String getCurrentTimeStamp() {
        return new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
    }

    public static double map(double n, double start1, double stop1, double start2, double stop2){
        double newVal = (n - start1) / (stop1 - start1) * (stop2 - start2) + start2;
        if (start2 < stop2) {
            return constrain(newVal, start2, stop2);
        } else {
            return constrain(newVal, stop2, start2);
        }
    }

    public static double constrain(double n, double low, double high){
        return Math.max(Math.min(n, high), low);
    }

    private static void defaultPrint(String input){
        System.out.print(getCurrentTimeStamp() + TIMESTAMP_INPUT_SEPARATOR + input);
    }

    public static void print(String input){
        defaultPrint(input);
    }

    public static void println(String input){
        defaultPrint(input + "\n");
    }

    public static void print(Player p, String s){
        boolean isDebugging = true;
        if (isDebugging) defaultPrint(p + " : " + s);
    }

    public static void println(Player p, String s){
        boolean isDebugging = true;
        if (isDebugging) defaultPrint(p + " : " + s+"\n");
    }
}
