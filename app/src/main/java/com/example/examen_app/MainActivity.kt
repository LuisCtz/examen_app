package com.example.examen_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaPrincipal()
            }
        }
    }
}

@Composable
fun PantallaPrincipal() {
    // Para el SnackBar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* no hace nada */ },
                containerColor = Color(0xFF6200EE) // morado
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { relleno ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(relleno)
                .verticalScroll(rememberScrollState())
        ) {
            // Cabecera
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.portadaa),
                    contentDescription = "Portada UPP",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("UPP", color = Color.White, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(8.dp))
                        Text("Universidad Politecnica de Pachuca", color = Color.White, fontSize = 12.sp)
                    }
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Compartir", tint = Color.White)
                }
            }

            // Perfil
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-45).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .border(3.dp, Color.White, CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(175.dp))
                Column(modifier = Modifier.offset(y = 48.dp)) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text("UPP TIID", fontSize = 12.sp, color = Color(0xFF6A1B9A))
                    }
                }
            }

            // Datos personales
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-30).dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Luis Armando Cervantes Tellez",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Verificado",
                        tint = Color(0xFF6A1B9A)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = " luis28ctz@micorreo.upp.edu.mx ",
                    color = Color(0xFF6A1B9A),
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = " Estudiante de Ingenieria en Tecnologias de la Informacion e Innovacion Digital de la Universidad Politecnica de Pachuca, apasionado por el desarrollo de aplicaciones moviles.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            // Lenguajes de programación
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "LENGUAJES DE PROGRAMACION",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text("Desliza →", color = Color.Gray, fontSize = 12.sp)
            }
            Spacer(Modifier.height(10.dp))
            val lenguajes = listOf("Python", "Java", "Kotlin", "C#", "C++", "JS", "TS", "Swift", "Dart", "PHP")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(lenguajes) { nombre ->
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(20.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(nombre, color = Color(0xFF4A148C), fontWeight = FontWeight.Medium)
                    }
                }
            }

            // Google Maps
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    "UBICACION",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(10.dp))

                val uppLocation = LatLng(20.0886, -98.7229)
                val cameraState = rememberCameraPositionState {
                    position = com.google.android.gms.maps.model.CameraPosition.Builder()
                        .target(uppLocation)
                        .zoom(17f)
                        .build()
                }

                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    cameraPositionState = cameraState
                ) {
                    Marker(
                        state = MarkerState(position = uppLocation),
                        title = "Universidad Politécnica de Pachuca",
                        snippet = "Carretera Pachuca - Actopan Km. 4.5"
                    )
                }

                // Botón que dispara el SnackBar — debajo del mapa
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Mensaje desde la UPP — Proyecto Mapa")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Mostrar mensaje")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Vista Previa - Completa")
@Composable fun PantallaPreview() {
    MaterialTheme {
        PantallaPrincipal()
    }
}
