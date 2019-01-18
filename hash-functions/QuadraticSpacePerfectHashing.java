import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> {
    static int p = 46337;

    int a, b;
    AnyType[] items;

    QuadraticSpacePerfectHashing() {
        a = b = 0;
        items = null;
    }

    QuadraticSpacePerfectHashing(ArrayList<AnyType> array) {
        AllocateMemory(array);
    }

    public void SetArray(ArrayList<AnyType> array) {
        AllocateMemory(array);
    }

    public int Size() {
        if (items == null)
            return 0;

        return items.length;
    }

    public boolean containsKey(int key) {
        // A completer
        return items[key] != null;
    }

    public boolean containsValue(AnyType x) {
        // A completer
        return items[getKey(x)] != null && items[getKey(x)].equals(x);

    }

    public void remove(AnyType x) {
        // A completer
        if (x != null)
            items[getKey(x)] = null;
    }

    public int getKey(AnyType x) {
        // A completer
        int y = ((a * x.hashCode() + b) % p);
        return y % items.length;
    }

    @SuppressWarnings("unchecked")
    private void AllocateMemory(ArrayList<AnyType> array) {
        Random generator = new Random(System.nanoTime());

        if (array == null || array.size() == 0) {
            // A completer
            return;
        }
        if (array.size() == 1) {
            a = b = 0;
            // A completer
            items = (AnyType[]) new Object[1];
            items[0] = array.get(0);
            return;
        }

        // A completer
        a = generator.nextInt(p - 1) + 1;
        b = generator.nextInt(p);
        int n = array.size();
        int m = n * n;
        items = (AnyType[]) new Object[m];
        for (int i = 0; i < n;) {
            int key = getKey(array.get(i));
            if (containsKey(key)) {
                makeEmpty();
                i = 0;
                a = generator.nextInt(p - 1) + 1;
                b = generator.nextInt(p);
            } else
                items[key] = array.get(i++);
        }
    }

    public String toString() {
        String result = "";

        for (AnyType item : items) {
            if (item != null) {
                result += "(" + getKey(item) + ", " + item + ") ";
            }
        }
        return result;
    }

    public void makeEmpty() {
        for (AnyType item : items)
            remove(item);
    }
}
