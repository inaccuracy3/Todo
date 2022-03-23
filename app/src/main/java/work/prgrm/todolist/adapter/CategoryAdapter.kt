package work.prgrm.todolist.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import work.prgrm.todolist.R
import work.prgrm.todolist.activity.EditCategoryActivity
import work.prgrm.todolist.application.App
import work.prgrm.todolist.entity.CategoryEntity
import work.prgrm.todolist.viewholder.CategoryViewHolder


class CategoryAdapter(private val list:List<CategoryEntity>,):RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        itemView.setBackgroundResource(R.drawable.view_pressed_selector_drawable)
        return CategoryViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryName.text = list[position].name
        holder.categoryName.setTextColor(list[position].color)

        @Suppress("DEPRECATION")
        holder.categoryName.setShadowLayer(1.1f,1.2f,1.2f, App.res.getColor(R.color.light_gray))

        holder.barColor.dividerColor = list[position].color
        holder.itemView.setOnClickListener(){
            var context = holder.itemView.context
            Toast.makeText(context,"ながおしでカテゴリーをへんしゅう",Toast.LENGTH_SHORT).show()
            Log.i("item clicked",position.toString())
        }
        holder.itemView.setOnLongClickListener(){
            Log.i("item long clicked",position.toString())
            var context = holder.itemView.context
            val intent = Intent(context,EditCategoryActivity::class.java)
            intent.putExtra("category",list[position])
            context.startActivity(intent)
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

