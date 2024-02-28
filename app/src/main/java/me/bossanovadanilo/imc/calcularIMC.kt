package me.bossanovadanilo.imc

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import kotlin.math.absoluteValue
import kotlin.math.pow

fun calcularIMC(altura: Double, peso: Double) : Double {
    var resultado = peso / (altura / 100).pow(2.0)
    return resultado.absoluteValue
}


fun determinarClassificacaoIMC(imc: Double): MutableState<String> {
    return if (imc < 18.5) {
        mutableStateOf("Chassi de Grilo")
    } else if (imc >= 18.5 && imc < 25.0) {
        mutableStateOf("Peso Ideal")
    } else if (imc >= 25.0 && imc < 30.0) {
        mutableStateOf("Levemente acima do peso")
    } else if (imc >= 30.0 && imc < 35.0) {
        mutableStateOf("Obesidade Grau I")
    } else if (imc >= 35.0 && imc < 40.0) {
        mutableStateOf("Obesidade Grau II")
    } else {
        mutableStateOf("Obesidade Grau III")
    }
}

fun aplicaCorDeFundo(imc: Double): Color  {
    return when (imc) {
        0.0 -> Color(0xFF329F6B) // Cor padrão para IMC 0.0
        in 0.0..18.4 -> Color(0xFFE57373) // Vermelho para "Chassi de Grilo"
        in 18.5..24.9 -> Color(0xFF81C784) // Verde para "Peso Ideal"
        in 25.0..29.9 -> Color(0xFFFFB74D) // Laranja para "Levemente acima do peso"
        in 30.0..34.9 -> Color(0xFFFF8A65) // Vermelho-alaranjado para "Obesidade Grau I"
        in 35.0..39.9 -> Color(0xFFD32F2F) // Vermelho escuro para "Obesidade Grau II"
        else -> Color(0xFFB71C1C) // Vermelho profundo para "Obesidade Grau III"
    }
}

fun classifaIMC(imc: Double): MutableState<String> {
    return when (imc) {
        0.0 -> "Informe seu peso e altura" // Cor padrão para IMC 0.0
        in 0.0..18.4 -> "Chassi de Grilo" // Vermelho para "Chassi de Grilo"
        in 18.5..24.9 -> "Peso Ideal" // Verde para "Peso Ideal"
        in 25.0..29.9 -> "Levemente acima do peso" // Laranja para "Levemente acima do peso"
        in 30.0..34.9 -> "Obesidade Grau I" // Vermelho-alaranjado para "Obesidade Grau I"
        in 35.0..39.9 -> "Obesidade Grau II" // Vermelho escuro para "Obesidade Grau II"
        else -> "Obesidade Grau III"// Vermelho profundo para "Obesidade Grau III"
    }
}