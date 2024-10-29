package com.example.myandroiddevhw04

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.myandroiddevhw04.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressBar = binding.progressIndicator
        val randomNumber = Random.nextInt(101)
        progressBar.progress = randomNumber
        binding.actualPointsTextView.setText("$randomNumber/100")

        binding.userNameText.doOnTextChanged { _, _, _, _ -> checkAndEnableSaveButton(binding) }

        binding.userPhoneText.doOnTextChanged { _, _, _, _ -> checkAndEnableSaveButton(binding) }
        binding.maleRadioButton.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        binding.femaleRadioButton.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            checkAndEnableSaveButton(binding)
            if (isChecked) {
                binding.deviceAuthorizationNotificationsCheckBox.isEnabled = true
                binding.notificationNewProductsCheckBox.isEnabled = true
            } else {
                binding.deviceAuthorizationNotificationsCheckBox.isEnabled = false
                binding.notificationNewProductsCheckBox.isEnabled = false
            }
        }
        binding.deviceAuthorizationNotificationsCheckBox.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        binding.notificationNewProductsCheckBox.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }

        binding.saveButton.setOnClickListener {
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndEnableSaveButton(binding: ActivityMainBinding) {
        val isNameValid =
            !binding.userNameText.text.isNullOrEmpty() && binding.userNameText.text!!.length <= 40
        val isPhoneValid = !binding.userPhoneText.text.isNullOrEmpty()
        val isGenderSelected =
            binding.maleRadioButton.isChecked || binding.femaleRadioButton.isChecked
        val isNotificationValid = binding.notificationsSwitch.isChecked &&
                (binding.deviceAuthorizationNotificationsCheckBox.isChecked ||
                        binding.notificationNewProductsCheckBox.isChecked)

        binding.saveButton.isEnabled =
            isNameValid && isPhoneValid && isGenderSelected && isNotificationValid
    }
}