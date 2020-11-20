package nktori.hiitjump.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nktori.hiitjump.R
import nktori.hiitjump.blackoutMode
import nktori.hiitjump.common.formatTime
import nktori.hiitjump.skipDifficulty

class SkipFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        blackoutMode = false

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_SkipFragment_to_HomeFragment)
        }

        return inflater.inflate(R.layout.fragment_skip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.skipDifficultyText).text = skipDifficulty.name

        view.findViewById<TextView>(R.id.workoutLengthText).text = formatTime(skipDifficulty.workout.getTotalLength())

        view.findViewById<CheckBox>(R.id.checkbox_blackout).isChecked = false

        view.findViewById<Button>(R.id.button_skip_start).setOnClickListener {
            blackoutMode = view.findViewById<CheckBox>(R.id.checkbox_blackout).isChecked
            findNavController().navigate(R.id.action_SkipFragment_to_SkipActiveFragment)
        }

        view.findViewById<Button>(R.id.button_workout_info).setOnClickListener {
            findNavController().navigate(R.id.action_SkipFragment_to_ExercisesFragment)
        }
    }
}