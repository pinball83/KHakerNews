package com.thrd.hackernewskotlin.domain.model

/**
 * @author thrd
 * @version 08.12.16.
 */
data class News(val id: Int, val title: String, val url: String, val by: String, val type: String, val time: Long)
