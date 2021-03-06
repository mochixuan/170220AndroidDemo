package com.wx.hencoder.practice11.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wx.hencoder.utils.DisplayUtil

class Practice1PointView : View {

    private var mPaint = Paint()

    constructor(context: Context) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPaint.isAntiAlias = true //抗锯齿
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = Color.parseColor("#ff99ffff")
        mPaint.strokeWidth = DisplayUtil.dpTopx(context,20f)
        mPaint.strokeCap = Paint.Cap.ROUND
        canvas?.drawPoint(DisplayUtil.dpTopx(context,100f),DisplayUtil.dpTopx(context,100f),mPaint)

        mPaint.color = Color.parseColor("#ff9999ff")
        mPaint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawPoint(DisplayUtil.dpTopx(context,180f),DisplayUtil.dpTopx(context,100f),mPaint)

    }

}
