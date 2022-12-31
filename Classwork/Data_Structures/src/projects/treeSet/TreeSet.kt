package projects.treeSet

class TreeSet<E : Comparable<E>?> {
	private var root: TreeNode<E>? = null
	var size = 0
	private var st: String? = null
	fun add(value: E) {
		if (!this.contains(root, value)) {
			if (root == null) root = TreeNode(value) else add(root, value)
			size++
		}
	}

	fun add(root: TreeNode<E>?, value: E): Unit = when {
		root!!.value!! > value -> when (root.left) {
			null -> root.left = TreeNode(value)
			else -> add(root.left, value)
		}
		root.right == null -> root.right = TreeNode(value)
		else -> add(root.right, value)
	}

	fun addAll(values: Collection<E>) = values.forEach(::add)

	fun addAll(vararg values: E) = values.forEach(::add)

	fun contains(root: TreeNode<E>?, value: E?): Boolean {
		if (root == null) return false
		if (root.value == value) return true
		if (value!! < root.value) return contains(root.left, value)
		return contains(root.right, value)
	}

	private fun minValue(root: TreeNode<E>?): E {
		var min = root!!.value
		var current = root.left
		while (current != null) {
			min = current.value
			current = current.left
		}
		return min
	}

	fun remove(value: E) {
		if (this.contains(root, value)) {
			root = remove(root, value)
			size--
		}
	}

	private fun remove(root: TreeNode<E>?, value: E): TreeNode<E>? {
		when {
			root!!.value == value -> when {
				(root.left == null) && (root.right == null) -> return null
				root.left == null -> return root.right
				root.right == null -> return root.left
				else -> {
					root.value = minValue(root.right)
					root.right = remove(root.right, root.value)
				}
			}
			value!! < root.value -> root.left = remove(root.left, value)
			else -> root.right = remove(root.right, value)
		}
		return root
	}

	fun removeAll(values: Collection<E>) = values.forEach(::remove)

	fun removeAll(vararg values: E) = values.forEach(::remove)

	fun preOrder(): String {
		st = "["
		preOrder(root)
		return "${st!!.substringBeforeLast(",")}]"
	}

	private fun preOrder(root: TreeNode<E>?) {
		if (root == null) return
		st += "${root.value}, "
		preOrder(root.left)
		preOrder(root.right)
	}

	fun inOrder(): String {
		st = "["
		inOrder(root)
		return "${st!!.substringBeforeLast(",")}]"
	}

	private fun inOrder(root: TreeNode<E>?) {
		if (root == null) return
		inOrder(root.left)
		st += "${root.value}, "
		inOrder(root.right)
	}

	fun postOrder(): String {
		st = "["
		postOrder(root)
		return "${st!!.substringBeforeLast(",")}]"
	}

	private fun postOrder(root: TreeNode<E>?) {
		if (root == null) return
		postOrder(root.left)
		postOrder(root.right)
		st += "${root.value}, "
	}

	inner class TreeNode<E : Comparable<E>?>(var value: E) {
		var right: TreeNode<E>? = null
		var left: TreeNode<E>? = null
		override fun toString() = "$value"
	}
}
