import java.util.*;
public class Merge{

  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[]data){
    mergesort(data, 0, data.length - 1);
  }
  private static void mergesort(int[] data, int start, int end){
    int len = end - start;
    int pivot = end + start / 2;
    if (len <= 1) return;
    if (len == 2){
      if (data[start] > data[end]) swap(data, start, end);
    }
    else{
      int[] d1 = sublist(data, start, pivot - 1);
      int[] d2 = sublist(data, pivot, end);
      mergesort(d1);
      mergesort(d2);
      data = merge(d1, d2);
    }
  }

  private static int[] merge(int[] data1, int[] data2){
    int[] out = new int[data1.length + data2.length];
    for (int i = 0; i < Math.min(data1.length, data2.length); i++){
      out[i] = Math.min(data1[i], data2[i]);
    }
    if (data1.length > data2.length){
      for (int i = data2.length; i < data1.length; i++){
        out[i] = data1[i];
      }
    }
    if (data2.length > data1.length){
      for (int i = data1.length; i < data2.length; i++){
        out[i] = data2[i];
      }
    }
    return out;
  }

  private static int[] sublist(int[] data, int first, int last){
    int[] temp = new int[last - first];
    for (int i = first; i < last; i++){
      temp[i] = data[i];
    }
    return temp;
  }

  private static void swap(int[] data, int i1, int i2){
    int temp = data[i2];
    data[i2] = data[i1];
    data[i1] = temp;
  }

  public static void main(String[]args){
    int[] i = new int[]{4, 6, 8, 23, 2, 1, 0};
    System.out.println(Arrays.toString(i));
    mergesort(i);
    System.out.println(Arrays.toString(i));
  }
}
