package projects.superList

fun main() {
	/*var temp = SuperList<Int>().apply {
		*//*add(0, 20);
		add(2);
		add(8);
		add(14);*//*
		add(0, 20);
		add(2);
		add(8);
		add(14);
//		add(10, 100);
		add(0, 20);
//		add(-1, 47);
		add(5, 16);
		add(4, 2);
		println(size())
		println(this)
	}
	temp.clear(false)
	println(temp)*/
	SuperList<Int>().apply {
		val list = SuperList<Int>().apply {
			add(0, 20);
			add(2);
			add(8);
			add(14);
			add(10, 100);
			add(0, 20);
			add(-1, 47);
			add(5, 16);
			add(4, 2);
			println(size())
			println(this)
			clear(false)
			(0..9).forEach { push(it) }
			println(size())
			println(this)
		}
		(0..4).forEach {
			add(list.pop())
			add(list.poll())
		}
		println(size())
		println(this)
		println(peekLast())
		println(peekFirst())
		println(size())
		clear(false)
		println(peekLast())
		println(peekFirst())
		println(size())
	}
	SuperList<Int>().apply {
		(0..49).forEach { add(it) }
		(0..49 step 3).forEach { print(get(it)) }
		println()
		(0..49 step 3).forEach { print(set(it, 0)) }
		(49 downTo 0 step 3).forEach { print(remove(it)) }
		println()
		println(size())
		println(this)
		println()
		println(remove(0))
		println(set(0,100))
		println(this)
	}
}
