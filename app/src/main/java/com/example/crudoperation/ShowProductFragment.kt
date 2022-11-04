package com.example.crudoperation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudoperation.databinding.FragmentShowProductBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ShowProductFragment : Fragment() {

    lateinit var DB: ProductDatabase
    private lateinit var binding: FragmentShowProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_show_product, container, false)
        binding = FragmentShowProductBinding.inflate(inflater,container,false)

        context?.apply {
            DB = ProductDatabase.getDatabase(this)
        }


        fun delete_data(P_id:Long) {
            GlobalScope.launch {
            DB.productDao().delete(P_id)
            }
        }


        val prdList = binding.recycleView
        fun updateData1(pid: Long, pName: String, pprice: Int, pstock: Int) {
                var directions = ShowProductFragmentDirections.actionShowProductFragmentToUpdateFragment(pid,pName,pprice,pstock)
                findNavController().navigate(directions)
        }
        DB.productDao().getData().observe(viewLifecycleOwner, Observer {
            fun itemClicked(pid:Long,PName:String,Pprice:Int,Pstock:Int)
            {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Do you want to delete or update data")
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                builder.setPositiveButton("DELETE DATA") { dialogInterface, which -> delete_data(pid)
                    Toast.makeText(context, "clicked On Delete", Toast.LENGTH_LONG).show()
                }

                builder.setNeutralButton("Cancel") { dialogInterface, which ->
                    Toast.makeText(context, "clicked cancel\n operation cancel", Toast.LENGTH_LONG)
                        .show()
                }

                builder.setNegativeButton("UPDATE DATA") { dialogInterface, which -> updateData1(pid,PName,Pprice,Pstock)
                    Toast.makeText(context, "click On Update", Toast.LENGTH_LONG).show()
                }

                val alertDialog: AlertDialog = builder.create()

                alertDialog.setCancelable(false)
                alertDialog.show()

            }
            prdList.adapter = Productadapter(it,::itemClicked)
            prdList.layoutManager = LinearLayoutManager(context)


        })
       binding.floatingButton.setOnClickListener {
           findNavController().navigate(R.id.action_showProductFragment_to_addProductFragment)

       }


        return binding.root
    }

}



