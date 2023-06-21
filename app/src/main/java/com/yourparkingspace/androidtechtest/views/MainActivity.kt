package com.yourparkingspace.androidtechtest.views

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.yourparkingspace.androidtechtest.models.HotSubmissionListViewData
import com.yourparkingspace.androidtechtest.ui.theme.AndroidTechTestTheme
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.GetHotSubmissions
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.ScrollReachEdgeListener
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.StatedBoolean
import com.yourparkingspace.androidtechtest.viewmodels.viewassistant.SubmissionListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val submissionListViewModel = SubmissionListViewModel()
    private var loadedData: HotSubmissionListViewData? = null
    private var isLoading = StatedBoolean(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTechTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainView(
                        isLoading,
                        submissionListViewModel, object : ScrollReachEdgeListener {
                            override fun onReachEnd() {
                                loadData(loadedData?.after)
                            }
                        })

                }
            }
        }
        loadData(null)
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
    viewModel: SubmissionListViewModel,
    onReachEndListener: ScrollReachEdgeListener?
) {
    ShowList(viewModel, onReachEndListener)
    LoadingView(isLoading)
}

@Composable
fun ShowList(viewModel: SubmissionListViewModel,
             onReachEndListener: ScrollReachEdgeListener?, ){
    val todoListState = viewModel.listStateFlow.collectAsState()

    val scrollState = rememberLazyListState()

    val scrollBoolean = remember { derivedStateOf { scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == scrollState.layoutInfo.totalItemsCount - 1 } }

    if (scrollBoolean.value) {
        onReachEndListener?.onReachEnd()
    }

    LazyColumn(state = scrollState,
        modifier = Modifier.fillMaxHeight()) {

        items(items = todoListState.value, itemContent = {
                item ->
            Card(
//                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .clickable {
                        println("clicked: " + item.id)
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
fun MainTodoViewPreview() {
    AndroidTechTestTheme {
        MainView(
            StatedBoolean(true),
            SubmissionListViewModel(),
            null
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTechTestTheme {
        Greeting("Android")
    }
}