package eg.gov.iti.jets.newsapp.auth.signup.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.auth.data.repo.RepoImpl
import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel.AuthState
import eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel.SignUpViewModel
import eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel.SignUpViewModelProvider
import eg.gov.iti.jets.newsapp.base.remote.APIClient
import eg.gov.iti.jets.newsapp.base.remote.AppRetrofit
import eg.gov.iti.jets.newsapp.databinding.FragmentSignUpBinding
import eg.gov.iti.jets.newsapp.util.NetworkConnectivityObserver


class SignUpFragment : Fragment() {
    private var binding: FragmentSignUpBinding? = null
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var signUpModel: SignUpModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        onClicks()
    }

    private fun initViewModel() {
        val viewModelFactory = SignUpViewModelProvider(RepoImpl(APIClient.signUpAPIService))
        signUpViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[SignUpViewModel::class.java]
    }

    private fun onClicks() {
        binding?.textViewSignIn?.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding?.btnSignUp?.setOnClickListener {
            Log.e("TAG", "onClicks: ")
            val email = binding?.emailEdiText?.text.toString().trim()
            val userName = binding?.userNameEdiText?.text.toString().trim()
            val password = binding?.passwordEditText?.text.toString().trim()
            signUpModel =
                SignUpModel(email = email, password = password, displayName = userName)
            if (checkNetwork()) {
                binding?.progressBar?.visibility = View.VISIBLE
                signUpViewModel.validateInputs(signUpModel)
                observeData()
                observeErrorMessage()
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    getString(R.string.no_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkNetwork(): Boolean {
        return NetworkConnectivityObserver.isOnline(requireActivity().applicationContext)
    }

    private fun observeData() {
        lifecycleScope.launchWhenCreated {
            signUpViewModel.validationStateFlow.collect {
                when (it) {
                    is AuthState.onSuccess -> {
                        binding?.progressBar?.visibility = View.GONE
                        navigateToNextScreen()
                        Toast.makeText(
                            requireActivity().applicationContext,
                            getString(it.message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is AuthState.onError -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(
                            requireActivity().applicationContext,
                            getString(it.message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun observeErrorMessage() {
        lifecycleScope.launchWhenCreated {
            signUpViewModel.errorStateFlow.collect { it ->
                binding?.progressBar?.visibility = View.GONE
                Toast.makeText(
                    requireActivity().applicationContext,
                    it?.message ?: "",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun navigateToNextScreen() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_signUpFragment_to_homeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}