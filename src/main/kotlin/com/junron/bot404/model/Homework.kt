package com.junron.bot404.model

import com.junron.bot404.firebase.HwboardFirestore
import com.junron.bot404.util.toDateString
import com.junron.bot404.util.uuid
import com.junron.pyrobase.jsoncache.IndexableItem
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Homework(
    override val id: String = uuid(),
    val subject: String = "",
    val dueDate: String = "",
    val text: String = "",
    val tags: List<String> = emptyList(),
    val lastEditPerson: String = "",
    val lastEditTime: String = "",
    val deleted: Boolean = false
) : IndexableItem {
    fun parseTags() = HwboardFirestore.hwboardConfig.tags.filter { it.id in tags || it.name in tags }
}


data class HomeworkNullable(
    val id: String? = null,
    val subject: String? = null,
    val dueDate: Date? = null,
    val text: String? = null,
    val tags: List<Tag> = emptyList(),
    val lastEditPerson: String? = null,
    val lastEditTime: Date? = null,
    val deleted: Boolean = false
) {
    fun toHomework() = Homework(
        id!!,
        subject!!,
        dueDate!!.toDateString(),
        text!!,
        tags.map { it.id },
        lastEditPerson!!,
        lastEditTime!!.toDateString()
    )
}
