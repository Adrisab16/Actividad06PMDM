package com.pmdm.actividad06pmdm

import android.annotation.SuppressLint
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
                ){MainView()}
            }
        }
    }
}

/**
 * Función principal de la aplicación, donde se establecen los botones y el diseño general,
 * así como el fondo y demás recursos visuales:
 */
@SuppressLint("DiscouragedApi")
@Composable
fun MainView(){
    // Aquí estarán las variable que inicializaremos:
    val context = LocalContext.current
    val deck = Baraja
    var revealcard by rememberSaveable {mutableStateOf("reverse")}

    // Diseño de la MainView:

    //Aquí insertaremos la imagen de fondo:
    Column(modifier = Modifier.fillMaxSize().paint(
            painter = painterResource(id = R.drawable.fondo),
            contentScale = ContentScale.FillHeight
        ),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = painterResource(id = context.resources.getIdentifier(revealcard, "drawable", context.packageName) ),
            contentDescription = "Shown Card",
            modifier = Modifier.height(600.dp).width(300.dp))

        Row(Modifier.padding(top = 25.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){ // Botones de dame carta y reiniciar
            Button(onClick = {
                    val card = deck.cogerCarta() // Variable para mostrar cartas de la lista de cartas
                    // Si no hay cartas en la baraja, aparecerá el mensaje toast:
                    revealcard = if (card == null) {Toast.makeText(context,"Ups, ya no hay más cartas para mostrar", Toast.LENGTH_SHORT).show(); "reverse"} // Mensaje toast para cuando nos quedamos sin cartas en la baraja:
                    else {"c" + card.idDrawable.toString()} // Si hay cartas en la baraja, las mostrará
                },
            ) {Text(text = "Show Card")}
            Button(onClick = {deck.newDeck(); deck.shuffle(); revealcard = "reverse"; deck.size = deck.cardlist.size
            }){Text("Reset")}}

        Row(modifier = Modifier.padding(top = 50.dp), horizontalArrangement = Arrangement.Center)
        {
            // En este texto se nos dirá cuantas cartas nos quedan para quedarnos con la baraja vacía
            Text(text = "${deck.size} cartas restantes",
                modifier = Modifier.background(color = Color.LightGray),
                fontSize = 20.sp
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {AppTheme {MainView()}}