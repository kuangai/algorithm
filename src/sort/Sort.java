package sort;

import java.util.Arrays;

public class Sort {


    static int []  mergeSort(int [] arr){
        if (arr.length <= 1){
            return arr;
        }
        int num = arr.length >> 1;
        int [] left = Arrays.copyOfRange(arr,0,num);
        int [] right = Arrays.copyOfRange(arr,num,arr.length);
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        int [] re = mergeTwoArray(mergeSort(left),mergeSort(right));
        System.out.println(Arrays.toString(re));
        return re;
    }

    static int [] mergeTwoArray(int [] left, int [] right){
        int [] result = new int[left.length+right.length];
        int i = 0, j = 0,  k = 0;

        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                result[k++] = left[i++];
            }else {
                result[k++] = right[j++];
            }
        }
        while(i < left.length){
            result[k++] = left[i++];
        }
        while(j < right.length){
            result[k++] = right[j++];
        }
        return result;
    }

    public static void qSort(int [] arr, int low, int high){
        if (arr.length < 2) return;
        if (low >= high) return;
        int left = low, right = high;
        int provid = arr[left];
        while (left < right){
            while(left < right && arr[right] >= provid) right --;
            arr[left] = arr[right];
            while(left < right && arr[left] <= provid) left ++;
            arr[right] = arr[left];
        }
        arr[left] = provid;
        qSort(arr,low,left-1);
        qSort(arr,left + 1,high);
    }

    public static void qSort(int arr[]){
        qSort(arr,0,arr.length-1);
    }

    public static void bucketSort(int [] arr){

    }


    public static void main(String[] args) {
        int arr []={1,11,14,9,0, 1, 1, 3, 4, 5} ;
        System.out.println("============================================================================");
        System.out.println(Arrays.toString(mergeSort(arr)));
        qSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
