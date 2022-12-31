package projects.treeSet

import java.lang.Math.random
import kotlin.io.println as p

fun main() = mainFinal()

fun mainInitial() {
	val set = TreeSet<String>()
	(0..19).forEach { _ ->
		val n = (random() * 20).toInt()
		print("${(n + 65).toChar()}")
		set.add("${(n + 65).toChar()}")
	}
	p()
	val preOrderSet = TreeSet<String>()
	var st = set.preOrder()
	st = st.substring(1, st.length - 1)
	val pre = st.split(", ").dropLastWhile(String::isEmpty)
	pre.indices.forEach { preOrderSet.add(pre[it]) }
	val inOrderSet = TreeSet<String>()
	var st2 = set.inOrder()
	st2 = st2.substring(1, st2.length - 1)
	val `in` = st2.split(", ").dropLastWhile(String::isEmpty)
	(0 until `in`.size - 1).forEach { inOrderSet.add(`in`[it]) }
	val postOrderSet = TreeSet<String>()
	var st3 = set.postOrder()
	st3 = st3.substring(1, st3.length - 1)
	val post = st3.split(", ").dropLastWhile(String::isEmpty)
	for (x in 0 until post.size - 1) postOrderSet.add(post[x])
	p("Original Set - PreOrder: " + set.preOrder())
	p("\nOriginal Set - InOrder: " + set.inOrder())
	p("Original Set - PostOrder: " + set.postOrder())
	p("****************************")
	p("PreOrder Copy - PreOrder: " + preOrderSet.preOrder())
	p("\nPreOrder Copy - InOrder: " + preOrderSet.inOrder())
	p("\nPreOrder Copy - PostOrder:" + preOrderSet.postOrder())
	p("****************************")
	p("InOrder Copy - PreOrder: " + inOrderSet.preOrder())
	p("\nInOrder Copy - InOrder: " + inOrderSet.inOrder())
	p("\nInOrder Copy - PostOrder: " + inOrderSet.postOrder())
	p("****************************")
	p("PostOrder Copy - PreOrder: " + postOrderSet.preOrder())
	p("\nPostOrder Copy - InOrder: " + postOrderSet.inOrder())
	p("\nPostOrder Copy - PostOrder: " + postOrderSet.postOrder())
	p("****************************")
}

fun mainMid() {
	val set = TreeSet<String>()
	for (x in 0..19) {
		val n = (Math.random() * 100).toInt()
		print("$n, ")
		set.add("" + n)
	}
	p()
	val preOrderSet = TreeSet<String>()
	var st = set.preOrder()
	st = st.substring(1, st.length - 1)
	val pre = st.split(", ").dropLastWhile(String::isEmpty)
	pre.indices.forEach { preOrderSet.add(pre[it]) }
	val inOrderSet = TreeSet<String>()
	st = set.inOrder()
	st = st.substring(1, st.length - 1)
	val `in` = st.split(", ").dropLastWhile(String::isEmpty)
	for (x in 0 until `in`.size - 1) inOrderSet.add(`in`[x])
	val postOrderSet = TreeSet<String>()
	st = set.postOrder()
	st = st.substring(1, st.length - 1)
	val post = st.split(", ").dropLastWhile(String::isEmpty)
	for (x in 0 until post.size - 1) postOrderSet.add(post[x])
	p("Original Set - PreOrder: ${set.preOrder()}\n")
	p("Original Set - InOrder: ${set.inOrder()}\n")
	p("Original Set - PostOrder: ${set.postOrder()}")
	p("****************************")
	p("PreOrder Copy - PreOrder: ${preOrderSet.preOrder()}\n")
	p("PreOrder Copy - InOrder: ${preOrderSet.inOrder()}\n")
	p("PreOrder Copy - PostOrder:${preOrderSet.postOrder()}")
	p("****************************")
	p("InOrder Copy - PreOrder: ${inOrderSet.preOrder()}\n")
	p("InOrder Copy - InOrder: ${inOrderSet.inOrder()}\n")
	p("InOrder Copy - PostOrder: ${inOrderSet.postOrder()}")
	p("****************************")
	p("PostOrder Copy - PreOrder: ${postOrderSet.preOrder()}\n")
	p("PostOrder Copy - InOrder: ${postOrderSet.inOrder()}\n")
	p("PostOrder Copy - PostOrder: ${postOrderSet.postOrder()}")
	p("****************************")
}

fun mainFinal() {
	val set = TreeSet<Int>().apply { addAll(30, 20, 50, 45, 10, 46, 10, 15, 47, 60) }
	fun x() {
		p("Original Set - PreOrder:\nSize: ${set.size}\n${set.preOrder()}\n")
		p("Original Set - InOrder:\nSize: ${set.size}\n${set.inOrder()}\n")
		p("Original Set - PostOrder:\nSize: ${set.size}\n${set.postOrder()}")
		p("\n****************************\n")
	}
	x()
	set.removeAll(60, 44, 30, 30)
	x()
}
