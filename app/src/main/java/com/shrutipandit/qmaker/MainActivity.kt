package com.shrutipandit.qmaker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shrutipandit.qmaker.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "Hello world", Toast.LENGTH_SHORT).show()

        database = FirebaseDatabase.getInstance().reference

        binding.okbtn.setOnClickListener {
            // Call the function to set 10 random data entries in Firebase
            uploadMultipleRandomData(10)
        }
    }

    private fun getRandomStudentName(): String {
        val studentNames = listOf(
            "Aarav", "Aditi", "Arjun", "Ananya", "Rohan", "Riya",
            "Aman", "Sneha", "Karan", "Priya", "Rahul", "Ishita",
            "Vikram", "Meera", "Rajesh", "Neha", "Sanjay", "Pooja"
        )
        return studentNames[Random.nextInt(studentNames.size)]
    }

    private fun getRandomFatherName(): String {
        val fatherNames = listOf(
            "Vinay Kumar Pandit", "Arjun Pandit", "Rohan Pandit", "Aman Pandit",
            "Rajesh Pandit", "Sanjay Pandit", "Rahul Pandit", "Vikram Pandit",
            "Karan Pandit", "Anil Pandit"
        )
        return fatherNames[Random.nextInt(fatherNames.size)]
    }

    private fun getRandomRollNumber(): String {
        val rollNumbers = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        return rollNumbers[Random.nextInt(rollNumbers.size)]
    }

    private fun uploadMultipleRandomData(count: Int) {
        var dataSavedCount = 0

        binding.okbtn.text = "0 / $count saved"  // Initialize button text to show progress

        repeat(count) {
            val studentName = getRandomStudentName()
            val fatherName = getRandomFatherName()
            val rollNumber = getRandomRollNumber()

            // Define data structure to store in Firebase
            val studentData = mapOf(
                "studentName" to studentName,
                "fatherName" to fatherName,
                "rollNumber" to rollNumber
            )

            // Save data to Firebase under a unique ID
            database.child("students").push().setValue(studentData)
                .addOnSuccessListener {
                    // Increment and update button text to indicate progress
                    dataSavedCount++
                    binding.okbtn.text = "$dataSavedCount / $count saved"

                    // Optionally, reset the button text after completion
                    if (dataSavedCount == count) {
                        binding.okbtn.text = "All data saved"
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save data!", Toast.LENGTH_SHORT).show()
                }
        }
    }


}
