package com.intermediait.marvel.data.repositories.mappers

import com.intermediait.marvel.data.network.models.EventNetwork
import com.intermediait.marvel.domain.models.Event
import java.text.SimpleDateFormat
import java.util.*

class EventViewMapper(
    private val comicMapper: ComicMapper
) {
    operator fun invoke(eventNetwork: EventNetwork): Event {
        with(eventNetwork) {
            return Event(
                id = id,
                title = title.toString(),
                thumbnail = ThumbnailMapper()(thumbnail),
                comics = comicMapper(comics),
                start = dateMapper(start)
            )
        }
    }

    private fun dateMapper(date: String?): String {
        date ?: return ""
        return formatDate(date)
    }

    private fun formatDate(stringDate: String): String {
        val startFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        /*
        Para que el mes quede con mayúscula se puede hacer de esta forma pero
        la eficiencia se ve muy afectada haciendo la carga demasiada lenta:
        val sym = DateFormatSymbols.getInstance(Locale("es"))
        sym.months = arrayOf("Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre")
        val endFormat = SimpleDateFormat("d 'de' MMMM yyyy", sym)
         */
        val endFormat = SimpleDateFormat("d 'de' MMMM yyyy", Locale("es"))
        val date = startFormat.parse(stringDate)!!
        return endFormat.format(date)
    }

}