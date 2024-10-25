package com.example.trabajojuanma

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AcercaDe() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(150.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            modifier = Modifier.padding(top = 100.dp ),
            text = "Help",
            fontSize = 24.sp

        )
        Image(
            painter = painterResource(id = R.drawable.torneojuegos),
            contentDescription = "torneo",

            )
        Text(
            text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque molestie nibh ut iaculis aliquam. Curabitur placerat libero ut sodales pulvinar. Proin a magna vel nunc egestas lacinia ac quis felis. Nullam dictum ipsum mi, nec sagittis nulla condimentum a. Mauris non est viverra, porttitor dolor in, blandit elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce placerat orci vel libero posuere vestibulum. Vivamus enim nunc, dignissim a lacus nec, pulvinar volutpat felis."
        )
    }
}