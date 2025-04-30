package com.example.fitnessapp3.ui.viewmodel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class HomeViewModel(context: Context) : ViewModel(), SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var stepSensor: Sensor? = null
    private var stepCount = 0

    var steps by mutableStateOf(0)
    var calories by mutableStateOf(0)
    var distance by mutableStateOf(0f)

    init {
        //init the step counter
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepSensor != null) {
            //reg list
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {

            stepCount = event.values[0].toInt()
            steps = stepCount
            calories = (steps * 0.05).toInt()  //0.05 calories per step
            //0.0008 km per step
            distance = (steps * 0.0008f)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

   //cleared
    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}