import java.util.*;
public class Merge{

  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[]data){
    mergesort(data, 0, data.length - 1);
  }
  private static void mergesort(int[] data, int start, int end){
    int len = end - start + 1;
    int pivot = (end + start) / 2;
    if (len <= 1) return;
    if (len == 2){
      if (data[start] > data[end]){
        swap(data, start, end);
      }
    }
    else{
      int[] d1 = Arrays.copyOfRange(data, start, pivot + 1);
      int[] d2 = Arrays.copyOfRange(data, pivot + 1, end + 1);
      mergesort(d1);
      mergesort(d2);
      int[] merged = merge(d1, d2);
      for (int i = 0; i < data.length; i++){
        data[i] = merged[i];
      }
    }
  }

  private static int[] merge(int[] data1, int[] data2){
    int[] out = new int[data1.length + data2.length];
    int i1 = 0;
    int i2 = 0;
    int iout = 0;
    while (i1 < data1.length || i2 < data2.length) {
      if (i1 >= data1.length){
         out[iout] = data2[i2];
         i2++;
       }
      else if (i2 >= data2.length){
        out[iout] = data1[i1];
        i1++;
      }
      else if (data1[i1] > data2[i2]){
        out[iout] = data2[i2];
        i2++;
      }
      else {
        out[iout] = data1[i1];
        i1++;
      }
      iout++;
    }
    return out;
  }


  private static void swap(int[] data, int i1, int i2){
    int temp = data[i2];
    data[i2] = data[i1];
    data[i1] = temp;
  }

  public static void main(String[]args){
    int[] i = new int[]{9, 6, 3, 23, 2, 1, 0};
    System.out.println(Arrays.toString(i));
    mergesort(i);
    System.out.println(Arrays.toString(i));
  }
}
