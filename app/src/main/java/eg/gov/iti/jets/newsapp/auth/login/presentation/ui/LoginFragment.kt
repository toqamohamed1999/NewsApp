package eg.gov.iti.jets.newsapp.auth.login.presentation.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginResponseState
import eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel.LoginViewModel
import eg.gov.iti.jets.newsapp.databinding.FragmentLoginBinding
import eg.gov.iti.jets.newsapp.util.Dialogs
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

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
        lifecycleScope.launch {
            viewModel.loginState.collect {
                when(it){
                    is LoginResponseState.Success ->{
                        //ToDo
                        /*
                        * Goto Main
                        * */
                    }
                    is LoginResponseState.Error ->{
                        Dialogs.getAletDialogBuilder(requireContext(),"Login","Unsuccessful  Login").show()
                    }
                    else ->{

                    }
                }
            }
        }
        binding.emailEdiText.doAfterTextChanged {

        }
    }

}