/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.node;

/**
 *
 * @author Omar
 */
public class NodeBook {

        // Each Node object is a B-tree node.

        public int size;
        public Comparable[] elems;
        public NodeBook[] childs;

        public NodeBook (int k, Comparable elem,
                    NodeBook left, NodeBook right) {
        // Construct a B-tree node of arity k, initially with one element, elem,
        // and two children, left and right.
            elems = new Comparable[k];
            childs = new NodeBook[k+1];
            // ... Each array has one extra component, to allow for possible 
            // overflow.
            this.size = 1;
            this.elems[0] = elem;
            this.childs[0] = left;
            this.childs[1] = right;
        }

        public NodeBook (int k, Comparable[] elems,
                            NodeBook[] childs, int l, int r) {
        // Construct a B-tree node of arity k, with its elements taken from the
        // subarray elems[l...r-1] and its children from the subarray
        // childs[l...r].
            this.elems = new Comparable[k];
            this.childs = new NodeBook[k+1];
            this.size = 0;
            for (int j = l; j < r; j++) {
                this.elems[this.size] = elems[j];
                this.childs[this.size] = childs[j];
                this.size++;
            }
            this.childs[this.size] = childs[r];
        }

        public boolean isLeaf () {
        // Return true if and only if this node is a leaf.
            return (childs[0] == null);
        }

        public int searchInNode (Comparable target) {
        // Return the index of the ltargeteftmost element in this node that is not less
        // than target.
            int l = 0, r = size-1;
            while (l <= r) {
                int m = (l + r)/2;
                int comp = target.compareTo(elems[m]);
                if (comp == 0)
                    return m;
                else if (comp < 0)
                    r = m - 1;
                else
                    l = m + 1;
            }
            return l;
        }

        public void insertInNode (Comparable elem,
                                NodeBook leftChild,
                                NodeBook rightChild,
                                int ins) {
        // Insert element elem, with children leftChild and rightChild, at 
        // position ins in this node.
            for (int i = size; i > ins; i--) {
                elems[i] = elems[i-1];
                childs[i+1] = childs[i];
            }
            size++;
            elems[ins] = elem;
            childs[ins] = leftChild;
            childs[ins+1] = rightChild;
        }

        public void coalesceLeft (NodeBook that, Comparable elem) {
        // Insert all that node's elements and children, followed by elem,
        // as the leftmost elements and children of this node.
            System.arraycopy(this.elems, 0, this.elems, that.size + 1, this.size);
            System.arraycopy(this.childs, 0, this.childs, that.size + 1, this.size + 1);
            System.arraycopy(that.elems, 0, this.elems, 0, that.size);
            this.elems[that.size] = elem;
            System.arraycopy(that.childs, 0, this.childs, 0, that.size + 1);
            this.size += that.size + 1;
         }

        public void coalesceRight (Comparable elem, NodeBook that) {
        // Insert all that node's elements and children, preceded by elem,
        // as the rightmost elements and children of this node.
            this.elems[this.size] = elem;
            System.arraycopy(that.elems, 0, this.elems, this.size + 1, that.size);
            System.arraycopy(that.childs, 0, this.childs, this.size + 1, that.size + 1);
            this.size += that.size + 1;
         }

        public void removeFromNode (int elemPos, int childPos) {
        // Remove from this node the element at position elemPos, and the child 
        // at position childPos.
            for (int i = elemPos; i < size; i++)
                elems[i] = elems[i+1];
            if (! isLeaf()) {
                for (int i = childPos; i < size; i++)
                    childs[i] = childs[i+1];
            }
            size--;
        }

    }