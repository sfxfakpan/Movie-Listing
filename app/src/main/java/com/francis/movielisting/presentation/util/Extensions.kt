package com.francis.movielisting.presentation.util

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers

fun Fragment.openUrl(uri: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    requireContext().startActivity(intent)
}

fun View.showSnackBar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun <T> ViewModel.liveDataBlockScope(block: suspend () -> LiveData<T>): LiveData<T> {
    return liveData(viewModelScope.coroutineContext + Dispatchers.IO) { emitSource(block()) }
}
