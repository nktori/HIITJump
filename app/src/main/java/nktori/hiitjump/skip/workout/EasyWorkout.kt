package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.activity.HIGH_KNEE
import nktori.hiitjump.skip.activity.LEFT
import nktori.hiitjump.skip.activity.LONG_REST
import nktori.hiitjump.skip.activity.REST
import nktori.hiitjump.skip.activity.RIGHT
import nktori.hiitjump.skip.activity.STANDARD

class EasyWorkout : Workout {
    override val activities = listOf(
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