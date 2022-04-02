package projects.superList.testing

import java.util.*
import java.util.Objects.nonNull
import java.util.Spliterator.*
import java.util.function.Consumer
import java.util.stream.Stream.iterate
import kotlin.math.pow
import kotlin.math.roundToInt

class SuperList<E>() : Cloneable {
	var size = 0
	private var first: SuperNode<E?>? = null
	private var last: SuperNode<E?>? = null

	constructor(c: Collection<E>?) : this() {
		addAll(c)
	}

	private fun linkFirst(e: E) {
		val first = first
		val node = SuperNode(null, e, first)
		this.first = node
		if (first == null) last = node else first.prev = node
		size++
	}

	private fun linkLast(e: E) {
		val last = last
		val node = SuperNode(last, e, null)
		this.last = node
		if (last == null) first = node else last.next = node
		size++
	}

	fun linkBefore(e: E, superNode: SuperNode<E?>?) {
		val prev = superNode!!.prev
		val node = SuperNode(prev, e, superNode)
		superNode.prev = node
		if (prev == null) first = node else prev.next = node
		size++
	}

	private fun unlinkFirst(first: SuperNode<E?>): E? {
		val element = first.item
		val next = first.next
		first.item = null
		first.next = null
		this.first = next
		if (next == null) last = null else next.prev = null
		size--
		return element
	}

	private fun unlinkLast(last: SuperNode<E?>): E? {
		val element = last.item
		val prev = last.prev
		last.item = null
		last.prev = null
		this.last = prev
		if (prev == null) first = null else prev.next = null
		size--
		return element
	}

	fun unlink(x: SuperNode<E?>?): E? {
		val element = x!!.item
		val next = x.next
		val prev = x.prev
		if (prev == null) first = next else {
			prev.next = next
			x.prev = null
		}
		if (next == null) last = prev else {
			next.prev = prev
			x.next = null
		}
		x.item = null
		size--
		return element
	}

	private fun getFirst(): E? {
		val f = first ?: throw NoSuchElementException()
		return f.item
	}

	fun getLast(): E? {
		val l = last ?: throw NoSuchElementException()
		return l.item
	}

	private fun removeFirst(): E? {
		val f = first ?: throw NoSuchElementException()
		return unlinkFirst(f)
	}

	fun removeLast(): E? {
		val l = last ?: throw NoSuchElementException()
		return unlinkLast(l)
	}

	private fun addFirst(e: E) = linkFirst(e)

	fun addLast(e: E) = linkLast(e)

	operator fun contains(o: Any?) = indexOf(o) >= 0

	fun size() = size

	fun add(e: E): Boolean {
		addLast(e)
		return true
	}

	fun remove(o: Any?): Boolean {
		if (o == null) {
			var x = first
			while (x != null) {
				if (x.item == null) {
					unlink(x)
					return true
				}
				x = x.next
			}
		} else {
			var x = first
			while (x != null) {
				if (o == x.item) {
					unlink(x)
					return true
				}
				x = x.next
			}
		}
		return false
	}

	private fun addAll(c: Collection<E>?): Boolean = addAll(size(), c)

	private fun addAll(index: Int, c: Collection<E>?): Boolean {
		checkPositionIndex(index)
		val a = c?.toList()!!
		val numNew = a.size
		if (numNew == 0) return false
		var pred: SuperNode<E?>?
		val succ: SuperNode<E?>?
		if (index == size()) {
			succ = null
			pred = last
		} else {
			succ = node(index)
			pred = succ!!.prev
		}
		for (o in a) {
			val newSuperNode = SuperNode(pred, o, null)
			if (pred == null) first = newSuperNode else pred.next = newSuperNode
			pred = newSuperNode
		}
		if (succ == null) last = pred else {
			pred!!.next = succ
			succ.prev = pred
		}
		size += numNew
		return true
	}

	fun clear(isN: Boolean) {
		if (!isN) {
			first = null
			last = null
		} else {
			var x = first
			while (x != null) {
				val next = x.next!!
				x.item = null
				x.next = null
				x.prev = null
				x = next
			}
			last = null
			first = last
		}
		size = 0
	}

	operator fun get(index: Int): E? {
		checkElementIndex(index)
		return node(index)!!.item
	}

	operator fun set(index: Int, element: E): E? {
		checkElementIndex(index)
		val x = node(index)
		val oldVal = x!!.item
		x.item = element
		return oldVal
	}

	fun add(index: Int, element: E) {
		checkPositionIndex(index)
		if (index == size()) addLast(element) else linkBefore(element, node(index))
	}

	fun remove(index: Int): E? {
		checkElementIndex(index)
		return unlink(node(index))
	}

	private fun isElementIndexValid(index: Int): Boolean {
		return index >= 0 && index < size()
	}

	private fun isPositionIndexValid(index: Int): Boolean {
		return index >= 0 && index <= size()
	}

	private fun outOfBoundsMsg(index: Int): String {
		return "Index: " + index + ", Size: " + size()
	}

	private fun checkElementIndex(index: Int) {
		if (!isElementIndexValid(index)) throw IndexOutOfBoundsException(outOfBoundsMsg(index))
	}

	private fun checkPositionIndex(index: Int) {
		if (!isPositionIndexValid(index)) throw IndexOutOfBoundsException(outOfBoundsMsg(index))
	}

	fun node(index: Int) = if (index < size() / 2) {
		var x = first
		for (i in 0 until index) x = x!!.next
		x
	} else {
		var x = last
		for (i in size() - 1 downTo index + 1) x = x!!.prev
		x
	}

	fun indexOf(o: Any?): Int {
		var index = 0
		if (o == null) {
			var x = first
			while (x != null) {
				if (x.item == null) return index
				index++
				x = x.next
			}
		} else {
			var x = first
			while (x != null) {
				if (o == x.item) return index
				index++
				x = x.next
			}
		}
		return -1
	}

	fun lastIndexOf(o: Any?): Int {
		var index = size()
		if (o == null) {
			var x = last
			while (x != null) {
				index--
				if (x.item == null) return index
				x = x.prev
			}
		} else {
			var x = last
			while (x != null) {
				index--
				if (o == x.item) return index
				x = x.prev
			}
		}
		return -1
	}

	fun peek() = peekFirst()

	fun element() = getFirst()

	fun poll() = first?.let { unlinkFirst(it) }

	fun remove() = pop()

	fun offer(e: E) = add(e)

	fun offerFirst(e: E): Boolean {
		push(e)
		return true
	}

	fun offerLast(e: E) = add(e)

	private fun peekFirst() = first?.item

	fun peekLast() = last?.item

	fun pollFirst() = first?.let { unlinkFirst(it) }

	fun pollLast() = last?.let { unlinkLast(it) }

	private fun push(e: E) = addFirst(e)

	private fun pop(): E? = removeFirst()

	fun removeFirstOccurrence(o: Any?) = remove(o)

	fun removeLastOccurrence(o: Any?): Boolean {
		if (o == null) {
			var x = last
			while (x != null) {
				if (x.item == null) {
					unlink(x)
					return true
				}
				x = x.prev
			}
		} else {
			var x = last
			while (x != null) {
				if (o == x.item) {
					unlink(x)
					return true
				}
				x = x.prev
			}
		}
		return false
	}

	fun listIterator(index: Int): MutableListIterator<E> {
		checkPositionIndex(index)
		class ListItr(index: Int) : MutableListIterator<E> {
			private var lastReturned: SuperNode<E?>? = null
			private var next: SuperNode<E?>?
			private var nextIndex: Int

			init {
				next = if (index == size()) null else node(index)
				nextIndex = index
			}

			override fun hasNext(): Boolean {
				return nextIndex < size()
			}

			override fun next(): E {
				if (!hasNext()) throw NoSuchElementException()
				lastReturned = next
				next = next!!.next
				nextIndex++
				return lastReturned?.item!!
			}

			override fun hasPrevious() = nextIndex > 0

			override fun previous(): E {
				if (!hasPrevious()) throw NoSuchElementException()
				next = if (next == null) last else next!!.prev
				lastReturned = next
				nextIndex--
				return lastReturned?.item!!
			}

			override fun nextIndex() = nextIndex

			override fun previousIndex() = nextIndex - 1

			override fun remove() {
				checkNotNull(lastReturned)
				val lastNext = lastReturned!!.next
				unlink(lastReturned)
				if (next === lastReturned) next = lastNext else nextIndex--
				lastReturned = null
			}

			override fun set(element: E) {
				checkNotNull(lastReturned)
				lastReturned!!.item = element
			}

			override fun add(element: E) {
				lastReturned = null
				if (next == null) addLast(element) else linkBefore(element, next)
				nextIndex++
			}

			override fun forEachRemaining(action: Consumer<in E>) {
				while (nextIndex < size()) {
					action.accept(next?.item!!)
					lastReturned = next
					next = next!!.next
					nextIndex++
				}
			}
		}
		return ListItr(index)
	}

	fun descendingIterator(): Iterator<E> = object : MutableIterator<E> {
		private val itr = listIterator(size())
		override fun hasNext() = itr.hasPrevious()

		override fun next() = itr.previous()

		override fun remove() = itr.remove()
	}

	public override fun clone() = try {
		super.clone() as SuperList<*>
	} catch (e: CloneNotSupportedException) {
		throw InternalError(e)
	}.also {
		first = last
		last = null
		size = 0
		iterate(first, ::nonNull) { it?.next }.map { it?.item }.forEach { add(it!!) }
	}

	fun toArray(): Array<out Any?> {
		val result = arrayOfNulls<Any>(size())
		var i = 0
		var x = first
		while (x != null) {
			result[i++] = x.item
			x = x.next
		}
		return result
	}

	fun <T> toArray(a: Array<T?>): Array<T?> {
		var a = a
		if (a.size < size()) a = java.lang.reflect.Array.newInstance(a.javaClass.componentType, size()) as Array<T?>
		var i = 0
		val result = a
		var x = first
		while (x != null) {
			result[i++] = x.item as T?
			x = x.next
		}
		if (a.size > size()) a[size()] = null
		return a
	}

	fun spliterator(): Spliterator<E> {
		class SLSpliterator<T>(val list: SuperList<T>, var est: Int) : Spliterator<T> {
			var current: SuperNode<T?>? = null
			var batch = 0

			@JvmName("getEst1")
			fun getEst(): Int {
				var s: Int
				val lst: SuperList<T>
				if (est.also { s = it } < 0) if (list.also { lst = it } == null) {
					est = 0
					s = est
				} else {
					current = lst.first
					est = lst.size()
					s = est
				}
				return s
			}

			override fun estimateSize() = getEst().toLong()

			override fun trySplit(): Spliterator<T>? {
				var p: SuperNode<T?>? = null
				val s = getEst()
				if (s > 1 && current.also { p = it!! } != null) {
					var n = batch + BATCH_UNIT
					if (n > s) n = s
					if (n > MAX_BATCH) n = MAX_BATCH
					val a = arrayOfNulls<Any>(n)
					var j = 0
					do {
						a[j++] = p?.item
					} while (p?.next.also { p = it!! } != null && j < n)
					current = p
					batch = j
					est = s - j
					return Spliterators.spliterator(a, 0, j, ORDERED)
				}
				return null
			}

			override fun forEachRemaining(action: Consumer<in T>) {
				var p: SuperNode<T?>? = null
				var n: Int
				if (action == null) throw NullPointerException()
				if (getEst().also { n = it } > 0 && current.also { p = it } != null) {
					current = null
					est = 0
					do {
						val e = p?.item
						p = p?.next
						if (e != null) action.accept(e)
					} while ((p != null) && (--n > 0))
				}
			}

			override fun tryAdvance(action: Consumer<in T>): Boolean {
				var p: SuperNode<T?>? = null
				if (getEst() > 0 && current.also { p = it!! } != null) {
					est--
					val e = p?.item
					current = p?.next
					if (e != null) action.accept(e)
					return true
				}
				return false
			}

			override fun characteristics() = ORDERED or SIZED or SUBSIZED

			private val BATCH_UNIT = 2.0.pow(10).roundToInt()
			private val MAX_BATCH = 2.0.pow(25).roundToInt()
		}
		return SLSpliterator(this, -1)
	}

	val isEmpty: Boolean
		get() = size() == 0

	override fun toString(): String {
		val stringBuilder = StringBuilder("[")
		val listIterator: ListIterator<E> = listIterator(0)
		if (!listIterator.hasNext()) return "[]"
		while (listIterator.hasNext()) stringBuilder.append(listIterator.next()).append(", ")
		return stringBuilder.delete(stringBuilder.length - 2, stringBuilder.length).toString() + "]"
	}

	class SuperNode<E> internal constructor(var prev: SuperNode<E>?, var item: E, var next: SuperNode<E>?) {
		val value: SuperNode<E>
			get() = this
	}
}
