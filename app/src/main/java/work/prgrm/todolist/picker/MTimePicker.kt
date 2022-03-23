package work.prgrm.todolist.picker

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import java.util.*

class MTimePicker {
    fun showTimePickerDialog(item: EditText,calendar:Calendar,activity:Activity){
        Locale.setDefault(Locale.JAPAN)
        val timePicker =
            MaterialTimePicker.Builder().setHour(12).setInputMode(INPUT_MODE_CLOCK).setMinute(10).build().apply {
                addOnPositiveButtonClickListener(){
                    val timeText = item.text.toString() + "at" + hour.toString() + minute.toString()
                    item.setText(timeText)
                }
            }
        timePicker.show((activity as FragmentActivity).supportFragmentManager,"TAG")

    }
}