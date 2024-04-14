package org.example.SortingAlgorithms;

import org.example.Exeptions.InvalidArrayLengthException;

public class QuickSort implements Strategy {
    @Override
    public void sort(int[] arr) throws InvalidArrayLengthException {
        // Sprawdzenie długości tablicy tylko raz na początku.
        if (arr.length < 3) {
            throw new InvalidArrayLengthException("Array must have at least 3 elements.");
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        // Usunięcie sprawdzenia długości tablicy z rekurencyjnej metody.
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivotIndex = medianOfThreeIndex(arr, low, high);
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high); // Przesunięcie pivota na koniec przed partycjonowaniem

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private int medianOfThreeIndex(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        if (arr[low] > arr[mid]) {
            swap(arr, low, mid);
        }
        if (arr[low] > arr[high]) {
            swap(arr, low, high);
        }
        if (arr[mid] > arr[high]) {
            swap(arr, mid, high);
        }
        // Mid teraz zawiera indeks wartości mediany, ale dla uproszczenia algorytmu
        // zwracamy mid jako pivot.
        return mid;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
