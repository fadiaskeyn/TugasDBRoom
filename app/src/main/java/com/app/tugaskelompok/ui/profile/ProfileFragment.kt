package com.app.tugaskelompok.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.app.tugaskelompok.LoginActivity
import com.app.tugaskelompok.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val preferences = this.requireActivity()
            .getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val userName = preferences.getString("userName", null)
        val userEmail = preferences.getString("userEmail", null)
        val userChar = userName?.first()


        binding.apply {
            profileName.text = userName
            profileEmail.text = userEmail
            profileChar.text = userChar.toString()
        }

        val buttonLogout: Button = binding.logoutButton
        buttonLogout.setOnClickListener {
            // Implement the logout logic here
            // For Room database-based authentication, you would clear the user's session.

            // For example, if you store the user's authentication state in shared preferences:
            context?.getSharedPreferences("UserData", 0)?.edit()?.clear()?.commit();
            // After logging out, navigate back to the login activity or any other desired destination
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish() // Close the profile activity or fragment
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}