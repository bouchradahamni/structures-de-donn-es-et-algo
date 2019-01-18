import java.util.List;

public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
        money = data;
        childs = null;
        worstChild = this; // car elle n'a rien encore acheté //
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
    	if(childs == null) {
    		childs = new BinarySearchTree<>(item);
    	}
    	else {
             childs.insert(item);
    	}
    	money += item.getMoney();
    	if(worstChild.getMoney() > item.worstChild.getMoney()) {
    		worstChild = item.worstChild;
    	}
    }

    // TODO: on retourne le montant en banque de la compagnie
    // O(1)
    public Integer getMoney() {
        return money;
    }

    // TODO: on rempli le builder de la compagnie et de ses enfants avec le format
    //A
    // > A1
    // > A2
    // >  > A21...
    // les enfants sont afficher du plus grand au plus petit (voir TestCompany.testPrint)
    // O(n)
    public void fillStringBuilderInOrder(StringBuilder builder, String prefix) {
    	
    	builder.append(prefix);
    	builder.append(getMoney() +"\n");
		
    	if(childs != null) {
    		List<BinaryNode<CompanyNode>> liste = childs.getItemsInOrder();
    		for(int i = liste.size() - 1; i>= 0; i--) {
    	    	liste.get(i).getData().fillStringBuilderInOrder(builder, prefix + " > ");
    		}
    }
}

    // TODO: on override le comparateur pour defenir l'ordre
    @Override
    public int compareTo(CompanyNode item) {
    	return money - item.getMoney(); // on compare money avec item get money //
    }
}
