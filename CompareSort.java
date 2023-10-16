import java.util.Random;
import java.util.Arrays;

public class CompareSort
{
    public static void main(String[] args)
    {
        int size = 10000; 
        int start = 1;
        int end = 10000;
        int[] randomArray = RandomizedArray(size, start, end);
        int[] gaps = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095};
        
        long startTime = System.nanoTime();
        
        BubbleSort(randomArray);
        
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        
        
        long startTime1 = System.nanoTime();
        
        SelectionSort(randomArray);
        
        long endTime1 = System.nanoTime();
        long elapsedTime1 = endTime1 - startTime1;
        
        
        long startTime2 = System.nanoTime();
        
        InsertionSort(randomArray);
        
        long endTime2 = System.nanoTime();
        long elapsedTime2 = endTime2 - startTime2;
        
        
        long startTimeQuick = System.nanoTime();
        
        QuickSort(randomArray, 0, randomArray.length -1);
        
        long endTimeQuick = System.nanoTime();
        long elapsedTimeQuick = endTimeQuick - startTimeQuick;
        
        
        long startTimeShell = System.nanoTime();
        
        ShellSort(randomArray, gaps);
        
        long endTimeShell = System.nanoTime();
        long elapsedTimeShell = endTimeShell - startTimeShell;
        
        System.out.println("Sorting a random array size of " + size + " took bubble sort " + elapsedTime + " nanoseconds to complete.");
        System.out.println("Sorting a random array size of " + size + " took selection sort " + elapsedTime1 + " nanoseconds to complete.");
        System.out.println("Sorting a random array size of " + size + " took insertion sort " + elapsedTime2 + " nanoseconds to complete.");
        System.out.println("Sorting a random array size of " + size + " took QuickSort sort " + elapsedTimeQuick + " nanoseconds to complete.");
        System.out.println("Sorting a random array size of " + size + " took Shell sort " + elapsedTimeShell + " nanoseconds to complete.");
    }
    public static int[] RandomizedArray(int size, int start, int end)
    {
        int[] arrayResult = new int[size];
        Random random = new Random(); 
        
        for(int i = 0; i < size; i++)
        {
            int randomVal = random.nextInt(end) + start;
            arrayResult[i] = randomVal;
        }
        return arrayResult;
    }
    public static void BubbleSort(int[] arr)
    {
        boolean swap; 
        int n = arr.length;
        
        for(int i = 0; i < n -1; i++)
        {
            swap = false;
            for(int j = 0; j < n - i - 1; j++)
            {
                if(arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swap = true;
                }
            }
            if(swap == false)
            {
                break;
            }
        }
        //System.out.println(Arrays.toString(arr));
    }
    public static void SelectionSort(int[] arr)
    {
        int n = arr.length;
        
        for(int i = 0; i < n - 1; i++)
        {
            int min = i;
            
            for(int j = i + 1; j < n; j++)
            {
                if(arr[j] < arr[min])
                {
                    min = j;
                }
            }
            int temp1 = arr[i];
            arr[i] = arr[min];
            arr[min] = temp1;
        }
        //System.out.println(Arrays.toString(arr));
    }
    public static void InsertionSort(int[] arr)
    {
        int n = arr.length;
        
        for(int i = 1; i < n -1; i++)
        {
            int key = arr[i]; 
            int j = i - 1;
            
            while(j >= 0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        //System.out.println(Arrays.toString(arr));
    }
    public static void QuickSort(int[] arr, int low, int high)
    {
        if(low < high)
        {
            int pivot = partition(arr, low, high);
            QuickSort(arr, low, pivot);
            QuickSort(arr, pivot + 1, high);
        }
    }
    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = low;
        
        for(int j = low; j < high; j++)
        {
            if(arr[j] < pivot)
            {
                if(i != j)
                {
                    arr[i] ^= arr[j];
                    arr[j] ^= arr[i];
                    arr[i] ^= arr[j];
                }
            }
        }
        arr[i] ^= arr[high];
        arr[high] ^= arr[i];
        arr[i] ^= arr[high];
        return i;
    }
    public static void ShellSort(int[] arr, int[] gaps)
    {
        for(int i = 0; i < gaps.length; i++)
        {
            int gap = gaps[i];
            for(int j = 0; j < gap; j++)
            {
                InsertionSortGap(arr, j, gap);
            }
        }
    }
    public static void InsertionSortGap(int[] arr, int start, int gap)
    {
        for(int i = start + gap; i < arr.length; i = i + gap) 
        {
        int current = i;
        while (current - gap >= 0 && arr[current] < arr[current - gap]) 
        {
            int temp = arr[current];
            arr[current] = arr[current - gap];
            arr[current - gap] = temp;
            current = current - gap;
        }
    }
    }
}