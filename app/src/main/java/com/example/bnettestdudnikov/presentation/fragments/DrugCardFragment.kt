package com.example.bnettestdudnikov.presentation.fragments

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bnettestdudnikov.R
import com.example.bnettestdudnikov.data.model.Drug
import com.example.bnettestdudnikov.databinding.FragmentDrugCardBinding
import com.example.bnettestdudnikov.databinding.FragmentListItemsBinding
import com.example.bnettestdudnikov.presentation.viewmodels.DrugCardViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

typealias Loaded = DrugCardViewModel.State.Loaded
typealias Loading = DrugCardViewModel.State.Loading
typealias Error = DrugCardViewModel.State.Error

@AndroidEntryPoint
class DrugCardFragment : Fragment() {
    private lateinit var bind: FragmentDrugCardBinding
    val BASE_URL = "http://shans.d2.i-partner.ru"

    private val viewModel: DrugCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentDrugCardBinding.inflate(inflater, container, false)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToViewModel()
        bind.appCompatImageButton.setOnClickListener {
            findNavController().navigate(R.id.listItemsFragment)
        }
        bind.buyConstraint?.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://shans-group.com/")
            )
            this@DrugCardFragment.startActivity(urlIntent)
        }
    }

    private fun bindToViewModel() {
        Log.d("пипец", "loads")
        viewModel.id = requireNotNull(requireArguments().getInt("id"))
        viewModel.state.observe(viewLifecycleOwner) { state ->
            val drug: Drug? =
                if (state is Loaded)
                    state.drug
                else
                    null
            if (drug != null) {
                with(bind) {
                    if (!(state as Loaded).isDb) {

                    } else {
                        val pic = BASE_URL + drug.image
                        Log.d("picture", pic)
                        Picasso.get().load(pic).into(bind.drugCardImg)
                    }

                    cardDescriptionTxt.text = drug.description
                    cardTitleTxt.text = drug.name
                }
            }

        }
    }

    companion object {
        private const val ID = "id"

        fun createArguments(id: Int): Bundle {
            return bundleOf(ID to id)
        }
    }

}