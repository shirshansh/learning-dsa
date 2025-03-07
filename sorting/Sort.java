package sorting;

public class Sort {
    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubble(int arr[], boolean descending) {
        int n = arr.length;
        for (int i=1; i<=n-1; i++) {
            boolean swapped = false;
            for (int j=0; j<=n-1-i; j++) {
                if (!descending && arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    swapped = true;
                } else if (descending && arr[j]<arr[j+1]) {
                    swap(arr,j,j+1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void insertion(int arr[], boolean descending) {
        int n = arr.length;
        for (int i=1; i<=n-1; i++) {
            int key = arr[i];
            int j = i-1;
            if (descending) {
                while (j>=0 && arr[j]<key) {
                    arr[j+1] = arr[j];
                    j--;
                }
            } else {
                while (j>=0 && arr[j]>key) {
                    arr[j+1] = arr[j];
                    j--;
                }
            }
            arr[j+1] = key;
        }
    }

    public static void selection(int arr[], boolean descending) {
        int n = arr.length;
        for (int i=0; i<=n-2; i++) {
            int idx = i;
            for (int j=i+1; j<=n-1; j++) {
                if (!descending && arr[j]<arr[idx]) {
                    idx = j;
                } else if (descending && arr[j]>arr[idx]) {
                    idx = j;
                }
            }
            swap(arr, i, idx);
        }
    }

    public static void merge(int arr[], int s, int e, boolean descending) {
        if (s<e) {
            int mid = s+(e-s)/2;
            merge(arr, s, mid, descending);
            merge(arr, mid+1, e, descending);
            mergeSort(arr, s, mid, e, descending);
        }
    }

    private static void mergeSort(int arr[], int s, int m, int e, boolean descending) {
        int temp[] = new int[e-s+1];
        int l = s, k = m+1, idx = 0;

        while (l<=m && k<=e) {
            if (!descending) {
                if (arr[l]<arr[k]) {
                    temp[idx++] = arr[l++];
                } else {
                    temp[idx++] = arr[k++];
                }
            } else {
                if (arr[l]>arr[k]) {
                    temp[idx++] = arr[l++];
                } else {
                    temp[idx++] = arr[k++];
                }
            }
        }

        while (l<=m) {
            temp[idx++] = arr[l++];
        }

        while (k<=e) {
            temp[idx++] = arr[k++];
        }
        
        for (idx=0, k=s; k<=e; idx++, k++) {
            arr[k] = temp[idx];
        }
    }

    public static void quick(int arr[], int s, int e, boolean descending) {
        if (s<e) {
            int pivotIndex = partition(arr, s, e, descending);
            quick(arr, s, pivotIndex-1, descending);
            quick(arr, pivotIndex+1, e, descending);
        }
    }

    private static int partition(int arr[], int s, int e, boolean descending) {
        int pivot = arr[e];
        int i = (s-1);

        while (s<e) {
            if (!descending) {
                if (arr[s]<=pivot) {
                    swap(arr, ++i, s);
                }
                s++;
            } else {
                if (arr[s]>=pivot) {
                    swap(arr, ++i, s);
                }
                s++;
            }
        }

        swap(arr, ++i, e);

        return i;
    }

    public static void count(int arr[], boolean descending) {
        if (arr.length == 0) return;

        int max = arr[0];
        for (int i=1; i<arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        
        int count[] = new int[max+1];
        for (int i=0; i<arr.length; i++) {
            count[arr[i]]++;
        }

        int idx = 0;
        if (!descending) {
            for (int i=0; i<=max; i++) {
                while (count[i]>0) {
                    arr[idx++] = i;
                    count[i]--;
                }
            }
        } else {
            for (int i=max; i>=0; i--) {
                while (count[i]>0) {
                    arr[idx++] = i;
                    count[i]--;
                }
            }
        }
    }

    public static void heap(int arr[], boolean descending) {
        int n = arr.length;

        for (int i=n/2-1; i>=0; i--) {
            heapify(arr, n, i, descending);
        }

        for (int i=1; i<=n-1; i++) {
            swap(arr, 0, n-i);
            heapify(arr, n-i, 0, descending);
        }
    }

    private static void heapify(int arr[], int n, int i, boolean descending) {
        int to_be_at_root = i;
        int left = 2*i+1;
        int right = 2*i+2;

        if (!descending) {
            if (left<n && arr[left]>arr[to_be_at_root]) to_be_at_root = left;
            if (right<n && arr[right]>arr[to_be_at_root]) to_be_at_root = right;
        } else {
            if (left<n && arr[left]<arr[to_be_at_root]) to_be_at_root = left;
            if (right<n && arr[right]<arr[to_be_at_root]) to_be_at_root = right;
        }
        
        if (to_be_at_root!=i) {
            swap(arr, i, to_be_at_root);
            heapify(arr, n, to_be_at_root, descending);
        }
    }
}