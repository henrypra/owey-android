package com.henrypra.owey.scichart.basics

import android.content.Context
import android.view.ViewGroup
import com.henrypra.owey.R
import com.scichart.charting.visuals.SciChartSurface
import com.scichart.charting.visuals.SciPieChartSurface
import com.scichart.extensions.builders.SciChartBuilder
import timber.log.Timber

object SciChartUtility {

    /**
     * Set LicenseKey for SciChart this needs to be set in the Application.OnCreate()
     */
    fun setSciChartLicense() {
        try {
            SciChartSurface.setRuntimeLicenseKey("<LicenseContract>\n" +
                    "  <Customer>henry.pratsch@outlook.de</Customer>\n" +
                    "  <OrderId>Trial</OrderId>\n" +
                    "  <LicenseCount>1</LicenseCount>\n" +
                    "  <IsTrialLicense>true</IsTrialLicense>\n" +
                    "  <SupportExpires>09/15/2019 00:00:00</SupportExpires>\n" +
                    "  <ProductCode>SC-ANDROID-2D-ENTERPRISE-SRC</ProductCode>\n" +
                    "  <KeyCode>6a4efeb9d81fd7af4deb599ce536057e8589097635594e727377edd1b0d0f76deb02cb8633afaa5abf32477a102fc4e302119ae53d41ba2ecf2100adc36f3af870641ab67599aa5d36e9579724a441d08a871dd7735231f790544fa6157183932f5e834db29e3496d7be0c6edab2c97f22f003ab944f189e33ebb4458cdf8094ad8abb225695acbd47a88346a264d0fd84b1e411fe16c7b74d188aa30271af3a4e0e200fbe557d834e3a6c884592d5aaac22</KeyCode>\n" +
                    "</LicenseContract>")
        } catch (e: Exception) {
            Timber.e(e, "Error when setting the license")
        }
    }

    fun setupDonutSciChart(context: Context,
                           container: ViewGroup,
                           surface: SciPieChartSurface): SciChartBuilder {
        container.removeAllViews()
        surface.theme = R.style.SciChart_Theme
        SciChartBuilder.init(context)
        container.addView(surface)
        return SciChartBuilder.instance()
    }

}