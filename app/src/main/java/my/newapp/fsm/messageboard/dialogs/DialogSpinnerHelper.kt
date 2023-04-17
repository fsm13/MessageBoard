package my.newapp.fsm.messageboard.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.SearchEvent
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.newapp.fsm.messageboard.R
import my.newapp.fsm.messageboard.utils.CityHelper

class DialogSpinnerHelper {

    fun showSpinnerDialog(context: Context, list: ArrayList<String>) {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_layout, null)
        val adapter = RCViewDialogSpinnerAdapter()
        val rcView = view.findViewById<RecyclerView>(R.id.rcSpView)
        val sv = view.findViewById<SearchView>(R.id.svSpinner)
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter
        builder.setView(view)
        adapter.updateAdapter(list)
        setSearchViewListener(adapter, list, sv)
        builder.show()
    }

    private fun setSearchViewListener(
        adapter: RCViewDialogSpinnerAdapter,
        list: ArrayList<String>,
        sv: SearchView?
    )
    {
        sv?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val tempList = CityHelper.filterListData(list, newText)
                adapter.updateAdapter(tempList)
                return true
            }
        })
    }
}