package com.tucorreo.tasksimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = "Task Simulator",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(uiState.message)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.runBlockingTask()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(" Tarea Bloqueante")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.runAsyncTask()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tarea Asíncrona")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.runParallelTasks()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(" Paralelas")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.runSequentialTasks()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Secuenciales")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.runCounterTask()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Contador")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.runFailingTask()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(" Tarea con Error")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.runCancellableTask()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(" Tarea Cancelable")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.cancelCurrentTask()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {

                    items(uiState.log) { item ->

                        Text(item)

                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}