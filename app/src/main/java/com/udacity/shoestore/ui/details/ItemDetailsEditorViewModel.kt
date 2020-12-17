package com.udacity.shoestore.ui.details

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ItemDetailsEditorViewModel : ViewModel() {
    val shoeTitle = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()

    var dialogIcon = R.drawable.ic_add
    var dialogTitle = R.string.add_item
    var deleteButtonVisibility = View.GONE

    private var _isInEditMode = false

    val isInEditMode: Boolean
        get() = _isInEditMode

    private val _canSave = MutableLiveData(false)

    val canSave: LiveData<Boolean>
        get() = _canSave

    fun updateCanWeSave() {
        _canSave.value = !shoeTitle.value.isNullOrBlank() &&
                !shoeSize.value.isNullOrBlank() &&
                !shoeCompany.value.isNullOrBlank()
    }

    var shoe: Shoe = Shoe("", 0.0, "", "")
        get() = field.apply {
            name = shoeTitle.value!!
            size = shoeSize.value?.toDouble() ?: 0.0
            company = shoeCompany.value!!
        }
        set(value) {
            field = value
            shoeTitle.value = value.name
            shoeSize.value = value.size.toString()
            shoeCompany.value = value.company

            dialogIcon = R.drawable.ic_edit
            dialogTitle = R.string.edit_item
            deleteButtonVisibility = View.VISIBLE
            _isInEditMode = true
        }
}