import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType> {
    static int p = 46337;

    QuadraticSpacePerfectHashing<AnyType>[] data;
    int a, b;

    LinearSpacePerfectHashing() {
        a = b = 0;
        data = null;
    }

    LinearSpacePerfectHashing(ArrayList<AnyType> array) {
        AllocateMemory(array);
    }

    public void SetArray(ArrayList<AnyType> array) {
        AllocateMemory(array);
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
            return;
        }

        // A completer
        a = generator.nextInt(p - 1) + 1;
        b = generator.nextInt(p);
        int m = array.size();
        data = new QuadraticSpacePerfectHashing[m];
        for (int i = 0; i < m; i++) {
            int cle = getKey(array.get(i));
            ArrayList<AnyType> al = new ArrayList<AnyType>();
            if (containsKey(cle))
                for (int j = 0; j < data[cle].items.length; j++) {
                    if (data[cle].items[j] != null)
                        al.add(data[cle].items[j]);
                }
            al.add(array.get(i));
            data[cle] = new QuadraticSpacePerfectHashing<AnyType>(al);
        }
    }

    public int Size() {
        if (data == null)
            return 0;

        int size = 0;
        for (int i = 0; i < data.length; ++i) {
            size += (data[i] == null ? 1 : data[i].Size());
        }
        return size;
    }

    public boolean containsKey(int key) {
        // A completer
        return data[key] == null ? false : true;
    }

    public int getKey(AnyType x) {
        // A completer
        int y = ((a * x.hashCode() + b) % p);
        return y % data.length;
    }

    public boolean containsValue(AnyType x) {
        // A completer
        if (data[getKey(x)] == null || !data[getKey(x)].containsValue(x))
            return false;
        return true;
    }

    public void remove(AnyType x) {
        // A completer
        data[getKey(x)].remove(x);
    }

    public String toString() {
        String res = "";
        // A completer
        for (int i = 0; i < data.length; i++) {
            if (containsKey(i)) {
                res += "[";
                res += i + "] -> ";
                res += data[i].toString() + "\n";
            }
        }
        return res;
    }

    public void makeEmpty() {
        // A completer
        data = null;
    }

}
