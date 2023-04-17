package verwaltung;

import administration.Customer;
import cargo.Cargo;
import cargo.DryBulkCargo;

import java.util.*;

public class Lager {
    public List<Customer> customerList;
    public List<Cargo> cargoList = new List<Cargo>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Cargo> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Cargo cargo) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Cargo> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Cargo> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Cargo get(int index) {
            return null;
        }

        @Override
        public Cargo set(int index, Cargo element) {
            return null;
        }

        @Override
        public void add(int index, Cargo element) {

        }

        @Override
        public Cargo remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Cargo> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Cargo> listIterator(int index) {
            return null;
        }

        @Override
        public List<Cargo> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    int maxsize;
    boolean full;
    int used;
    public boolean einfuegen(DryBulkCargo cargo){
        try{
            cargoList.add(cargo);
            return true;
        }catch(Exception e){
            return false;
        }

    }
    public List<DryBulkCargo> abrufen(){
        return new ArrayList<>();
    }
    public boolean entfernen(DryBulkCargo Cargo){
        throw new IllegalStateException();
    }
    public Date inspection(DryBulkCargo Cargo){
        throw new IllegalStateException();
    }
}
