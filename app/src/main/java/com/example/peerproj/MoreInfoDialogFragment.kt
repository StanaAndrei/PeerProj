package com.example.peerproj

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MoreInfoDialogFragment : DialogFragment() {
    private lateinit var builder: AlertDialog.Builder

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.fragment_more_info_dialog)
        return builder.create()
    }


}