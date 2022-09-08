package pro.sky.HW16algorithms3;

import pro.sky.HW16algorithms3.exception.InvalidIndexException;
import pro.sky.HW16algorithms3.exception.NullItemException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] repository;
    private int size;

    public IntegerListImpl() {
        repository = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        repository = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);
        if (size == repository.length) {
            grow();
        }
        repository[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        if (size == repository.length) {
            grow();
        }
        if (index == size) {
            repository[size++] = item;
            return item;
        }
        System.arraycopy(repository, index, repository, index + 1, index - size); // сдвиг всех элементов
        repository[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        repository[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = repository[index];
        if (index != size) {
            System.arraycopy(repository, index + 1, repository, index, size - index); // сдвиг влево
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        Integer[] repositoryCopy = toArray(); // toArray дает копию массива, поэтому работа идет с новым массивом
        quickSort(repositoryCopy, 0, repositoryCopy.length - 1); // сортировка
        int min = 0; // метод бинарного поиска
        int max = repositoryCopy.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(repositoryCopy[mid])) {
                return true;
            }
            if (item < repositoryCopy[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index); // проверяем интервал
        return repository[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {

        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() { //обнуляем
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(repository, size);
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void sortInsertion(Integer[] arr) { //самый быстрый способ сортировки
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
        private void quickSort ( Integer[] arr, int begin, int end){ // быстрая сортировка с рекурсией
            if (begin < end) {
                int partitionIndex = partition(arr, begin, end);

                quickSort(arr, begin, partitionIndex - 1);
                quickSort(arr, partitionIndex + 1, end);
            }
        }
        private int partition (Integer[] arr, int begin, int end){
            int pivot = arr[end];
            int i = (begin - 1);

            for (int j = begin; j < end; j++) {
                if (arr[j] <= pivot) {
                    i++;

                    swapElements(arr, i, j);
                }
            }
            swapElements(arr, i + 1, end);
            return i + 1;
        }
        private static void swapElements (Integer[] arr, int left, int right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        /* private void grow () { // массив растет в полтора раза, становится 15 элементов
            size = size + size/2;
            Integer[] newRepository = new Integer[size];
            Arrays.copyOf(repository, size);
            repository = newRepository;
        } */
        private void grow() {
            repository = Arrays.copyOf(repository, size + size/2);
        }

}
