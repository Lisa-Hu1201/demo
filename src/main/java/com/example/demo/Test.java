package com.example.demo;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Test {

	public static int countSegments(String s) {
//		\\s - matches single whitespace character
//		\\s+ - matches sequence of one or more whitespace characters.
//		小寫s : 代表space
//		+ 表示比對一或多次
		StringTokenizer st = new StringTokenizer(s," ");
		System.out.println(st);
        int c =0;
        while(st.hasMoreTokens())
        {
            c++;
//            st.nextToken();
            System.out.println(st.nextToken());
        }
        
        return c;
    }
	
	public static void main(String[] args) {
		String s = ", , , ,        a, eaefa";
//		System.out.println(countSegments("      hello i am      "));
//		", , , ,        a, eaefa"
		System.out.println(Arrays.toString(s.trim().split(" +")));
		System.out.println(s.trim().split(" +").length);
//		System.out.println(s);
	}

}

