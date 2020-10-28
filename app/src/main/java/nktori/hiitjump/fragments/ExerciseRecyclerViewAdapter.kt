package nktori.hiitjump.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import nktori.hiitjump.R

import nktori.hiitjump.skip.exercise.Exercise

class ExerciseRecyclerViewAdapter(
    private val exercises: List<Exercise>
) : RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_exercises, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exercises[position]
        holder.exerciseLengthView.text = "${item.length} seconds"
        holder.exerciseNameView.text = item.name
    }

    override fun getItemCount(): Int = exercises.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exerciseNameView: TextView = view.findViewById(R.id.exercise_name)
        val exerciseLengthView: TextView = view.findViewById(R.id.exercise_length)
    }
}