package com.ochoa.semana09_servicios.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ochoa.semana09_servicios.data.model.User
import com.ochoa.semana09_servicios.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

/**
 * Estados posibles de la UI
 * Sealed class asegura que solo existen estos 3 estados
 */
sealed class UserUiState {
    object Loading : UserUiState()                                // Cargando datos
    data class Success(val users: List<User>) : UserUiState()   // Datos cargados exitosamente
    data class Error(val message: String) : UserUiState()       // Error al cargar
}

/**
 * ViewModel que maneja la lógica de negocio y el estado de la UI
 * Sobrevive a cambios de configuración (rotación de pantalla)
 */
class UserViewModel : ViewModel() {

    // Repositorio para obtener los datos
    private val repository = UserRepository()

    // Variable para almacenar la lista completa y original de la API
    private var allUsers: List<User> = emptyList()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    // StateFlow privado y mutable (solo el ViewModel puede modificarlo)
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)

    // StateFlow público e inmutable (la UI solo puede observarlo)
    val uiState: StateFlow<UserUiState> = _uiState

    init {
        // Cargar usuarios automáticamente al crear el ViewModel
        loadUsers()
    }

    /**
     * Función que actualiza el texto de búsqueda.
     * @param text El texto ingresado por el usuario.
     */
    fun onSearchTextChange(text: String) {
        _searchText.value = text // Actualiza el StateFlow del texto de búsqueda

        // Disparar el filtro cada vez que el texto cambia
        applyFilter()
    }

    // Función para aplicar el filtro
    private fun applyFilter() {
        // La lista original se toma de la variable 'allUsers' (la fuente de verdad)
        val originalUsers = allUsers
        val query = _searchText.value.trim().lowercase()

        val filteredUsers = if (query.isEmpty()) {
            originalUsers
        } else {
            originalUsers.filter { user ->
                // Filtra por nombre o username
                user.name.lowercase().contains(query) ||
                        user.username.lowercase().contains(query)
            }
        }

        // Se verifica el estado actual para no sobrescribir Loading o Error
        val currentState = _uiState.value
        if (currentState !is UserUiState.Loading && currentState !is UserUiState.Error) {
            // Actualizamos el estado de éxito con la lista filtrada
            _uiState.update {
                UserUiState.Success(filteredUsers)
            }
        }
    }

    /**
     * Función pública para cargar o recargar usuarios
     * Maneja los diferentes estados: Loading, Success, Error
     */
    fun loadUsers() {
        // viewModelScope se cancela automáticamente cuando el ViewModel se destruye
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                // Intentar obtener usuarios del repositorio
                val users = repository.getUsers()

                allUsers = users

                // Aplicar el filtro inmediatamente después de la carga inicial
                // para reflejar cualquier texto que ya esté en _searchText
                val query = _searchText.value.trim().lowercase()

                val listToShow = if (query.isEmpty()) {
                    users
                } else {
                    users.filter { user ->
                        user.name.lowercase().contains(query) ||
                                user.username.lowercase().contains(query)
                    }
                }

                _uiState.value = UserUiState.Success(listToShow)
            } catch (e: Exception) {
                // Capturar cualquier error (red, parseo, etc.)
                _uiState.value = UserUiState.Error(
                    e.message ?: "Error desconocido al cargar usuarios"
                )
            }
        }
    }
}