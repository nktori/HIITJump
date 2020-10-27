package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.exercise.HIGH_KNEE
import nktori.hiitjump.skip.exercise.LEFT
import nktori.hiitjump.skip.exercise.LONG_REST
import nktori.hiitjump.skip.exercise.REST
import nktori.hiitjump.skip.exercise.RIGHT
import nktori.hiitjump.skip.exercise.STANDARD

class EasyWorkout : Workout {
    override val exercises = listOf(
        STANDARD,
        REST,
        RIGHT,
        REST,
        LEFT,
        REST,
        HIGH_KNEE,
        REST,
        STANDARD,
        LONG_REST
    )
}