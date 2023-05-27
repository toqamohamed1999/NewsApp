package eg.gov.iti.jets.newsapp.auth.login.presentation.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import eg.gov.iti.jets.newsapp.BuildConfig
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.auth.login.data.local.LoginRepoImpl
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginModel
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginAPIInterface
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginResponseState
import eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel.LoginViewModel
import eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel.LoginViewModelFactory
import eg.gov.iti.jets.newsapp.base.remote.APIClient
import eg.gov.iti.jets.newsapp.databinding.FragmentLoginBinding
import eg.gov.iti.jets.newsapp.util.Dialogs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private  var validEmail =false
    private var validPassword = false

    private lateinit var binding: FragmentLoginBinding
    private  lateinit var viewModel:LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = LoginViewModelFactory(LoginRepoImpl(APIClient.loginAPIService))
                viewModel = ViewModelProvider(this,factory)[LoginViewModel::class.java]

        lifecycleScope.launch {
            viewModel.loginState.collect {
                when(it){
                    is LoginResponseState.Success ->{
                        viewModel.saveUserDataToSharedPrefs(it.response)
                        view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    is LoginResponseState.Error ->{
                        Dialogs.getAletDialogBuilder(requireContext(),"Login","Unsuccessful  Login").show()
                    }
                    else ->{
                        Toast.makeText(requireContext(),"Loading",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        lifecycleScope.launch{
            viewModel.userExists.collect{
                if (it)  {
                    Dialogs.SnakeToast(view,"Login successful")
                    view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                else{
                    Dialogs.SnakeToast(view,"Please Login First")
                }
            }
        }
        binding.emailEdiText.doAfterTextChanged {
           if(!viewModel.validateEmail(binding.emailEdiText.text.toString()))
           {
                binding.emailEdiText.setTextColor(Color.RED)
               this.validEmail = false
           }else{
               binding.emailEdiText.setTextColor(Color.GREEN)
               this.validEmail = true
           }
        }
        binding.passwordEdiText.doAfterTextChanged {
            if(!viewModel.validatePassword(binding.passwordEdiText.text.toString()))
            {
                binding.passwordEdiText.setTextColor(Color.RED)
                this.validPassword = false
            }else{
                binding.passwordEdiText.setTextColor(Color.GREEN)
                this.validPassword = true
            }
        }
        binding.LoginBtn.setOnClickListener {
            if(validEmail && validPassword){
                viewModel.login(BuildConfig.API_KEY, LoginModel(
                    binding.emailEdiText.text.toString(),
                    binding.passwordEdiText.text.toString(),true))
            }
        }
    }

}