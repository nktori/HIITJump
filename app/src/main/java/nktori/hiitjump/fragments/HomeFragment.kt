package nktori.hiitjump.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nktori.hiitjump.R
import nktori.hiitjump.skip.Difficulty
import nktori.hiitjump.skipDifficulty

class HomeFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_easy).setOnClickListener {
            setDifficulty(Difficulty.EASY)
            findNavController().navigate(R.id.action_FirstFragment_to_SkipFragment)
        }

        view.findViewById<Button>(R.id.button_medium).setOnClickListener {
            setDifficulty(Difficulty.MEDIUM)
            findNavController().navigate(R.id.action_FirstFragment_to_SkipFragment)
        }

        view.findViewById<Button>(R.id.button_hard).setOnClickListener {
            setDifficulty(Difficulty.HARD)
            findNavController().navigate(R.id.action_FirstFragment_to_SkipFragment)
        }

        view.findViewById<Button>(R.id.button_intense).setOnClickListener {
            setDifficulty(Difficulty.INTENSE)
            findNavController().navigate(R.id.action_FirstFragment_to_SkipFragment)
        }
    }

    private fun setDifficulty(difficulty: Difficulty) {
        skipDifficulty = difficulty
    }
}