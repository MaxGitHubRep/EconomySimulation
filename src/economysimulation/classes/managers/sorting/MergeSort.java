package economysimulation.classes.managers.sorting;

import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.sorting.conditions.SearchCondition;

/**
 *
 * @author Max Carter
 */
public class MergeSort {

    /** This variable contains the characteristics of the sorting algorithm. */
    private GameSorter gameSorter = null;
    
    /**
     * Creates a new merge sorter with a {@code GameSorter} instance.
     * @param gameSorter Instance of the {@code GameSorter}.
     */
    public MergeSort(GameSorter gameSorter) {
        if (gameSorter == null)
            throw new NullPointerException("The MergeSort cannot work without an uninstantiated GameSorter.");
        this.gameSorter = gameSorter;
    }
    
    /**
     * Get the {@code GameSorter} that is used by the merge sort.
     * @return {@code GameSorter} instance.
     */
    public GameSorter getGameSorter() {
        return gameSorter;
    }
    
    /**
     * Merges two arrays together.
     * @param arr    List of data.
     * @param left   Size of left array.
     * @param middle Middle index of the array.
     * @param right  Size of right array.
     */
    private void merge(GamePackage arr[], int left, int middle, int right) {
        int size1 = middle - left + 1;
        int size2 = right - middle;
 
        // Create temporary lists.
        GamePackage LeftList[] = new GamePackage [size1];
        GamePackage RightList[] = new GamePackage [size2];
 
        // Copying data to lists.
        for (int i=0; i<size1; ++i)
            LeftList[i] = arr[left + i];
        for (int j=0; j<size2; ++j)
            RightList[j] = arr[middle + 1 + j];
 
        int i = 0, j = 0;
 
        // Merging lists.
        int k = left;
        while (i < size1 && j < size2) {
            int value1 = 0, value2 = 0;
            
            // Find the component that is being sorted.
            switch (gameSorter.getSearchComponent()) {
                case GDP:
                    value1 = LeftList[i].getScore();
                    value2 = RightList[j].getScore();
                    break;
                case TICKS:
                    value1 = LeftList[i].getTicks();
                    value2 = RightList[j].getTicks();
                    break;
                default:
                    value1 = (int) LeftList[i].getComponentFromId(gameSorter.getSearchComponent().getIndex());
                    value2 = (int) RightList[j].getComponentFromId(gameSorter.getSearchComponent().getIndex());
                    break;
            }
            
            if (gameSorter.getSearchCondition() == SearchCondition.HIGH_TO_LOW) {
                if (value1 >= value2) {
                    arr[k] = LeftList[i];
                    i++;
                } else {
                    arr[k] = RightList[j];
                    j++;
                }
            } else if (gameSorter.getSearchCondition() == SearchCondition.LOW_TO_HIGH) {
                if (value1 <= value2) {
                    arr[k] = LeftList[i];
                    i++;
                } else {
                    arr[k] = RightList[j];
                    j++;
                }
            }
            
            
            k++;
        }
 
        // Copying remaining elements.
        while (i < size1) {
            arr[k] = LeftList[i];
            i++;
            k++;
        }
 
        while (j < size2) {
            arr[k] = RightList[j];
            j++;
            k++;
        }
    }

    /**
     * Puts an array in order.
     * @param arr        The array to sort.
     * @param left       Size of left.
     * @param right      Size of right.   
     */
    public void sort(GamePackage arr[], int left, int right) {
        if (left < right) {
            // Get mid point.
            int middle = (left+right)/2;
 
            // Sort lists in order.
            sort(arr, left, middle);
            sort(arr , middle+1, right);
 
            // Merge the two sorted lists together.
            merge(arr, left, middle, right);
        }
    }
    
}
