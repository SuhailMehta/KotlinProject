package com.tala.assignment.ui.customview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.support.v7.widget.GridLayoutManager

class AutoFitRecycleView: RecyclerView{

    private lateinit var mLayoutManager : GridLayoutManager
    private var columnWidth : Int = -1

    constructor(context: Context) : super(context){
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attrsArray = intArrayOf(android.R.attr.columnWidth)
            val array = context.obtainStyledAttributes(attrs, attrsArray)
            columnWidth = array.getDimensionPixelSize(0, -1)
            array.recycle()
        }

        mLayoutManager = GridLayoutManager(getContext(), 1)
        layoutManager = mLayoutManager
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        if (columnWidth > 0) {
            val spanCount = Math.max(1, measuredWidth / columnWidth)
            mLayoutManager.setSpanCount(spanCount)
        }
    }

}