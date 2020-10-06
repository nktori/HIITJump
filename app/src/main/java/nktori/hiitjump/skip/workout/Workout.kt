package nktori.hiitjump.skip.workout

import nktori.hiitjump.skip.activity.Activity

interface Workout {
    val activities: List<Activity>

    fun getTotalLength() = activities.map { it.length }.sum()*5
}