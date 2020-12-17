package com.udacity.shoestore.ui.login

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
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        with(binding) {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = this@LoginFragment

            onLoginButtonClicked = View.OnClickListener { onLoginButtonClicked() }
        }

        viewModel.email.observe(viewLifecycleOwner) { viewModel.fieldsUpdated() }
        viewModel.password.observe(viewLifecycleOwner) { viewModel.fieldsUpdated() }

        return binding.root
    }

    private fun onLoginButtonClicked() {
        val navigateTo = LoginFragmentDirections.toWelcomeFragment(viewModel.email.value!!)
        findNavController().navigate(navigateTo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }
}