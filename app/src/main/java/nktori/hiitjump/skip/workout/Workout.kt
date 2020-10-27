package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.exercise.Exercise

interface Workout {
    val exercises: List<Exercise>

    fun getTotalLength() = exercises.map { it.length }.sum()*5
}