package work.prgrm.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import work.prgrm.todolist.R
import work.prgrm.todolist.application.App
import work.prgrm.todolist.entity.ImportanceType
import work.prgrm.todolist.entity.TodoEntity
import work.prgrm.todolist.entity.TodolistWithCategoriesEntity
import work.prgrm.todolist.viewholder.TodoViewHolder

class TodoAdapter(private val list:List<TodolistWithCategoriesEntity>): RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.card.setOnClickListener{

        }

        var str: String?
        holder.textTodo.text = list[position].todoEntity.todoText
        str = App.res.getString(R.string.start_date)+":"+list[position].todoEntity.startDateText
        holder.textStartDate.text = str
        str = App.res.getString(R.string.goal_date)+":"+list[position].todoEntity.goalDateText
        holder.textGoalDate.text = str
        str = App.res.getString(R.string.deadline_date)+":"+list[position].todoEntity.deadlineDateText
        holder.textDeadlineDate.text = str

        holder.textTermType.text = list[position].todoEntity.termType.name
        holder.textImportanceType.text = list[position].todoEntity.importanceType.name
        val color = when(list[position].todoEntity.importanceType){
            ImportanceType.ADDITIVE -> {
                App.res.getColor(R.color.yellow,context.theme)
            }
            ImportanceType.LOW -> {
                App.res.getColor(R.color.green_100,context.theme)
            }
            ImportanceType.MIDDLE -> {
                App.res.getColor(R.color.green_300,context.theme)
            }
            ImportanceType.HIGH -> {
                App.res.getColor(R.color.green_500,context.theme)
            }
            ImportanceType.SUPERHIGH -> {
                App.res.getColor(R.color.green_700,context.theme)
            }
        }
        holder.textImportanceType.setBackgroundColor(color)
        holder.card.strokeColor = list[position].categoryEntity.color
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(items:List<TodoEntity>){

    }
}

