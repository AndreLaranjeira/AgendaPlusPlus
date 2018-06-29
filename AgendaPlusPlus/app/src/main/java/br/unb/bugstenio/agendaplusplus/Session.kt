package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.Project
import br.unb.bugstenio.agendaplusplus.model.Object.User
import kotlinx.android.synthetic.main.nav_header_main_navigation_drawer.*
import org.joda.time.DateTime

object Session {
    var user: User? = null
    var project: Project? = null
}

fun DateTime.toString2():String {
    return "${this.dayOfMonth}/${this.monthOfYear}/${this.year} | %02d:%02d".format(this.hourOfDay, this.minuteOfHour)
}