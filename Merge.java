import java.util.*;
public class Merge{

  /*sort the array from least to greatest value. This is a wrapper function*/
  public static void mergesort(int[]data){
    mergesortOpt(data);
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

  public static void mergesortOpt(int[]data){
    int[] sto = new int[data.length];
    mergesortOpt(data, sto, 0, data.length - 1);
  }
  private static void mergesortOpt(int[] data, int[] sto, int start, int end){
    int len = end - start + 1;
    int pivot = (end + start) / 2;
    if (len <= 1) return;
    if (len <= 3){
      insertionSort(data, start, end);
    }
    else{
      mergesortOpt(data, sto, start, pivot);
      mergesortOpt(data, sto, pivot + 1, end);
      merge(data, sto, start, pivot + 1, end);
      for (int i = 0; i < len; i++){
        data[i + start] = sto[i];
      }
    }
  }

  private static void merge(int[] data, int[] sto, int start, int pivot, int end){
    int i1 = start;
    int i2 = pivot;
    int iout = 0;
    while (i1 < pivot || i2 <= end) {
      if (i1 >= pivot){
         sto[iout] = data[i2];
         i2++;
       }
      else if (i2 > end){
        sto[iout] = data[i1];
        i1++;
      }
      else if (data[i1] > data[i2]){
        sto[iout] = data[i2];
        i2++;
      }
      else {
        sto[iout] = data[i1];
        i1++;
      }
      iout++;
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


  public static void insertionSort(int[] ary, int lo, int hi){
    for (int i = lo + 1; i <= hi; i++){
      int temp = ary[i];
      if (ary[i - 1] > ary[i]){
        int x = i - 1;
        while (x > lo && ary[x - 1] > temp){
          ary[x + 1] = ary[x];
          x--;
        }
        ary[x + 1] = ary[x];
        ary[x] = temp;
      }
    }
}

  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
int[]MAX_LIST = {1000000000,500,10};
for(int MAX : MAX_LIST){
  for(int size = 31250; size < 2000001; size*=2){
    long qtime=0;
    long btime=0;
    //average of 5 sorts.
    for(int trial = 0 ; trial <=5; trial++){
      int []data1 = new int[size];
      int []data2 = new int[size];
      for(int i = 0; i < data1.length; i++){
        data1[i] = (int)(Math.random()*MAX);
        data2[i] = data1[i];
      }
      long t1,t2;
      t1 = System.currentTimeMillis();
      Merge.mergesortOpt(data2);
      t2 = System.currentTimeMillis();
      qtime += t2 - t1;
      t1 = System.currentTimeMillis();
      Arrays.sort(data1);
      t2 = System.currentTimeMillis();
      btime+= t2 - t1;
      if(!Arrays.equals(data1,data2)){
        System.out.println("FAIL TO SORT!");
        System.exit(0);
      }
    }
    System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
  }
  System.out.println();
}
  }
}
