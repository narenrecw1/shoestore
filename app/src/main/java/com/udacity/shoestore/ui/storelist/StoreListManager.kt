package com.udacity.shoestore.ui.storelist

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LayoutStoreItemBinding
import com.udacity.shoestore.models.Shoe

typealias ItemClickHandler = (Shoe) -> Unit

/**
 * A class that inflates a set of entries in a [LinearLayout] based on a data model, while reusing
 * existing nodes. This is not to be mistaken with a RecyclerView, cause we do not remove views when
 * they are out of bounds. We just do not create unnecessary views.
 *
 * @author Sri Harsha Chilakapati
 */
class StoreListManager(
    private val container: LinearLayout,
    private val itemClickHandler: ItemClickHandler
) {

    private val entries = mutableListOf<LayoutStoreItemBinding>()

    fun reRender(shoes: List<Shoe>) {
        ensureCapacity(shoes.size)
        entries.zip(shoes).forEach { pair -> render(pair.first, pair.second) }
    }

    private fun render(binding: LayoutStoreItemBinding, item: Shoe) {
        with(binding) {
            shoe = item
            onClickListener = View.OnClickListener { itemClickHandler(item) }
            executePendingBindings()
        }
    }

    /**
     * A function to ensure the capacity of the entries in the container. Handles the removal of
     * destroyed views too.
     *
     * @param capacity - The required capacity in the container
     */
    private fun ensureCapacity(capacity: Int) {
        // If the capacity is the same, don't do anything
        if (entries.size == capacity) {
            return
        }

        // If there are less entries than the capacity, create the remaining
        if (entries.size < capacity) {
            val remaining = capacity - entries.size
            val inflater = LayoutInflater.from(container.context)

            repeat(remaining) {
                val binding = DataBindingUtil.inflate<LayoutStoreItemBinding>(
                    inflater,
                    R.layout.layout_store_item,
                    container,
                    true
                )

                entries.add(binding)
            }

            return
        }

        // If not, then there are extra entries in the container. We need to remove them
        val extraEntries = entries.size - capacity
        container.removeViews(entries.size - extraEntries - 1, extraEntries)
    }
}