package com.udacity.shoestore.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private val viewModel: WelcomeViewModel by viewModels()

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = WelcomeFragmentArgs.fromBundle(requireArguments())

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        with(binding) {
            onFabClicked = View.OnClickListener { onFloatingActionButtonClicked() }

            viewModel = this@WelcomeFragment.viewModel.apply {
                userName = args.userName
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    private fun onFloatingActionButtonClicked() {
        val navigateTo = WelcomeFragmentDirections.toInstructionsFragment()
        findNavController().navigate(navigateTo)
    }
}