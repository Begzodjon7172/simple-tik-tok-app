package uz.bnabiyev.simpletiktokapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.bnabiyev.simpletiktokapp.databinding.ItemViewBinding
import uz.bnabiyev.simpletiktokapp.models.MyReels

class ViewPagerAdapter(private val list: ArrayList<MyReels>) :
    RecyclerView.Adapter<ViewPagerAdapter.Vh>() {

    inner class Vh(private val itemViewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun onBind(myReels: MyReels) {
            itemViewBinding.tvName.text = myReels.title
            itemViewBinding.videoView.setVideoURI(Uri.parse(myReels.videoLink))
            itemViewBinding.videoView.start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}