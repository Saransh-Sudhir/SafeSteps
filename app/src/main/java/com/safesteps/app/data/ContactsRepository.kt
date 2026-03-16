package com.safesteps.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Extension property for DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "contacts_preferences")

@Serializable
data class ContactSerializable(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val relationship: String = "",
    val isPrimary: Boolean = false
)

class ContactsRepository(private val context: Context) {

    private val CONTACTS_KEY = stringPreferencesKey("emergency_contacts")

    val contacts: Flow<List<Contact>> = context.dataStore.data
        .map { preferences ->
            val contactsJson = preferences[CONTACTS_KEY] ?: "[]"
            try {
                val serializableContacts = Json.decodeFromString<List<ContactSerializable>>(contactsJson)
                serializableContacts.map { it.toContact() }
            } catch (e: Exception) {
                emptyList()
            }
        }

    suspend fun addContact(contact: Contact) {
        context.dataStore.edit { preferences ->
            val currentContactsJson = preferences[CONTACTS_KEY] ?: "[]"
            val currentContacts = try {
                Json.decodeFromString<List<ContactSerializable>>(currentContactsJson)
            } catch (e: Exception) {
                emptyList()
            }
            val updatedContacts = currentContacts + contact.toSerializable()
            preferences[CONTACTS_KEY] = Json.encodeToString(updatedContacts)
        }
    }

    suspend fun deleteContact(contactId: String) {
        context.dataStore.edit { preferences ->
            val currentContactsJson = preferences[CONTACTS_KEY] ?: "[]"
            val currentContacts = try {
                Json.decodeFromString<List<ContactSerializable>>(currentContactsJson)
            } catch (e: Exception) {
                emptyList()
            }
            val updatedContacts = currentContacts.filter { it.id != contactId }
            preferences[CONTACTS_KEY] = Json.encodeToString(updatedContacts)
        }
    }

    suspend fun updateContact(updatedContact: Contact) {
        context.dataStore.edit { preferences ->
            val currentContactsJson = preferences[CONTACTS_KEY] ?: "[]"
            val currentContacts = try {
                Json.decodeFromString<List<ContactSerializable>>(currentContactsJson)
            } catch (e: Exception) {
                emptyList()
            }
            val updatedContacts = currentContacts.map {
                if (it.id == updatedContact.id) updatedContact.toSerializable() else it
            }
            preferences[CONTACTS_KEY] = Json.encodeToString(updatedContacts)
        }
    }

    private fun Contact.toSerializable(): ContactSerializable {
        return ContactSerializable(
            id = this.id,
            name = this.name,
            phoneNumber = this.phoneNumber,
            relationship = this.relationship,
            isPrimary = this.isPrimary
        )
    }

    private fun ContactSerializable.toContact(): Contact {
        return Contact(
            id = this.id,
            name = this.name,
            phoneNumber = this.phoneNumber,
            relationship = this.relationship,
            isPrimary = this.isPrimary
        )
    }
}