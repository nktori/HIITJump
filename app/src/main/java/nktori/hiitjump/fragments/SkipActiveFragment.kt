package nktori.hiitjump.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nktori.hiitjump.R
import nktori.hiitjump.blackoutMode
import nktori.hiitjump.common.formatTime
import nktori.hiitjump.skipDifficulty

class SkipActiveFragment: Fragment() {

    private var totalSeconds = 0
    private var loopSeconds = 0
    private var activityIndex = 0
    private var currentActivity = skipDifficulty.workout.activities[activityIndex]
    private var isRunning = false

    override fun onResume() {
        super.onResume()
        if (blackoutMode) {
            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
            activity?.window?.statusBarColor = activity?.resources?.getColor(R.color.blackout)!!
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        activity?.window?.statusBarColor = activity?.resources?.getColor(R.color.colorPrimaryDark)!!
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var layout = R.layout.fragment_skip_active
        if (blackoutMode) {
            layout = R.layout.fragment_skip_active_blackout
        }
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_skip_stop).setOnClickListener {
            isRunning = false
            findNavController().navigate(R.id.action_SkipActiveFragment_to_SkipFragment)
        }

        setTime(view.findViewById(R.id.skipTimeTotal), skipDifficulty.workout.getTotalLength())
        view.findViewById<TextView>(R.id.currentActivityName).text = currentActivity.name
        workoutTimer(view)
    }

    private fun workoutTimer(view: View) {
        isRunning = true
        val skipTimeCount = view.findViewById<TextView>(R.id.skipTimeCount)
        val currentActivityNameView = view.findViewById<TextView>(R.id.currentActivityName)
        val currentActivityTimeView = view.findViewById<TextView>(R.id.currentActivityTime)
        MediaPlayer.create(this.context, currentActivity.audioFile).start()
        object : CountDownTimer((skipDifficulty.workout.getTotalLength() * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isRunning) this.cancel()
                setTime(skipTimeCount, totalSeconds)
                setActivity(currentActivityNameView, currentActivityTimeView)
                totalSeconds++
                loopSeconds++
            }
            override fun onFinish() {
                setTime(skipTimeCount, totalSeconds)
                currentActivityNameView.text = getString(R.string.done)
                currentActivityTimeView.text = ""
                finishedButton(view.findViewById(R.id.button_skip_stop))
            }
        }.start()
    }

    private fun setActivity(nameView: TextView, timeView: TextView) {
        if (loopSeconds == skipDifficulty.workout.activities[activityIndex].length) {
            loopSeconds = 0
            activityIndex++
            if (activityIndex == skipDifficulty.workout.activities.size) {
                activityIndex = 0
            }
            currentActivity = skipDifficulty.workout.activities[activityIndex]
            nameView.text = currentActivity.name

            MediaPlayer.create(this.context, currentActivity.audioFile).start()
        }
        timeView.text = (currentActivity.length - loopSeconds).toString()
    }

    private fun setTime(textView: TextView, time: Int) {
        textView.text = formatTime(time)
    }

    private fun finishedButton(button: Button) {
        button.text = getString(R.string.back)
        button.background = resources.getDrawable(R.drawable.round_button_grey)
    }
}