package nktori.hiitjump.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
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
        return inflater.inflate(R.layout.fragment_skip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val difficultTextView = view.findViewById<TextView>(R.id.skipDifficultyText)
        difficultTextView.text = skipDifficulty.name

        val workoutLengthTextView = view.findViewById<TextView>(R.id.workoutLengthText)
        workoutLengthTextView.text = formatTime(skipDifficulty.workout.getTotalLength())

        view.findViewById<Button>(R.id.button_skip_back).setOnClickListener {
            findNavController().navigate(R.id.action_SkipFragment_to_HomeFragment)
        }

        view.findViewById<Button>(R.id.button_skip_start).setOnClickListener {
            findNavController().navigate(R.id.action_SkipFragment_to_SkipActiveFragment)
        }
    }
}