package projects.superList;

import java.util.*;
import java.util.function.Consumer;

import static java.lang.Math.*;
import static java.lang.reflect.Array.newInstance;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Stream.iterate;

class SuperList<E> {
	private int size = 0;
	private SuperNode<E> first, last;

	public SuperList(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	public SuperList() {
	}

	private void linkFirst(E e) {
		final var first = this.first;
		final SuperNode<E> node = new SuperNode<>(null, e, first);
		this.first = node;
		if (first == null) last = node;
		else first.prev = node;
		size++;
	}

	void linkLast(E e) {
		final var last = this.last;
		final SuperNode<E> node = new SuperNode<>(last, e, null);
		this.last = node;
		if (last == null) first = node;
		else last.next = node;
		size++;
	}

	void linkBefore(E e, SuperNode<E> superNode) {
		final var prev = superNode.prev;
		final SuperNode<E> node = new SuperNode<>(prev, e, superNode);
		superNode.prev = node;
		if (prev == null) first = node;
		else prev.next = node;
		size++;
	}

	private E unlinkFirst(SuperNode<E> first) {
		final var element = first.item;
		final var next = first.next;
		first.item = null;
		first.next = null;
		this.first = next;
		if (next == null) last = null;
		else next.prev = null;
		size--;
		return element;
	}

	private E unlinkLast(SuperNode<E> last) {
		final var element = last.item;
		final var prev = last.prev;
		last.item = null;
		last.prev = null;
		this.last = prev;
		if (prev == null) first = null;
		else prev.next = null;
		size--;
		return element;
	}

	E unlink(SuperNode<E> x) {
		final var element = x.item;
		final var next = x.next;
		final var prev = x.prev;
		if (prev == null) first = next;
		else {
			prev.next = next;
			x.prev = null;
		}
		if (next == null) last = prev;
		else {
			next.prev = prev;
			x.next = null;
		}
		x.item = null;
		size--;
		return element;
	}

	public E getFirst() {
		final var f = first;
		if (f == null) throw new NoSuchElementException();
		return f.item;
	}

	public E getLast() {
		final var l = last;
		if (l == null) throw new NoSuchElementException();
		return l.item;
	}

	public E removeFirst() {
		final var f = first;
		if (f == null) throw new NoSuchElementException();
		return unlinkFirst(f);
	}

	public E removeLast() {
		final var l = last;
		if (l == null) throw new NoSuchElementException();
		return unlinkLast(l);
	}

	public void addFirst(E e) {
		linkFirst(e);
	}

	public void addLast(E e) {
		linkLast(e);
	}

	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	public int size() {
		return size;
	}

	public boolean add(E e) {
		addLast(e);
		return true;
	}

	public boolean remove(Object o) {
		if (o == null) {
			for (var x = first; x != null; x = x.next)
				if (x.item == null) {
					unlink(x);
					return true;
				}
		} else for (var x = first; x != null; x = x.next)
			if (o.equals(x.item)) {
				unlink(x);
				return true;
			}
		return false;
	}

	public boolean addAll(Collection<? extends E> c) {
		return addAll(size(), c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		checkPositionIndex(index);
		List<E> a = new ArrayList<>(c);
		var numNew = a.size();
		if (numNew == 0) return false;
		SuperNode<E> pred, succ;
		if (index == size()) {
			succ = null;
			pred = last;
		} else {
			succ = node(index);
			pred = succ.prev;
		}
		for (var o : a) {
			var newSuperNode = new SuperNode<E>(pred, o, null);
			if (pred == null) first = newSuperNode;
			else pred.next = newSuperNode;
			pred = newSuperNode;
		}
		if (succ == null) last = pred;
		else {
			pred.next = succ;
			succ.prev = pred;
		}
		size += numNew;
		return true;
	}

	public void clear(boolean isN) {
		if (!isN) {
			first = null;
			last = null;
		} else {
			for (var x = first; x != null; ) {
				var next = x.next;
				x.item = null;
				x.next = null;
				x.prev = null;
				x = next;
			}
			first = last = null;
		}
		size = 0;
	}

	public E get(int index) {
		checkElementIndex(index);
		return node(index).item;
	}

	public E set(int index, E element) {
		checkElementIndex(index);
		var x = node(index);
		var oldVal = x.item;
		x.item = element;
		return oldVal;
	}

	public void add(int index, E element) {
		checkPositionIndex(index);
		if (index == size()) addLast(element);
		else linkBefore(element, node(index));
	}

	public E remove(int index) {
		checkElementIndex(index);
		return unlink(node(index));
	}

	private boolean isElementIndexValid(int index) {
		return index >= 0 && index < size();
	}

	private boolean isPositionIndexValid(int index) {
		return index >= 0 && index <= size();
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size();
	}

	private void checkElementIndex(int index) {
		if (!isElementIndexValid(index)) throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndexValid(index)) throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	SuperNode<E> node(int index) {
		if (index < (size() / 2)) {
			var x = first;
			for (var i = 0; i < index; i++) x = x.next;
			return x;
		} else {
			var x = last;
			for (var i = size() - 1; i > index; i--) x = x.prev;
			return x;
		}
	}

	public int indexOf(Object o) {
		var index = 0;
		if (o == null) for (var x = first; x != null; x = x.next) {
			if (x.item == null) return index;
			index++;
		}
		else for (var x = first; x != null; x = x.next) {
			if (o.equals(x.item)) return index;
			index++;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		var index = size();
		if (o == null) for (var x = last; x != null; x = x.prev) {
			index--;
			if (x.item == null) return index;
		}
		else for (var x = last; x != null; x = x.prev) {
			index--;
			if (o.equals(x.item)) return index;
		}
		return -1;
	}

	public E peek() {
		return peekFirst();
	}

	public E element() {
		return getFirst();
	}

	public E poll() {
		final var f = first;
		return (f == null) ? null : unlinkFirst(f);
	}

	public E remove() {
		return pop();
	}

	public boolean offer(E e) {
		return add(e);
	}

	public boolean offerFirst(E e) {
		push(e);
		return true;
	}

	public boolean offerLast(E e) {
		return add(e);
	}

	public E peekFirst() {
		final var f = first;
		return (f == null) ? null : f.item;
	}

	public E peekLast() {
		final var l = last;
		return (l == null) ? null : l.item;
	}

	public E pollFirst() {
		final var f = first;
		return (f == null) ? null : unlinkFirst(f);
	}

	public E pollLast() {
		final var l = last;
		return (l == null) ? null : unlinkLast(l);
	}

	public void push(E e) {
		addFirst(e);
	}

	public E pop() {
		return removeFirst();
	}

	public boolean removeFirstOccurrence(Object o) {
		return remove(o);
	}

	public boolean removeLastOccurrence(Object o) {
		if (o == null) for (SuperNode<E> x = last; x != null; x = x.prev) {
			if (x.item == null) {
				unlink(x);
				return true;
			}
		}
		else for (var x = last; x != null; x = x.prev) {
			if (o.equals(x.item)) {
				unlink(x);
				return true;
			}
		}
		return false;
	}

	public ListIterator<E> listIterator(int index) {
		checkPositionIndex(index);
		class ListItr implements ListIterator<E> {
			private SuperNode<E> lastReturned, next;
			private int nextIndex;

			ListItr(int index) {
				next = (index == size()) ? null : node(index);
				nextIndex = index;
			}

			@Override
			public boolean hasNext() {
				return nextIndex < size();
			}

			@Override
			public E next() {
				if (!hasNext()) throw new NoSuchElementException();
				lastReturned = next;
				next = next.next;
				nextIndex++;
				return lastReturned.item;
			}

			@Override
			public boolean hasPrevious() {
				return nextIndex > 0;
			}

			@Override
			public E previous() {
				if (!hasPrevious()) throw new NoSuchElementException();
				lastReturned = next = (next == null) ? last : next.prev;
				nextIndex--;
				return lastReturned.item;
			}

			@Override
			public int nextIndex() {
				return nextIndex;
			}

			@Override
			public int previousIndex() {
				return nextIndex - 1;
			}

			@Override
			public void remove() {
				if (lastReturned == null) throw new IllegalStateException();
				var lastNext = lastReturned.next;
				unlink(lastReturned);
				if (next == lastReturned) next = lastNext;
				else nextIndex--;
				lastReturned = null;
			}

			@Override
			public void set(E e) {
				if (lastReturned == null) throw new IllegalStateException();
				lastReturned.item = e;
			}

			@Override
			public void add(E e) {
				lastReturned = null;
				if (next == null) addLast(e);
				else linkBefore(e, next);
				nextIndex++;
			}

			@Override
			public void forEachRemaining(Consumer<? super E> action) {
				requireNonNull(action);
				while (nextIndex < size()) {
					action.accept(next.item);
					lastReturned = next;
					next = next.next;
					nextIndex++;
				}
			}
		}
		return new ListItr(index);
	}

	public Iterator<E> descendingIterator() {
		return new Iterator<>() {
			private final ListIterator<E> itr = listIterator(size());

			@Override
			public boolean hasNext() {
				return itr.hasPrevious();
			}

			@Override
			public E next() {
				return itr.previous();
			}

			@Override
			public void remove() {
				itr.remove();
			}
		};
	}

	public Object clone() {
		SuperList<E> clone;
		try {
			clone = (SuperList<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
		clone.first = clone.last = null;
		clone.size = 0;
		iterate(first, Objects::nonNull, x -> x.next).map(x -> x.item).forEach(e -> clone.add(e));
		return clone;
	}

	public Object[] toArray() {
		var result = new Object[size()];
		var i = 0;
		for (var x = first; x != null; x = x.next) result[i++] = x.item;
		return result;
	}

	public <T> T[] toArray(T[] a) {
		if (a.length < size()) a = (T[]) newInstance(a.getClass().getComponentType(), size());
		var i = 0;
		var result = a;
		for (var x = first; x != null; x = x.next) result[i++] = (T) x.item;
		if (a.length > size()) a[size()] = null;
		return a;
	}

	public Spliterator<E> spliterator() {
		final class SLSpliterator<T> implements Spliterator<T> {
			private static final int BATCH_UNIT = toIntExact(round(pow(2, 10)));
			private static final int MAX_BATCH = toIntExact(round(pow(2, 25)));
			final SuperList<T> list;
			SuperNode<T> current;
			int est;
			int batch;

			SLSpliterator(SuperList<T> list, int est) {
				this.list = list;
				this.est = est;
			}

			int getEst() {
				int s;
				final SuperList<T> lst;
				if ((s = est) < 0) if ((lst = list) == null) s = est = 0;
				else {
					current = lst.first;
					s = est = lst.size();
				}
				return s;
			}

			@Override
			public long estimateSize() {
				return getEst();
			}

			@Override
			public Spliterator<T> trySplit() {
				SuperNode<T> p;
				int s = getEst();
				if (s > 1 && (p = current) != null) {
					var n = batch + BATCH_UNIT;
					if (n > s) n = s;
					if (n > MAX_BATCH) n = MAX_BATCH;
					var a = new Object[n];
					int j = 0;
					do {
						a[j++] = p.item;
					} while ((p = p.next) != null && j < n);
					current = p;
					batch = j;
					est = s - j;
					return Spliterators.spliterator(a, 0, j, ORDERED);
				}
				return null;
			}

			@Override
			public void forEachRemaining(Consumer<? super T> action) {
				SuperNode<T> p;
				int n;
				if (action == null) throw new NullPointerException();
				if ((n = getEst()) > 0 && (p = current) != null) {
					current = null;
					est = 0;
					do {
						var e = p.item;
						p = p.next;
						action.accept(e);
					} while (p != null && --n > 0);
				}
			}

			@Override
			public boolean tryAdvance(Consumer<? super T> action) {
				SuperNode<T> p;
				if (action == null) throw new NullPointerException();
				if (getEst() > 0 && (p = current) != null) {
					est--;
					var e = p.item;
					current = p.next;
					action.accept(e);
					return true;
				}
				return false;
			}

			@Override
			public int characteristics() {
				return ORDERED | SIZED | SUBSIZED;
			}
		}
		return new SLSpliterator<>(this, -1);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String toString() {
		var stringBuilder = new StringBuilder("[");
		var listIterator = listIterator(0);
		if (!listIterator.hasNext()) return "[]";
		while (listIterator.hasNext()) stringBuilder.append(listIterator.next()).append(", ");
		return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()) + "]";
	}

	private static class SuperNode<E> {
		private E item;
		private SuperNode<E> next, prev;

		SuperNode(SuperNode<E> prev, E element, SuperNode<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}

		public SuperNode<E> getNext() {
			return next;
		}

		public void setNext(SuperNode<E> next) {
			this.next = next;
		}

		public SuperNode<E> getPrev() {
			return prev;
		}

		public void setPrev(SuperNode<E> prev) {
			this.prev = prev;
		}

		SuperNode<E> getValue() {
			return this;
		}
	}
}
