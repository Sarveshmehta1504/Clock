package com.example.clock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun Clock(
    seconds: Float = 0f,
    minutes: Float = 0f,
    hours: Float = 0f,
    radius: Dp = 100.dp,
){
    Canvas(
        modifier = Modifier.size(radius * 2f)
    ) {
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                radius.toPx() ,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.WHITE
                    setShadowLayer(
                        50f,
                        0f,
                        0f,
                        android.graphics.Color.argb(50, 0, 0, 0)
                    )

                }
            )
            for (i in 0..59) {
                val angleInRad = i * (360f/60f) * (Math.PI/180f)
                val lineLength = if( i %5 == 0)20.dp.toPx() else 15.dp.toPx()
                val strokeWidth = if( i %5 == 0) 1.dp.toPx() else 0.5.dp.toPx()
                val color = if( i %5 == 0) androidx.compose.ui.graphics.Color.DarkGray else Color(0xFF606060)

                val lineStart = Offset(
                    x = (radius.toPx() * cos(angleInRad) + center.x).toFloat(),
                    y = (radius.toPx() * sin(angleInRad) + center.y).toFloat()
                )

                val lineEnd = Offset(
                    x = ((radius.toPx()- lineLength) * cos(angleInRad) + center.x).toFloat(),
                    y = ((radius.toPx()- lineLength) * sin(angleInRad) + center.y).toFloat()
                )

                drawLine(
                    color = color,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = strokeWidth
                )

            }
            rotate(
                degrees = seconds * (360f/60f)
            ){
                drawLine(
                    color = Color.Red,
                    start = center,
                    end = Offset(center.x, 20.dp.toPx()),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(
                degrees = minutes * (360f/60f)
            ){
                drawLine(
                    color = Color.Black,
                    start = center,
                    end = Offset(center.x, 20.dp.toPx()),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(
                degrees = hours * (360f/12f)
            ){
                drawLine(
                    color = Color.Black,
                    start = center,
                    end = Offset(center.x, 35.dp.toPx()),
                    strokeWidth = 4.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

        }
    }
}
