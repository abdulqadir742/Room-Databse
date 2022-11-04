package com.example.crudoperation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Database
import com.example.crudoperation.databinding.FragmentShowProductBinding
import com.example.crudoperation.databinding.FragmentUpdateBinding
import com.example.crudoperation.databinding.FragmentUpdateBindingImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {
    lateinit var DB: ProductDatabase
    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var binding: FragmentUpdateBinding


    private var productId: Long = 0
    private var productName: String = ""
    private var productPrice: Int = 0
    private var productStock: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        context?.apply {
            DB = ProductDatabase.getDatabase(this)
        }

        productId = args.productID
        productName = args.pname
        productPrice = args.pprice
        productStock = args.pstock


        binding.UpdateProName.setText(productName)
        binding.UpdateproPrice.setText(productPrice.toString())
        binding.UpdateproStock.setText(productStock.toString())

        binding.btnUpdate.setOnClickListener {
            var ProductName = binding.UpdateProName.text.toString()
            var ProductPrice = binding.UpdateproPrice.text.toString()
            var ProductStock = binding.UpdateproStock.text.toString()


            if (ProductName.isEmpty() || ProductPrice.isEmpty() || ProductStock.isEmpty())
            {
                Toast.makeText(context,"All fields are required", Toast.LENGTH_SHORT).show()
            }
            else{
                GlobalScope.launch {
                    DB.productDao().getUpdateProduct(
                        productId,
                        ProductName,
                        ProductPrice.toInt(),
                        ProductStock.toInt()
                    )
                }
                    findNavController().navigate(R.id.action_updateFragment_to_showProductFragment)

            }
        }

        binding.btnCancel.setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_showProductFragment)
        }

        return binding.root
    }


}