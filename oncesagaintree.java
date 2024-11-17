import java.util.ArrayList;

class Node2 {
    int data;
    Node2 left;
    Node2 right;
    Node2 Parent;
    Node2(int data){
        this.data = data;
        this.left =null;
        this.right = null;
        this.Parent=null;
    }
}
class tree2{

    Node2 root ;
    tree2(int Rdata){

        root = new Node2(Rdata);
        //root.Paraent=root;
    }
    void addNode(int data){
        if (root == null) root = new Node2(data);
        else
            addrecursive(root,data);
    }
    void addrecursive(Node2 root, int data){

        if(data > root.data){
            if(root.right == null){
               root.right = new Node2(data);
               root.right.Parent = root;
            }
            else {
                addrecursive(root.right,data);
            }
        }
        else if (data < root.data){
            if(root.left ==null){

                root.left = new Node2(data);
                root.left.Parent=root;
            }
            else{
                addrecursive(root.left, data);
            }
        }

    }
    void POS(Node2 root){
        if(root != null) {
            System.out.print(root.data+"  ");
            POS(root.left);
            POS(root.right);
        }
    }
    Node2 findValue(Node2 root,double data ){
            //if found
            if(root.data == data){
                return root;
            }
            else if(data > root.data){
                if (root.right != null) {
                    return findValue(root.right, data);
                }
                return root;
            }
            else if (data < root.data){
                if(root.left != null) {
                    return findValue(root.left, data);
                }

            }return root;
    }
    void deletion(Node2 root , double data){
       Node2 value = findValue(root, data);

       // case 1 no right and left children
        if(value.left == null && value.right==null){
            if(value.data >= value.Parent.data){
                value.Parent.right = null;
            }
            if(value.data < value.Parent.data){
                value.Parent.left = null;
            }
        }
            //if noe has one child on its right
        else  if(value.left ==null && value.right !=null) {
                  // deleted value is in the left
                 // of parent node and having right child
                if(value.Parent.left ==value) {
                   value.Parent.left = value.right;
                }

                // deleted value is in the right of parent
                // node and having right child
                else if(value.Parent.right == value){
                        value.Parent.right = value.right;
                }

                     value.right.Parent = value.Parent;
        }
        //if noe has one child on its left
        else if(value.left!= null  && value.right==null){
                if  (value.Parent.left == value){
                     value.Parent.left = value.left;
                }
                else if(value.Parent.right ==value){
                        value.Parent.right = value.left;
                }
                value.left.Parent = value.Parent;
        }

        else {//if node has right and left child
                 Node2 findnext = leftdecendant(value.right);
                 value.data = findnext.data;
                 deletion(findnext, findnext.data);
        }


    }
    Node2 leftdecendant(Node2 root ){
        if(root.left==null){
            return root;
        }
        else {
            return leftdecendant(root.left);
        }
    }
    Node2 rightdecendant(Node2 root){
        //final Node2 now = root;
        if(root.Parent == null){
            return null;
        }
        if(root.Parent.data > root.data){
           return root.Parent;
       }

       else if (root.Parent.data < root.data){
           return rightdecendant(root.Parent);
       }
      return root;
    }
   Node2 Next (Node2 root){
     //  Node2 find  =  findValue(root , root.data);
        if(root.right != null){
          return  leftdecendant(root.right);
        }
        else if (root.right == null){
          return  rightdecendant(root);
        }
        return null;
    }
    void IOS1(Node2 CRoot){
        if(CRoot != null){
            IOS1(CRoot.left);
            System.out.print(CRoot.data+"  ");
            IOS1(CRoot.right);
        }
    }

    ArrayList<Integer> RangeSearch(int x , int y , Node2 root) {
        ArrayList<Integer> list = new ArrayList();
        Node2 range = findValue(root, x);
        while(range.data < y){
            if(range.data > x){
                list.add(range.data);
            }
            range = Next(range);

        }return list;
     }


}
public class oncesagaintree {
    public static void main(String args[]) {
        tree2 two = new tree2(40);
        two.addNode(35);
        two.addNode(50);
        two.addNode(30);
        two.addNode(36);
        two.addNode(45);
       // two.addNode(4);

        two.addNode(60);
        two.addNode(20);
        two.addNode(42);
        two.addNode(46);
        two.addNode(70);
        two.addNode(41);
        //two.POS(two.root);
        two.IOS1(two.root);
        System.out.println();
        two.deletion(two.root,45);
        two.IOS1(two.root);

    }
}
