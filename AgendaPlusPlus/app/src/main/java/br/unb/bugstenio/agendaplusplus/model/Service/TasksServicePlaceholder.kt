package br.unb.bugstenio.agendaplusplus.model.Service

import android.util.Log
import br.unb.bugstenio.agendaplusplus.model.Object.Task
import java.util.*

class TasksServicePlaceholder {
    private val stringLoca: String = "A\nA\nA\nA\nA\nA"
    private val database : MutableList<DayTask> = mutableListOf(
            /*DayTask(getDate(19,6-1,2018), listOf(
                    Task(title = "Recebe", description = stringLoca),
                    Task(title = "Faz nada", description = stringLoca),
                    Task(title = "Manda", description = stringLoca)
                    )),
            DayTask(getDate(20,6-1,2018), listOf(
                    Task(title = "Manda", description = stringLoca),
                    Task(title = "Recebe", description = stringLoca),
                    Task(title = "Faz nada", description = stringLoca)
                    )),
            DayTask(getDate(21,6-1,2018), listOf(
                    Task(title = "Manda", description = stringLoca),
                    Task(title = "Faz nada", description = stringLoca),
                    Task(title = "Recebe", description = stringLoca)
                    ))*/
    )

    private fun getDate(day: Int, month: Int, year: Int): Date {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
    }

    data class DayTask(val date: Date, val tasks: List<Task>)

    fun getTasksByDate(date: Date): List<Task> {
        val results = database.filter{
            Log.i("HAHA", "${it.date}, ${date}")
            it.date.time == date.time
        }
        if(results.size > 0){
            return results[0].tasks
        } else {
            return listOf<Task>()
        }
    }
}