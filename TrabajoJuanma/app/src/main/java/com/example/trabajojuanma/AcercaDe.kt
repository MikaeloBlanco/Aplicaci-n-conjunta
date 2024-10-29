package com.example.trabajojuanma

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AcercaDe() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            modifier = Modifier.padding(top = 100.dp ),
            text = context.getString(R.string.Aboutus_title),
            color=MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge

        )

            Image(
                painter = painterResource(id = R.drawable.torneojuegos),
                contentDescription = "torneo",
                modifier =Modifier.size(300.dp)
                    .clip(MaterialTheme.shapes.large)

            )

        Text(
            text=context.getString(R.string.Aboutus_text),
            color=MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(horizontal = 50.dp).padding(top = 10.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}