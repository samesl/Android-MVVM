package com.android.saman.sample.androidmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.saman.sample.androidmvvm.di.modules.ActivityModule
import com.android.saman.sample.androidmvvm.di.modules.FragmentModule
import com.android.saman.sample.androidmvvm.utils.app
import com.android.saman.sample.androidmvvm.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_first.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val component by lazy { requireActivity().app.applicationComponent().plus(FragmentModule()) }
    @Inject
    lateinit var factory : ViewModelProvider.Factory
    private lateinit var baseViewModel: BaseViewModel
    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        component.inject(this)
        baseViewModel = ViewModelProvider(this,factory)[BaseViewModel::class.java]
        Timber.d("ViewModel is working fine and the result is ${baseViewModel.returnTestValue()}")

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable.add(baseViewModel.getAllSampleData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val sampleEntity = "First Name : " + it.firstName + " Last Name : " + it.lastName
                textview_first.text = sampleEntity
            })
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}