package com.josetorres.marvel.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josetorres.marvel.databinding.ViewCharacterBinding
import com.josetorres.marvel.domain.model.CharacterDomain
import com.josetorres.marvel.loadUrl

class CharacterAdapter(
    private var items: List<CharacterDomain>,
    private val listener: (CharacterDomain) -> Unit
) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private lateinit var itemBinding: ViewCharacterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBinding =
            ViewCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }


    override fun getItemCount() = items.size

    class ViewHolder(private val itemBinding: ViewCharacterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: CharacterDomain, listener: (CharacterDomain) -> Unit) = with(itemView) {

            itemBinding.txtTitleCharacter.text = item.name

            itemBinding.txtDescriptionCharacter.text = item.description

            itemBinding.imgBgCharacter.loadUrl(item.thumbnail?.path + "." + item.thumbnail?.extension)

            itemBinding.cvMain.setOnClickListener {
                listener(item)
            }
        }
    }
}
