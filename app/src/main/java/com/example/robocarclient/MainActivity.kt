package com.example.robocarclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonForward = findViewById<Button>(R.id.buttonForward)
        val buttonLeft = findViewById<Button>(R.id.buttonLeft)
        val buttonRight = findViewById<Button>(R.id.buttonRight)
        val buttonStop = findViewById<Button>(R.id.buttonStop)
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val buttonBark1 = findViewById<Button>(R.id.buttonBark)
        val buttonBark2 = findViewById<Button>(R.id.buttonBark2)
        val buttonConnect = findViewById<Button>(R.id.buttonConnect)
        val buttonDisconnect = findViewById<Button>(R.id.buttonDisconnect)

        var socketSession: DefaultClientWebSocketSession? = null

        suspend fun moveForward() {
            try {
                socketSession?.send(Frame.Text("forward"))
            } catch (e: Exception) {
                println(e.message)
            }
        }

        buttonForward.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                moveForward()
            }
        }

        suspend fun moveBack() {
            try {
                socketSession?.send(Frame.Text("back"))
            } catch (e: Exception) {

            }
        }

        suspend fun moveLeft() {
            try {
                socketSession?.send(Frame.Text("left"))
            } catch (e: Exception) {

            }
        }

        suspend fun moveRight() {
            try {
                socketSession?.send(Frame.Text("right"))
            } catch (e: Exception) {

            }
        }

        suspend fun stop() {
            try {
                socketSession?.send(Frame.Text("stop"))
            } catch (e: Exception) {

            }
        }

        suspend fun playBark() {
            try {
                socketSession?.send(Frame.Text("bark"))
            } catch (e: Exception) {

            }
        }

        suspend fun playBark2() {
            try {
                socketSession?.send(Frame.Text("bark2"))
            } catch (e: Exception) {

            }
        }

        suspend fun connect() {
            try {
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        socketSession = HttpClient(CIO) { install(WebSockets) }
                            .webSocketSession(
                                method = HttpMethod.Get,
                                host = "192.168.0.152",
                                port = 4444,
                                path = "/"
                            )
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            } catch (e: Exception) {

            }
        }

        fun disconnect() {
            socketSession?.cancel()
        }

        buttonBack.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                moveBack()
            }
        }

        buttonLeft.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                moveLeft()
            }
        }

        buttonRight.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                moveRight()
            }
        }

        buttonStop.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                stop()
            }
        }

        buttonBark1.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                playBark()
            }
        }

        buttonBark2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                playBark2()
            }
        }

        buttonConnect.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                connect()
            }
        }

        buttonDisconnect.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                disconnect()
            }
        }

    }
}