package ISC4U;

import java.util.*;

public class Driver {

	public static void main(String[] args) {
		
		int l = 1000000;
		
		int[] A = new int [l];
		
		for (int i = 0; i < A.length - 1; i++) {
			A[i] = (int)(Math.random()*100);
		}
		
		Utilities.count = 0;
		
	    Utilities.mergeSort(A, 0, A.length - 1);

		System.out.println(Utilities.count);
		Utilities.count = 0;
		
		/*Utilities.bubbleSort(A, A.length);
		
		System.out.println(Utilities.count);
		Utilities.count = 0;
		
		A = new int [l];
		
		for (int i = 0; i < A.length - 1; i++) {
			A[i] = (int)(Math.random()*100);
		}

		Utilities.selectionSort(A);

		System.out.println(Utilities.count);
		Utilities.count = 0;
		
		A = new int [l];
		
		for (int i = 0; i < A.length - 1; i++) {
			A[i] = (int)(Math.random()*100);
		}

		Utilities.insertionSort(A);
		
		System.out.println(Utilities.count);
		
		Utilities.count = 0;
		
		A = new int [l];
		
		for (int i = 0; i < A.length - 1; i++) {
			A[i] = (int)(Math.random()*100);
		}
		*/
		//Utilities.mergeSort(A, 0, A.length -1);
		
		//System.out.println(Utilities.count);
		//Utilities.count = 0;
		
	}

}