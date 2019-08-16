package com.henrypra.owey.scichart.basics

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.henrypra.owey.R
import com.henrypra.owey.model.Debt
import com.scichart.charting.SizingMode
import com.scichart.charting.visuals.SciPieChartSurface
import com.scichart.charting.visuals.renderableSeries.IPieRenderableSeries
import com.scichart.charting.visuals.renderableSeries.PieSegment
import com.scichart.drawing.common.FontStyle
import java.util.*

object BalanceSciChart {
    fun initChart(context: Context,
                  container: ViewGroup,
                  surface: SciPieChartSurface,
                  debtList: List<Debt>) {

        val builder = SciChartUtility.setupDonutSciChart(context, container, surface)
        val pieSegmentList = mutableListOf<PieSegment>()

        val allDebts: Double = debtList.filter { it.isDebt == true }.sumByDouble { it.amount!! }
        val allLoans: Double = debtList.filter { it.isDebt == false }.sumByDouble { it.amount!! }

        val dimen = context.resources.getDimension(R.dimen.textsize_medium)
        val style = FontStyle(Typeface.DEFAULT_BOLD, dimen, Color.WHITE)

        val segmentDebt = builder.newPieSegment()
                .withValue(allDebts)
                .withTitleStyle(style)
                .withTitle("")
                .withFillColor(ContextCompat.getColor(context, R.color.orange))
                .build()

        val segmentLoan = builder.newPieSegment()
                .withValue(allLoans)
                .withTitleStyle(style)
                .withTitle("")
                .withFillColor(ContextCompat.getColor(context, R.color.light_green))
                .build()

        pieSegmentList.add(segmentDebt)
        pieSegmentList.add(segmentLoan)
        val donutSeriesHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62f, context.resources.displayMetrics)

        val donutSeries: IPieRenderableSeries? = builder.newDonutSeries()
                .withSegments(*pieSegmentList.toTypedArray())
                .withHeightSizingModeP(SizingMode.Absolute).withHeight(donutSeriesHeight)
                .build()

        Collections.addAll(surface.renderableSeries, donutSeries)
        donutSeries?.animate(600)
    }

}
