package com.example.architecturedemo.views.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider

import com.example.architecturedemo.R
import com.example.architecturedemo.utils.getViewModel
import com.example.architecturedemo.utils.ResourceState
import dagger.android.support.DaggerFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturedemo.ActivityIndicatorListener
import com.example.architecturedemo.views.adapter.UsersAdapter
import com.example.domain.model.UserDomain
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : DaggerFragment(), HomeNavigator {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: HomeViewModel
    var activityIndicator: ActivityIndicatorListener? = null
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = getViewModel(viewModelFactory)
        homeViewModel.setNavigator(this)
        if (activity!! is ActivityIndicatorListener) {
            activityIndicator = activity as ActivityIndicatorListener
        }
        initObservers()
    }

    private fun initObservers() {
        homeViewModel.getAllUsersLiveData.observe(this, Observer {
            when (it?.status) {
                ResourceState.LOADING -> {
                    showActivityIndicator()
                }
                ResourceState.SUCCESS -> {
                    it.data?.let {
                        usersAdapter = UsersAdapter(
                            userList = it
                        )
                        users_recyclerView.adapter = usersAdapter
                        users_recyclerView.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        val divider =
                            DividerItemDecoration(users_recyclerView.context, DividerItemDecoration.VERTICAL)
                        divider.setDrawable(
                            ContextCompat.getDrawable(
                                context!!, R.drawable.row_divider
                            )!!
                        )
                        users_recyclerView.addItemDecoration(divider)
                        dismissActivityIndicator()
                    }
                    dismissActivityIndicator()
                }
                ResourceState.ERROR -> {
                    dismissActivityIndicator()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getAllUsers()
    }

    private fun showActivityIndicator() {
        activityIndicator?.showActivityIndicator()
    }

    private fun dismissActivityIndicator() {
        activityIndicator?.dismissActivityIndicator()
    }


    override fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
