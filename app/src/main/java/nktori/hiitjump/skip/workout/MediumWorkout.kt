package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.exercise.JACKS
import nktori.hiitjump.skip.exercise.LONG_REST
import nktori.hiitjump.skip.exercise.LUNGE
import nktori.hiitjump.skip.exercise.REST
import nktori.hiitjump.skip.exercise.SIDE
import nktori.hiitjump.skip.exercise.SQUATS

class MediumWorkout : Workout {
    override val exercises = listOf(
        JACKS,
        REST,
        SQUATS,
        REST,
        SIDE,
        REST,
        LUNGE,
        REST,
        JACKS,
        LONG_REST
    )
}