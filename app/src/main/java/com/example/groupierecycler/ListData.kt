package com.example.groupierecycler

sealed class ListData {
    data class HeaderData(val header: String) : ListData()
    data class BodyData(val rating: String, val body: String) : ListData()
}