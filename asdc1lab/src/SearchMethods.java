import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchMethods {
    private final String FILENAME = "people.txt";
    private final int ARRAY_SIZE = 50;
    private String[] unsortedArray = new String[ARRAY_SIZE];
    private String[] sortedArray = new String[ARRAY_SIZE];

    public SearchMethods() {
        readFile();
        sortArray();
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < ARRAY_SIZE) {
                unsortedArray[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortArray() {
        sortedArray = unsortedArray.clone();
        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = i + 1; j < sortedArray.length; j++) {
                String[] person1 = sortedArray[i].split(",");
                String[] person2 = sortedArray[j].split(",");
                int year1 = Integer.parseInt(person1[3]);
                int year2 = Integer.parseInt(person2[3]);
                if (year1 > year2) {
                    String temp = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = temp;
                }
            }
        }
    }

    // Linear search in unsorted array
    public void linearSearchUnsorted(String key) {
        long startTime = System.nanoTime();
        for (int i = 0; i < unsortedArray.length; i++) {
            if (unsortedArray[i].contains(key)) {
                System.out.println("Found " + key + " in unsorted array at index " + i);
                break;
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time taken for linear search in unsorted array: " + duration + " nanoseconds");
    }

    // Linear search in sorted array
    public void linearSearchSorted(String key) {
        long startTime = System.nanoTime();
        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i].contains(key)) {
                System.out.println("Found " + key + " in sorted array at index " + i);
                break;
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time taken for linear search in sorted array: " + duration + " nanoseconds");
    }

    // Binary search in sorted array
    public void binarySearch(String key) {
        int low = 0;
        int high = sortedArray.length - 1;
        long startTime = System.nanoTime();
        while (low <= high) {
            int mid = (low + high) / 2;
            String[] person = sortedArray[mid].split(",");
            int result = key.compareTo(person[1]);
            if (result == 0) {
                System.out.println("Found " + key + " in sorted array at index " + mid);
                break;
            } else if (result < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time taken for binary search in sorted array: " + duration + " nanoseconds");
    }

    // Interpolation search in sorted array
    public void interpolationSearch(String key) {
        int low = 0;
        int high = sortedArray.length - 1;
        long startTime = System.nanoTime();
        while (low <= high) {
            String[] personLow = sortedArray[low].split(",");
            String[] personHigh = sortedArray[high].split(",");
            int yearLow = Integer.parseInt(personLow[3]);
            int yearHigh = Integer.parseInt(personHigh[3]);
            if (yearLow == yearHigh) {
                if (key.equals(personLow[1])) {
                    System.out.println("Found " + key + " in sorted array at index " + low);
                    break;
                } else {
                    low++;
                }
            } else {
                int mid = low + ((Integer.parseInt(key) - yearLow) * (high - low)) / (yearHigh - yearLow);
                if (mid < low || mid > high) {
                    break;
                }
                String[] personMid = sortedArray[mid].split(",");
                int yearMid = Integer.parseInt(personMid[3]);
                if (key.compareTo(personMid[1]) == 0) {
                    System.out.println("Found " + key + " in sorted array at index " + mid);
                    break;
                } else if (key.compareTo(personMid[1]) < 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time taken for interpolation search in sorted array: " + duration + " nanoseconds");
    }
}