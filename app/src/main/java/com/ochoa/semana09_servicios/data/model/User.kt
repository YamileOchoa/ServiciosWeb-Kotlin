package com.ochoa.semana09_servicios.data.model


/**
 * Modelo de datos que representa un Usuario
 * Esta clase se mapea directamente con el JSON que retorna la API
 */
data class User(
    val id: Int,       // ID único del usuario
    val name: String,  // Nombre completo
    val username: String,// Nombre de usuario
    val email: String, // Correo electrónico
    val phone: String,  // Número de teléfono
    val website: String  // Sitio web personal
)

