/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.structure;

import edd.proyecto2.model.Book;
import edd.proyecto2.node.LinkedStack;
import edd.proyecto2.node.NodeBook;
import java.util.Stack;

public class BTreeBook {

    // Each Btree object is a B-tree header.
    
    // This B-tree is represented as follows: arity is the maximum number
    // of children per node, and root is a link to its root node.
    
    // Each B-tree node is represented as follows: size contains its size; a 
    // subarray elems[0...size-1] contains its elements; and a subarray
    // childs[0...size] contains links to its child nodes. For each element 
    // elems[i], childs[i] is a link to its left child, and childs[i+1] is a 
    // link to its right child. In a leaf node, all child links are null.
    
    // Moreover, for every element x in the left subtree of element y:
    //    x.compareTo(y) < 0
    // and for every element z in the right subtree of element y:
    //    z.compareTo(y) > 0.
    
    private final int arity;
    private NodeBook root;
    private String content;

    public BTreeBook (int k) {
    // Construct an empty B-tree of arity k.
        root = null;
        arity = k;
    }

    public NodeBook search (Comparable target) {
    // Find which if any node of this B-tree contains an element equal to target. 
    // Return a link to that node, or null if there is no such node.
        if (root == null)
            return null;
        NodeBook curr = root;
        for (;;) {
            int pos = curr.searchInNode(target);
            if (target.equals(curr.elems[pos]))
                return curr;
            else if (curr.isLeaf())
                return null;
            else
                curr = curr.childs[pos];
        }
    }

    public void insert (Comparable elem) {
    // Insert element elem into this B-tree.
        if (root == null) {
            root = new NodeBook(arity, elem, null, null);
            return;
        }
        LinkedStack ancestors = new LinkedStack();
        NodeBook curr = root;
        for (;;) {
            int currPos = curr.searchInNode(elem);
            if (elem.equals(curr.elems[currPos]))
                return;
            else if (curr.isLeaf()) {
                curr.insertInNode(elem, null, null, currPos);
                if (curr.size == arity)  // curr has overflowed
                    splitNode(curr, ancestors);
                return;
            } else {
                ancestors.addLast(currPos);
                ancestors.addLast(curr);
                curr = curr.childs[currPos];
            }
        }
    }

    private void splitNode (NodeBook node, 
                            Stack ancestors) {
    // Split the overflowed node in this B-tree. The stack ancestors contains 
    // all ancestors of node, together with the known insertion position in each of 
    // these ancestors.
        int medPos = node.size/2;
        Comparable med = node.elems[medPos];
        NodeBook leftSib = new NodeBook(arity,
                node.elems, node.childs, 0, medPos);
        NodeBook rightSib = new NodeBook(arity,
                node.elems, node.childs, medPos+1, node.size);
        if (node == root)
            root = new NodeBook(arity, med, leftSib, 
                    rightSib);
        else {
            NodeBook parent =
                    (NodeBook) ancestors.pop();
            int parentIns = ((Integer)
                    ancestors.pop()).intValue();
            parent.insertInNode(med, leftSib, rightSib, 
                    parentIns);
            if (parent.size == arity)  // parent has overflowed
                splitNode(parent, ancestors);
        }
    }

    public void delete (Comparable elem) {
    // Delete element elem from this B-tree.
        if (root == null)
            return;
        LinkedStack ancestors = new LinkedStack();
        NodeBook curr = root;
        int currPos;
        for (;;) {
            currPos = curr.searchInNode(elem);
            if (elem.equals(curr.elems[currPos]))
                break;
            else if (curr.isLeaf())
                return;
            else {
                ancestors.push(currPos);
                ancestors.push(curr);
                curr = curr.childs[currPos];
            }
        }
        if (curr.isLeaf()) {
            curr.removeFromNode(currPos, currPos);
            if (underflowed(curr))
                restock(curr, ancestors);
        } else {
            NodeBook leftmostNode = findLeftmostNode(curr.childs[currPos+1], ancestors);
            Comparable nextElem = leftmostNode.elems[0];
            leftmostNode.removeFromNode(0, 0);
            curr.elems[currPos] = nextElem;
            if (underflowed(leftmostNode))
                restock(leftmostNode, ancestors);
        }
    }

    private void restock (NodeBook node,  Stack ancestors) {
    // Restock node, which has underflowed. 
    // The stack ancestors contains all ancestors of node, together 
    // with the child position in each of these ancestors.
        if (node == root) {  // node.size == 0
            root = node.childs[0];
            return;
        }
        NodeBook parent = (NodeBook)ancestors.peek();
        int childPos = 0;
        while (parent.childs[childPos] != node)  childPos++;
        int sibMinSize = (arity - 1)/2;
        if (childPos > 0 && parent.childs[childPos-1].size > sibMinSize) {
            NodeBook sib = parent.childs[childPos-1];
            Comparable parentElem = parent.elems[childPos-1];
            Comparable spareElem = sib.elems[sib.size-1];
            NodeBook spareChild = sib.childs[sib.size];
            sib.removeFromNode(sib.size-1, sib.size);
            node.insertInNode(parentElem, spareChild, node.childs[0], 0);
            parent.elems[childPos-1] = spareElem;
        } else if (childPos < parent.size && parent.childs[childPos+1].size > sibMinSize) {
            NodeBook sib = parent.childs[childPos+1];
            Comparable parentElem = parent.elems[childPos];
            Comparable spareElem = sib.elems[0];
            NodeBook spareChild = sib.childs[0];
            sib.removeFromNode(0, 0);
            node.insertInNode(parentElem, node.childs[node.size], spareChild, node.size);
            parent.elems[childPos] = spareElem;
        } else if (childPos > 0) {
            NodeBook sib = parent.childs[childPos-1];
            Comparable parentElem = parent.elems[childPos-1];
            node.coalesceLeft(sib, parentElem);
            parent.removeFromNode(childPos-1, childPos-1);
            if (underflowed(parent))
                restock(parent, ancestors);
        } else {  // childPos < parent.size
            NodeBook sib = parent.childs[childPos+1];
            Comparable parentElem = parent.elems[childPos];
            node.coalesceRight(parentElem, sib);
            parent.removeFromNode(childPos, childPos+1);
            if (underflowed(parent))
                restock(parent, ancestors);
        }
    }

    private void removeFromNode (NodeBook node, int elemPos, int childPos) {  // OBSOLETE
    // Remove from node the element whose index is elemPos, and the child 
    // whose index is childPos.
        for (int i = elemPos; i < node.size; i++)
            node.elems[i] = node.elems[i+1];
        if (! node.isLeaf()) {
            for (int i = childPos; i < node.size; i++)
                node.childs[i] = node.childs[i+1];
        }
        node.size--;
    }

    private NodeBook findLeftmostNode (NodeBook top, Stack ancestors) {
    // Return the leftmost leaf node in the subtree whose topmost node is top.
    // Push the node's ancestors on to the stack ancestors.
        NodeBook curr = top;
        while (! curr.isLeaf()) {
            ancestors.push(0);
            ancestors.push(curr);
            curr = curr.childs[0];
        }
        return curr;
    }

    private boolean underflowed (NodeBook node) {
    // Return true if and only if node has underflowed.
        int minSize = (node == root ? 1 : (arity - 1)/2);
        return (node.size < minSize);
    }
    
    //////////// Driver ////////////

    public void print () {
    // Print a textual representation of this B-tree.
        printSubtree(root, "");
    }

    private static void printSubtree (NodeBook top, String indent) {
    // Print a textual representation of the subtree of this B-tree whose
    // topmost node is top, indented by the string of spaces indent.
        if (top == null)
            System.out.println(indent + "o");
        else {
            System.out.println(indent + "o-->");
            boolean isLeaf = top.isLeaf();
            String childIndent = indent + "    ";
            for (int i = 0; i < top.size; i++) {
                if (! isLeaf)  printSubtree(top.childs[i], childIndent);
                if(top.elems[i] instanceof Book){
                Book book = (Book)top.elems[i];
                System.out.println(childIndent + book.getTitulo());
                }else if(top.elems[i] instanceof Integer){
                    System.out.println(childIndent + top.elems[i]);
                }
            }
            if (! isLeaf)  printSubtree(top.childs[top.size], childIndent);
        }
    }
    
    public String printReport(){
        content = "";
        return printTree(this.root);
    }
    public String printTree (NodeBook top) {
        if(top==null){
            System.out.println("El arbol esta vacio");
        }else{
            content += "node" + top.hashCode() + "[ label = \""; 
            int elements = getSizeChildren(top.elems);
                for (Comparable elem : top.elems) {
                    if(elem instanceof Book){
                        Book b = (Book)elem;
                        if(elements!=1){
                            content += b.getTitulo()+ " /n" + b.getISBN() + " | ";
                            elements--;
                        }
                        else
                            content += b.getTitulo() + " /n"+ b.getISBN() + " ";
                    }else if(elem instanceof Integer){
                        if(elements!=1){
                            content += elem + " | ";
                            elements--;
                        }else
                            content += elem;
                    }              
                }
                content+="\" ]; \n";
                for(NodeBook elem: top.childs){
                    if(elem!=null){
                        printTree(elem);
                        content += "node" + top.hashCode() + "->" + "node" + elem.hashCode() + "; \n";
                    }
               }
        }
        return content;
    }
    
    public int getSizeChildren(Comparable[] elems){
        int tamanio = 0;
        for(Comparable e: elems){
            if(e instanceof Book){
                if(e!=null){
                    tamanio++;
                }
            }else if(e instanceof Integer){
                Integer o = (Integer)e;
                if(o != 0){
                    tamanio++;
                }
            }
        }
        return tamanio;
    }
}