package ISC4U;
import java.util.*;

public class Utilities {

	public static long count = 0;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
	}

	public static void bubbleSort(int arr[], int n)
    {
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                     
                    // Swap arr[j] and arr[j+1]
                    swap(arr, j, j + 1);
                    swapped = true;
                    count++;
                }
            }
 
            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }
	
	public static void selectionSort (int [] A) {
		for (int i = A.length-1; i > 0; i--) {
			int big = 0;
			for (int j = 1; j <= i; j++) {
				count++;
				if (A[j] > A[big]) {
					big = j;
				}
			}
			swap(A, big, i);	
		}
	}
	
	public static void insertionSort (int [] A) {
		for (int i = 1; i < A.length; i++) {
			int temp = A[i];
			int j = i - 1;
			while (j >= 0 && A[j] > temp) {
				count++;
				A[j + 1] = A[j];
				j--;
			}
			A[j + 1] = temp;
		}
	}
	
	public static void mergeSort2(int[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    int[] l = new int[mid];
	    int[] r = new int[n - mid];

	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSort2(l, mid);
	    mergeSort2(r, n - mid);

	    merge(a, l, r, mid, n - mid);
	}
	
	public static void merge(
			  int[] a, int[] l, int[] r, int left, int right) {
			 
			    int i = 0, j = 0, k = 0;
			    while (i < left && j < right) {
			    	count++;
			        if (l[i] <= r[j]) {
			            a[k++] = l[i++];
			        }
			        else {
			            a[k++] = r[j++];
			        }
			    }
			    while (i < left) {
			        a[k++] = l[i++];
			        count++;
			    }
			    while (j < right) {
			        a[k++] = r[j++];
			        count++;
			    }
			}

	
	public static void mergeSort(int[] a, int start, int end)
	{
	//if only 2 or fewer elements
	if (end - start < 2)
	{
	//if 2 elements and need to be swapped
	if (end > start && a[end] < a[start])
	swap(a, start, end);
	}
	else
	{
	int middle = (start + end) / 2;
	mergeSort(a, start, middle);
	mergeSort(a, middle + 1, end);
	merge(a, start, middle, end);
	}
	}
	
	public static void merge(int[] a, int start, int middle, int end) {
	    // Create temporary arrays
	    int[] leftArray = new int[middle - start + 1];
	    int[] rightArray = new int[end - middle];

	    // Copy data to temporary arrays
	    for (int i = 0; i < leftArray.length; ++i) {
	    	count++;
	        leftArray[i] = a[start + i];
	    }
	    for (int j = 0; j < rightArray.length; ++j) {
	        rightArray[j] = a[middle + 1 + j];
	        count++;
	    }
	    // Merge the temporary arrays

	    // Initial indexes of first and second subarrays
	    int leftIndex = 0, rightIndex = 0;

	    // Initial index of merged subarray
	    int mergedIndex = start;

	    // Merge the temp arrays back into the original array
	    while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
	    	count++;
	        if (leftArray[leftIndex] <= rightArray[rightIndex]) {
	            a[mergedIndex] = leftArray[leftIndex];
	            leftIndex++;
	        } else {
	            a[mergedIndex] = rightArray[rightIndex];
	            rightIndex++;
	        }
	        mergedIndex++;
	    }

	    // Copy remaining elements of leftArray[] if any
	    while (leftIndex < leftArray.length) {
	    	count++;
	        a[mergedIndex] = leftArray[leftIndex];
	        leftIndex++;
	        mergedIndex++;
	    }

	    // Copy remaining elements of rightArray[] if any
	    while (rightIndex < rightArray.length) {
	    	count++;
	        a[mergedIndex] = rightArray[rightIndex];
	        rightIndex++;
	        mergedIndex++;
	    }
	}
	
	public static void quickSort(int arr[], int begin, int end) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);

	        quickSort(arr, begin, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}
	
	public static int partition(int arr[], int begin, int end) {
	    int pivot = arr[end];
	    int i = (begin-1);

	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;
	            
	            swap (arr, i, j); 
	        	count++;
	        }
	    }
    	
	    swap(arr, i + 1, end);
        
	    return i+1;
	}
	
	public static void swap (int [] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}