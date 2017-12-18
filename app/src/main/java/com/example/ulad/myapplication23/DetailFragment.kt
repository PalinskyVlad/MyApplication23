package com.example.ulad.myapplication23

import android.app.Activity
import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.ulad.myapplication23.model.Product
import android.database.sqlite.SQLiteDatabase
import com.example.ulad.myapplication23.model.DB
import android.content.ContentValues


class DetailFragment : Fragment(), View.OnClickListener {
    interface DataPass {
        fun onDataPass()
    }
    private var dataPass: DataPass? = null
    private var product: Product? = null
    private var id: Long = 0
    var editText1: EditText? = null
    var editText2: EditText? = null
    var editText3: EditText? = null
    var editText4: EditText? = null
    var editText5: EditText? = null
    var editText6: EditText? = null
    var button1: Button? = null
    var button2: Button? = null
    var builder: AlertDialog.Builder? = null
    var alertDialog: AlertDialog? = null
    var choose = 0

    private var db: DB? = null

    private var sqLiteDatabase: SQLiteDatabase? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.detail_fragment, container, false)
        val bundle = arguments
        if (bundle != null)

            product = bundle.getParcelable("product")

        db = DB(context)

        sqLiteDatabase = db!!.readableDatabase

        val buttonFragment = ButtonFragment()

        childFragmentManager.beginTransaction().add(R.id.button_container, buttonFragment).commit()

        builder = AlertDialog.Builder(activity)

        builder!!.setPositiveButton("OK", { dialog, which ->
            if (choose == 0) {

                if (product != null) {

                    val contentValues = ContentValues()

                    contentValues.put("name", editText1!!.text.toString())

                    contentValues.put("UPC", editText2!!.text.toString())

                    contentValues.put("producer", editText3!!.text.toString())

                    contentValues.put("price", Integer.parseInt(editText4!!.text.toString()))

                    contentValues.put("shelfLife", Integer.parseInt(editText5!!.text.toString()))
                    contentValues.put("quantity", Integer.parseInt(editText6!!.text.toString()))

                    sqLiteDatabase!!.update("PRODUCT2", contentValues, "_id = ?", arrayOf((product!!.id.toString())))
                } else {

                    val contentValues = ContentValues()

                    contentValues.put("name", editText1!!.text.toString())

                    contentValues.put("UPC", editText2!!.text.toString())

                    contentValues.put("producer", Integer.parseInt(editText3!!.text.toString()))

                    contentValues.put("price", Integer.parseInt(editText4!!.text.toString()))

                    contentValues.put("shelfLife", Integer.parseInt(editText5!!.text.toString()))

                    contentValues.put("quantity", Integer.parseInt(editText6!!.text.toString()))

                    sqLiteDatabase!!.insert("PRODUCT", null, contentValues)

                }

                dataPass!!.onDataPass()

            } else {

                sqLiteDatabase!!.delete("PRODUCT2", "_id = ?", arrayOf((product!!.id.toString())))

                        dataPass!!.onDataPass()

            }
        })

        builder!!.setNegativeButton("NO", { dialog, _ -> dialog.cancel() })

        builder!!.setCancelable(false)

        return view
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val bundle = arguments
            button1 = activity.findViewById<View>(R.id.button_Create) as Button
            button2 = activity.findViewById<View>(R.id.button_Delete) as Button
            button1!!.setOnClickListener(this)
            button2!!.setOnClickListener(this)
            if (bundle != null) {
                product = bundle.getParcelable("product")
                editText1 = (view.findViewById<View>(R.id.editText1) as EditText)
                editText2 = (view.findViewById<View>(R.id.editText2) as EditText)
                editText3 = (view.findViewById<View>(R.id.editText3) as EditText)
                editText4 = (view.findViewById<View>(R.id.editText4) as EditText)
                editText5 = (view.findViewById<View>(R.id.editText5) as EditText)
                editText6 = (view.findViewById<View>(R.id.editText6) as EditText)
                if (product != null) {
                    editText1!!.setText(product!!.name)
                    editText2!!.setText(product!!.UPC)
                    editText3!!.setText(product!!.producer)
                    editText4!!.setText(product!!.price.toString())
                    editText5!!.setText(product!!.shelfLife.toString())
                    editText6!!.setText(product!!.quantity.toString())
                } else {
                    id = bundle.getLong("id")
                    button1!!.text = "Add"
                    button2!!.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_Create -> {
                choose = 0
                builder?.setMessage("Change?")
                alertDialog = builder?.create()
                alertDialog?.show()
            }
            R.id.button_Delete -> {
                choose = 1
                builder?.setMessage("Delete?")
                alertDialog = builder?.create()
                alertDialog?.show()
            }
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        this.dataPass = getActivity() as DataPass
    }
}