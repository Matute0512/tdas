package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import ar.edu.uns.cs.ed.tdas.Position;

public class BTNode <E> implements Position <E> {

    private E element;
    private BTNode<E> left,right,parent;
    
    public BTNode(E elem, BTNode<E> hi, BTNode<E> hd, BTNode<E> padre){
        element = elem;
        left = hi;
        right = hd;
        parent = padre;

    }
    // Metodos 
    public void setElement(E elem){
        element= elem;
    }
    public void setParent(BTNode<E> p){
        parent = p;
    }
    public void setRight(BTNode<E> hd){
        right = hd;
    }
    public void setLeft(BTNode<E> hi){
        left = hi;
    }

    //Consultas
    public BTNode<E> getRight(){
        return right;
    }
    public BTNode<E> getLeft(){
        return left;
    }
    public BTNode<E> getParent(){
        return parent;
    }

    @Override
    public E element() {
        return element;
    }
    
}
