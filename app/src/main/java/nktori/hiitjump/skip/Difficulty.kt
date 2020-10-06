package nktori.hiitjump.skip

import nktori.hiitjump.skip.workout.EasyWorkout
import nktori.hiitjump.skip.workout.HardWorkout
import nktori.hiitjump.skip.workout.IntenseWorkout
import nktori.hiitjump.skip.workout.MediumWorkout
import nktori.hiitjump.skip.workout.Workout

enum class Difficulty(val workout: Workout) {
    EASY(EasyWorkout()),
    MEDIUM(MediumWorkout()),
    HARD(HardWorkout()),
    INTENSE(IntenseWorkout())
}