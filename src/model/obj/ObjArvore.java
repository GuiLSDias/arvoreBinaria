package model.obj;

public abstract class ObjArvore<T> implements Comparable<T> {

    public abstract boolean equals(Object o);
    public abstract int hashCode();
    public abstract String toString();
    public abstract int compareTo(T outro);

}
