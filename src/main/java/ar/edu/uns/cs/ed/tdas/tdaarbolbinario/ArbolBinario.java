package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;

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
       return iterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'positions'");
    }

    @Override
    public E replace(Position<E> v, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }

    @Override
    public Position<E> root() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'root'");
    }

    @Override
    public Position<E> parent(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parent'");
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'children'");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInternal'");
    }

    @Override
    public boolean isExternal(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExternal'");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRoot'");
    }

    @Override
    public void createRoot(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRoot'");
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirstChild'");
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLastChild'");
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBefore'");
    }

    @Override
    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
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
        if (nodo.getHIzquierdo() == null) {
            throw new BoundaryViolationException("El nodo no tiene hijo izquierdo.");
        }
        return nodo.getHIzquierdo(); 
    }

    @Override
    public Position<E> right(Position<E> v) {
        BTNode<E> nodo = checkPosition(v);
        if (nodo.getHDerecho() == null) {
            throw new BoundaryViolationException("El nodo no tiene hijo derecho.");
        }
        return nodo.getHDerecho();
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        boolean resultado = false;
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getHIzquierdo() != null) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        boolean resultado = false;
        BTNode<E> nodo = checkPosition(v);
        if(nodo.getHDerecho() != null) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public Position<E> addLeft(Position<E> v, E r) {
        BTNode<E> nodo = checkPosition(v);
        
        if(root == null) {
            throw new InvalidPositionException("El árbol está vacío.");
        }
        if(nodo.getHIzquierdo() != null) {
            throw new InvalidPositionException("El nodo ya tiene un hijo izquierdo.");
        }
        BTNode<E> resultado = new BTNode<>(r, null,nodo.getHDerecho(),nodo);
        cant++;
        return resultado;
    }

    @Override
    public Position<E> addRight(Position<E> v, E r) {
        BTNode<E> nodo = checkPosition(v);
        
        if(root == null) {
            throw new InvalidPositionException("El árbol está vacío.");
        }
        if(nodo.getHDerecho() != null) {
            throw new InvalidPositionException("El nodo ya tiene un hijo derecho.");
        }
        BTNode<E> resultado = new BTNode<>(r, nodo.getHIzquierdo(), null, nodo);
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
