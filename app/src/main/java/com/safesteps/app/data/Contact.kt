package com.safesteps.app.data

data class Contact(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val relationship: String = "",
    val isPrimary: Boolean = false
)