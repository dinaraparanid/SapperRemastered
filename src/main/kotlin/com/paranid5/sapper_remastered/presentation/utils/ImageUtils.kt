package com.paranid5.sapper_remastered.presentation.utils

import java.awt.Image
import java.awt.RenderingHints
import java.awt.image.BufferedImage

/**
 * Gets image of required scale
 * @param srcImg image itself
 * @param w width
 * @param h height
 * @return scaled image
 */

fun getScaledImage(srcImg: Image, w: Int, h: Int) =
    BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB).also {
        it.createGraphics().apply {
            setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            drawImage(srcImg, 0, 0, w, h, null)
            dispose()
        }
    }