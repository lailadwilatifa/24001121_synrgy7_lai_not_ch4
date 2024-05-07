package com.example.note.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.note.viewmodel.LoginViewModel
import com.example.note.R
import com.example.note.databinding.FragmentLoginBinding
import com.example.note.utils.PreferenceManager
import com.example.note.viewmodel.ViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val preferenceManager = PreferenceManager(requireContext())

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(null, preferenceManager, findNavController())
        )[LoginViewModel::class.java]

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        observerToast()

        setValue()

        return binding.root
    }

    private fun setValue(){
        binding.emailEditText.addTextChangedListener {
            viewModel._inputEmail.value = it?.toString()
        }

        binding.passwordEditText.addTextChangedListener {
            viewModel._inputPassword.value = it?.toString()
        }
    }

    private fun observerToast(){
        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}