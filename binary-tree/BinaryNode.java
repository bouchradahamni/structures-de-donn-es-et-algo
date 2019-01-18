import java.util.List;

public class BinaryNode<T extends Comparable<? super T>> {
	private T data;
	private BinaryNode<T> right;
	private BinaryNode<T> left;

	// TODO: initialisation
	// O(1)
	// data la donne du prblm
	//
	public BinaryNode(T data) {
		this.data = data; // constructeur
		right = null;
		left = null;

	}

	// TODO: on retourne la donnee voulue
	// O(1)
	public T getData() {
		return data;
	}

	// TODO: on ajoute une nouvelle donnee au bon endroit
	// O(log(n))
	public void insert(T item) {
		int compareResult = item.compareTo(data);
		if (compareResult < 0 || compareResult == 0) {
			if (this.left == null)
				left = new BinaryNode<T>(item);
			else
				left.insert(item);
		} else {
			if (this.right == null)
				right = new BinaryNode<T>(item);
			else
				right.insert(item);

		}
	}

	// TODO: est-ce que l'item fais partie du noeuds courant
	// O(log(n))
	public boolean contains(T item) {
		int compareResult = item.compareTo(data);
		if (compareResult < 0) {
			if (this.left == null)
				return false;
			else
				return left.contains(item);
		} else if (compareResult > 0) {
			if (this.right == null)
				return false;
			else
				return right.contains(item);
		} else {
			return true;
		}
	}

	// TODO: on retourne la maximale de l'arbre
	// O(n)
	public int getHeight() {
		if (left == null && right == null) {
			return 0;
		}
		int hightleft = 0;
		int heightright = 0;
		if (left != null) {
			hightleft = left.getHeight();
		}
		if (right != null) {
			heightright = right.getHeight();
		}
		return 1 + Math.max(hightleft, heightright);
	}

	// TODO: l'ordre d'insertion dans la liste est l'ordre logique
	// de manière que le plus petit item sera le premier inseré
	// O(n)
	public void fillListInOrder(List<BinaryNode<T>> result) {
		if (left != null) {
			// result.add(left);
			left.fillListInOrder(result);
		}
		result.add(this);
		if (right != null) {
			right.fillListInOrder(result);
		}
	}
}
