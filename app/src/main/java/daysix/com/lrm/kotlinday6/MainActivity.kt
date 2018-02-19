package daysix.com.lrm.kotlinday6

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.media.MediaPlayer
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var uri:Uri
    private lateinit var mediaController: MediaController
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var dataResponseList: ArrayList<DataResponse>
    private lateinit var adapter: RecyclerView.Adapter<MyAndroidAdapter.ViewHolder>
    private lateinit var indicatorList: ArrayList<CircleIndicator>


    private lateinit var rvIndicators:RecyclerView
    var surfaceView: SurfaceView? = null
    var surfaceHolder: SurfaceHolder? = null
    var pausing = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoView=findViewById(R.id.video_view) as VideoView
        viewPager=findViewById(R.id.vp_texts) as ViewPager
        rvIndicators=findViewById(R.id.rv_indicators) as RecyclerView
        dataResponseList= ArrayList<DataResponse>()
        indicatorList= ArrayList<CircleIndicator>()
        rvIndicators.bringToFront()
        setUpData()
    }


    fun setUpData()
    {
        val uriPath = "android.resource://daysix.com.lrm.kotlinday6/${R.raw.demo_video}"
        uri = Uri.parse(uriPath)
        Log.d("video","${uri.toString()}")
        mediaController = MediaController(this@MainActivity)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(null)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnCompletionListener (object :MediaPlayer.OnCompletionListener{
            override fun onCompletion(p0: MediaPlayer?) {
                videoView.start()
            }
        })
        setViewPager()
    }


    fun setViewPager() {
        var x = 9
        while (x > 0) {
            dataResponseList.add(DataResponse("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."))
            x--
        }
        pagerAdapter = ViewPagerAdapter(supportFragmentManager, dataResponseList, this@MainActivity)
        viewPager.adapter = pagerAdapter
        initRecylerView()


        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
             Log.d("position1: ","$position")
                if (position < 5) {
                    setCircleBackGrounds(position)
                } else {
                    setCircleBackGrounds(position % 5)
                }
            }

            override fun onPageSelected(position: Int) {
                Log.d("position2: ","$position")
            }
        })

    }
        fun setCircleBackGrounds(pos: Int) {
            var i=4
            while (i>=0)
            {
                if(i==pos)
                {
                    indicatorList.get(i).setIndicator(true)
                }
                else
                {
                    indicatorList.get(i).setIndicator(false)
                }
                i--
            }
            adapter.notifyDataSetChanged()

        }


        fun initRecylerView() {
            //adding a layoutmanager
            indicatorList.add(CircleIndicator(true))
            indicatorList.add(CircleIndicator(false))
            indicatorList.add(CircleIndicator(false))
            indicatorList.add(CircleIndicator(false))
            indicatorList.add(CircleIndicator(false))
            rvIndicators.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false) as RecyclerView.LayoutManager?
            adapter = MyAndroidAdapter(indicatorList)
            //now adding the adapter to recyclerview
            rvIndicators.adapter = adapter

        }



    class MyAndroidAdapter(val indicatorList: ArrayList<CircleIndicator>) : RecyclerView.Adapter<MyAndroidAdapter.ViewHolder>()
    {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAndroidAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_circle, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyAndroidAdapter.ViewHolder, position: Int) {
            holder.bindItems(indicatorList[position])

        }

        override fun getItemCount(): Int {
            return indicatorList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(indicator: CircleIndicator)
            {
                val ivCircle = itemView.findViewById(R.id.iv_circle) as ImageView
                if (indicator.isSelected) {
                    ivCircle.setBackgroundResource(R.drawable.circle2)
                } else {
                    ivCircle.setBackgroundResource(R.drawable.circle1)
                }
            }

            }
    }





}
