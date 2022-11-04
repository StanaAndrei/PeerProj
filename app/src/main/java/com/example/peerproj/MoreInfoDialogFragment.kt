package com.example.peerproj

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MoreInfoDialogFragment : DialogFragment() {
    private lateinit var builder: AlertDialog.Builder

    companion object {
        private const val TARGET_URL = "http://www.moma.org"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.fragment_more_info_dialog)
        builder.setPositiveButton(R.string.more_info_poz) { _, _ ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(TARGET_URL)
            startActivity(intent)
        }
        builder.setNegativeButton(R.string.more_info_neg) { dialog, _ ->
            dialog.cancel()
        }
        return builder.create()
    }


}