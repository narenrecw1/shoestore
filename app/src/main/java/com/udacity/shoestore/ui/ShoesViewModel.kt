package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {
    // Adding dummy data. Ideally should come from a repository
    private val shoesList = mutableListOf(
        Shoe("VERSE-Slow down Gym shoes for Men", 8.0, "Sketchers", "Perfect gym shoes for men."),
        Shoe("VERSE-Slow down Gym shoes for Men", 9.0, "Sketchers", "Perfect gym shoes for men."),
        Shoe("VERSE-Slow down Gym shoes for Men", 10.0, "Sketchers", "Perfect gym shoes for men."),
        Shoe("VERSE-Slow down Gym shoes for Men", 11.0, "Sketchers", "Perfect gym shoes for men."),

        Shoe("CALYFIA Sneakers for Women", 4.0, "Fila", "Perfect sneakers for women."),
        Shoe("CALYFIA Sneakers for Women", 5.0, "Fila", "Perfect sneakers for women."),
        Shoe("CALYFIA Sneakers for Women", 6.0, "Fila", "Perfect sneakers for women."),
        Shoe("CALYFIA Sneakers for Women", 7.0, "Fila", "Perfect sneakers for women.")
    )

    private val _shoes = MutableLiveData<List<Shoe>>(shoesList)

    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe(shoe: Shoe) {
        shoesList.add(shoe)
        _shoes.value = shoesList
    }

    fun updateShoe(@Suppress("UNUSED_PARAMETER") shoe: Shoe) {
        _shoes.value = shoesList
    }

    fun removeShoe(shoe: Shoe) {
        shoesList.remove(shoe)
        _shoes.value = shoesList
    }
}