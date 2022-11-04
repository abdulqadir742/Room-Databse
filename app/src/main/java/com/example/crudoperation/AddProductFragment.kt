package com.example.crudoperation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.crudoperation.databinding.FragmentAddProductBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding

    lateinit var DB:ProductDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        binding = FragmentAddProductBinding.inflate(inflater,container,false)

//        DB = ProductDatabase.getDatabase(requireContext())

        context?.apply {
            DB = ProductDatabase.getDatabase(this)
        }

        binding.btnSave.setOnClickListener{
            var ProductName = binding.proName.text.toString()
            var ProductPrice = binding.proPrice.text.toString()
            var ProductStock = binding.proStock.text.toString()

            if (ProductName.isEmpty() || ProductPrice.isEmpty() || ProductStock.isEmpty())
            {
                Toast.makeText(context,"All fields are required",Toast.LENGTH_SHORT).show()
            }
            else{
                GlobalScope.launch {
                    DB.productDao().insert(Product(0,ProductName,ProductPrice.toInt(),ProductStock.toInt()))
                    binding.proName.setText("")
                    binding.proPrice.setText("")
                    binding.proStock.setText("")

                    findNavController().navigate(R.id.action_addProductFragment_to_showProductFragment)
                }
            }

        }

        binding.btnCancel.setOnClickListener{
            findNavController().navigate(R.id.action_addProductFragment_to_showProductFragment)
        }



        return binding.root
    }

}