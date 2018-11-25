package economysimulation.classes.managers.sorting.conditions;

import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.sorting.GameSorter;

/**
 *
 * @author Max Carter
 */
public class MergeSort {

    private void merge(GamePackage arr[], int left, int middle, int right, GameSorter gameSorter) {
        int size1 = middle - left + 1;
        int size2 = right - middle;
 
        // Create temporary lists.
        GamePackage LeftList[] = new GamePackage [size1];
        GamePackage RightList[] = new GamePackage [size2];
 
        // Copying data to lists.
        for (int i=0; i<size1; ++i)
            LeftList[i] = arr[left + i];
        for (int j=0; j<size2; ++j)
            RightList[j] = arr[middle + 1+ j];
 
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
            
            
            if (value1 >= value2) {
                arr[k] = LeftList[i];
                i++;
            }
            else {
                arr[k] = RightList[j];
                j++;
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
        if (left < right) {
            // Get mid point.
            int m = (left+right)/2;
 
            // Sort lists in order.
            sort(arr, left, m, gameSorter);
            sort(arr , m+1, right, gameSorter);
 
            // Merge the two sorted lists together.
            merge(arr, left, m, right, gameSorter);
        }
    }
    
}
