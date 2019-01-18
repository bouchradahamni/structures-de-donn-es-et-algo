import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable<? super T> > {

    private BinaryNode<T> root;   // to store the adress of root node;

    public BinarySearchTree() { }

    // TODO: initialisation
    public BinarySearchTree(T item) {
           root = new BinaryNode<T> (item); // j'ai intialisé my root
    }

    // TODO: on insere un nouvel item a partir de la racine
    // O(log(n))
    public void insert(T item) {
    	if(root != null)
          root.insert(item);
    	else 
    		root = new BinaryNode<T> (item);
    }

    // TODO: est-ce qu'un item fais partie de l'arbre
    // O(log(n))
    public boolean contains(T item) {
        if(root != null) 
    		 return root.contains(item);
        else
        	 return false;
    }

    // TODO: trouver la hauteur de l'arbre
    // O(n)
    public int getHeight() {
    	if(root != null)
          return root.getHeight();
    	else 
    		return 0;
    }

    // TODO: placer dans une liste les items de l'arbre en ordre
    // O(n)
    public List<BinaryNode<T>> getItemsInOrder() {
    if(root != null) {
      List<BinaryNode<T>> liste = new ArrayList<BinaryNode<T>>();
      root.fillListInOrder(liste);
      return liste ;
     }
    else {
    	  List<BinaryNode<T>> liste = new ArrayList<BinaryNode<T>>();
          return liste;
    }
    	
    
      
      
    } 

    // TODO: retourner la liste d'item en String selon le bon format
    // O(n)
    public String toStringInOrder() {
    	 List<BinaryNode<T>> liste = this.getItemsInOrder();
    	String a = "[";
    	for(int i = 0 ; i < this.getItemsInOrder().size(); i++) {
    		a = a+ liste.get(i).getData() + ", " ;
    	}
    	/* methode substring*/
    	a = a.substring(0, a.lastIndexOf(","));
        a = a + ']';
        return a ;
 
    }
}