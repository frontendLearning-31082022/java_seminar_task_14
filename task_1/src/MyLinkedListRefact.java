import java.util.NoSuchElementException;

public class MyLinkedListRefact extends MyLinkedList {
    private Size size=new Size();

    private class Size{
        MyLinkedList.Node lastNode=null;
        MyLinkedList.Node before=null;

        int size=1;
        public int getSize(MyLinkedList.Node head){
            if (lastNode==null)lastNode=head;
            if (before!=null? before.next!=lastNode : true)reCount();

            while (hasNext(lastNode)){
                size++;
                if (!hasNext(lastNode.next))before=lastNode;
                lastNode=lastNode.next;
            }
            return size;
        }

        void reCount(){
            lastNode=head;
            size=1;
            while (hasNext(lastNode)){
                size++;
                if (!hasNext(lastNode.next))before=lastNode;
                lastNode=lastNode.next;
            }
        }

        public void removedOne(Node lastNode) {
            size--;
            this.lastNode=lastNode;
        }
    }
    public int getSize(){
        return size.getSize(this.head);
    }
    public boolean hasNext(MyLinkedList.Node node){
        return node.next!=null;
    }


    boolean contains(int value){
        Node temp=this.head;
       while (temp.value!=value && hasNext(temp))temp=temp.next;

       return temp.next!=null;
    }

    int popLast() throws NoSuchElementException {
        if (this.head==null){
            throw new NoSuchElementException("EmptyList. Nothing to popLast");
        }

        if (this.head.next==null){
            int val=head.value;
            this.head=null;
            return val;
        }

        Node before=null;
        Node last=this.head;

        while (last.next!=null){before=last; last=last.next;}

        before.next=null;
        this.size.removedOne(before);

        return last.value;
    }

    public MyLinkedListRefact reversed(){
        MyLinkedListRefact newList=new MyLinkedListRefact();

        Node tempOLD=head;
        Node tempNew=new Node(head.value,null);

        while (hasNext(tempOLD)){
            Node second=new Node(tempOLD.next.value,tempNew);
            tempOLD=tempOLD.next;
            if (!hasNext(tempOLD))newList.head=second;
            tempNew=second;
        }

        return newList;
    }


    // педали
    @Override
    public int popFirst() {
        int res= super.popFirst();

        this.size.removedOne(this.size.lastNode);
        return res;
    }
    @Override
    public int pop(int index) {
        int res= super.pop(index);

        this.size.reCount();
        return res;
    }


}
