package com.example.trabajojuanma

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Composable
fun Configuracion(){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val PERSONAS_DIAS_KEY = stringPreferencesKey("personas_dias")
    // Estados para las selecciones
    var selectedPersonas by remember { mutableStateOf(1) }
    var selectedDias by remember { mutableStateOf(1) }


    // Leer valores iniciales desde DataStore
    LaunchedEffect(Unit) {
        getSelection(context).collect { (personas, dias) ->
            selectedPersonas = personas
            selectedDias = dias
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(scrollState)
        .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp,30.dp),
            text = context.getString(R.string.Configuracion_Header),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        // Grupo de opciones para seleccionar el número de personas
        Text(
            text = stringResource(id = R.string.select_people),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.primary
        )
        RadioButtonGroup(
            options = (1..5).toList(),
            selectedOption = selectedPersonas,
            onOptionSelected = { selectedPersonas = it }
        )

        // Grupo de opciones para seleccionar el número de días
        Text(
            text = stringResource(id = R.string.select_days),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.primary
        )
        RadioButtonGroup(
            options = (1..20).toList(),
            selectedOption = selectedDias,
            onOptionSelected = { selectedDias = it }
        )
    }
}

// Guardar la selección en DataStore
suspend fun saveSelection(context: Context, personas: Int, dias: Int) {
    val PERSONAS_DIAS_KEY = stringPreferencesKey("personas_dias")
    context.dataStore.edit { preferences ->
        preferences[PERSONAS_DIAS_KEY] = "$personas,$dias"
    }
}

// Leer la selección de DataStore
fun getSelection(context: Context): Flow<Pair<Int, Int>> {
    val PERSONAS_DIAS_KEY = stringPreferencesKey("personas_dias")
    return context.dataStore.data.map { preferences ->
        val value = preferences[PERSONAS_DIAS_KEY] ?: "1,1"
        val (personas, dias) = value.split(",").map { it.toInt() }
        personas to dias
    }
}

@Composable
fun RadioButtonGroup(
    options: List<Int>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
) {
    // Aplicamos un fondo al grupo completo de RadioButtons
    Box(
        modifier = Modifier
            .fillMaxSize()  // Ocupa todo el espacio disponible
            .background(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium)  // Fondo del grupo completo
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Filas con dos columnas de RadioButtons
            val rows = options.chunked(4)  // Esto divide la lista en sublistas de 2 elementos
            for (row in rows) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Para cada elemento en la fila
                    row.forEach { option ->
                        RadioButtonWithLabel(
                            label = option.toString(),
                            isSelected = option == selectedOption,
                            onClick = { onOptionSelected(option) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonWithLabel(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Text(text = label)
    }
}
