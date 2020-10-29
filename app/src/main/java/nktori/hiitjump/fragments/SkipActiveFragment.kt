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
    private var exerciseIndex = 0
    private var currentExercise = skipDifficulty.workout.exercises[exerciseIndex]
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
        view.findViewById<TextView>(R.id.currentExerciseName).text = currentExercise.name
        workoutTimer(view)
    }

    private fun workoutTimer(view: View) {
        isRunning = true
        val skipTimeCount = view.findViewById<TextView>(R.id.skipTimeCount)
        val currentExerciseNameView = view.findViewById<TextView>(R.id.currentExerciseName)
        val currentExerciseTimeView = view.findViewById<TextView>(R.id.currentExerciseTime)

        val workoutLength = skipDifficulty.workout.getTotalLength() + 1 // +1 needed for timer
        val totalLength = ((workoutLength + 3) * 1000).toLong()
        object : CountDownTimer(totalLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isRunning) cancel()

                when {
                    millisUntilFinished > (workoutLength * 1000) -> {
                        playAudio(R.raw.beep)
                    }
                    millisUntilFinished < (workoutLength * 1000) && millisUntilFinished > ((workoutLength - 1) * 1000) -> {
                        playAudio(currentExercise.audioFile)
                    }
                    else -> {
                        setTime(skipTimeCount, totalSeconds)
                        setExercise(currentExerciseNameView, currentExerciseTimeView)
                        totalSeconds++
                        loopSeconds++
                    }
                }
            }

            override fun onFinish() {
                setTime(skipTimeCount, totalSeconds)
                currentExerciseNameView.text = getString(R.string.done)
                currentExerciseTimeView.text = ""
                finishedButton(view.findViewById(R.id.button_skip_stop))
                playAudio(R.raw.beep)
            }
        }.start()
    }

    private fun setExercise(nameView: TextView, timeView: TextView) {
        if (loopSeconds == skipDifficulty.workout.exercises[exerciseIndex].length) {
            loopSeconds = 0
            exerciseIndex++
            if (exerciseIndex == skipDifficulty.workout.exercises.size) {
                exerciseIndex = 0
            }
            currentExercise = skipDifficulty.workout.exercises[exerciseIndex]
            nameView.text = currentExercise.name

            playAudio(currentExercise.audioFile)
        }
        timeView.text = (currentExercise.length - loopSeconds).toString()
    }

    private fun setTime(textView: TextView, time: Int) {
        textView.text = formatTime(time)
    }

    private fun finishedButton(button: Button) {
        button.text = getString(R.string.back)
        button.background = resources.getDrawable(R.drawable.round_button_grey)
    }

    private fun playAudio(file: Int) {
        try {
            MediaPlayer.create(context, file).start()
        } catch(e: Exception) {}
    }
}