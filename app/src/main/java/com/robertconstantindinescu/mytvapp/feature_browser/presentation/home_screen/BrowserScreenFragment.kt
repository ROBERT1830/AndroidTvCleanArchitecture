package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import com.robertconstantindinescu.mytvapp.R
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.Category
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen.presenter.NewMoviesPosterWithImagePresenter
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen.presenter.PosterWithTextPresenter
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.util.Constants.TV_APP_NAME
import com.robertconstantindinescu.mytvapp.util.DataSourceState
import com.robertconstantindinescu.mytvapp.util.SingleUiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BrowserScreenFragment: BrowseSupportFragment()  {

    private val viewModel: BrowserScreenViewModel by viewModels()
    //private lateinit var adapter: ArrayObjectAdapter
    private val backGroundManager by lazy {
        BackgroundManager.getInstance(requireActivity()).apply {
            attach(requireActivity().window)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        title = TV_APP_NAME
        if (savedInstanceState == null){
            prepareEntranceTransition()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(BrowserScreenEvent.SearchMovies)


        observeData()
        collectSingleUiEvent()

        //NAVIGATION
        setOnItemViewClickedListener { itemViewHolder, _, _, _ ->
            when(itemViewHolder.view){
                is ImageCardView -> {

                }
                is TextView -> {
                    viewModel.onEvent(BrowserScreenEvent.OnOldMoviesHeaderClick)
                }
            }
        }
        setDynamicBackground()
    }

    private fun collectSingleUiEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.singleUiEvent.collectLatest { singleEvent ->
                when(singleEvent){
                    is SingleUiEvent.NavigateSucess -> {
                        findNavController().navigate(
                            BrowserScreenFragmentDirections.
                            actionHomeFragmentToBrowserOldMoviesFragment()
                        )
                    }
                    is SingleUiEvent.NavigateToDetails -> {

                    }
                    is SingleUiEvent.NavigationUp -> {

                    }
                    else -> Unit
                }
            }
        }


    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.mState.collect {
                when(it){
                    is DataSourceState.Empty -> {
                        //Do nothing
                        Unit
                    }
                    is DataSourceState.Loading -> {
                        Log.d("Loading", "IS_LOADING")

                    }
                    is DataSourceState.Success -> {
                        Log.d("SUCCESS", "IS_SUCCESS")
                        displayData(it.data)
                        startEntranceTransition()
                    }

                }
            }
        }

    }

    private fun displayData(categories: List<Category>) {
        val adapter = ArrayObjectAdapter(ListRowPresenter())
        for (category in categories){
            val headerItem = HeaderItem(category.id, category.genre)
            val rowAdapter = ArrayObjectAdapter(NewMoviesPosterWithImagePresenter())
            for (movie in category.newMovies){
                rowAdapter.add(movie)
            }
            adapter.add(ListRow(headerItem, rowAdapter))
        }

        val oldMoviesHeaderItem = HeaderItem(100, "Old Movies")
        val oldMoviesAdapter = ArrayObjectAdapter(PosterWithTextPresenter())
        oldMoviesAdapter.add(resources.getString(R.string.watch_old_movies))
        adapter.add(ListRow(oldMoviesHeaderItem, oldMoviesAdapter))

        this.adapter = adapter


    }

    private fun setDynamicBackground() {
        setOnItemViewSelectedListener { itemViewHolder, _, rowPresenter, _ ->
            if (itemViewHolder?.view != null) {
                if (itemViewHolder.view is ImageCardView){
                    val bitmapDrawable =
                        (itemViewHolder.view as ImageCardView).mainImageView.drawable as? BitmapDrawable

                    if (bitmapDrawable != null) {
                        Palette.from(bitmapDrawable.bitmap).generate { palette ->
                            (palette?.vibrantSwatch ?: palette?.dominantSwatch)?.let { swatch ->
                                backGroundManager.color = swatch.rgb
                            }
                        }
                    }
                }



            }
        }
    }

}































