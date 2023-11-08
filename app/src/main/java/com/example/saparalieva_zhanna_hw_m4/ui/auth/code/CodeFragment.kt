package com.example.saparalieva_zhanna_hw_m4.ui.auth.code

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.saparalieva_zhanna_hw_m4.R
import com.example.saparalieva_zhanna_hw_m4.databinding.FragmentCodeBinding
import com.example.saparalieva_zhanna_hw_m4.ui.auth.phone.PhoneFragment
import com.example.saparalieva_zhanna_hw_m4.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString(PhoneFragment.VER_ID_KEY)
        binding.btnAccept.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(verId!!, binding.etCode.text.toString())

            signInWithPhoneAuthCredential(credential)
            findNavController().navigate(R.id.navigation_home)
        }

        val inputText = binding.outlinedTextField.editText?.text.toString()
        binding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnSuccessListener {
                context?.showToast("Registration was successful ")
                findNavController().navigate(R.id.navigation_home)
            }
            .addOnFailureListener{
            context?.showToast(it.message.toString())
            }
    }


}