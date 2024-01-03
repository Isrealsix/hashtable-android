package com.example.hashtable

import Hash
import HashSerialization
import IntObjectBuilder
import ObjectFactory
import StringObjectBuilder
import Vector2D
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream

enum class SelectedType(val value: String) {
    STRING("String"),
    VECTOR("Vector2D")
}

class MainActivity : ComponentActivity() {
    private lateinit var mapSizeInput: EditText
    private lateinit var addButton: Button
    private lateinit var stringButton: Button
    private lateinit var vectorButton: Button
    private lateinit var enterNumberInput: EditText
    private lateinit var generateButton: Button
    private lateinit var idInput: EditText
    private lateinit var hashButton: Button
    private lateinit var getIdButton: Button
    private lateinit var removeButton: Button
    private lateinit var serializeButton: Button
    private lateinit var deserializeButton: Button
    private lateinit var resizeButton: Button

    private lateinit var timeContent: TextView
    private lateinit var resultContent: TextView

    lateinit var selectedType: SelectedType
    var size = 0
    var hash: Hash = Hash(0)
    var factory = ObjectFactory().apply {
        objectRecorder(StringObjectBuilder())
        objectRecorder(IntObjectBuilder())
    }
    var vector2D = Vector2D()
    var hashSerialization = HashSerialization()



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapSizeInput = findViewById(R.id.enter_map_size)

        addButton = findViewById(R.id.addButtonString)
        addButton.setOnClickListener { onAddButtonClicked() }

        stringButton = findViewById(R.id.stringButtonString)
        stringButton.setOnClickListener { onStringButtonClicked() }

        vectorButton = findViewById(R.id.vectorButtonString)
        vectorButton.setOnClickListener { onVectorButtonClicked() }

        enterNumberInput = findViewById(R.id.enter_number)

        generateButton = findViewById(R.id.generateButtonString)
        generateButton.setOnClickListener { onGenerateButtonClicked() }

        idInput = findViewById(R.id.enter_id)

        hashButton = findViewById(R.id.hashButtonString)
        hashButton.setOnClickListener { onHashButtonClicked() }

        getIdButton = findViewById(R.id.getIdButtonString)
        getIdButton.setOnClickListener { onGetIdButtonClicked() }

        removeButton = findViewById(R.id.removeButtonString)
        removeButton.setOnClickListener { onRemoveButtonClicked() }

        serializeButton = findViewById(R.id.serializeButtonString)
        serializeButton.setOnClickListener { onSerializeButtonClicked() }

        deserializeButton = findViewById(R.id.deserializeButtonString)
        deserializeButton.setOnClickListener { onDeserializeButtonClicked() }

        resizeButton = findViewById(R.id.resizeButtonString)
        resizeButton.setOnClickListener { onResizeButtonClicked() }



        timeContent = findViewById(R.id.timeTextView)
        resultContent = findViewById(R.id.largeTextView)
    }


    @SuppressLint("SetTextI18n")
    private fun onAddButtonClicked() {
//        resultContent.text = mapSizeInput.text

        try {
            val getValue = mapSizeInput.text.toString()
            size = getValue.toInt()
            hash = Hash(size)
            if (size > 0) {
//                val myDialog = MyDialog()
//                myDialog.isVisible = true
//                myDialog.setBounds(500, 500, 500, 450)
            } else {
                resultContent.text = "Incorrect value"
            }
        } catch (exception: Exception) {
            resultContent.text = "Text field should contain integer value"
        }
    }

    private fun onStringButtonClicked() {
        selectedType = SelectedType.STRING
    }

    private fun onVectorButtonClicked() {
        selectedType = SelectedType.VECTOR
    }

    private fun onGenerateButtonClicked() {
//        resultContent.text = enterNumberInput.text
        val numberValue = enterNumberInput.text.toString()

        if (selectedType === SelectedType.STRING) {
            val start = System.currentTimeMillis()
            val number = numberValue.toInt()
            val stringObjectBuilder = factory.getBuilderByName("String")
                ?: throw java.lang.IllegalStateException("Type not found")
            for (i in 0 until number) {
                hash.put(stringObjectBuilder.create())
            }
            // увеличение хэш-таблицы
            //hash = hash.resizeHash(hash)
            val finish = System.currentTimeMillis() - start
            timeContent.text = finish.toString()
        } else {

            val number = numberValue.toInt()
            for (i in 0 until number) {
                hash.put(vector2D.create())
                //factory = ObjectFactory(vector2D)
            }
            factory.getBuilderByName("Vector2D")
            hash = hash.resizeHash(timeContent)
        }
    }

    fun initialiseTimeContent() {
        timeContent.text = ""
    }

    private fun onHashButtonClicked() {
        initialiseTimeContent()
        resultContent.text = hash.toString()
    }

    private fun onGetIdButtonClicked() {


        val keyGetter = idInput.text.toString()
        val number = keyGetter.toIntOrNull()

        if (number != null) {
            val res = hash!![number].toString()
            resultContent.text = res
        } else {
            resultContent.text = "Not found"
        }
    }

    private fun onRemoveButtonClicked() {
        resultContent.text = "Remove"

        val keyRemover = idInput.text.toString()
        val number = keyRemover.toIntOrNull()

        if (number != null) {
            val res = hash!!.remove(number)
            resultContent.text = res.toString()
        } else {
            resultContent.text = "Not found"
        }
    }

    private fun writeContentToFile(uri: android.net.Uri) {
        contentResolver.openOutputStream(uri)?.use { outputStream: OutputStream ->
            val text = hash!!.toString()
            outputStream.write(text.toByteArray())
        }
    }

    private val fileOpenLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    readContentFromFile(uri)
                }
            }
        }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/plain"
        fileOpenLauncher.launch(intent)
    }

    private fun readContentFromFile(uri: android.net.Uri) {
        contentResolver.openInputStream(uri)?.use { inputStream: InputStream ->
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
            resultContent.text = stringBuilder.toString()
        }
    }

    private val fileSaveLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    writeContentToFile(uri)
                }
            }
        }

    private fun onSerializeButtonClicked() {

        if (selectedType === SelectedType.STRING) {

            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TITLE, "hashMap.txt")
            fileSaveLauncher.launch(intent)
        }

        if (selectedType === SelectedType.VECTOR) {
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TITLE, "hashMap.txt")
            fileSaveLauncher.launch(intent)
        }
    }

    private fun onDeserializeButtonClicked() {
        openFilePicker()
    }

    private fun onResizeButtonClicked() {
        initialiseTimeContent()
        hash = hash.resizeHash(timeContent)
    }
}