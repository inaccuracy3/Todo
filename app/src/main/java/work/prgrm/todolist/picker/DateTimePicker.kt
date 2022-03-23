package work.prgrm.todolist.picker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.EditText
import java.util.*

class DateTimePicker() {
    fun showDatePickerDialog(item:EditText,calendar: Calendar,context:Context){
        val dateSetListener = DatePickerDialog.OnDateSetListener{ datePicker, year, month, dayOfMonth->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            calendar.set(year,month,dayOfMonth)
            item.setText(String.format("%d/%02d/%02d", year, month+1,dayOfMonth))
            if(item.text.toString() != ""){
                showTimePickerDialog(item,calendar,context)
            }
        }
        DatePickerDialog(context,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
    fun showTimePickerDialog(item:EditText,calendar:Calendar,context:Context){
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute->
            calendar.set(Calendar.HOUR_OF_DAY,hour)
            calendar.set(Calendar.MINUTE,minute)
            val str = item.text.toString()+"/"+String.format("%02d:%02d", hour, minute)
            item.setText(str)
        }
            TimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
    }
}