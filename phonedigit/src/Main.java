/**
 * Created by voks1969 on 6/11/2016.
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static Map <Integer, String > globalmap = new HashMap<Integer, String>() {{
        put(0,"");
        put(1,"");
        put(2,"abc");
        put(3,"def");
        put(4,"ghi");
        put(5,"jkl");
        put(6,"mno");
        put(7,"pqrs");
        put(8,"tuv");
        put(9,"wxyz");
    }};

    static List<String> result = new ArrayList<String>();

    static class MyUtil {

        private static void getWords(Integer[] num, Integer pos) {
            String str = globalmap.get(num[pos]);
            if(str.length()==0){
             str = "" + num[pos];
            }
            char[] chars = str.toCharArray();

            int resultsize = result.size();
            if(resultsize==0 ) {
                for(char c : chars) {
                    String resstr =""+c;
                    result.add(resstr);
                }
            }
            else {
                for(int index=0;index<resultsize;index++) {
                    String resstr = result.remove(0);
                    for (char c : chars) {
                        result.add(resstr + c);
                    }
                }
            }
        }

        public static void printWords(Integer[] num) {
            for(int ii=0;ii<num.length;ii++)
                getWords(num, ii);
            for(int index=0;index<result.size();index++)
                System.out.print(" "+ result.get(index) + " ");
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        //Integer inp_number[] = {1, 2,3,9};
        Integer inp_number[] = {2,3,4};
        System.out.print("Input: ");
        for(int x : inp_number)
            System.out.print(x+" ");
        System.out.print("\n");
        System.out.print("Output: ");
        MyUtil.printWords(inp_number);
    }
}
