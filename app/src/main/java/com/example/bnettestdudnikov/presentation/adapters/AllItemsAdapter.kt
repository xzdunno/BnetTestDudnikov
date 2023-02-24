package com.example.bnettestdudnikov.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bnettestdudnikov.R
import com.example.bnettestdudnikov.data.model.Drug
import com.example.bnettestdudnikov.databinding.FragmentListItemsBinding
import com.example.bnettestdudnikov.databinding.ItemRowBinding
import com.example.bnettestdudnikov.presentation.fragments.DrugCardFragment
import com.squareup.picasso.Picasso

class AllItemsAdapter() : RecyclerView.Adapter<CustomViewHolderFav>() {
    private var _binding: ItemRowBinding? = null
    private val bind get() = _binding!!
    var listData: List<Drug>? = null
    fun setListdata(list: List<Drug>) {
        listData = list
    }

    override fun getItemCount(): Int {
        if (listData?.size != null) {
            Log.d("kok", listData?.size.toString())
            return listData?.size!!
        } else return 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderFav {
        _binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolderFav(bind)
    }

    override fun onBindViewHolder(holder: CustomViewHolderFav, position: Int) {
        var cur = listData!!.get(position)
        holder.bind(cur)
        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(R.id.drugCardFragment, DrugCardFragment.createArguments(cur.id))

        }
    }
}

class CustomViewHolderFav(binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    val BASE_URL = "http://shans.d2.i-partner.ru"
    val img = binding.drugRecImg
    val title = binding.recTitleTxt
    val descr = binding.recDescriptionTxt
    fun bind(drug: Drug) {
        Picasso.get().load(BASE_URL + drug.image).into(img)
        title.text = drug.name
        descr.text = drug.description
    }
}