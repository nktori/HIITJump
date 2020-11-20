package nktori.hiitjump.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import nktori.hiitjump.MainActivity
import nktori.hiitjump.R
import nktori.hiitjump.common.formatTime
import nktori.hiitjump.common.isActive
import nktori.hiitjump.skipDifficulty

class SkipService: Service() {
    private val CHANNEL_ID = "SkipService"
    private val NOTIFICATION_ID = 1

    private var totalSeconds = 0
    private var loopSeconds = 0
    private var exerciseIndex = 0
    private var currentExercise = skipDifficulty.workout.exercises[exerciseIndex]

    companion object {
        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, SkipService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startIntent)
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, SkipService::class.java)
            context.stopService(stopIntent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()

        startForeground(NOTIFICATION_ID, getNotification("Workout Started!", input!!))
        startWorkout()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startWorkout() {
        val workoutLength = skipDifficulty.workout.getTotalLength() + 1 // +1 needed for timer
        val totalLength = ((workoutLength + 3) * 1000).toLong()
        object : CountDownTimer(totalLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isActive) cancel()
                else {
                    when {
                        millisUntilFinished > (workoutLength * 1000) -> {
                            playAudio(R.raw.beep)
                        }
                        millisUntilFinished < (workoutLength * 1000) && millisUntilFinished > ((workoutLength - 1) * 1000) -> {
                            playAudio(currentExercise.audioFile)
                        }
                        else -> {
                            updateWorkout()
                        }
                    }
                    updateNotification(currentExercise.name, "${formatTime(totalSeconds)}  -  ${currentExercise.length - loopSeconds}")
                }
            }

            override fun onFinish() {
                sendUpdate(bundleOf(
                    Pair("finished", true),
                    Pair("totalTime", totalSeconds)
                ))
                playAudio(R.raw.beep)
            }
        }.start()
    }

    private fun updateWorkout() {
        val extras = Bundle()

        if (loopSeconds == skipDifficulty.workout.exercises[exerciseIndex].length) {
            loopSeconds = 0
            exerciseIndex++
            if (exerciseIndex == skipDifficulty.workout.exercises.size) {
                exerciseIndex = 0
            }
            currentExercise = skipDifficulty.workout.exercises[exerciseIndex]

            playAudio(currentExercise.audioFile)
        }

        extras.putString("exerciseName", currentExercise.name)
        extras.putInt("totalTime", totalSeconds)
        extras.putInt("loopTime", currentExercise.length - loopSeconds)
        sendUpdate(extras)

        totalSeconds++
        loopSeconds++
    }

    private fun sendUpdate(extras: Bundle) {
        val intent = Intent("workout-update")
        intent.putExtras(extras)
        this.sendBroadcast(intent)
    }

    private fun playAudio(file: Int) {
        try {
            MediaPlayer.create(this, file).start()
        } catch(e: Exception) {}
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Skip Service Channel", NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    private fun getNotification(title: String, text: String): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification(title: String, text: String) {
        val notification = getNotification(title, text)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}

