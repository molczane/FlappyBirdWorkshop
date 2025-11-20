package org.ai.workshop.flappybirdworkshop

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

/**
 * Simple, IP-safe vector sprites used in Phase 3.
 * Shapes intentionally differ from any original game art while approximating the vibe.
 */
object Sprites {
    /**
     * Draw a stylized bird centered at [cx], [cy] with nominal [size].
     * The sprite is vector-based for crisp density-independent rendering.
     */
    fun DrawScope.drawBird(cx: Float, cy: Float, size: Size, tilt: Float = 0f) {
        val bodyW = size.width
        val bodyH = size.height
        translate(left = cx - bodyW / 2f, top = cy - bodyH / 2f) {
            // Body
            drawRoundRect(
                color = Color(0xFFF4D13D), // warm yellow
                size = size,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(bodyH * 0.25f, bodyH * 0.25f)
            )

            // Belly accent
            drawRoundRect(
                color = Color(0xFFFFF3C0),
                topLeft = Offset(bodyW * 0.1f, bodyH * 0.45f),
                size = Size(bodyW * 0.55f, bodyH * 0.4f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(bodyH * 0.2f, bodyH * 0.2f)
            )

            // Wing (simple oval-like shape using round rect)
            drawRoundRect(
                color = Color(0xFFE0B92E),
                topLeft = Offset(bodyW * 0.25f, bodyH * 0.3f),
                size = Size(bodyW * 0.38f, bodyH * 0.38f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(bodyH * 0.19f, bodyH * 0.19f)
            )

            // Eye
            val eyeR = bodyH * 0.1f
            drawCircle(
                color = Color.White,
                radius = eyeR * 1.2f,
                center = Offset(bodyW * 0.72f, bodyH * 0.32f)
            )
            drawCircle(
                color = Color(0xFF2B2B2B),
                radius = eyeR * 0.6f,
                center = Offset(bodyW * 0.72f, bodyH * 0.32f)
            )

            // Beak (small trapezoid drawn as path)
            val beak = Path().apply {
                moveTo(bodyW * 0.88f, bodyH * 0.4f)
                lineTo(bodyW * 1.05f, bodyH * 0.45f)
                lineTo(bodyW * 0.88f, bodyH * 0.5f)
                close()
            }
            drawPath(beak, color = Color(0xFFE57C23))

            // Tail triangles
            val tail = Path().apply {
                moveTo(bodyW * 0.05f, bodyH * 0.45f)
                lineTo(0f, bodyH * 0.38f)
                lineTo(bodyW * 0.05f, bodyH * 0.58f)
                close()
            }
            drawPath(tail, color = Color(0xFFE0B92E))
        }
    }

    /**
     * Draw a stylized pipe segment at [x], with vertical bounds [top]..[bottom].
     * If [isTop] is true, the cap is drawn at the bottom; otherwise at the top.
     */
    fun DrawScope.drawPipe(x: Float, top: Float, bottom: Float, width: Float, isTop: Boolean) {
        val pipeColor = Color(0xFF4DAF57)
        val shadow = Color(0xFF3E8D46)
        val light = Color(0xFF6ED36F)

        val height = (bottom - top).coerceAtLeast(0f)
        if (height <= 0f) return

        // Main tube
        drawRect(
            color = pipeColor,
            topLeft = Offset(x, top),
            size = Size(width, height)
        )

        // Side shading bands
        drawRect(
            color = light,
            topLeft = Offset(x + width * 0.12f, top),
            size = Size(width * 0.12f, height)
        )
        drawRect(
            color = shadow,
            topLeft = Offset(x + width * 0.76f, top),
            size = Size(width * 0.12f, height)
        )

        // Cap lip
        val capH = width * 0.28f
        if (isTop) {
            drawRect(
                color = pipeColor.copy(alpha = 0.95f),
                topLeft = Offset(x - width * 0.08f, bottom - capH),
                size = Size(width * 1.16f, capH)
            )
        } else {
            drawRect(
                color = pipeColor.copy(alpha = 0.95f),
                topLeft = Offset(x - width * 0.08f, top),
                size = Size(width * 1.16f, capH)
            )
        }
    }
}
