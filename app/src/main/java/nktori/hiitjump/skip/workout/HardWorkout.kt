package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.activity.BOXER
import nktori.hiitjump.skip.activity.CRISS_CROSS
import nktori.hiitjump.skip.activity.HIGH_HARD
import nktori.hiitjump.skip.activity.LONG_REST
import nktori.hiitjump.skip.activity.OFF_STEP
import nktori.hiitjump.skip.activity.REST

class HardWorkout : Workout {
    override val activities = listOf(
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