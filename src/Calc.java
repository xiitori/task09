import util.JTableUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Calc {
    public static List<Integer> createNewList(List<Integer> list) {
        List<Integer> array = new ArrayList<Integer>(list);
        combSort(array);
        deleteSame(array);
        List<Integer> current = new ArrayList<Integer>();
        current.add(array.get(0));
        List<Integer> max = new ArrayList<Integer>();
        max.add(array.get(0));
        for(int i = 1; i < array.size(); i++) {
            if(array.get(i - 1) + 1 == array.get(i)) {
                current.add(array.get(i));
                if(current.size() > max.size()) {
                    max.clear();
                    max.addAll(current);
                }
            } else {
                current.clear();
                current.add(array.get(i));

            }
        }
        return max;
    }
    public static void combSort(List<Integer> list) {
        double factor = 1.247;
        int step = (int) (list.size() - 1 / factor);
        while(step >= 1) {
            for(int i = 0; i + step < list.size(); i++) {
                if(list.get(i) > list.get(i + step)) {
                    int x = list.get(i);
                    list.set(i, list.get(i + step));
                    list.set(i + step, x);
                }
            }
            step /= factor;
        }
    }

    public static void deleteSame(List<Integer> list) {
        while(true) {
            int cnt = 0;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1) == list.get(i)) {
                    list.remove(i);
                    cnt++;
                }
            }
            if(cnt == 0) {
                break;
            }
        }
    }
}