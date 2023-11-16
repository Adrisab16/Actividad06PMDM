package com.pmdm.actividad06pmdm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.actividad06pmdm.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

// Función principal del juego
@Composable
fun Juego(){
    // inicializamos las variables
    val context = LocalContext.current
    var cartaMostrar by rememberSaveable { mutableStateOf("reverso") }
    val miBaraja = Baraja
    //Columna con una imagen , dos botones  y un texto que muestra el número de cartas que quedan en la baraja
    Column( modifier = Modifier
        .fillMaxSize()
        .paint(painter = painterResource(id = R.drawable.casino), contentScale = ContentScale.FillHeight),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = painterResource(id = context.resources.getIdentifier(cartaMostrar, "drawable", context.packageName) ),
            contentDescription = "Carta mostrada",
            modifier = Modifier
                .height(600.dp)
                .width(300.dp)
        )
        Row(
            Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){ // Botones de dame carta y reiniciar
            Button(
                onClick = {
                    val micarta = miBaraja.cogerCarta()
                    cartaMostrar = if (micarta == null) {
                        Toast.makeText( context,"No hay más cartas ", Toast.LENGTH_SHORT).show()
                        "reverso"
                    }else
                        "c" + micarta.idDrawable.toString()
                },
            ) {
                Text(text = "Dame una carta ")
            }
            Button(onClick = {
                miBaraja.creaBaraja()
                miBaraja.barajar()
                cartaMostrar = "reverso"
                miBaraja.tamanio = miBaraja.listaCartas.size
            }) {
                Text("Reiniciar ")
            }
        }
        Row(modifier = Modifier.padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center
        ){
            // Texto que indica cuantas cartas quedan en la baraja
            Text(text = "Quedan ${miBaraja.tamanio} cartas en la baraja",
                modifier = Modifier
                    .background(color = Color.White),
                fontSize = 20.sp
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Juego()
    }
}