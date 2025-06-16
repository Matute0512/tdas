package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class ArbolBinario <E> implements BinaryTree<E> {

    private BTNode<E> root;
    private int cant;

    public ArbolBinario(){
        root = null;
        cant = 0;
    }

    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return cant == 0;
    }

    @Override
    public Iterator<E> iterator() {
       PositionList<E> lista = new ListaDE<E>();
       if(!isEmpty()){
            preOrdenElementos(root,lista);
       }
       return lista.iterator();
    }

    private void preOrdenElementos(BTNode<E> nodo, PositionList<E> lista) {
        lista.addLast(nodo.element());
        if(nodo.getLeft()!= null){
            preOrdenElementos(nodo.getLeft(), lista);
        }
        if(nodo.getRight()!= null){
            preOrdenElementos(nodo.getRight(), lista);
        }
    }

    @Override
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> lista = new ListaDE<>();
        if(!isEmpty()){
            preOrdenPosiciones(root,lista);
        }
        return lista;
    }

    private void preOrdenPosiciones(BTNode<E> nodo, PositionList<Position<E>> lista) {
        lista.addLast(nodo);
        if(nodo.getLeft() != null){
            preOrdenPosiciones(nodo.getLeft(), lista);
        }
        if(nodo.getRight() != null){
            preOrdenPosiciones(nodo.getRight(), lista);
        }
    }

    @Override
    public E replace(Position<E> v, E e) {
        BTNode<E> nuevo = checkPosition(v);
        E resultado = nuevo.element();
        nuevo.setElement(e);
        return resultado; 
    }

    @Override
    public Position<E> root() {
        if(isEmpty()){
            throw new EmptyTreeException("Error: El arbol esta vacio");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getParent() == null){
            throw new BoundaryViolationException("Error: El nodo es la raiz(por lo tanto no tiene padre).");
        } 
        return nodo.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        PositionList<Position<E>> lista = new ListaDE();
        if(!isEmpty())
            preOrdenPosiciones(nodo, lista);
        return lista;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        return (nodo.getLeft() != null || nodo.getRight() != null);
    }

    @Override
    public boolean isExternal(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        return (nodo.getLeft() == null && nodo.getRight() == null);
    }

    @Override
    public boolean isRoot(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        return nodo == root;
    }

    @Override
    public void createRoot(E e) {
        if(root != null){
            throw new InvalidOperationException("Error: El Arbol Binario ya posee una raiz.");
        }
        root = new BTNode<E>(e, null,null,null);
        cant++;
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        BTNode<E> nodo = checkPosition(p);
        if(nodo.getLeft() != null){
            throw new InvalidPositionException("Error: El nodo ya posee un hijo izquierdo.");
        }
        BTNode<E> nuevo = new BTNode<E>(e,null,null,nodo);
        nodo.setLeft(nuevo);
        cant++;
        return nuevo;
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        BTNode<E> nodo = checkPosition(p);
        if(nodo.getRight() != null){
            throw new InvalidOperationException("Error: El nodo ya posee un hijo derecho.");
        }
        BTNode<E> nuevo = new BTNode<E>(e, null,null, nodo);
        nodo.setRight(nuevo);
        cant++;
        return nuevo;
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        BTNode<E> padre = checkPosition(p);
        BTNode<E> hdre = checkPosition(padre);
        if(padre.getLeft() != null){
            throw new InvalidPositionException("Error: El nodo ya posee un hijo izquierdo.");
        }
        if (hdre.getParent() != padre || padre.getRight() != hdre) {
            throw new InvalidPositionException("Error: El nodo rb no es hijo derecho del nodo p");
        }
        BTNode<E> nuevo = new BTNode<E>(e,null, null, padre);
        padre.setLeft(nuevo);
        cant++;
        return nuevo;
    }

    @Override
    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
         BTNode<E> padre = checkPosition(p);
        BTNode<E> hrizq = checkPosition(padre);
        if(padre.getLeft() != null){
            throw new InvalidPositionException("Error: El nodo ya posee un hijo izquierdo.");
        }
        if (hrizq.getParent() != padre || padre.getRight() != hrizq) {
            throw new InvalidPositionException("Error: El nodo lb no es hijo izquierdo del nodo p");
        }
        BTNode<E> nuevo = new BTNode<E>(e,null, null, padre);
        padre.setRight(nuevo);
        cant++;
        return nuevo;
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeExternalNode'");
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeInternalNode'");
    }

    @Override
    public void removeNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNode'");
    }

    @Override
    public Position<E> left(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        if (nodo.getLeft() == null) {
            throw new BoundaryViolationException("El nodo no tiene hijo izquierdo.");
        }
        return nodo.getLeft(); 
    }

    @Override
    public Position<E> right(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        if (nodo.getRight() == null) {
            throw new BoundaryViolationException("El nodo no tiene hijo derecho.");
        }
        return nodo.getRight();
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        boolean resultado = false;
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getLeft() != null) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        boolean resultado = false;
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getRight() != null) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public Position<E> addLeft(Position<E> v, E r) {
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getLeft() != null) {
            throw new InvalidOperationException("El nodo ya tiene un hijo izquierdo.");
        }
        BTNode<E> resultado = new BTNode<>(r, null,null,nodo);
        nodo.setLeft(resultado);
        cant++;
        return resultado;
    }

    @Override
    public Position<E> addRight(Position<E> v, E r) {
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getRight() != null) {
            throw new InvalidOperationException("El nodo ya tiene un hijo derecho.");
        }
        BTNode<E> resultado = new BTNode<>(r, null, null, nodo);
        nodo.setRight(resultado);
        cant++;
        return resultado;
    }

    @Override
    public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attach'");
    }

    private BTNode<E> checkPosition (Position<E> p) {
		BTNode<E> resultado = null;
		if (p == null) {
			throw new InvalidPositionException("Posición nula.");
		}

		if (this.isEmpty()) {
			throw new InvalidPositionException("Posición inválida");
		}

		try {
			resultado = (BTNode<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Posición inválida");
		}

		return resultado;
    } 
}
