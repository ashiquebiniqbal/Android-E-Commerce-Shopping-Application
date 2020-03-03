package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application: Sabin Constantin Lungu.
// Purpose of Application / Class: Contains the Java code for the DIY activity that corresponds to the DIY XML code.
// Date of Last Modification: 03/02/2020
// Any errors? None

public class DIYActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int current_product_id = 1;
    private TextView diyFirstProductTxt;

    private ImageView diyFirstProductImg;
    private TextView diyFirstProductCost;

    private TextView diyFirstProductColourLbl;
    private Spinner diyFirstProductColourMenu;

    private TextView diyFirstProductSizeLbl;
    private Spinner diyFirstProductSizeMenu;

    private TextView diyFirstProductQuantityLbl;
    private Spinner diyFirstProductQuantityMenu;
    private Button diyFirstProductToAddToBasketBtn;

    private TextView diySecondProductTxt;
    private ImageView diySecondProductImg;

    private TextView diySecondProductCost;

    private TextView diySecondProductColourLbl;
    private Spinner diySecondProductColourMenu;

    private TextView diySecondProductSizeLbl;
    private Spinner diySecondProductSizeMenu;

    private TextView diySecondProductQuantityLbl;
    private Spinner diySecondProductQuantityMenu;

    private Button diySecondProductAddToBasketBtn;

    private double[] diyFirstProductCosts = new double[]{0.00, 40.00, 80.00, 160.00, 320.00, 640.00};
    private double[] diySecondProductCosts = new double[]{0.00, 20.00, 40.00, 80.00, 160.00, 320.00};

    private boolean coloursAdded = false;
    private boolean sizesAdded = false;
    private boolean quantitiesAdded = false;

    private CustomArrayAdapter quantitiesAdapter;
    private SizeArrayAdapter sizeArrayAdapter;
    private ColourArrayAdapter coloursAdapter;

    private ArrayList<TechActivity.Colours> diyListOfColoursOne = null;
    private ArrayList<Size> diyListOfSizesOne = null;
    private ArrayList<TechActivity.Quantities> diyListOfQuantitiesOne = null; // An Array list of quantities for the first diy product

    // Creates the array lists for the second DIY product.
    private ArrayList<TechActivity.Colours> diyListOfColoursTwo = null;
    private ArrayList<Size> diyListOfSizesTwo = null;
    private ArrayList<TechActivity.Quantities> diyListOfQuantitiesTwo = null;

    private ImageView cartIcon;
    private HashMap<Integer, Products> listOfProductsToAddToBasket = new HashMap<Integer, Products>(); // Creates a new hash map of products with an associated ID
    private Button nextPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy);

        // Initialise components

        this.diyFirstProductTxt = findViewById(R.id.diyFirstProductTxt);
        this.diyFirstProductImg = findViewById(R.id.diyFirstProductImg);

        this.diyFirstProductCost = findViewById(R.id.diyFirstProductCostTxt);
        this.diyFirstProductColourLbl = findViewById(R.id.diyFirstProductColourLbl);
        this.diyFirstProductColourMenu = findViewById(R.id.diyFirstProductColourMenu);

        this.diyFirstProductSizeLbl = findViewById(R.id.diyFirstProductSizeLbl);
        this.diyFirstProductSizeMenu = findViewById(R.id.diyFirstProductSizeMenu);

        this.diyFirstProductQuantityLbl = findViewById(R.id.diyFirstProductQuantityLbl);
        this.diyFirstProductQuantityMenu = findViewById(R.id.diyFirstProductQuantityMenu);
        this.diyFirstProductToAddToBasketBtn = findViewById(R.id.diyFirstProductAddToBasketBtn);

        this.diySecondProductTxt = findViewById(R.id.diySecondProductTxt);
        this.diySecondProductImg = findViewById(R.id.diySecondProductImg);

        this.diySecondProductCost = findViewById(R.id.diySecondProductCostLbl);
        this.diySecondProductColourLbl = findViewById(R.id.diySecondProductColourLbl);
        this.diySecondProductColourMenu = findViewById(R.id.diySecondProductColourMenu);

        this.diySecondProductSizeLbl = findViewById(R.id.diySecondProductSizeLbl);
        this.diySecondProductSizeMenu = findViewById(R.id.diySecondProductSizeMenu);

        this.diySecondProductAddToBasketBtn = findViewById(R.id.secondAddToBasketBtn);

        addToDIYColourList();
        addToDIYSizesList();

        addToDIYQuantitiesListOne();
        addToDIYQuantitiesListTwo();

        // Set-up adapters.

        this.coloursAdapter = new ColourArrayAdapter(DIYActivity.this, diyListOfColoursOne);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        diyFirstProductColourMenu.setAdapter(coloursAdapter);
        diyFirstProductColourMenu.setOnItemSelectedListener(DIYActivity.this);

        this.nextPageBtn = findViewById(R.id.diyNextPageBtn);

        this.nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (v.getId() == R.id.diyNextPageBtn) {
                        Intent nextDiyIntent = new Intent(DIYActivity.this, DIYActivityTwo.class);
                        startActivity(nextDiyIntent);
                    }
                } catch (ActivityNotFoundException exc) {
                    Log.d(String.valueOf(R.string.error), exc.toString());
                }
            }
        });
    }

    private boolean addToDIYColourList() {
        return true;
    }

    private boolean addToDIYSizesList() {
        return true;
    }

    private boolean addToDIYQuantitiesListOne() {
        return true;
    }

    private boolean addToDIYQuantitiesListTwo() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Add the toolbar menu
        // Inflate the activities menu
        MenuInflater activityInflater = getMenuInflater(); // Get the activity inflater
        activityInflater.inflate(R.menu.homepagemenu, menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.basket_action_button, menu);

        View view = menu.findItem(R.id.cart_menu).getActionView();

        cartIcon = view.findViewById(R.id.cart_icon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent basketIntent = new Intent(DIYActivity.this, BasketActivity.class); // Create a basket intent
                basketIntent.putExtra("map", listOfProductsToAddToBasket); // Transit over the hash map data to the basket
                startActivity(basketIntent); // Start the intent
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            switch (item.getItemId()) {
                case R.id.sportsAndOutdoorsCategory:
                    Intent sportsCategory = new Intent(DIYActivity.this, SportsAndOutdoorsActivity.class);
                    startActivity(sportsCategory);

                    break;

                case R.id.techCategory:
                    Intent techActivity = new Intent(DIYActivity.this, TechActivity.class);
                    startActivity(techActivity);
                    break;

                case R.id.clothingCategory:
                    Intent clothingActivity = new Intent(DIYActivity.this, ClothingCategory.class);
                    startActivity(clothingActivity);
                    break;

                case R.id.diyCategory:
                    Intent diyActivity = new Intent(DIYActivity.this, DIYActivity.class);
                    startActivity(diyActivity);
                    break;

                default:
                    super.onOptionsItemSelected(item);

            }

        } catch (ActivityNotFoundException exc) {
            Log.d(String.valueOf(R.string.error), exc.toString());
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
