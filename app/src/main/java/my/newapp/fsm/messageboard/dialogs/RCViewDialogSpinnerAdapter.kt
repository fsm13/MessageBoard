package my.newapp.fsm.messageboard.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.newapp.fsm.messageboard.R
import my.newapp.fsm.messageboard.act.EditAdsActivity
import org.w3c.dom.Text

class RCViewDialogSpinnerAdapter(var tvSelection: TextView, var dialog: AlertDialog)
    : RecyclerView.Adapter<RCViewDialogSpinnerAdapter.SpViewHolder>() {
    val mainList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sp_list_item, parent, false)
        return SpViewHolder(view, tvSelection, dialog)
    }

    override fun onBindViewHolder(holder: SpViewHolder, position: Int) {
        holder.setData(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    class SpViewHolder(itemView: View, var tvSelection: TextView, var dialog: AlertDialog)
        : RecyclerView.ViewHolder(itemView), OnClickListener{
        private var itemText = ""
        fun setData(text: String) {
            val tvSpItem: TextView = itemView.findViewById(R.id.tvSpItem)
            tvSpItem.text = text
            itemText = text
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            tvSelection.text = itemText
            dialog.dismiss()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(list: ArrayList<String>) {
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }
}