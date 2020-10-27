package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.exercise.ALT_CRISS_CROSS
import nktori.hiitjump.skip.exercise.BACKWARDS
import nktori.hiitjump.skip.exercise.CRISS_CROSS_INTENSE
import nktori.hiitjump.skip.exercise.DOUBLE_UND
import nktori.hiitjump.skip.exercise.MUMMY
import nktori.hiitjump.skip.exercise.REST_INTENSE
import nktori.hiitjump.skip.exercise.REST_LONG_INTENSE


class IntenseWorkout : Workout {
    override val exercises = listOf(
        CRISS_CROSS_INTENSE,
        REST_INTENSE,
        BACKWARDS,
        REST_INTENSE,
        ALT_CRISS_CROSS,
        REST_INTENSE,
        MUMMY,
        REST_INTENSE,
        DOUBLE_UND,
        REST_LONG_INTENSE
    )
}