package daysix.com.lrm.kotlinday6

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by manasi on 13/2/18.
 */
class DataFragment: Fragment() {

    private var tvData:TextView?=null
    private lateinit var response: DataResponse

    companion object {
        val ARG_RES:String ="ARG_RES"
        fun newInstance(response: DataResponse): DataFragment {
            val fragment = DataFragment()
            val args = Bundle()
            // assign its Value
            args.putSerializable(ARG_RES, response)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments.getSerializable(ARG_RES) != null) {
            response = arguments.getSerializable(ARG_RES) as DataResponse
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater?.inflate(R.layout.fragment_data, container, false)
        tvData = root?.findViewById(R.id.tv_data)
        tvData?.text=response.dataContent
        return root
    }


}