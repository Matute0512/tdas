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
    PositionList<Position<E>> lista = new ListaDE<>();

    if (nodo.getLeft() != null) lista.addLast(nodo.getLeft());
    if (nodo.getRight() != null) lista.addLast(nodo.getRight());

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
        BTNode<E> nodo = checkPosition(p);
        // Verificamos que el nodo sea externo (sin hijos)
        if (isExternal(nodo)) {
            BTNode<E> parent = nodo.getParent();
            if (parent != null) {
                // Si el nodo es el hijo izquierdo del padre
                if (parent.getLeft() == nodo) {
                    parent.setLeft(null);
                }   
                else { // Si el nodo es el hijo derecho del padre
                    parent.setRight(null);
                }
                cant--;
            }
        } 
        else {
            throw new InvalidOperationException("Error: El nodo no es externo.");
        }
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        BTNode<E> nodo = checkPosition(p);
        if (!isInternal(nodo)) {
            throw new InvalidOperationException("El nodo no es interno");
        }
        
        // Caso especial: raíz con un solo hijo
        if (nodo == root && (nodo.getLeft() == null || nodo.getRight() == null)) {
            BTNode<E> hijo = (nodo.getLeft() != null) ? nodo.getLeft() : nodo.getRight();
            root = hijo;
            if (hijo != null) {
                hijo.setParent(null);
            }
            cant--;
            return;
        }
        
        // Caso general: nodo interno no raíz
        removeNode(nodo);
    }
    @Override
    public void removeNode(Position<E> p) {
         BTNode<E> nodo = checkPosition(p);
        
        if (nodo == root && (nodo.getLeft() != null && nodo.getRight() != null)) {
            throw new InvalidPositionException("No se puede eliminar la raíz con dos hijos");
        }
        
        BTNode<E> padre = nodo.getParent();
        BTNode<E> hijoUnico = null;
        
        if (nodo.getLeft() != null && nodo.getRight() != null) {
            throw new InvalidPositionException("No se puede eliminar nodo con dos hijos");
        } else if (nodo.getLeft() != null) {
            hijoUnico = nodo.getLeft();
        } else if (nodo.getRight() != null) {
            hijoUnico = nodo.getRight();
        }
        
        if (padre == null) { // Es la raíz
            root = hijoUnico;
            if (hijoUnico != null) {
                hijoUnico.setParent(null);
            }
        } else {
            if (padre.getLeft() == nodo) {
                padre.setLeft(hijoUnico);
            } else {
                padre.setRight(hijoUnico);
            }
            
            if (hijoUnico != null) {
                hijoUnico.setParent(padre);
            }
        }
        
        cant--;
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
        BTNode<E> nodo = checkPosition(r);
    if (isInternal(nodo)) {
        throw new InvalidPositionException("Error: El nodo no puede ser interno.");
    }
    
    // Adjuntamos T1
    if (T1 != null && !T1.isEmpty()) {
        // Asumimos que T1 tiene una raíz
        BTNode<E> rootT1 = (BTNode<E>) T1.root();
        nodo.setLeft(rootT1);
        rootT1.setParent(nodo);
        cant += T1.size();
    }
    
    // Adjuntamos T2
    if (T2 != null && !T2.isEmpty()) {
        // Asumimos que T2 tiene una raíz
        BTNode<E> rootT2 = (BTNode<E>) T2.root();
        nodo.setRight(rootT2);
        rootT2.setParent(nodo);
        cant += T2.size();
    }
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
