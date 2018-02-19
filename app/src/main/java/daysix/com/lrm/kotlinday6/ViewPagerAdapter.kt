package daysix.com.lrm.kotlinday6

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by manasi on 13/2/18.
 */
class ViewPagerAdapter  (fragmentManager: FragmentManager, private val responseList: ArrayList<DataResponse>,context: Context) :
        FragmentStatePagerAdapter(fragmentManager) {
    var mainActivity: MainActivity

    init {
        mainActivity= context as MainActivity
    }

    // METHOD 2
    override fun getItem(position: Int): Fragment {
            if (position > 4) {
               // mainActivity.setCircleBackGrounds(position % 5)
            }
    return DataFragment.newInstance(responseList.get(position))

    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun getItemPosition(`object`: Any?): Int {
        return super.getItemPosition(`object`)
    }

   /* override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(container)
    }*/

    // METHOD 3
    override fun getCount(): Int {
        return responseList.size
    }
}