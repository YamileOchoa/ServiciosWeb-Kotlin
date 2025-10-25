package com.ochoa.semana09_servicios.data.remote

import com.ochoa.semana09_servicios.data.model.User
import retrofit2.http.GET


/**
 * Interfaz que define los endpoints de la API
 * Retrofit implementará automáticamente esta interfaz
 */
interface ApiService {

    /**
     * Obtiene la lista completa de usuarios
     * @GET indica que es una petición GET
     * "users" es el endpoint relativo a la baseUrl
     * suspend permite que sea llamada desde una corrutina
     */
    @GET("users")
    suspend fun getUsers(): List<User>
}