package work.prgrm.todolist.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import work.prgrm.todolist.R

class TodoViewHolder(item: View) :  RecyclerView.ViewHolder(item){
    //layout 内の view を定義する。
    val card:MaterialCardView = item.findViewById(R.id.card)
    val textTodo:TextView = item.findViewById(R.id.text_todo)
    val textStartDate:TextView = item.findViewById(R.id.text_start_date)
    val textGoalDate:TextView = item.findViewById(R.id.text_goal_date)
    val textDeadlineDate:TextView = item.findViewById(R.id.text_deadline_date)
    val textTermType:TextView = item.findViewById(R.id.text_term_type)
    val textImportanceType:TextView = item.findViewById(R.id.text_importance_type)
}