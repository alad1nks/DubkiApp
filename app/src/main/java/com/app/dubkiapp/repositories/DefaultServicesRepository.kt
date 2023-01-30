package com.app.dubkiapp.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.app.dubkiapp.R
import com.app.dubkiapp.domain.Service
import javax.inject.Inject


class DefaultServicesRepository @Inject constructor(
    context: Context
): ServicesRepository {
    private val services = listOf(
        Service(
            stringResourceId = R.string.title_leisure_booking,
            imageResourceId = R.drawable.baseline_calendar,
            onClick = {
                Toast.makeText(context, "Скоро добавим!", Toast.LENGTH_SHORT).show()
            }
        ),

        Service(
            stringResourceId = R.string.title_speed_dating,
            imageResourceId = R.drawable.baseline_like,
            onClick = {
                Toast.makeText(context, "Скоро добавим!", Toast.LENGTH_SHORT).show()
            }
        ),

        Service(
            stringResourceId = R.string.title_support_the_authors,
            imageResourceId = R.drawable.baseline_ruble,
            onClick = {
                val uri = Uri.parse("https://tips.yandex.ru/guest/payment/1019090")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        ),

        Service(
            stringResourceId = R.string.title_contact_the_developer,
            imageResourceId = R.drawable.baseline_message_24,
            onClick = {
                val uri = Uri.parse("https://t.me/asteslenko")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        ),
    )

    override fun getServices(): List<Service> {
        return services
    }

}