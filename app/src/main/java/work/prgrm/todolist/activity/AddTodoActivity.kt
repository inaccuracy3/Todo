package work.prgrm.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import work.prgrm.todolist.R
import work.prgrm.todolist.entity.*
import work.prgrm.todolist.picker.MDatePicker
import work.prgrm.todolist.repository.CategoryRepository
import work.prgrm.todolist.repository.TodoRepository
import work.prgrm.todolist.repository.TodoWithCategoryRepository
import java.util.*


class AddTodoActivity:AppCompatActivity() {
    private val todoRepository = TodoRepository()
    private val categoryRepository = CategoryRepository()
    private val todoWithCategoryRepository = TodoWithCategoryRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add)
        val datePicker = MDatePicker()
        val calendar = Calendar.getInstance()
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = getString(R.string.title_add_todo)
        var termType: TermType = TermType.ONCE
        var importanceType:ImportanceType = ImportanceType.ADDITIVE
        var category:CategoryEntity? = null
        var categoryList: MutableList<CategoryEntity>

        val textInputTodo:TextInputLayout = findViewById(R.id.text_input_todo)
        val editTodo:EditText = findViewById(R.id.edit_text_todo)
        val textInputStartDate:TextInputLayout = findViewById(R.id.text_input_start_date)
        val editStartDate:EditText = findViewById(R.id.edit_text_start_date)
        val textInputGoalDate:TextInputLayout = findViewById(R.id.text_input_goal_date)
        val editGoalDate:EditText = findViewById(R.id.edit_text_goal_date)
        val textInputDeadlineDate:TextInputLayout = findViewById(R.id.text_input_deadline_date)
        val editDeadlineDate:EditText = findViewById(R.id.edit_text_deadline_date)
        val termTypeMenu:TextInputLayout = findViewById(R.id.term_type_menu)
        val importanceTypeMenu:TextInputLayout = findViewById(R.id.enthusiasm_type_menu)
        val categoryMenu:TextInputLayout = findViewById(R.id.category_menu)

        val backButton: Button = findViewById(R.id.back_button)
        val addButton:Button = findViewById(R.id.save_button)



        editStartDate.setOnClickListener{
            datePicker.showDatePickerDialog(editStartDate,calendar,this)
        }

        editGoalDate.setOnClickListener{
            datePicker.showDatePickerDialog(editGoalDate,calendar,this)
        }

        editDeadlineDate.setOnClickListener{
            datePicker.showDatePickerDialog(editDeadlineDate,calendar,this)
        }

        val termTypeItems:MutableList<String> = mutableListOf()
        TermType.values().forEach {
            termTypeItems.add(it.name)
        }
        val termTypeAdapter = ArrayAdapter(this,R.layout.list_item,termTypeItems)
        (termTypeMenu.editText as? AutoCompleteTextView)?.setText(termTypeItems[0])
        (termTypeMenu.editText as? AutoCompleteTextView)?.setAdapter(termTypeAdapter)
        (termTypeMenu.editText as? AutoCompleteTextView)?.onItemClickListener =
            OnItemClickListener{_, _, i, _ ->
                termType = enumValueOf(termTypeAdapter.getItem(i)!!)
            }

        val importanceTypeItems:MutableList<String> = mutableListOf()
        ImportanceType.values().forEach{
            importanceTypeItems.add(it.name)
        }
        val importanceTypeAdapter = ArrayAdapter(this,R.layout.list_item,importanceTypeItems)
        (importanceTypeMenu.editText as? AutoCompleteTextView)?.setText(importanceTypeItems[0])
        (importanceTypeMenu.editText as? AutoCompleteTextView)?.setAdapter(importanceTypeAdapter)
        (importanceTypeMenu.editText as? AutoCompleteTextView)?.onItemClickListener =
            OnItemClickListener{_, _, i, _ ->
                importanceType = enumValueOf(importanceTypeAdapter.getItem(i)!!)
            }

        CoroutineScope(Dispatchers.Default).launch{
            categoryList = categoryRepository.selectAll()
            val categoryItems = mutableListOf<String>()
            categoryList.forEach {
                categoryItems.add(it.name)
            }
            CoroutineScope(Dispatchers.Main).launch{
                category = categoryList[0]
                val categoryAdapter = ArrayAdapter(this@AddTodoActivity,R.layout.list_item,categoryItems)
                (categoryMenu.editText as? AutoCompleteTextView)?.setText(categoryItems[0])
                (categoryMenu.editText as? AutoCompleteTextView)?.setAdapter(categoryAdapter)
                (categoryMenu.editText as? AutoCompleteTextView)?.setOnItemClickListener{_,_,i,_ ->
                    category = categoryList[i]
                    Log.i("category",category.toString())
                }
            }
        }



        addButton.setOnClickListener{
            when {
                editTodo.text.toString() == "" -> {
                    Toast.makeText(this,"Todoが登録されていません",Toast.LENGTH_SHORT).show()
                }
                editDeadlineDate.text.toString() == "" -> {
                    Toast.makeText(this,"締切日が登録されていません",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    CoroutineScope(Dispatchers.Default).launch {
                        todoRepository.insert(
                            TodoEntity(
                                0,
                                editTodo.text.toString(),
                                editStartDate.text.toString(),
                                editGoalDate.text.toString(),
                                editDeadlineDate.text.toString(),
                                IsDone.DOING,
                                importanceType,
                                termType
                            )
                        )
                        val lastTodo:TodoEntity = todoRepository.selectLast()
                        todoWithCategoryRepository.insert(TodoWithCategoryEntity(lastTodo.id,category!!.id))

                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        backButton.setOnClickListener{
            finish()
        }

    }
}