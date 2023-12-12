public class TTest {


  static int[] arr1 = {-1, 3, 5, 7, 9};
  static int[] arr2 = { -3, -1, 4, 5 , 7, 8};

  public static void main(String[] args) {
    solu();
  }

  private static void solu() {

    int  i = 0;
    int j=0;
    int[] res = new int[arr1.length+ arr2.length];
    int k =0;

    while (i < arr1.length && j < arr2.length){

      if(arr1[i]<arr2[j]){
        res[k]=arr1[i];
        i++;
      } else {
        res[k]=arr2[j];
        j++;
      }
      k++;
    }

      while (j<arr2.length){
        res[k]=arr2[j];
        j++;
        k++;
      }



      while (i<arr1.length){
        res[k]=arr1[i];
        i++;
        k++;
      }

    for(int n=0; n<res.length;n++){
      System.out.println(res[n]);
    }
  }
}
