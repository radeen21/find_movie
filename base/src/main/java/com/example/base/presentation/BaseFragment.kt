package com.example.base.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.base.di.ComponentOwner
import com.example.base.di.Injectable
import com.example.base.di.SubComponentOwner
import com.example.base.di.component.BaseComponent


@SuppressLint("Registered")
abstract class BaseFragment<VM : BaseViewModel> : Fragment(),
    Injectable {
    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onInitObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectComponent()
    }

    protected open fun onInitViews() = Unit

    protected open fun onInitObservers() = Unit

    override fun <C : BaseComponent?> getBaseComponent(cClass: Class<C>?): C {
        if (activity?.application is ComponentOwner<*>) {
            val component =
                (activity?.application as ComponentOwner<*>).getComponent()!!
            if (component is BaseComponent) {
                return cClass?.cast(component)!!
            }
        }
        throw IllegalArgumentException("Application does not contain component with type $cClass")
    }

    override fun <SubC> getAppSubComponent(subClass: Class<SubC>?): SubC {
        if (activity?.application is SubComponentOwner) {
            val subComponent: SubC? =
                (activity?.application as SubComponentOwner).getSubComponent(subClass)
            if (subClass?.isInstance(subComponent)!!) {
                return subClass.cast(subComponent)!!
            }
        }
        throw java.lang.IllegalArgumentException("Application does not contain sub component with type $subClass")
    }
}