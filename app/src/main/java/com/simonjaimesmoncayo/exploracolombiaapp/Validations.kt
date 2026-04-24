package com.simonjaimesmoncayo.exploracolombiaapp

import android.util.Patterns

//returnar un true si es valido y un flase si no esvalido
//tambien retorne una cadena que me diga que paso si no es valido

fun validateEmail(email: String): Pair<Boolean, String> {
    return when {
        email.isEmpty() -> Pair(false, "El correo es requerido.")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "El formato de email no es válido.")
        !email.endsWith("@test.com") -> Pair(false, "Ese email no es corporativo (@test.com).")
        else -> Pair(true, "")
    }
}

fun validatePassword(password: String): Pair<Boolean, String> {
    return when {
        password.isEmpty() -> Pair(false, "La contraseña es requerida.")
        password.length < 6 -> Pair(false, "La contraseña debe tener al menos 6 caracteres.")
        !password.any { it.isDigit() } -> Pair(false, "La contraseña debe tener al menos un número.")
        else -> Pair(true, "")
    }
}