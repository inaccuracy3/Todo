package work.prgrm.todolist.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDivider
import work.prgrm.todolist.R


class CategoryViewHolder(item:View):RecyclerView.ViewHolder(item) {
    val categoryName:TextView = item.findViewById(R.id.category_name)
    val barColor:MaterialDivider = item.findViewById(R.id.bar_color)
}
