package dz.islem.tvguide.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import dz.islem.tvguide.R
import dz.islem.tvguide.data.DataProvider
import dz.islem.tvguide.data.model.Guide
import dz.islem.tvguide.data.model.PageInfo
import dz.islem.tvguide.utils.FileUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sideViewArray : Array<View?>
    private var sideViewIndex = 0
    private lateinit var mGuideList : List<Guide>
    private lateinit var mAdapter : ContentPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initSideView()
        initViewPager()
        setListener()
    }

    private fun setListener() {
        exit.setOnClickListener(this)
    }

    private fun initViewPager() {
        mAdapter = ContentPagerAdapter(this, mGuideList.get(sideViewIndex).info)
        view_pager.adapter = mAdapter
        view_pager.offscreenPageLimit = 3
    }

    private fun initSideView() {
        val linearLayout = findViewById<LinearLayout>(R.id.tag_view_container)
        sideViewArray = arrayOfNulls<View>(linearLayout.childCount)
        for (b in 0 until linearLayout.childCount) {
            sideViewArray[b] = linearLayout.getChildAt(b)
            sideViewArray[b]?.setBackgroundColor(resources.getColor(R.color.transparent))
        }
        sideViewArray[sideViewIndex]?.setBackgroundColor(resources.getColor(R.color.color_tag_selected))
    }

    private fun initData() {
        mGuideList = DataProvider.getMappedData("data.json")
    }

    override fun onClick(view: View?) {
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == 20 && sideViewIndex < mGuideList.size - 1) {
            sideViewIndex++
            refreshUI()
        }
        if (keyCode == 19 && sideViewIndex > 0) {
            sideViewIndex--
            refreshUI()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun refreshUI() {
        initSideView()
        refreshData(mGuideList[sideViewIndex].info)
    }

    private fun refreshData(pageInfo: List<PageInfo>) {
        mAdapter.refresh(pageInfo)
        view_pager.adapter = mAdapter
        view_pager.currentItem = 0
    }
}