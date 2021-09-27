package com.carousell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String line = sc.nextLine();

            String[] arguments = inputParser(line);
            System.out.println(Dispatcher.process(arguments));
        }
    }

    private static String[] inputParser(String input){
        List<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        boolean open = false;
        for(int pos=0; pos<input.length(); pos++){
            if(input.charAt(pos) == ' ' && !open){
                list.add(sb.toString());
                sb = new StringBuilder();
            }else if(input.charAt(pos) == '\''){
                if(!open)
                    open = true;
                else
                    open = false;
            }else{
                sb.append(""+input.charAt(pos));
            }
        }
        list.add(sb.toString());
        return list.toArray(new String[list.size()]);
    }
}
