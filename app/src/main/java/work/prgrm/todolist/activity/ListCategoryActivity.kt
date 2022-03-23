package work.prgrm.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import work.prgrm.todolist.R
import work.prgrm.todolist.adapter.CategoryAdapter
import work.prgrm.todolist.entity.CategoryEntity
import work.prgrm.todolist.repository.CategoryRepository


class ListCategoryActivity : AppCompatActivity() {
    private var recyclerCategory:RecyclerView? = null
    private var items = mutableListOf<CategoryEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        recyclerCategory = findViewById(R.id.recycler_category)


        recyclerCategory?.adapter = CategoryAdapter(items)
        recyclerCategory?.layoutManager = LinearLayoutManager(this)

        val todolistButton: Button = findViewById(R.id.todolist_button)
        val addCategoryButton: Button = findViewById(R.id.add_category_button)

        todolistButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        addCategoryButton.setOnClickListener{
            val intent = Intent(this,AddCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
        val categoryRepository = CategoryRepository()
        CoroutineScope(Dispatchers.Default).launch {
            items = categoryRepository.selectAll()
            withContext(Dispatchers.Main) {
                recyclerCategory?.adapter = CategoryAdapter(items)
                val adapterTodo = recyclerCategory?.adapter
                adapterTodo?.notifyDataSetChanged()
            }
        }
    }
}