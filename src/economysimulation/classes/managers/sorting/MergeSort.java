package economysimulation.classes.managers.sorting;

import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.sorting.GameSorter;
import economysimulation.classes.managers.sorting.conditions.SearchCondition;

/**
 *
 * @author Max Carter
 */
public class MergeSort {

    private GameSorter gameSorterInstance = null;
    
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
            switch (gameSorterInstance.getSearchComponent()) {
                case GDP:
                    value1 = LeftList[i].getScore();
                    value2 = RightList[j].getScore();
                    break;
                case TICKS:
                    value1 = LeftList[i].getTicks();
                    value2 = RightList[j].getTicks();
                    break;
                default:
                    value1 = (int) LeftList[i].getComponentFromId(gameSorterInstance.getSearchComponent().getIndex());
                    value2 = (int) RightList[j].getComponentFromId(gameSorterInstance.getSearchComponent().getIndex());
                    break;
            }
            
            if (gameSorterInstance.getSearchCondition() == SearchCondition.HIGH_TO_LOW) {
                if (value1 >= value2) {
                    arr[k] = LeftList[i];
                    i++;
                } else {
                    arr[k] = RightList[j];
                    j++;
                }
            } else if (gameSorterInstance.getSearchCondition() == SearchCondition.LOW_TO_HIGH) {
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

    public void sort(GamePackage arr[], int left, int right, GameSorter gameSorter) {
        this.gameSorterInstance = gameSorter;
        if (left < right) {
            // Get mid point.
            int middle = (left+right)/2;
 
            // Sort lists in order.
            sort(arr, left, middle, gameSorterInstance);
            sort(arr , middle+1, right, gameSorterInstance);
 
            // Merge the two sorted lists together.
            merge(arr, left, middle, right);
        }
    }
    
}
