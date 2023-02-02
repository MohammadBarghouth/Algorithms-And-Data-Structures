

public class List<T> {
    private T value;
    private List<T> next;

    public static void main(String[] args) {
        List<Integer> l = new List<>(1,1,1,1,2,35,6,9,9,9,4,9,5,67,7,7,7);
       // System.out.println(removeDuplicatesR(l));
       // System.out.println(insertBeforeRS(l,10,1 ));
        //System.out.println(removeRS(l,9));
        // System.out.println(copyRS(l));
        System.out.println(pushRS(l, 3));
    }

    public List(T value, List<T> next){
        this.value = value;
        this.next = next;
    }

    public List(T ...values){
        if(values.length == 0) return;

        List<T> node = this;
        for(int i = 0; i < values.length; i++){
            node.value = values[i];

            if(i + 1 < values.length){
                node.next = new List<>(null, null);
            }

            node = node.next;
        }
    }

    //MODIFY THE LIST -----------------------------------------------------------

    public void pushR(T value){
        if(this.next == null) this.next = new List<>(value, null);
        else this.next.pushR(value);
    }

    public void pushL(T value){
        List<T> node = this;

        while(node.next != null) node = node.next;

        node.next = new List<>(value, null);
    }

    public void shift(){
        if(this.next == null) return;
        this.value = this.next.value;
        this.next = this.next.next;
    }

    public void append(List<T> listNode){
        List<T> last = this;

        while(last.next != null) last = last.next;

        last.next = listNode;
    }

    public void insertBeforeL(T valueToInsert, T insertBefore){
        if(this.value.equals(insertBefore)){
            this.value = valueToInsert;
            this.next = new List<>(insertBefore, this.next);
            return; // <---
        }

        List<T> node = this;

        while(node.next != null){
            if(node.next.value.equals(insertBefore)){
                node.next = new List<>(valueToInsert, node.next);
                return;
            }
            node = node.next;
        }
    }

    public void insertBeforeR(T valueToInsert, T insertBefore){
        if(this.value.equals(insertBefore)){
            this.value = valueToInsert;
            this.next = new List<>(insertBefore, this.next);
        }
        else if(this.next != null) this.next.insertBeforeR(valueToInsert, insertBefore);  // <-- else if is important
    }

    public static <T> List<T> insertBeforeRS(List<T> node, T valueToInsert, T insertBefore){
        if(node.value.equals(insertBefore)) return new List<>(valueToInsert, node);
        if(node.next == null) return node;

        node.next = insertBeforeRS(node.next, valueToInsert, insertBefore);
        return node;
    }

    public List<T> removeR(T value){
        if(this.value.equals(value)) {
            if(this.next != null) return this.next.removeR(value); // <-- do not forget "next.removeR(value)" and "next != null"!
            return null;
        }
        if(this.next != null) this.next = this.next.removeR(value);

        return this;
    }

    public static <T> List<T> removeRS(List<T> node, T value){
        if(node == null) return null;
        if(node.value.equals(value)) return removeRS(node.next, value);

        if(node.next != null) node.next = removeRS(node.next,value);
        return node;
    }

    public static <T> List<T> removeL(T value, List<T> listNode){
        List<T> node = listNode;

        while (node.value.equals(value)) node = node.next;

        final List<T> toReturn = node;

        while(node.next != null) {
            if(node.next.value.equals(value)){
                node.next = node.next.next;
            }else {  // <-- important: either or!
                node = node.next;
            }
        }

        return toReturn;
    }

    public static <T> void removeDuplicates (List<T> node){
        //Shift and contains
        while(node.next != null){
            if(node.next.containsL(node.value)){  // <-- "node".value
                node.shift();
            }else{
                node = node.next;
            }
        }
    }

    public static <T> List<T> removeDuplicatesR (List<T> node){
        if(node == null) return null;
        if(node.next == null) return node;
        if(node.next.containsR(node.value)) return removeDuplicatesR(node.next); // do not forget removeDuplicatesR !!
        node.next = removeDuplicatesR(node.next);
        return node;

    }

    public void intersect (List<T> nodeToIntersectWith){
        List<T> node = this;

        while(node.next != null){
            if(nodeToIntersectWith.containsL(node.value) && !node.next.containsL(node.value) ){
                node = node.next;
            }else{
                node.shift();
            }
        }
    }

    public void unify (List<T> nodeToUniteWith){
        List<T> node = nodeToUniteWith;
        List<T> myLast = this;

        removeDuplicates(this);

        while(myLast.next != null) myLast = myLast.next;

        while(node != null){
            if(!this.containsL(node.value)){
                myLast.next = new List<>(node.value, null);
                myLast = myLast.next;
            }
            node = node.next;
        }
    }

    //GET INFORMATION ABOUT THE LIST AND ITS CONTENT ----------------------------------------

    public List<T> lastR(){
        if(this.next == null) return this;
        return this.next.lastR();
    }

    public static <T> List<T> lastRS(List<T> node){
        if(node == null) return null;
        if(node.next == null) return node;
        return lastRS(node.next);
    }

    public List<T> lastL(){
        List<T> last = this;
        while (last.next != null) last = last.next;
        return last;
    }

    public boolean containsR(T value){
        if(this.value.equals(value)) return true;
        if(this.next == null) return false;
        return this.next.containsR(value);
    }

    public boolean containsL(T value){
        List<T> node = this;

        while(node != null){
            if(node.value.equals(value)) return true;
            node = node.next;
        }

        return false;
    }

    public List<T> searchR (T value){
        if(this.value.equals(value)) return this;
        if(this.next == null) return null;
        return this.next.searchR(value);
    }

    public List<T> searchL(T value){
        List<T> node = this;
        while(node != null){
            if(node.value.equals(value)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public T middle(){
        List<T> quickNode = this;
        List<T> lazyNode = this;


        while(quickNode != null && quickNode.next != null){
            quickNode = quickNode.next.next;
            lazyNode = lazyNode.next;
        }

        return lazyNode.value;
    }


    public static Integer min(List<Integer> node){
        Integer min = node.value;

        while(node != null) {
            min = Math.min(min, node.value);
            node = node.next;
        }

        return min;
    }

    public static <T> boolean hasLoopL(List<T> node){
        List<T> lazyNode = node;
        List<T> quickNode = node;

        while(lazyNode.next != null && quickNode.next != null  && quickNode.next.next != null) {
            lazyNode = lazyNode.next;
            quickNode = quickNode.next.next;

            if(lazyNode == quickNode) return true;
        }

        return false;
    }

    // PERFORM OPERATIONS WITHOUT CHANGING THE ORIGINAL LIST --------------------------------------

    public static <T> List<T> copyRS(List<T> nodeToCopy){
        if(nodeToCopy == null) return null;

        final List<T> copy = new List<>(nodeToCopy.value, null);
        copy.next = copyRS(nodeToCopy.next);
        return copy;
    }

    public static <T> List<T> copyL(List<T> nodeToCopy){
        if(nodeToCopy == null) return null;
        List<T> copyNode = new List<>(null, null);
        List<T> node = copyNode;

        while(nodeToCopy != null){
            node.value = nodeToCopy.value;
            if(nodeToCopy.next != null)
                node.next = new List<>(null, null);

            nodeToCopy = nodeToCopy.next;
            node = node.next;
        }

        return copyNode;
    }

    public static <T> List<T> pushRS(List<T> node, T value) {
        if(node == null) return new List<T>(value);

        return new List<>(
                node.value,
                pushRS(node.next, value)
        );
    }

    public static <T> List<T> copyWithPushLS(List<T> nodeToCopy){
        if(nodeToCopy == null) return null;
        List<T> newNode = new List<>(nodeToCopy.value, null);

        nodeToCopy = nodeToCopy.next; // <-- important

        while(nodeToCopy != null){
            newNode.pushL(nodeToCopy.value);
            nodeToCopy = nodeToCopy.next;
        }

        return newNode;
    }

    public static <T> List<T> reverse(List<T> node){
        List<T> reversedList = null;

        while(node != null){
            reversedList = new List<>(node.value, reversedList);
            node = node.next;
        }

        return reversedList;
    }

    // FUNCTIONAL PROGRAMMING --------------------------------------------------------------

    public List<T> filter (FilterLambda<T> lambda){
        List<T> current = this;
        List<T> node = new List<>(null, null);
        final List<T> filteredList = node;

        while(current != null){
            if(lambda.operate(current.value)){
                if(node.value == null) node.value = current.value;
                else {
                    node.next = new List<>(current.value, null);
                    node = node.next;
                }
            }

            current = current.next;
        }

        return filteredList;
    }

    public <M> List<M> map (MapLambda<T, M> lambda){
        List<T> current = this.next;
        List<M> node = new List<>(lambda.operate(this.value), null);
        final List<M> mappedList = node;

        while(current != null){
            node.next = new List<>(lambda.operate(current.value), null);

            node = node.next;
            current = current.next;
        }

        return mappedList;
    }

    public <M> M reduce (ReduceLambda<T, M> lambda, M defaultValue){
        List<T> current = this;
        M acc = defaultValue;

        while(current != null){
            acc = lambda.operate(acc, current.value);
            current = current.next;
        }

        return acc;
    }


    @Override
    public String toString(){
        return this.value + ", " + (this.next != null? this.next.toString(): "");
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(List<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public List<T> getNext() {
        return next;
    }
}

interface FilterLambda<T>{
    boolean operate (T value);
}

interface MapLambda<T, M>{
    M operate (T value);
}

interface ReduceLambda<T, M>{
    M operate (M acc, T value);
}
