

public class ListNode<T> {
    private T value;
    private ListNode<T> next;

    public ListNode(T value, ListNode<T> next){
        this.value = value;
        this.next = next;
    }

    public ListNode(T ...values){
        if(values.length == 0) return;

        ListNode<T> node = this;
        for(int i = 0; i < values.length; i++){
            node.value = values[i];

            if(i + 1 < values.length){
                node.next = new ListNode<>(null, null);
            }

            node = node.next;
        }
    }

    //MODIFY THE LIST -----------------------------------------------------------

    public void pushR(T value){
        if(this.next == null) this.next = new ListNode<>(value, null);
        else this.next.pushR(value);
    }

    public void pushL(T value){
        ListNode<T> node = this;

        while(node.next != null) node = node.next;

        node.next = new ListNode<>(value, null);
    }

    public void shift(){
        if(this.next == null) return;
        this.value = this.next.value;
        this.next = this.next.next;
    }

    public void append(ListNode<T> listNode){
        ListNode<T> last = this;

        while(last.next != null) last = last.next;

        last.next = listNode;
    }

    public void insertBeforeL(T valueToInsert, T insertBefore){
        if(this.value.equals(insertBefore)){
            this.value = valueToInsert;
            this.next = new ListNode<>(insertBefore, this.next);
            return; // <---
        }

        ListNode<T> node = this;

        while(node.next != null){
            if(node.next.value.equals(insertBefore)){
                node.next = new ListNode<>(valueToInsert, node.next);
                return;
            }
            node = node.next;
        }
    }

    public void insertBeforeR(T valueToInsert, T insertBefore){
        if(this.value.equals(insertBefore)){
            this.value = valueToInsert;
            this.next = new ListNode<>(insertBefore, this.next);
        }
        else if(this.next != null) this.next.insertBeforeR(valueToInsert, insertBefore);  // <-- else if is important
    }

    public ListNode<T> removeR(T value){
        if(this.value.equals(value)) {
            if(this.next != null) return this.next.removeR(value); // <-- do not forget "next.removeR(value)" and "next != null"!
            return null;
        }
        if(this.next != null) this.next = this.next.removeR(value);

        return this;
    }

    public static <T> ListNode<T> removeL(T value, ListNode<T> listNode){
        ListNode<T> node = listNode;

        while (node.value.equals(value)) node = node.next;

        final ListNode<T> toReturn = node;

        while(node.next != null) {
            if(node.next.value.equals(value)){
                node.next = node.next.next;
            }else {  // <-- important: either or!
                node = node.next;
            }
        }

        return toReturn;
    }

    public void removeDuplicates (){
        //Shift and contains
        ListNode<T> node = this;

        while(node.next != null){
            if(node.next.containsL(node.value)){  // <-- "node".value
                node.shift();
            }else{
                node = node.next;
            }
        }
    }

    public void intersect (ListNode<T> nodeToIntersectWith){
        ListNode<T> node = this;

        while(node.next != null){
            if(nodeToIntersectWith.containsL(node.value) && !node.next.containsL(node.value) ){
                node = node.next;
            }else{
                node.shift();
            }
        }
    }

    public void unify (ListNode<T> nodeToUniteWith){
        ListNode<T> node = nodeToUniteWith;
        ListNode<T> myLast = this;

        this.removeDuplicates();

        while(myLast.next != null) myLast = myLast.next;

        while(node != null){
            if(!this.containsL(node.value)){
                myLast.next = new ListNode<>(node.value, null);
                myLast = myLast.next;
            }
            node = node.next;
        }
    }

    //GET INFORMATION ABOUT THE LIST AND ITS CONTENT ----------------------------------------

    public ListNode<T> lastR(){
        if(this.next == null) return this;
        return this.next.lastR();
    }

    public ListNode<T> lastL(){
        ListNode<T> last = this;
        while (last.next != null) last = last.next;
        return last;
    }

    public boolean containsR(T value){
        if(this.value.equals(value)) return true;
        if(this.next == null) return false;
        return this.next.containsR(value);
    }

    public boolean containsL(T value){
        ListNode<T> node = this;

        while(node != null){
            if(node.value.equals(value)) return true;
            node = node.next;
        }

        return false;
    }

    public ListNode<T> searchR (T value){
        if(this.value.equals(value)) return this;
        if(this.next == null) return null;
        return this.next.searchR(value);
    }

    public ListNode<T> searchL(T value){
        ListNode<T> node = this;
        while(node != null){
            if(node.value.equals(value)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public T middle(){
        ListNode<T> quickNode = this;
        ListNode<T> lazyNode = this;


        while(quickNode != null && quickNode.next != null){
            quickNode = quickNode.next.next;
            lazyNode = lazyNode.next;
        }

        return lazyNode.value;
    }


    public static Integer min(ListNode<Integer> node){
        Integer min = node.value;

        while(node != null) {
            min = Math.min(min, node.value);
            node = node.next;
        }

        return min;
    }

    public static <T> boolean hasLoopL(ListNode<T> node){
        ListNode<T> lazyNode = node;
        ListNode<T> quickNode = node;

        while(lazyNode.next != null && quickNode.next != null  && quickNode.next.next != null) {
            lazyNode = lazyNode.next;
            quickNode = quickNode.next.next;

            if(lazyNode == quickNode) return true;
        }

        return false;
    }

    // PERFORM OPERATIONS WITHOUT CHANGING THE ORIGINAL LIST --------------------------------------

    public static <T> ListNode<T> copyR(ListNode<T> nodeToCopy){
        if(nodeToCopy == null) return null;

        final ListNode<T> copyNode = new ListNode<>(nodeToCopy.value, null);

        if(nodeToCopy.next != null) copyNode.next = copyR(nodeToCopy.next);

        return copyNode;
    }

    public static <T> ListNode<T> copyL(ListNode<T> nodeToCopy){
        if(nodeToCopy == null) return null;
        ListNode<T> copyNode = new ListNode<>(null, null);
        ListNode<T> node = copyNode;

        while(nodeToCopy != null){
            node.value = nodeToCopy.value;
            if(nodeToCopy.next != null)
                node.next = new ListNode<>(null, null);

            nodeToCopy = nodeToCopy.next;
            node = node.next;
        }

        return copyNode;
    }

    public static <T> ListNode<T> copyWithPush(ListNode<T> nodeToCopy){
        if(nodeToCopy == null) return null;
        ListNode<T> newNode = new ListNode<>(nodeToCopy.value, null);

        nodeToCopy = nodeToCopy.next; // <-- important

        while(nodeToCopy != null){
            newNode.pushL(nodeToCopy.value);
            nodeToCopy = nodeToCopy.next;
        }

        return newNode;
    }

    public static <T> ListNode<T> reverse(ListNode<T> node){
        if(node == null) return null;
        ListNode<T> reversedList = null;

        while(node != null){
            final ListNode<T> copyNode = new ListNode<>(node.value, null);
            copyNode.next = reversedList;
            reversedList = copyNode;

            node = node.next;
        }

        return reversedList;
    }

    // FUNCTIONAL PROGRAMMING --------------------------------------------------------------

    public ListNode<T> filter (FilterLambda<T> lambda){
        ListNode<T> current = this;
        ListNode<T> node = new ListNode<>(null, null);
        final ListNode<T> filteredList = node;

        while(current != null){
            if(lambda.operate(current.value)){
                if(node.value == null) node.value = current.value;
                else {
                    node.next = new ListNode<>(current.value, null);
                    node = node.next;
                }
            }

            current = current.next;
        }

        return filteredList;
    }

    public <M> ListNode<M> map (MapLambda<T, M> lambda){
        ListNode<T> current = this.next;
        ListNode<M> node = new ListNode<>(lambda.operate(this.value), null);
        final ListNode<M> mappedList = node;

        while(current != null){
            node.next = new ListNode<>(lambda.operate(current.value), null);

            node = node.next;
            current = current.next;
        }

        return mappedList;
    }

    public <M> M reduce (ReduceLambda<T, M> lambda, M defaultValue){
        ListNode<T> current = this;
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

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public ListNode<T> getNext() {
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
