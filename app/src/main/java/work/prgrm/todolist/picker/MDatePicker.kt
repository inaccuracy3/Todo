package work.prgrm.todolist.picker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.LocaleList
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.selects.select
import work.prgrm.todolist.application.App
import java.text.SimpleDateFormat
import java.util.*

class MDatePicker {
    private val mTimePicker = MTimePicker()
    fun showDatePickerDialog(item: EditText,calendar:Calendar,activity:Activity){

        val today = Calendar.getInstance()
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(today.timeInMillis)
                .build().apply {
                    addOnPositiveButtonClickListener {
                        val selectDate = convertLongToTime(selection!!)
                        item.setText(selectDate)
                    }
                }
        mTimePicker.showTimePickerDialog(item,calendar,activity)
        datePicker.show((activity as FragmentActivity).supportFragmentManager,"TAG")
    }
    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm")
        return format.format(date)
    }
}