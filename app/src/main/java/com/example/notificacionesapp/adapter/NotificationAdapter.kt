// app/src/main/java/com/example/notificacionesapp/adapter/NotificationAdapter.kt
package com.example.notificacionesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notificacionesapp.R
import com.example.notificacionesapp.model.NotificationItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var notifications: List<NotificationItem> = emptyList()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    fun updateData(newData: List<NotificationItem>) {
        notifications = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount(): Int = notifications.size

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val appNameText: TextView = itemView.findViewById(R.id.appNameText)
        private val timestampText: TextView = itemView.findViewById(R.id.timestampText)
        private val titleText: TextView = itemView.findViewById(R.id.titleText)
        private val contentText: TextView = itemView.findViewById(R.id.contentText)
        private val transactionDetails: LinearLayout = itemView.findViewById(R.id.transactionDetails)
        private val senderText: TextView = itemView.findViewById(R.id.senderText)
        private val amountText: TextView = itemView.findViewById(R.id.amountText)

        fun bind(notification: NotificationItem) {
            appNameText.text = notification.appName
            timestampText.text = dateFormat.format(Date(notification.timestamp))
            titleText.text = notification.title
            contentText.text = notification.content

            // Mostrar detalles de transacción si están disponibles
            if (notification.sender != null && notification.amount != null) {
                transactionDetails.visibility = View.VISIBLE
                senderText.text = notification.sender
                amountText.text = notification.amount
            } else {
                transactionDetails.visibility = View.GONE
            }
        }
    }
}