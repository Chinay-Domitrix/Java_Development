package objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.chameleonCritters.fadingChameleonCritter

import info.gridworld.actor.Actor
import objectOriented.gridWorld.projects.critters.chameleonCritter.ChameleonCritter
import java.util.*

open class FadingChameleonCritter : ChameleonCritter() {
	override fun processActors(actors: ArrayList<Actor>) {
		color = if (actors.size == 0) color.darker() else actors[Random().nextInt(actors.size)].color
	}
}
