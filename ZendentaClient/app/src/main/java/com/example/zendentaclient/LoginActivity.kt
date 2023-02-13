package com.example.zendentaclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.data.source.session.UserPreferences
import com.example.zendentaclient.databinding.ActivityLoginBinding
import com.example.zendentaclient.model.User
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private  var binding: ActivityLoginBinding? = null
    private lateinit var userPreferences : UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferences = UserPreferences(applicationContext)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        var user = userPreferences.getUserSession()
        if (!(user?.username.isNullOrEmpty())){
            authUser(user?.email!!, user?.password!!)
            //moveToMainActivity()
        }

        observeMessageResponse()
        setUpButtonLogin()
    }

    fun setUpButtonLogin(){
        setEmailValidation()
        binding?.btnLogin?.setOnClickListener {
            if(formIsValid()){
                authUser(
                    binding?.edtEmail?.text.toString(),
                    binding?.edtPassword?.text.toString(),
                )
            }
        }
    }

    fun authUser(email: String, password : String){
        //login user with
        Repository.doLoginUser(
            email, password
        )
        Repository.loggedUser.observe(this){
            if (it != null){
                userPreferences.saveSesions(it)
                moveToMainActivity()
            }
        }
    }
    

    private fun observeMessageResponse(){
        Repository.message.observe(this){
            Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun moveToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        //intent.putExtra("userLoginData", data)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
    fun formIsValid(): Boolean{
        when{
            binding?.edtEmail?.text.isNullOrEmpty()->{
                Toast.makeText(this, "Username is Empty", Toast.LENGTH_SHORT).show()
                binding?.edtEmail?.error = "Username is Empty"
                return false
            }

            binding?.edtEmail?.text!!.isNotEmpty()->{
                if(Patterns.EMAIL_ADDRESS.matcher(binding?.edtEmail?.text).matches()){
                    return true

                }else{
                    binding?.edtEmail?.error = "Email Is Not Valid"
                    return false
                }
            }

            binding?.edtPassword?.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show()
                binding?.edtPassword?.error = "Password is Empty"
                return false
            }

            else -> {
                return true
            }
        }
    }
    
    fun setEmailValidation(){
        binding?.edtEmail?.addTextChangedListener { 
            text -> Patterns.EMAIL_ADDRESS
        }
    }
}