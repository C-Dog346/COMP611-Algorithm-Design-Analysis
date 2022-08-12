/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HackerRankCodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Callum
 */
public class LonelyInteger {

    public static int lonelyInteger(List<Integer> a) {
    // Write your code here
        Collections.sort(a);
        boolean[] arr = new boolean[a.get(a.size()-1)+1];

        int answer = 0;
        
        for (Integer integer : a) {
            if (arr[integer] == true)
                arr[integer] = false;
            else
                arr[integer] = true;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == true)   
                answer = i;
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for (Integer i = 0; i < 10; i++) {
            list.add(i);
            list.add(i);
        }
        list.remove(new Integer(5));
        
        System.out.println(LonelyInteger.lonelyInteger(list));
    }
    
}
