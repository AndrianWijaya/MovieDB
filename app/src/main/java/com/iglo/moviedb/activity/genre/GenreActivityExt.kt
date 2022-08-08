package com.iglo.moviedb.activity.genre

import android.app.ProgressDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import com.iglo.common.ResponseApps
import com.iglo.common.entity.genre.Genre
import com.iglo.common.helper.clear
import com.iglo.common.helper.isEmpty
import com.iglo.common.helper.size
import com.iglo.common.helper.toggle
import com.iglo.moviedb.activity.discover_movie.DiscoverMovieActivity

var actionMode: ActionMode? = null

fun GenreActivity.observeLiveData() = with(vm) {
    binding.recyclerView.adapter = adapter

    var dialog: ProgressDialog? = null
    genreData.observe(this@observeLiveData) {
        when (it.condition) {
            ResponseApps.ERROR -> {
                dialog?.dismiss()
                Toast.makeText(
                    this@observeLiveData, "Network Error(${it.code.toString()})",
                    Toast.LENGTH_SHORT
                ).show()
            }
            ResponseApps.SUCCESS -> {
                adapter.submitData(it.data.orEmpty())
                dialog?.dismiss()
            }
            ResponseApps.LOADING -> {
                dialog?.dismiss()
                dialog = ProgressDialog(this@observeLiveData).apply {
                    setCancelable(false)
                    setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    setMessage("Memproses ..")
                    show()
                }
            }
        }

    }
    selection.observe(this@observeLiveData) {
        actionMode ?: kotlin.run {
            actionMode = startSupportActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return true
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    return true
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    val sel = vm.selection
                    if (!sel.isEmpty()) {
                        adapter.clearSelection { vm.selection.clear() }
                    }
                    actionMode = null
                }

            })
        }
        actionMode?.title = "${vm.selection.size()} selected"
        if (it.isEmpty()) {
            actionMode?.finish()
            actionMode = null
        }
    }
}


fun GenreActivity.initBinding() = with(vm) {
    binding.recyclerView.adapter = adapter
    binding.nextButton.setOnClickListener {
        selection.value.orEmpty().map {
            it.id
        }.apply {
            val intent = Intent(this@initBinding, DiscoverMovieActivity::class.java)
            intent.putIntegerArrayListExtra("EXTRA_DATA", ArrayList(this))
            startActivity(intent)
        }
    }

    binding.retry.setOnClickListener {
        getGenre()
    }
}

fun GenreActivity.toggleClick(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selection.toggle(genre)
    }

}

fun GenreActivity.startActionMode(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selection.toggle(genre)
    }
}