import java.util.Arrays;

public class Mergesort {

    public void mergeProcess(int[] arr, int left, int mid, int right) {

//        System.out.println(arr[mid]);
        // 두 개의 배열의 크기를 구한다.
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        System.out.println(leftSize + ", " + rightSize);

        // tp 배열 생성 및 복사
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; ++i)
            leftArray[i] = arr[left + i];
        for (int j = 0; j < rightSize; ++j)
            rightArray[j] = arr[mid + 1 + j];

        // 두 개의 배열을 합쳐준다.
        int i = 0, j = 0;
        int k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            }
            else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        //  남은 배열을 넣어준다.
        while (i < leftSize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // 남은 배열을 넣어준다.
        while (j < rightSize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }


    }

    public void mergeSortFunc(int[] arr, int left, int right) {
        //

        int mid = (left + right) / 2;
        System.out.println(left + ", " + mid + ", " + right);

        if(left < right) {

            mergeSortFunc(arr, left, mid);
            mergeSortFunc(arr, mid + 1, right);
            mergeProcess(arr, left, mid, right);
        }
    }

    void sort() {
        // 원본
        int[] arr = {4, 5, 1, 2, 7, 4, 2, 3};
        //
        int[] tp = new int[arr.length];

        mergeSortFunc(arr, 0, arr.length -1);

        System.out.println(Arrays.toString(arr));

    }
}
