package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.exercise.BOXER
import nktori.hiitjump.skip.exercise.CRISS_CROSS
import nktori.hiitjump.skip.exercise.HIGH_HARD
import nktori.hiitjump.skip.exercise.LONG_REST
import nktori.hiitjump.skip.exercise.OFF_STEP
import nktori.hiitjump.skip.exercise.REST

class HardWorkout : Workout {
    override val exercises = listOf(
        BOXER,
        REST,
        OFF_STEP,
        REST,
        CRISS_CROSS,
        REST,
        HIGH_HARD,
        REST,
        BOXER,
        LONG_REST
    )
}