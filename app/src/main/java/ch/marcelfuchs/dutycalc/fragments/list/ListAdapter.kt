package ch.marcelfuchs.dutycalc.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.marcelfuchs.dutycalc.R
import ch.marcelfuchs.dutycalc.model.Tour
import kotlinx.android.synthetic.main.item_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var tourList = emptyList<Tour>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tourList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tourList[position]

        holder.itemView.day1DutyTime_tv.text= currentItem.dutyDayList[0].dutyTime.toString()
        holder.itemView.day2DutyTime_tv.text= currentItem.dutyDayList[1].dutyTime.toString()
        holder.itemView.day3DutyTime_tv.text= currentItem.dutyDayList[2].dutyTime.toString()
        holder.itemView.day4DutyTime_tv.text= currentItem.dutyDayList[3].dutyTime.toString()
        holder.itemView.day5DutyTime_tv.text= currentItem.dutyDayList[4].dutyTime.toString()
        holder.itemView.day6DutyTime_tv.text= currentItem.dutyDayList[5].dutyTime.toString()
        holder.itemView.day7DutyTime_tv.text= currentItem.dutyDayList[6].dutyTime.toString()
        holder.itemView.totalHours_tv.text= currentItem.totalHours.toString()

//        holder.itemView.rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    fun setData(tour: List<Tour>) {
        this.tourList = tour
        notifyDataSetChanged()
    }
}