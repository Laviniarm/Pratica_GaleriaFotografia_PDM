package com.example.galeriafotografia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeriafotografia.ui.theme.GaleriaFotografiaTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaFotografiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Tela()
                }
            }
        }
    }
}

val fotos = listOf(
    Foto(R.drawable.pexels_w_w_299285_889839, "Choro", "WW", "2021"),
    Foto(R.drawable.pexels_moose_photos_170195_1037992, "Cores", "Steve", "2020"),
    Foto(R.drawable.pexels_shonejai_445753_1227497, "Tudo", "Shonejai", "2018"))

@Composable
fun Tela() {
    var fotoIndex by remember{ mutableStateOf(0) }

    fun mostraProximaFoto(){
        fotoIndex = (fotoIndex + 1) % fotos.size
    }
    fun mostarAnteriorFoto(){
        fotoIndex = (fotoIndex - 1 + fotos.size) % fotos.size
    }

    val fotoAtual = fotos[fotoIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter = painterResource(id = fotoAtual.imagem),
                contentDescription = fotoAtual.titulo,
                modifier = Modifier.size(400.dp))

            Spacer(modifier = Modifier.height(10.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Título: ${fotoAtual.titulo}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fotografo: ${fotoAtual.fotografo}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Ano: ${fotoAtual.ano}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
            Button(onClick = {mostarAnteriorFoto()},  colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) )  {
                Text(text = "Anterior")
            }

            Button(onClick = { mostraProximaFoto() },  colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )) {
                Text(text = "Próxima")
            }
        }

        }

}

