/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 *
 * Tai pirmoji kompleksinės duomenų struktūros klasė, kuri leidžia apjungti
 * atskirus objektus į vieną visumą - sąrašą. Objektai, kurie bus dedami į
 * sąrašą, turi tenkinti interfeisą Comparable<E>.
 *
 * Užduotis: Peržiūrėkite ir išsiaiškinkite pateiktus metodus. Metodų algoritmai
 * yra aptarti paslaitos metu. Realizuokite nurodytus metodus.
 *****************************************************************************
 */
package studijosKTU;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Koreguota 2015-09-18
 * @author Aleksis
 * @param <E> Sąrašo elementų tipas (klasė)
 */
public class ListKTU<E extends Comparable<E>>
		implements ListADT<E>, Iterable<E>, Cloneable {

	private Node<E> first;   // rodyklė į pirmą mazgą
	private Node<E> last;    // rodyklė į paskutinį mazgą
	private Node<E> current; // rodyklė į einamąjį mazgą, naudojama getNext
	private int size;        // sąrašo dydis, tuo pačiu elementų skaičius

	/**
	 * Constructs an empty list.
	 */
	public ListKTU() {
	}
/**
 * 
 * @param e Ieškomas elementas
 * @return true jei rado, false jei nerado
 */
        public boolean contains(E e){
            if(e == null)
                return false;
            for (Node<E> e1 = first; e1 != null; e1 = e1.next){
                if(e1.element == e)
                    return true;
            }
            return false;
        }
        /**
         * 
         * @return 
         * grąžina pašalintą reikšmę
         */
        public E removeLast(){
            E e = last.element;
            first.findNode(size - 2).next = null;
            size--;
            last = first.findNode(size - 1);
            return e;
        }
	/**
	 * metodas add įdeda elementą į sąrašo pabaigą
	 * @param e - tai įdedamas elementas (jis negali būti null)
	 * @return true, jei operacija atlikta sėkmingai
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			return false;   // nuliniai objektai nepriimami
		}
		if (first == null) {
			first = new Node<>(e, first);
			last = first;
		} else {
			Node<E> e1 = new Node(e, null);
			last.next = e1;
			last = e1;
		}
		size++;
		return true;
	}

	/**
	 * Įterpia elementą prieš k-ąją poziciją
	 *
	 * @param k pozicija
	 * @param e įterpiamasis elementas
	 * @return jei k yra blogas, grąžina null
	 */
	@Override
	public boolean add(int k, E e) {
		if (e == null) {
                    return false;
		}
		if (k < 0 || k > size) {//tikrinama ar indeksas atitinka sąrašo ribas
                    return false;
		}
                if(k == 0){//jei elementas dedamas į pirmą vietą
                    first = new Node(e, first);
                    size++;
                    return true;
                }
                if(k == size){// jei elementas įdedamas į pakutinę vietą
                    last.next = new Node(e, null);
                    last = last.next;
                    size++;
                    return true;
                }
		Node<E> prevKNode = first.findNode(k - 1);
                Node<E> kNode = first.findNode(k);
                Node<E> NewNode = new Node(e, kNode);
                prevKNode.next = NewNode;
                kNode = NewNode;
                size++;
                return true;
	}

	/**
	 *
	 * @return sąrašo dydis (elementų kiekis)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * patikrina ar sąrašas yra tuščias
	 *
	 * @return true, jei tuščias
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Išvalo sąrašą
	 */
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
		current = null;
	}

	/**
	 * Grąžina elementą pagal jo indeksą
	 *		(pradinis indeksas 0)
	 * @param k indeksas
	 * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
	 */
	@Override
	public E get(int k) {
		if (k < 0 || k >= size) {
			return null;
		}
		current = first.findNode(k);
		return current.element;
	}

	/**
	 * Pakeičia k-ojo elemento reikšmę
	 *
	 * @param k keičiamo elemento indeksas
	 * @param e nauja elemento reikšmė
	 * @return senoji reikšmė
	 */
	@Override
	public E set(int k, E e) {
            if (first != null && k < size) {
                E oldValue = first.findNode(k).element;
		first.findNode(k).element = e;
                return oldValue;
            }
            return null;
	}

	/**
	 * pereina prie kitos reikšmės ir ją grąžina
	 *
	 * @return kita reikšmė
	 */
	@Override
	public E getNext() {
		if (current == null) {
			return null;
		}
		current = current.next;
		if (current == null) {
			return null;
		}
		return current.element;
	}
        public void removeRange(int fromIndex, int toIndex){
            if(first != null && toIndex >= fromIndex && fromIndex < size &&
                    toIndex < size && fromIndex >= 0 && toIndex >= 0){
                first.findNode(fromIndex - 1).next = first.findNode(toIndex + 1);
                size = size - (toIndex - fromIndex + 1);
            }
        }
	/**
	 * Šalina elementą pagal indeksą
	 *
	 * @param k šalinamojo indeksas
	 * @return pašalintas elementas
	 */
	@Override
	public E remove(int index) {
            if(index < size && index >= 0 && first != null){
                    E removableElement = first.findNode(index).element;
                    if (index == 0){//jei pirmas elementas
			Node<E> removable = first.findNode(index);
                        first = removable.next;
                        removable = null;
                    }
                    else if(index == size - 1){// jei paskutinis elementas
                        last = null;
                        last = first.findNode(index - 1);
                    }
                    else{// jei per vidurį elementas
                        Node<E> removable = first.findNode(index);
                        first.findNode(index - 1).next = removable.next;
                    }
                    size--;
                    return removableElement;
            }
            return null;
	}

	/**
	 *
	 * @return sąrašo kopiją
	 */
	@Override
	public ListKTU<E> clone() {
		ListKTU<E> cl = null;
		try {
			cl = (ListKTU<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
			System.exit(1);
		}
		if (first == null) {
			return cl;
		}
		cl.first = new Node<>(this.first.element, null);
		Node<E> e2 = cl.first;
		for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
			e2.next = new Node<>(e2.element, null);
			e2 = e2.next;
			e2.element = e1.element;
		}
		cl.last = e2.next;
		cl.size = this.size;
		return cl;
	}
    //  !

	/**
	 * Formuojamas Object masyvas (E tipo masyvo negalima sukurti) ir surašomi
	 * sąrašo elementai
	 *
	 * @return sąrašo elementų masyvas
	 */
	public Object[] toArray() {
		Object[] a = new Object[size];
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			a[i++] = e1.element;
		}
		return a;
	}

	/**
	 * Masyvo rikiavimas Arrays klasės metodu sort
	 */
	public void sortSystem() {
		Object[] a = this.toArray();
		Arrays.sort(a);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Rikiavimas Arrays klasės metodu sort pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortSystem(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu
	 */
	public void sortBuble() {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (e1.element.compareTo(e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortBuble(Comparator<? super E> c) {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (c.compare(e1.element, e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
	 *
	 * @return iteratoriaus objektą
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIteratorKTU();
	}

	/**
	 * Iteratoriaus metodų klasė
	 */
	class ListIteratorKTU implements Iterator<E> {

		private Node<E> iterPosition;

		ListIteratorKTU() {
			iterPosition = first;
		}

		@Override
		public boolean hasNext() {
			return iterPosition != null;
		}

		@Override
		public E next() {
			E d = iterPosition.element;
			iterPosition = iterPosition.next;
			return d;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Studentams reikia realizuoti ListItr.remove()");
		}
	}

	/**
	 * Vidinė mazgo klasė
	 *
	 * @param <E> mazgo duomenų tipas
	 */
	private static class Node<E> {

		private E element;    // ji nematoma už klasės ListKTU ribų
		private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą

		Node(E data, Node<E> next) { //mazgo konstruktorius
			this.element = data;
			this.next = next;
		}

		/**
		 * Suranda sąraše k-ąjį mazgą
		 *
		 * @param k ieškomo mazgo indeksas (prasideda nuo 0)
		 * @return surastas mazgas
		 */
		public Node<E> findNode(int k) {
			Node<E> e = this;
			for (int i = 0; i < k; i++) {
				e = e.next;
			}
			return e;
		}
	} // klasės Node pabaiga
}
