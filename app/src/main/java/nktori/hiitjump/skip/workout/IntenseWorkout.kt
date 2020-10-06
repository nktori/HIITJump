package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.activity.ALT_CRISS_CROSS
import nktori.hiitjump.skip.activity.BACKWARDS
import nktori.hiitjump.skip.activity.CRISS_CROSS_INTENSE
import nktori.hiitjump.skip.activity.DOUBLE_UND
import nktori.hiitjump.skip.activity.MUMMY
import nktori.hiitjump.skip.activity.REST_INTENSE
import nktori.hiitjump.skip.activity.REST_LONG_INTENSE


class IntenseWorkout : Workout {
    override val activities = listOf(
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