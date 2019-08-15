package com.henrypra.owey.feature.creation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.utility.DialogUtil
import kotlinx.android.synthetic.main.fragment_creation.*

class CreationFragment : BaseContractFragment<CreationContract.Presenter>(), CreationContract.View, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private val PICK_CONTACT = 1
    private val currencyList = arrayOf("€", "£", "$")
    private var selectedCurrency: String? = currencyList[0]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
        initSpinner()
    }

    private fun initOnClickListeners() {
        btn_send?.setOnClickListener(this)
        btn_add_contact?.setOnClickListener(this)
        btn_cancel?.setOnClickListener(this)
    }

    private fun initSpinner() {
        spinner_currency?.onItemSelectedListener = this
        val currencyAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, currencyList)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_currency?.adapter = currencyAdapter
    }

    private fun isValidInput(input: EditText?): Boolean {
        return if (TextUtils.isEmpty(input?.text.toString())) {
            input?.error = activity?.getString(R.string.error_code_empty_field)
            false
        } else {
            true
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedCurrency = currencyList[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                if (isValidInput(edt_title) && isValidInput(edt_amount) && isValidInput(edt_friend)) {
                    try {
                        val amount: Double? = edt_amount?.text.toString().toDouble()
                        val title: String = edt_title?.text.toString()
                        val friend: String = edt_friend?.text.toString()
                        val note: String = edt_note?.text.toString()
                        val isDebt: Boolean? = switch_debt?.checkedTogglePosition == 1

                        presenter?.createDebt(amount, selectedCurrency, title, friend, note, isDebt)
                    } catch (e: Exception) {
                        DialogUtil.buildCreationErrorDialog(context)
                        e.stackTrace
                    }
                }

            }
            R.id.btn_add_contact -> {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(intent, PICK_CONTACT)
            }
            R.id.btn_cancel -> activity?.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_CONTACT && resultCode == RESULT_OK) {
            val contactUri = data?.data
            val cursor = activity?.contentResolver?.query(contactUri, null, null, null, null)
            cursor?.moveToFirst()
            val column: Int? = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

            edt_friend?.setText(column?.let { cursor.getString(it) })
        }
    }
}