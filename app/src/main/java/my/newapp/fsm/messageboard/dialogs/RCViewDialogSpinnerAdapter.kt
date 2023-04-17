package my.newapp.fsm.messageboard.dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.newapp.fsm.messageboard.R

class RCViewDialogSpinnerAdapter: RecyclerView.Adapter<RCViewDialogSpinnerAdapter.SpViewHolder>() {
    val mainList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sp_list_item, parent, false)
        return SpViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpViewHolder, position: Int) {
        holder.setData(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    class SpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(text: String) {
            val tvSpItem: TextView = itemView.findViewById(R.id.tvSpItem)
            tvSpItem.text = text
        }
    }

    fun updateAdapter(list: ArrayList<String>) {
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }
}