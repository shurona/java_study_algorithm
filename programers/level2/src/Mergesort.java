public class Mergesort {

    public void mergeProcess(int[] arr, int left, int mid, int right) {

        System.out.println(arr[mid]);
    }

    public void mergeSortFunc(int[] arr, int left, int right) {
        //
        int mid = (left + right) / 2;

        if(left < right) {
            mergeSortFunc(arr, left, mid);
            mergeSortFunc(arr, mid +1, right);
            mergeProcess(arr, left, mid, right);
        }
    }

    void sort() {
        // 원본
        int[] arr = {4, 5, 1, 2, 7, 4, 2};

        //
        int[] tp = new int[arr.length];

        mergeSortFunc(arr, 0, arr.length);

        System.out.println(arr[0]);

    }
}
