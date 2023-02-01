package saulmm.coordinatorexamples.staggeredrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import saulmm.coordinatorexamples.R

class PostsAdapter internal constructor(
    private val context: Context,
    private val postItems: List<PostItem>
) :
    RecyclerView.Adapter<PostsAdapter.ListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImageView: RoundedImageView = itemView.findViewById(R.id.imagePost)
        val textView: TextView = itemView.findViewById(R.id.position)

        fun setPostImage(postItem: PostItem) {
            postImageView.setImageResource(postItem.image)
            ("Pos :"+position.toString() +", Size :"+  getNameVal(postItem.viewType)).also { textView.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = inflater.inflate(R.layout.post_item_container, parent, false)

        when(viewType){
            ViewType.BIG.id ->{
                val itemView = inflater.inflate(R.layout.post_item_container_big, parent, false)
                return ListViewHolder(itemView)
            }
            ViewType.SMALL.id ->{
                val itemView = inflater.inflate(R.layout.post_item_container_small, parent, false)
                return ListViewHolder(itemView)
            }
            ViewType.EMPTY.id ->{
                val itemView = inflater.inflate(R.layout.post_item_container_empty, parent, false)
                return ListViewHolder(itemView)
            }
        }
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val postItem =  postItems[position]
        if(postItem.viewType !=    ViewType.EMPTY.id){
            holder.setPostImage(postItems[position])
        }

    }

    override fun getItemCount(): Int {
        return postItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return postItems[position].viewType
    }


}