package com.example.coroutines1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.coroutines1.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {
    private lateinit var binding : FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener{
            Toast.makeText(context,"Calculating..",Toast.LENGTH_SHORT).show()
            viewLifecycleOwner.lifecycleScope.launch{
                val number = provideFirstValue()
                addOne(number)
            }
            binding.editableText.text.clear()
        }
    }

    suspend fun provideFirstValue():Int?{
        val number = binding.editableText.text.toString().toIntOrNull()
        delay(2000)
        return number
    }
    fun addOne(number:Int?){
        if (number != null){
            binding.numberShower.text = "${number.plus(1)}"
        } else {
            val placeholder = binding.numberShower.text.toString().toIntOrNull()
            binding.numberShower.text = "${placeholder?.plus(1)}"
        }
    }
}