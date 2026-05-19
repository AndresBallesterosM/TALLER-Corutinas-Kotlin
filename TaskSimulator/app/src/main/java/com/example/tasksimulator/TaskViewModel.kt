package com.tucorreo.tasksimulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class TaskUiState(
    val isLoading: Boolean = false,
    val message: String = "Presiona un botón para comenzar",
    val log: List<String> = emptyList()
)

class TaskViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState

    private var currentJob: Job? = null

    private fun addLog(entry: String) {
        val currentLog = _uiState.value.log.toMutableList()
        currentLog.add(0, "• $entry")

        _uiState.value = _uiState.value.copy(
            log = currentLog
        )
    }

    fun runBlockingTask() {

        addLog("⏳ Iniciando tarea bloqueante")

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            message = "Ejecutando tarea bloqueante..."
        )

        Thread.sleep(4000)

        _uiState.value = _uiState.value.copy(
            isLoading = false,
            message = "Tarea bloqueante finalizada"
        )

        addLog("✅ Tarea bloqueante completada")
    }

    fun runAsyncTask() {

        currentJob = viewModelScope.launch {

            addLog("⏳ Iniciando tarea asíncrona")

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                message = "Ejecutando tarea asíncrona..."
            )

            withContext(Dispatchers.IO) {
                delay(4000)
            }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = "Tarea asíncrona completada"
            )

            addLog("✅ Tarea asíncrona completada")
        }
    }

    fun runParallelTasks() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                message = "Ejecutando tareas paralelas..."
            )

            val start = System.currentTimeMillis()

            val job1 = launch(Dispatchers.IO) {
                delay(2000)
                addLog("✔ Tarea 1 completada")
            }

            val job2 = launch(Dispatchers.IO) {
                delay(3000)
                addLog("✔ Tarea 2 completada")
            }

            val job3 = launch(Dispatchers.IO) {
                delay(1500)
                addLog("✔ Tarea 3 completada")
            }

            job1.join()
            job2.join()
            job3.join()

            val total = (System.currentTimeMillis() - start) / 1000.0

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = "Paralelas terminadas en ${total}s"
            )
        }
    }

    fun runSequentialTasks() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                message = "Ejecutando tareas secuenciales..."
            )

            val start = System.currentTimeMillis()

            withContext(Dispatchers.IO) {

                delay(2000)
                addLog("✔ Tarea 1 completada")

                delay(3000)
                addLog("✔ Tarea 2 completada")

                delay(1500)
                addLog("✔ Tarea 3 completada")
            }

            val total = (System.currentTimeMillis() - start) / 1000.0

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = "Secuenciales terminadas en ${total}s"
            )
        }
    }

    fun runCounterTask() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true
            )

            for (i in 1..10) {

                _uiState.value = _uiState.value.copy(
                    message = "Contador: $i"
                )

                delay(1000)
            }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = "Contador terminado ✅"
            )
        }
    }

    fun runFailingTask() {

        viewModelScope.launch {

            try {

                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    message = "Simulando error..."
                )

                delay(2000)

                throw Exception("Fallo de red simulado")

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    message = "Ocurrió un error ❌"
                )

                addLog("❌ Error: ${e.message}")
            }
        }
    }
    
    fun runCancellableTask() {

        currentJob = viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true
            )

            for (i in 1..10) {

                _uiState.value = _uiState.value.copy(
                    message = "Tarea cancelable: $i"
                )

                delay(1000)
            }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = "Tarea terminada ✅"
            )
        }
    }

    fun cancelCurrentTask() {

        currentJob?.cancel()

        _uiState.value = _uiState.value.copy(
            isLoading = false,
            message = "Tarea cancelada ❌"
        )

        addLog("⚠️ Tarea cancelada")
    }

    fun clearLog() {
        _uiState.value = TaskUiState()
    }
}