package com.iglo.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<Binding : ViewDataBinding, ViewModel : AndroidViewModel> :
    AppCompatActivity() {
    lateinit var binding: Binding
    abstract val layoutResourceId: Int

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    abstract val vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId,null,false
        )
        binding.lifecycleOwner = this
        AndroidInjection.inject(this)
        binding.setVariable(BR.vm,vm)
        setContentView(binding.root)

    }
}