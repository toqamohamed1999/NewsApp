package eg.gov.iti.jets.newsapp.profile.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.databinding.FragmentSecondProfileBinding
import eg.gov.iti.jets.newsapp.profile.data.local.ProfileLocalSource
import eg.gov.iti.jets.newsapp.profile.data.repo.ProfileRepo
import eg.gov.iti.jets.newsapp.profile.presentation.viewModel.ProfileViewModel
import eg.gov.iti.jets.newsapp.profile.presentation.viewModel.ProfileViewModelProvider
import eg.gov.iti.jets.newsapp.util.Dialogs
import kotlinx.coroutines.flow.collectLatest


class SecondProfileFragment : Fragment() {


    private var binding1: FragmentSecondProfileBinding? = null
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding1 = FragmentSecondProfileBinding.inflate(layoutInflater, container, false)
        return binding1?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setUserData()
        onClicks()
    }


    private fun initViewModel() {
        val viewModelProvider =
            ProfileViewModelProvider(ProfileRepo.getProfileRepoInstance(ProfileLocalSource.getLocalSourceInstance()))
        profileViewModel =
            ViewModelProvider(requireActivity(), viewModelProvider)[ProfileViewModel::class.java]
    }

    private fun setUserData() {
        lifecycleScope.launchWhenStarted {
            profileViewModel.userSharedFlow.collectLatest { user ->
                binding1?.texViewUserEmail?.text = user?.email
                binding1?.textViewUserName?.text = user?.userName
            }
        }

    }

    private fun onClicks() {
        binding1?.btnLogout?.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        Dialogs.showConfirmationDialog(
            requireContext(), getString(R.string.sure_to_log_out),
            //onYes
            {
                logeOut()
            },
            // on No
            {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.logoutFailed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    private fun logeOut() {
        val isLogout = profileViewModel.deleteUser()
        if (isLogout == true) {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_secondProfileFragment_to_loginFragment)
        } else {
            Toast.makeText(
                requireActivity().baseContext,
                getString(R.string.logoutFailed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding1 = null
    }
}