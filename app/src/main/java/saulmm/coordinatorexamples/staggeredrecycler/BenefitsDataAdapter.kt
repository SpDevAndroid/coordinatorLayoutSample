package saulmm.coordinatorexamples.staggeredrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import saulmm.coordinatorexamples.R

class BenefitsDataAdapter(private val context: Context) :
    RecyclerView.Adapter<BenefitsDataAdapter.BenefitCategoryItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /** Will use this set data for setting adapter **/
    private val benefitContainerEntryList: ArrayList<String> =
        arrayListOf("ALL", "TEST1", "TEST2", "TEST3")

    init {

    }


    inner class BenefitCategoryItemViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {

        val postsRecyclerView: RecyclerView =
            view.findViewById<RecyclerView>(R.id.postsRecyclerView)

        fun onBind(position: Int) {
            setUpRecycler()
        }

        private fun setUpRecycler() {
            val glm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            glm.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            postsRecyclerView.layoutManager = glm
            val postItems: MutableList<PostItem> = java.util.ArrayList()
            postItems.add(PostItem(R.drawable.leeminho, ViewType.BIG.id)) // 0
            postItems.add(PostItem(R.drawable.leejongsuk, ViewType.SMALL.id)) // 1
            postItems.add(PostItem(R.drawable.chaeunwoo, ViewType.BIG.id)) //2
            postItems.add(PostItem(R.drawable.seokangjoon, ViewType.BIG.id)) //3
            postItems.add(PostItem(R.drawable.kimsoohyun, ViewType.EMPTY.id)) //4
            postItems.add(PostItem(R.drawable.parkseojoon, ViewType.SMALL.id)) // 5
            postItems.add(PostItem(R.drawable.parkseojoon, ViewType.SMALL.id)) // 0
            postItems.add(PostItem(R.drawable.seoinguk, ViewType.BIG.id)) // 1
            postItems.add(PostItem(R.drawable.jichangwook, ViewType.BIG.id)) //2
            postItems.add(PostItem(R.drawable.yooseungho, ViewType.BIG.id)) //3
            postItems.add(PostItem(R.drawable.kimsoohyun, ViewType.SMALL.id)) //4
            postItems.add(PostItem(R.drawable.leeseunggi, ViewType.EMPTY.id)) // 5
            postItems.add(PostItem(R.drawable.leeminho, ViewType.BIG.id)) // 0
            postItems.add(PostItem(R.drawable.leejongsuk, ViewType.SMALL.id)) // 1
            postItems.add(PostItem(R.drawable.chaeunwoo, ViewType.BIG.id)) //2
            postItems.add(PostItem(R.drawable.seokangjoon, ViewType.BIG.id)) //3
            postItems.add(PostItem(R.drawable.kimsoohyun, ViewType.EMPTY.id)) //4
            postItems.add(PostItem(R.drawable.parkseojoon, ViewType.SMALL.id)) // 5
            postItems.add(PostItem(R.drawable.parkseojoon, ViewType.SMALL.id)) // 0
            postItems.add(PostItem(R.drawable.seoinguk, ViewType.BIG.id)) // 1
            postItems.add(PostItem(R.drawable.jichangwook, ViewType.BIG.id)) //2
            postItems.add(PostItem(R.drawable.yooseungho, ViewType.BIG.id)) //3
            postItems.add(PostItem(R.drawable.kimsoohyun, ViewType.SMALL.id)) //4
            postItems.add(PostItem(R.drawable.leeseunggi, ViewType.EMPTY.id)) // 5
            postsRecyclerView.adapter = PostsAdapter(context, postItems)
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BenefitCategoryItemViewHolder {
        val itemView = inflater.inflate(R.layout.layout_recycler_item, parent, false)
        return BenefitCategoryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BenefitCategoryItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return benefitContainerEntryList.size
    }


    fun getItemTitle(position: Int): String {
        return benefitContainerEntryList[position]
    }

    companion object {
        val TAG = "Odp2TabAdapter"
    }


}