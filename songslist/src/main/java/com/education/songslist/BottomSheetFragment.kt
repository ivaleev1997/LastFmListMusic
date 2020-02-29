package com.education.songslist

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.education.core_api.dto.LastFmTrack
import com.education.songslist.customView.CurveLfView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment() {

    companion object {
        fun newInstance(track: LastFmTrack) = BottomSheetFragment().apply{
            arguments = Bundle().apply { putSerializable(BOTTOM_SHEET_ARGEUMENTS_KEY, track) }
        }
        const val BOTTOM_SHEET_ARGEUMENTS_KEY = "last_fm_track_info"
    }

    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>
    private lateinit var curveLineView1: CurveLfView
    private lateinit var curveLineView2: CurveLfView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(R.id.design_bottom_sheet)
            behavior = sheet?.let { it1 -> BottomSheetBehavior.from(it1) } as BottomSheetBehavior<View>
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
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)

        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        curveLineView1 = view.findViewById(R.id.chartLfView1)
        curveLineView2 = view.findViewById(R.id.chartLfView2)
        val artist: TextView = view.findViewById(R.id.trackName)
        val trackName: TextView = view.findViewById(R.id.trackName)
        val listeners: TextView = view.findViewById(R.id.listeners)
        val playCounts: TextView = view.findViewById(R.id.playCounts)
        if (arguments != null) {
            val track = requireArguments()[BOTTOM_SHEET_ARGEUMENTS_KEY] as LastFmTrack
            artist.text = track.artist.name
            trackName.text = track.name
            listeners.text = track.listeners
            playCounts.text = track.playCount
            //binding.track = arguments!![BOTTOM_SHEET_ARGEUMENTS_KEY] as LastFmTrack?
            curveLineView1.start()
            curveLineView2.start()
        }
    }

    fun updateContent(lastFmTrack: LastFmTrack) {
        arguments?.putSerializable(BOTTOM_SHEET_ARGEUMENTS_KEY, lastFmTrack)
    }
}
