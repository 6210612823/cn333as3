package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme
import kotlin.random.Random.Default.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    var random: Int = nextInt(1, 1000)
                    GuessNumber(random)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "NumberGuessingGame")
}

@Composable
fun GuessNumber(random: Int) {
    var count = remember { mutableStateOf(0) }
    var randomNumber = remember { mutableStateOf(random)}
    var output = remember { mutableStateOf("")}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text (
            text = "Guess the number (1-1000)",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                )

        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Insert the number (1-1000)") }
        )

        Text (
            text = ""
                )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    text = ""
                    var random = nextInt(1, 1000)
                    randomNumber.value = random
                    output.value = ""
                    count.value = 0
                }
            ) {
                Text(text = "Reset", fontSize = 30.sp)
            }

            Button(
                onClick = {
                    var input = 0
                    var isdatText = 0
                    count.value = count.value + 1

                    try {
                        input = text.toInt()
                    } catch (text: NumberFormatException) {
                        output.value = "Please enter only number"
                        isdatText = 1
                    }

                    val checkAnswer = if (input > randomNumber.value) { output.value = "The number is lower than $input" }
                    else if (input < randomNumber.value) { output.value = "The number is bigger than $input" }
                    else { output.value = "Congratulations you win !" }

                    if (isdatText == 0) {
                        checkAnswer
                    }

                }
            ) {
                Text(text = "Guess", fontSize = 30.sp)
            }
        }
        Text (
            text = "${output.value}",
            fontSize = 18.sp
        )

        Text (
            text = "You have guessed ${count.value} times",
            fontSize = 18.sp
        )
    /*
        Text (
            text = "{$randomNumber.value}",
            fontSize = 18.sp
        )
    */
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberGuessingGameTheme {
        Greeting("Android")
    }
}