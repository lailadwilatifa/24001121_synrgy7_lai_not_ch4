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
import com.example.note.R
import com.example.note.databinding.FragmentRegisterBinding
import com.example.note.utils.PreferenceManager
import com.example.note.viewmodel.RegisterViewModel
import com.example.note.viewmodel.ViewModelFactory

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        val preferenceManager = PreferenceManager(requireContext())

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(null, preferenceManager, findNavController())
        )[RegisterViewModel::class.java]

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        observerToast()

        setValue()

        return binding.root
    }

    private fun setValue(){
        binding.usernameEditText.addTextChangedListener {
            viewModel._inputUsername.value = it?.toString()
        }

        binding.emailEditText.addTextChangedListener {
            viewModel._inputEmail.value = it?.toString()
        }

        binding.passwordEditText.addTextChangedListener {
            viewModel._inputPassword.value = it?.toString()
        }

        binding.passwordConfirmEditText.addTextChangedListener {
            viewModel._inputConfirmPassword.value = it?.toString()
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