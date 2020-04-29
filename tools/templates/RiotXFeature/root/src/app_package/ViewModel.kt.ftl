package ${escapeKotlinIdentifiers(packageName)}

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import im.vector.riotx.core.platform.VectorViewModel

<#if createViewEvents>
<#else>
import im.vector.riotx.core.platform.EmptyViewEvents
</#if>

class ${viewModelClass} @AssistedInject constructor(@Assisted initialState: ${viewStateClass})
    <#if createViewEvents>
    : VectorViewModel<${viewStateClass}, ${actionClass}, ${viewEventsClass}>(initialState) {
    <#else>
    : VectorViewModel<${viewStateClass}, ${actionClass}, EmptyViewEvents>(initialState) {
    </#if>
    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: ${viewStateClass}): ${viewModelClass}
    }

    companion object : MvRxViewModelFactory<${viewModelClass}, ${viewStateClass}> {

        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: ${viewStateClass}): ${viewModelClass}? {
            val fragment: ${fragmentClass} = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }

    override fun handle(action: ${actionClass}) {
		//TODO
    }

}
