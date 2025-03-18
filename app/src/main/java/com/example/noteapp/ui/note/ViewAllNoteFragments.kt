package com.example.noteapp.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [ViewAllNoteFragments.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewAllNoteFragments : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_fragments, container, false)
    }
}