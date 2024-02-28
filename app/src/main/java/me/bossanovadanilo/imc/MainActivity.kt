package me.bossanovadanilo.imc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import me.bossanovadanilo.imc.ui.theme.IMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImcScreen()
                }
            }
        }
    }
}

@Composable
fun ImcScreen() {

    var peso = remember {
           mutableStateOf("")
    }


    var altura = remember {
        mutableStateOf("")
    }

    var imc =  remember {
        mutableStateOf(0.0)
    }

    var statusImc = remember {
        mutableStateOf("Informe seu peso e altura")
    }

    var corFundo = remember {
        mutableStateOf(0xFF329F6B)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
        ) {

            // -------- Head --------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(colorResource(id = R.color.vermelho_fiap))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.bmi_logo_color),
                    contentDescription = "bmi",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(top = 16.dp)
                )

                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)

                )

            }

            // -------- Formulario ----------
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))
            {
                Card(modifier = Modifier
                    .offset(y = (-27).dp)
                    .fillMaxWidth(),
                    //.height(300.dp),
                    //.size(250.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F6F6)),
                    elevation = CardDefaults.cardElevation(4.dp),
                    //shape = CircleShape,
                    //border = BorderStroke(width = 4.dp, color = Color.Black)


                ) {

                    Text(
                        text = "Seus Dados",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.vermelho_fiap),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Seu peso",
                        modifier = Modifier.padding(bottom = 8.dp, start = 24.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.vermelho_fiap)
                    )

                    OutlinedTextField(
                        value = peso.value,
                        onValueChange = {peso.value = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        placeholder = {
                            Text(text = "Seu peso em Kg. Ex.: 135.23")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                            focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Sua altura",
                        modifier = Modifier.padding(bottom = 8.dp, start = 24.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.vermelho_fiap)
                    )

                    OutlinedTextField(
                        value = altura.value,
                        onValueChange = {altura.value = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        placeholder = {
                            Text(
                                text = "Sua altura em cm. Ex.: 172"
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                            focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                                  imc.value = calcularIMC(
                                      altura = altura.value.toDouble(),
                                      peso =- peso.value.toDouble()
                                  )

                                //statusImc = determinarClassificacaoIMC(imc.value)
                                statusImc = classifaIMC(imc.value)
                                Log.i("statusIMC", statusImc.value)


                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 24.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.vermelho_fiap)),
                        enabled = peso.value.isNotBlank() && altura.value.isNotBlank() // Verifica se ambos os campos estão preenchidos
                    ) {
                        Text(
                            text = "CALCULAR",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                } // Fim Card Formulario



            }
            // Fim do Column Formulario

            // --- Card Resultado
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp, vertical = 24.dp),

                colors = CardDefaults.cardColors(containerColor = aplicaCorDeFundo(imc.value)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxSize()

                ) {
                    Column {
                        Text(text = "Resultado",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(text = statusImc.value,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.heightIn(max = 80.dp).size(200.dp)
                        )
                    }

                    Text(text = String.format("%.1f", imc.value),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontSize = 36.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
            //Fim do Card Resultado

        } // Fim Coluna Principal

    }// Fim Box
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImcScreenPreview() {
    ImcScreen()
}