package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.activity.JACKS
import nktori.hiitjump.skip.activity.LONG_REST
import nktori.hiitjump.skip.activity.LUNGE
import nktori.hiitjump.skip.activity.REST
import nktori.hiitjump.skip.activity.SIDE
import nktori.hiitjump.skip.activity.SQUATS

class MediumWorkout : Workout {
    override val activities = listOf(
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