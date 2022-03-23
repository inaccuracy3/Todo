package work.prgrm.todolist.activity

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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
import work.prgrm.todolist.entity.CategoryEntity
import work.prgrm.todolist.repository.CategoryRepository

class AddCategoryActivity : AppCompatActivity() {
    private val repository = CategoryRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_add)

        val editCategory:EditText = findViewById(R.id.edit_category_name)
        val barColor:MaterialDivider = findViewById(R.id.bar_color)
        val colorPicker: ColorPicker = findViewById(R.id.color_picker)
        val colorCenter: ImageView = findViewById(R.id.color_center)
        val backButton: Button = findViewById(R.id.back_button)
        val saveButton: Button = findViewById(R.id.save_button)

        colorPicker.setColorSelectionListener(object:SimpleColorSelectionListener(){
            override fun onColorSelected(color: Int) {
                editCategory.setTextColor(color)
                barColor.dividerColor = color
                //colorCenter.background.setColorFilter(BlendModeColorFilter(color,PorterDuff.Mode.MULTIPLY))
                colorCenter.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(color,BlendModeCompat.SRC_ATOP)
            }
        })
        backButton.setOnClickListener{
            finish()
        }

        saveButton.setOnClickListener{
            CoroutineScope(Dispatchers.Default).launch {
                if(editCategory.text.toString() != "") {
                    repository.insert(
                        CategoryEntity(
                            0,
                            editCategory.text.toString(),
                            barColor.dividerColor
                        )
                    )
                    withContext(Dispatchers.Main){
                        finish()
                    }
                }else{
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddCategoryActivity, "カテゴリを入力してください", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}