package nktori.hiitjump.common

fun formatTime(time: Int): String {
    val minutes = time % 3600 / 60
    val secs = time % 60

    return String.format("%02d:%02d", minutes, secs)
}

var isActive = false