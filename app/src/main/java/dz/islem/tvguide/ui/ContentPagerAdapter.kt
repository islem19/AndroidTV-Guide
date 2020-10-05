package dz.islem.tvguide.ui

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import dz.islem.tvguide.R
import dz.islem.tvguide.data.model.PageInfo

class ContentPagerAdapter(private val context: Context, private var pagesInfo: List<PageInfo>) : PagerAdapter() {
    private val PAGE_INDEX_RES = intArrayOf(
        R.mipmap.ic_page_one,
        R.mipmap.ic_page_two,
        R.mipmap.ic_page_three,
        R.mipmap.ic_page_four,
        R.mipmap.ic_page_five,
        R.mipmap.ic_page_six
    )

    override fun getCount(): Int = pagesInfo.size ?: 0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    fun refresh(pagesInfo: List<PageInfo>) {
        this.pagesInfo = pagesInfo
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    override fun getPageWidth(position: Int): Float {
        return 0.952f
    }

    private fun getIdentifier(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    private class ViewHolder internal constructor(view: View) {
        var descTextView: TextView = view.findViewById(R.id.desc_text_area)
        var imageView: ImageView = view.findViewById(R.id.main_image)
        var noteTextView: TextView = view.findViewById(R.id.note_text_view)
        var numView: ImageView = view.findViewById(R.id.num_image)
        var titleView: TextView = view.findViewById(R.id.title_view)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, container, false)
        val viewHolder = ViewHolder(view)
        val pageInfo: PageInfo = pagesInfo[position]
        viewHolder.titleView.text = pageInfo.title
        viewHolder.numView.setImageResource(PAGE_INDEX_RES[pageInfo.pageIndex])
        viewHolder.imageView.setImageResource(getIdentifier(context, pageInfo.mainIcon))
        if (!TextUtils.isEmpty(pageInfo.note)) {
            viewHolder.noteTextView.text = pageInfo.note
        } else {
            viewHolder.noteTextView.visibility = View.INVISIBLE
        }
        viewHolder.descTextView.text = pageInfo.description
        container.addView(view)
        return view
    }
}
