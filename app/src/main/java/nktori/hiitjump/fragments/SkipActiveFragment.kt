package nktori.hiitjump.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nktori.hiitjump.R
import nktori.hiitjump.blackoutMode
import nktori.hiitjump.common.formatTime
import nktori.hiitjump.common.isActive
import nktori.hiitjump.services.SkipService
import nktori.hiitjump.skipDifficulty

class SkipActiveFragment: Fragment() {

    private var exerciseIndex = 0
    private var currentExercise = skipDifficulty.workout.exercises[exerciseIndex]

    lateinit var skipTimeCountView: TextView
    lateinit var currentExerciseNameView: TextView
    lateinit var currentExerciseTimeView: TextView

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val extras = intent.extras
            val totalTime = extras?.get("totalTime")
            val loopTime = extras?.get("loopTime")
            val exerciseName = extras?.get("exerciseName")

            if (totalTime != null) {
                skipTimeCountView.text = formatTime(extras["totalTime"] as Int)
            }
            if (extras?.get("finished") != null) {
                currentExerciseNameView.text = getString(R.string.done)
                currentExerciseTimeView.text = ""
                setFinishedButton()
                SkipService.stopService(context)
            } else {
                exerciseName.let {
                    if (it != null) setExercise(exerciseName as String, loopTime as Int)
                    else setExercise(null, loopTime as Int)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (blackoutMode) {
            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
            activity?.window?.statusBarColor = activity?.resources?.getColor(R.color.blackout)!!
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        context!!.registerReceiver(broadcastReceiver, IntentFilter("workout-update"))
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        activity?.window?.statusBarColor = activity?.resources?.getColor(R.color.colorPrimaryDark)!!
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        context!!.unregisterReceiver(broadcastReceiver);
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

        skipTimeCountView = view.findViewById(R.id.skipTimeCount)
        currentExerciseNameView = view.findViewById(R.id.currentExerciseName)
        currentExerciseTimeView = view.findViewById(R.id.currentExerciseTime)

        view.findViewById<Button>(R.id.button_skip_stop).setOnClickListener {
            isActive = false
            SkipService.stopService(context!!)
            findNavController().navigate(R.id.action_SkipActiveFragment_to_SkipFragment)
        }

        view.findViewById<TextView>(R.id.skipTimeTotal).text = formatTime(skipDifficulty.workout.getTotalLength())

        view.findViewById<TextView>(R.id.currentExerciseName).text = currentExercise.name
        isActive = true
        SkipService.startService(context!!, "Skip Service in running")
    }

    private fun setExercise(exerciseName: String?, loopSeconds: Int) {
        if (exerciseName != null && exerciseName != currentExerciseNameView.text) currentExerciseNameView.text = exerciseName
        currentExerciseTimeView.text = loopSeconds.toString()
    }

    private fun setFinishedButton() {
        val button = view!!.findViewById<Button>(R.id.button_skip_stop)
        button.text = getString(R.string.back)
        button.background = resources.getDrawable(R.drawable.round_button_grey)
    }
}
