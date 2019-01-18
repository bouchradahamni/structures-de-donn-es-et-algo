import java.util.*; 

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType>
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType [ ] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( boolean min )
    {
		this.min = min;
		currentSize = 0;
		array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( AnyType[] items, boolean min )
    {
    	this.min = min;
    	array = (AnyType[]) new Comparable[ items.length + 1];
    	currentSize = items.length;
    	
    	int i = 1; // car l'index commence a 1 ,comment je vais faire le décalage ???//
    	for( AnyType item : items ) {
            array[ i++ ] = item;
    	}
    	if(min) { 
    		buildMinHeap();
    	}
    	else {   
    		buildMaxHeap();
    	}
        // COMPLETEZ
    	// invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
    }
    
    public boolean offer( AnyType x )
    {
    	if (x == null) {
    	    throw new NullPointerException("Cannot insert null in a BinaryHeap");
    	}
    	else {
	    	if( currentSize + 1 == array.length )
	    	    doubleArray();
	    	
	    	int hole = ++currentSize;
	    	if(min) {
	    		for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
	    			array[ hole ] = array[ hole / 2 ];
	    	}else {
	    		for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) > 0; hole /= 2) 
		            array[ hole ] = array[ hole / 2 ];
	    	}
		    array[ hole ] = x;
	        modifications++ ;
	        return true ; 	
	    }
    }
    
    public AnyType peek()
    {
		if(!isEmpty())
		    return array[1];
		
		return null;
    }
    
    public AnyType poll()
    {
    	if(!isEmpty()) {
    	       modifications++;
    	      if(min) {
    	    	AnyType minItem = peek();
    	    	swapReferences( 1,currentSize--);
    	    	percolateDownMinHeap( 1,currentSize);
    	    	return minItem;
    	       }
    	       else {
    	    	AnyType maxItem = peek();
    	    	swapReferences( 1,currentSize--);
    	    	percolateDownMaxHeap( 1,currentSize);
    	    	return maxItem;
    	    			
    	    	}
    	      }
    	      else 
    	    	  return null;
    		//COMPLETEZ
    }
    
    public Iterator<AnyType> iterator()
    {
    	return (Iterator<AnyType>)new HeapIterator();
    
    }
    
    private void buildMinHeap()
    {
    	//COMPLETEZ
    	for(int i = currentSize/2 ; i > 0 ; i-- ) {
    		percolateDownMinHeap(array, i, currentSize + 1, true );
    	}
    }
    
    private void buildMaxHeap()
    {
    	//COMPLETEZ
    	for(int i = currentSize/2 ; i > 0 ; i-- ) {
    		percolateDownMaxHeap(array, i, currentSize + 1, true );
       }
    }
    
    public boolean isEmpty()
    {
    	return currentSize == 0;
    }
    
    public int size()
    {
    	return currentSize;
    }
    
    public void clear()
    {
		currentSize = 0;
		modifications = 0;
		array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    private static int leftChild( int i, boolean heapIndexing )
    {
    	return ( heapIndexing ? 2*i : 2*i+1 );
    }
    
    private void swapReferences( int index1, int index2 )
    {
    	swapReferences(array, index1, index2);
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
				    void swapReferences( AnyType[] array, int index1, int index2 )
    {
		AnyType tmp = array[ index1 ];
		array[ index1 ] = array[ index2 ];
		array[ index2 ] = tmp;
    }
    
    @SuppressWarnings("unchecked")
	private void doubleArray()
    {
		AnyType [ ] newArray;
		
		newArray = (AnyType []) new Comparable[ array.length * 2 ];
		for( int i = 0; i < array.length; i++ )
		    newArray[ i ] = array[ i ];
		array = newArray;
    }
    
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMinHeap( int hole, int size )
    {
    	percolateDownMinHeap(array, hole, size, true);
    }
    
    /**
     * @param array   Tableau d'element
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
				    void percolateDownMinHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
    	//COMPLETEZ À VÉRIFIER
	    	
    		int enfant = 0;
	    	AnyType tmp = array[hole];
	    	for(tmp = array[hole]; leftChild(hole, heapIndexing) < size ; hole = enfant) {
	    		enfant = leftChild(hole, heapIndexing);
	    		if(enfant != size -1 && array[enfant + 1].compareTo(array[enfant]) < 0)
	    			enfant ++;
	    		if(array[enfant].compareTo(tmp) < 0)
	    			array[ hole ]= array[enfant];
	    		else
	    			break;
	    	}
	    	array[hole] = tmp;
    }
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMaxHeap( int hole, int size )
    {
    	percolateDownMaxHeap(array, hole, size, true);
    }
    
    /**
     * @param array         Tableau d'element
     * @param hole          Position a percoler
     * @param size          Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>> 
				    void percolateDownMaxHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
    	//COMPLETEZ
    	
    	int enfant = 0;
    	AnyType tmp = array[hole];
    	for(tmp = array[hole]; leftChild(hole, heapIndexing) < size ; hole = enfant) {
    		enfant = leftChild(hole, heapIndexing);
    		if(enfant != size -1 && array[enfant + 1].compareTo(array[enfant]) > 0)
    			enfant ++;
    		if(array[enfant].compareTo(tmp) > 0)
    			array[ hole ]= array[enfant];
    		else
    			break;
    	}
    	array[hole] = tmp;
    	 
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSort( AnyType[] a )
    {
    	//COMPLETEZ
	    	for(int i = a.length/2; i >= 0; i--)
	    		percolateDownMaxHeap(a,i,a.length , true);
	    	for(int i = a.length - 1; i > 0; i--) {
	    		swapReferences(a,0,i);
	    		percolateDownMaxHeap(a,0,i,true);
	    	}
    		
    }
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSortReverse( AnyType[] a )
    {
    	//COMPLETEZ
	    	for(int i = a.length/2; i >= 0; i--)
	    		percolateDownMinHeap(a,i,a.length , true);
	    	for(int i = a.length - 1; i > 0; i--) {
	    		swapReferences(a,0,i);
	    		percolateDownMinHeap(a,0,i,true);
	    	}
    }
    
    public String nonRecursivePrintFancyTree()
    {
    	  int noeud = 1;
    	  String outputString = "";  
    	  StringBuilder sb = new StringBuilder();
          Stack<Integer> tmp = new Stack<Integer>(); 

          tmp.push(noeud);
          
          for(;;)
          { 
        	  boolean estVide = tmp.empty();
        	  if(estVide)
        		  break;
        	  Vector<Boolean> estBrancheGauche = new Vector<Boolean>(); 
        	  noeud = tmp.pop();
             
        	  if(2 * noeud <= currentSize)
        	  {
        		  tmp.push(leftChild(noeud, true) + 1); 
        		  tmp.push(leftChild(noeud, true));
        	  }
             
        	  for(int k = noeud; k > 0; k /= 2)
        	  {
        		  if(k % 2 == 0)
        			  estBrancheGauche.add(true);
        		  else
        			  estBrancheGauche.add(false);
        	  }
             
        	  for(int k = estBrancheGauche.size() - 1; k > 0; k--)
        	  {
        		  if(estBrancheGauche.get(k))
        			  sb.append("|  ");
        		  else
        			  sb.append("   ");
              }
              
              if(noeud <= currentSize)
                 sb.append( "|__" + array[noeud] + "\n");
              else 
                 sb.append( "|__" + "null" + "\n");
           }
          outputString += sb;
          return outputString;
    }
    
    public String printFancyTree()
    {
    	return printFancyTree(1, "");
    }
    
    private String printFancyTree( int index, String prefix)
    {
    	String outputString = "";
	
    	outputString = prefix + "|__";
	
    	if( index <= currentSize )
		    {
			boolean isLeaf = index > currentSize/2;
			
			outputString += array[ index ] + "\n";
			
			String _prefix = prefix;
			
			if( index%2 == 0 )
			    _prefix += "|  "; // un | et trois espace
			else
			    _prefix += "   " ; // quatre espaces
			
			if( !isLeaf ) {
			    outputString += printFancyTree( 2*index, _prefix);
			    outputString += printFancyTree( 2*index + 1, _prefix);
			}
		    }
		else
		    outputString += "null\n";
		
		return outputString;
    }
    
    private class HeapIterator implements Iterator {
    	int index  = 1;
    	int nbModifications = modifications;
	public boolean hasNext() {
	    //COMPLETEZ
		
		if (this.index  <= currentSize)
			return true;
		return false;
	}

	public Object next() throws NoSuchElementException, 
				    ConcurrentModificationException, 
				    UnsupportedOperationException {
	    //COMPLETEZ
		 /*Iterator<AnyType> itterateur = iterator();*/
		 if(nbModifications != modifications)
				throw new ConcurrentModificationException("Modification du monceau en cours d'itération");
		 
		/*if (nbModifications != modifications)
    		throw new ConcurrentModificationException("Modification du monceau en cours d'itération");*/
		if(!hasNext()) {
			 throw new UnsupportedOperationException();
		}
		return array[index++];
	}
	
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
}
 
 
    	
    	
    	
 
