package work.prgrm.todolist.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.google.android.material.divider.MaterialDivider
import com.madrapps.pikolo.ColorPicker
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import work.prgrm.todolist.R
import work.prgrm.todolist.application.App
import work.prgrm.todolist.entity.CategoryEntity
import work.prgrm.todolist.repository.CategoryRepository


class EditCategoryActivity : AppCompatActivity() {
    private val categoryRepository = CategoryRepository()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_edit)
        val category = intent.getSerializableExtra("category") as CategoryEntity
            Log.i("name", category.name)
            Log.i("id", category.id.toString())
            Log.i("color", category.color.toString(), Throwable())
        val editCurrentCategory:EditText = findViewById(R.id.edit_current_category)
        val editNewCategory:EditText = findViewById(R.id.edit_new_category)
        val textCurrentColor:TextView = findViewById(R.id.text_current_color)
        val textNewColor:TextView = findViewById(R.id.text_new_color)
        val barCurrentColor:MaterialDivider = findViewById(R.id.bar_current_color)
        val barNewColor:MaterialDivider = findViewById(R.id.bar_new_color)
        val colorPicker:ColorPicker = findViewById(R.id.color_picker)
        val colorCenter:ImageView = findViewById(R.id.color_center)
        val backButton: Button = findViewById(R.id.back_button)
        val saveButton:Button = findViewById(R.id.save_button)
        val deleteButton:Button = findViewById(R.id.delete_button)

        editNewCategory.isActivated = false


        textCurrentColor.text = textCurrentColor.text.toString()
        textCurrentColor.setTextColor(category.color)
        textCurrentColor.paint.setShadowLayer(1.1f,1.2f,1.2f, App.res.getColor(R.color.light_gray,this.theme))
        textNewColor.paint.setShadowLayer(1.1f,1.2f,1.2f, App.res.getColor(R.color.light_gray,this.theme))
        barCurrentColor.dividerColor = category.color
        editCurrentCategory.setText(category.name)
        colorPicker.setColorSelectionListener(object : SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {

                textNewColor.setTextColor(color)

                barNewColor.dividerColor = color

                colorCenter.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(color, BlendModeCompat.SRC_ATOP)
//                @Suppress("DEPRECATION")
//                colorCenter.background.setColorFilter(color,PorterDuff.Mode.MULTIPLY)
            }
        })

        backButton.setOnClickListener{
            finish()
        }

        saveButton.setOnClickListener{
            //update category
            CoroutineScope(Dispatchers.Default).launch {
                var categoryName = editNewCategory.text.toString()
                if(categoryName == "" || categoryName == " "){
                    categoryName = category.name
                }
                categoryRepository.update(CategoryEntity(category.id,categoryName,barNewColor.dividerColor))
                withContext(Dispatchers.Main){
                    finish()
                }
            }
        }

        deleteButton.setOnClickListener{
            //delete category
            CoroutineScope(Dispatchers.Default).launch{
                if(category.id != 0L && category.id != 1L) {
                    categoryRepository.delete(category)
                }
                withContext(Dispatchers.Main){
                    finish()
                }
            }

        }
    }
}