package ch.marcelfuchs.dutycalc.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.model.DutyDay
import kotlinx.android.synthetic.main.item_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dutyDayList = emptyList<DutyDay>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dutyDayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dutyDayList[position]

        holder.itemView.tv_date.text= currentItem.date.toString()
        holder.itemView.tv_dutyTime.text= currentItem.dutyTime.toString()

//        holder.itemView.rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    fun setData(dutyDay: List<DutyDay>) {
        this.dutyDayList = dutyDay
        notifyDataSetChanged()
    }
}