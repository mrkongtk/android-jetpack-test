package com.yourparkingspace.androidtechtest.views

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.yourparkingspace.androidtechtest.models.HotSubmissionListViewData
import com.yourparkingspace.androidtechtest.ui.theme.AndroidTechTestTheme
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.GetHotSubmissions
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.ScrollReachEdgeListener
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.StatedBoolean
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.StatedString
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.SubmissionListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val submissionListViewModel = SubmissionListViewModel()
    private var loadedData: HotSubmissionListViewData? = null
    private var isLoading = StatedBoolean(false)
    private var showContent = StatedBoolean(false)
    private var showContentUrl = StatedString(String())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTechTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(
                        isLoading,
                        showContent,
                        showContentUrl,
                        submissionListViewModel, object : ScrollReachEdgeListener {
                            override fun onReachEnd() {
                                loadData(loadedData?.after)
                            }
                        })
                }
            }
        }
        loadData(loadedData?.after)
    }

    override fun onDestroy() {
        mCompositeDisposable.dispose()
        super.onDestroy()
    }

    private fun loadData(after: String?) {
        synchronized(mCompositeDisposable) {
            if (isLoading.value) {
                return
            }
            isLoading.value = true
        }
        mCompositeDisposable.add(
            GetHotSubmissions.execute(after)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { x ->
                        loadedData = x
                        submissionListViewModel.addRecords(x.submissionDataViewList)
                    },
                    { e -> Log.e(TAG, e.stackTraceToString()) },
                    {
                        synchronized(mCompositeDisposable) {
                            isLoading.value = false
                        }
                    }
                ))
    }
}

@Composable
fun MainView(
    isLoading: StatedBoolean,
    showContent: StatedBoolean,
    showContentUrl: StatedString,
    viewModel: SubmissionListViewModel,
    onReachEndListener: ScrollReachEdgeListener?
) {

    ShowList(viewModel, onReachEndListener, showContent, showContentUrl)
    ShowWebContent(showContent, showContentUrl)
    LoadingView(isLoading)
}

@Composable
fun ShowList(viewModel: SubmissionListViewModel,
             onReachEndListener: ScrollReachEdgeListener?,
             showContent: StatedBoolean,
             showContentUrl: StatedString,){
    val submissionListState = viewModel.listStateFlow.collectAsState()

    val scrollState = rememberLazyListState()

    val scrollBoolean = remember { derivedStateOf { scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == scrollState.layoutInfo.totalItemsCount - 1 } }

    if (scrollBoolean.value) {
        onReachEndListener?.onReachEnd()
    }

    LazyColumn(state = scrollState,
        modifier = Modifier.fillMaxHeight()) {

        items(items = submissionListState.value, itemContent = {
                item ->
            Card(
//                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .clickable {
                        showContent.value = true
                        showContentUrl.value = item.url
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 100.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        text = item.title
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = item.author,
                        textAlign = TextAlign.Right,
                    )
                }
            }

        })
    }
}

@Composable
fun ShowWebContent(
    showContent: StatedBoolean,
    showContentUrl: StatedString,
){
    if (showContent.value) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                OutlinedButton(
                    modifier = Modifier.padding(4.dp).width(50.dp),
                    onClick = { showContent.value = false }
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Center,
                        text = "X"
                    )
                }
                AndroidView(factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = WebViewClient()
                        loadUrl(showContentUrl.value)
                    }
                }, update = {
                    it.loadUrl(showContentUrl.value)
                })
            }

        }
    }
}

@Composable
fun LoadingView(isLoading: StatedBoolean) {
    if (isLoading.value){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    AndroidTechTestTheme {
        MainView(
            StatedBoolean(true),
            StatedBoolean(true),
            StatedString("https://www.google.com"),
            SubmissionListViewModel(),
            null)
    }
}