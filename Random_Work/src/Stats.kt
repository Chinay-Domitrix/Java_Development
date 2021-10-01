class Stats {
	private val scoreList = ArrayList<ScoreInfo>()
	private fun record(score: Int) {
		var found = false
		var i = 0
		while (i < scoreList.size) {
			if (scoreList[i].score == score) {
				found = true
				break
			}
			i++
		}
		if (scoreList.size == 0 || !found) {
			scoreList.add(ScoreInfo(score))
			i = 0
			while (i < scoreList.size - 1) {
				var minIndex = i
				for (j in i + 1 until scoreList.size) if (scoreList[j].score < scoreList[minIndex].score) minIndex = j
				val pH = scoreList[i]
				scoreList[i] = scoreList[minIndex]
				scoreList[minIndex] = pH
				i++
			}
			return
		}
		scoreList[i].increment()
	}

	fun recordScores(stuScores: IntArray) = stuScores.forEach(this::record)
}

internal class ScoreInfo(val score: Int) {
	var frequency = 1
		private set

	fun increment() {
		frequency++
	}
}