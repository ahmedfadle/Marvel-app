package com.marvel.app.presentation.characters

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.marvel.app.R
import com.marvel.app.common.extentions.loadImage
import com.marvel.app.common.utils.Utils
import com.marvel.app.databinding.DialogSummaryDetailsBinding
import com.marvel.app.domain.models.Item
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsDialogPopFragment : DialogFragment()  {

    var itemModel: Item? = null
    var finishVBiew: Boolean = false
    var cancable: Boolean = true

    lateinit var binding:DialogSummaryDetailsBinding




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_Dialog_Custom)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DialogSummaryDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (dialog != null)
            dialog!!.setCancelable(cancable)

        itemModel?.let {
            binding.ivCover.loadImage(Utils.getFullImagePath(it.resourceURI ?: "","jpg"))
            binding.tvName.text = it.name
        }

        binding.ivClose.setOnClickListener(){
            if (finishVBiew)
                requireActivity().finish()
            dismiss()
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            if (requireArguments().containsKey(Item_KEY))
                itemModel = requireArguments()!!.get(Item_KEY) as Item


        }
    }

    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.80).toInt()
        dialog?.window?.setLayout(width, height)
    }

    companion object {

        const val Item_KEY = "Item"
    }



}