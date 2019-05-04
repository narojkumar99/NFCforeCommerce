package com.oganbelema.nfcforecommerce.admin.addcustomer

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

import com.oganbelema.nfcforecommerce.R
import com.oganbelema.nfcforecommerce.base.BaseNfcListenerFragment
import com.oganbelema.nfcforecommerce.util.Util
import kotlinx.android.synthetic.main.add_customer_fragment.*

class AddCustomerFragment : BaseNfcListenerFragment() {

    /**
     * {@inheritDoc}
     */
    override fun onNfcTagDiscovered(intent: Intent?) {
        showTagDetectedViewState()
        processReceivedIntent(intent)
    }

    /**
     * Updates the view to show the tag detected state
     * It hides the scan tag animation and shows the add customer view elements
     */
    private fun showTagDetectedViewState() {
        if (scanNfcTagHintViews != null) {
            scanNfcTagHintViews.visibility = View.GONE
            addCustomerViews.visibility = View.VISIBLE
        }
    }

    /**
     * Extracts the discovered tag from the received intent to obtain it's id and populate the
     * tagIdEditTextView with the tag id and notify the user that a tag was discovered with a snackBar
     * @param intent this the intent received from the BaseActivity containing the discovered tag
     */
    private fun processReceivedIntent(intent: Intent?) {
       if (intent != null){
           val nfcTag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
           val tagId = Util.byteArrayToHexString(nfcTag?.id)
           tagIdEditText.setText(tagId)
           Snackbar.make(this.view!!, "Nfc tag discovered with tag id: $tagId", Snackbar.LENGTH_LONG).show()
       }
    }

    private lateinit var viewModel: AddCustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_customer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddCustomerViewModel::class.java)
        // TODO: Use the ViewModel
    }


}
