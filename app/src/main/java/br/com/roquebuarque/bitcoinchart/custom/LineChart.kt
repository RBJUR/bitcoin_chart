package br.com.roquebuarque.bitcoinchart.custom


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import br.com.roquebuarque.bitcoinchart.data.DatePoint
import br.com.roquebuarque.bitcoinchart.util.MaskUtil
import java.util.Collections

class LineChart(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var parentWidth = 0
    private var parentHeight = 0
    private var viewWidth = 0
    private var viewHeight = 0
    private var values: List<Float>? = null
    private val linePaint = Paint()
    private val chartPaint = Paint()
    private var spSize = 12
    private var scaledTextSize = spSize * resources.displayMetrics.scaledDensity
    private var scaleMarginLeft = 60 * resources.displayMetrics.scaledDensity
    private var label = ""
    private var recLabel = RectF()

    fun updateValues(listValues: List<DatePoint>) {
        val listY = arrayListOf<Float>()
        if (listValues.isNotEmpty()) {
            for (data in listValues) {
                listY.add(data.value)
            }
        }
        values = listY
    }

    fun labelText(text: String) {
        label = text
    }

    fun execute() {
        invalidate()
    }


    public override fun onDraw(c: Canvas) {
        super.onDraw(c)
        drawLines(c)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        viewWidth = MeasureSpec.getSize(widthMeasureSpec) / 10
        viewHeight = MeasureSpec.getSize(heightMeasureSpec) / 10
        parentWidth = viewWidth * 9
        parentHeight = viewHeight * 9
        this.setMeasuredDimension(parentWidth, parentHeight)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    private fun drawLines(c: Canvas) {
        val listSize = if (values != null) values!!.size else 0
        if (listSize != 0) {

            linePaint.flags = Paint.ANTI_ALIAS_FLAG
            linePaint.color = -0x414142
            linePaint.strokeWidth = 2f
            linePaint.textSize = scaledTextSize

            //Get max and min values from list
            val maxValue = Collections.max(values)
            val minValue = Collections.min(values)

            //Range of values infos
            val valueInfo = (maxValue!! - minValue!!).toInt() / LINE_NUMBERS
            var currentInfoValue: Int


            //Count to draw multiple lines
            val height = (parentHeight - MARGIN_BOTTOM) / LINE_NUMBERS

            //Add margin
            parentHeight -= MARGIN_HEIGHT

            currentInfoValue = maxValue.toInt()
            //draw multiple lines
            for (i in 0 until LINE_NUMBERS + 1) {
                linePaint.color = Color.BLACK
                c.drawText(
                    "" + MaskUtil.formatMoney(currentInfoValue.toDouble(), false),
                    START_MARGIN_TEXT_X.toFloat(), (height * i + 2).toFloat() + START_MARGIN_TEXT_Y, linePaint
                )

                linePaint.color = -0x414142
                c.drawLine(
                    scaleMarginLeft,
                    (height * i).toFloat() + START_MARGIN_TEXT_Y,
                    parentWidth.toFloat(),
                    (height * i).toFloat() + START_MARGIN_TEXT_Y,
                    linePaint
                )
                currentInfoValue -= valueInfo
            }

            chartPaint.flags = Paint.ANTI_ALIAS_FLAG
            chartPaint.color = -0xe5632f
            chartPaint.strokeWidth = 10f

            //Drawing label
            recLabel.apply {
                left = scaleMarginLeft
                top = (parentHeight + START_MARGIN_TEXT_Y * 3).toFloat() - 25f
                right = scaleMarginLeft + 20
                bottom = (parentHeight + START_MARGIN_TEXT_Y * 3).toFloat() - 5
            }

            c.drawRect(recLabel, chartPaint)
            c.drawText(
                label,
                recLabel.width() + scaleMarginLeft + 10, (parentHeight + START_MARGIN_TEXT_Y * 3).toFloat(), linePaint
            )

            //Calculate size of each width line
            var part = ((parentWidth - scaleMarginLeft) / listSize)
            val fix = part


            //Range of values to draw graph
            val rangeValue = maxValue - minValue
            var value: Int
            var countX = part + scaleMarginLeft
            var lastY: Int
            part += scaleMarginLeft

            //Calculate percent of first value
            value = calculatePercent(values!![0] - minValue, rangeValue)
            value = getPercentFromScreen(value, parentHeight)


            //Draw graph
            for (i in values!!.indices) {
                lastY = value
                value = calculatePercent(values!![i] - minValue, rangeValue)
                value = getPercentFromScreen(value, parentHeight)
                countX += fix

                c.drawLine(part, lastY.toFloat(), countX, value.toFloat(), chartPaint)
                part = countX
            }
        }

    }

    /**
     * Method to calculate percent of values
     * @param num1
     * @param num2
     * @return
     */
    private fun calculatePercent(num1: Float, num2: Float): Int {
        val value = num1 * 100
        return (value / num2).toInt()
    }

    /**
     * Method to calculate percent of screen
     * @param num1
     * @param num2
     * @return
     */
    private fun getPercentFromScreen(num1: Int, num2: Int): Int {
        return parentHeight - (num1 * num2 / 100)
    }


    companion object {
        private const val LINE_NUMBERS = 8
        private const val START_MARGIN_TEXT_X = 40
        private const val START_MARGIN_TEXT_Y = 40
        private const val MARGIN_HEIGHT = 50
        private const val MARGIN_BOTTOM = 50
    }

}