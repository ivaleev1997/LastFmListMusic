package com.lastfmlistmusic.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lastfmlistmusic.BOTTOM_SHEET_ARGEUMENTS_KEY
import com.lastfmlistmusic.R
import com.lastfmlistmusic.data.remote.model.LastFmTrack
import com.lastfmlistmusic.databinding.FragmentBottomSheetBinding
import com.lastfmlistmusic.view.CurveLfView

class BottomSheetFragment: BottomSheetDialogFragment() {

    companion object {
        fun newInstance(track: LastFmTrack) = BottomSheetFragment().apply{
            arguments = Bundle().apply { putSerializable(BOTTOM_SHEET_ARGEUMENTS_KEY, track) }
        }
    }

    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>
    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var curveLineView1: CurveLfView
    private lateinit var curveLineView2: CurveLfView



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(R.id.design_bottom_sheet)
            behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        curveLineView1 = view.findViewById(R.id.chartLfView1)
        curveLineView2 = view.findViewById(R.id.chartLfView2)
        if (arguments != null) {
            binding.track = arguments!![BOTTOM_SHEET_ARGEUMENTS_KEY] as LastFmTrack?
            curveLineView1.start()
            curveLineView2.start()
        }
    }

    fun updateContent(lastFmTrack: LastFmTrack) {
        arguments?.putSerializable(BOTTOM_SHEET_ARGEUMENTS_KEY, lastFmTrack)
    }
}