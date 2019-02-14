package com.nirmaan_bits.nirmaan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class utkarsh extends AppCompatActivity implements View.OnClickListener {
    private CardView utk1,utk2,utk3,utk4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utkarsh_);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

switch (ProjectsFragment.project){

    case 1:
        collapsingToolbarLayout.setTitle("GB Baas");
    break;
    case 2:
        collapsingToolbarLayout.setTitle("GB CB");
        break;
    case 3:
        collapsingToolbarLayout.setTitle("SAP");
        break;
    case 4:
        collapsingToolbarLayout.setTitle("PCD");
        break;
    case 5:
        collapsingToolbarLayout.setTitle("SKO");
        break;
    case 6:
        collapsingToolbarLayout.setTitle("Utkarsh");
        break;
    case 7:
        collapsingToolbarLayout.setTitle("Disha");
        break;
    case 8:
        collapsingToolbarLayout.setTitle("Unnati 1");
        break;
    case 9:
        collapsingToolbarLayout.setTitle("Unnati 2");
        break;
        default:break;




}
        utk1 = findViewById(R.id.utkarsh1);
        utk2 = findViewById(R.id.utkarsh2);
        utk3 = findViewById(R.id.utkarsh3);
        utk4 = findViewById(R.id.utkarsh4);

        utk1.setOnClickListener(this);

        utk2.setOnClickListener(this);

        utk3.setOnClickListener(this);

        utk4.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            Intent i;
            switch (v.getId())  {
                case R.id.utkarsh1 :
                    i=new Intent(this,utkarsh_aim.class);
                    startActivity(i);
                    break;
                case R.id.utkarsh2 :
                    i=new Intent(this,utkarsh_semPlan.class);
                    startActivity(i);
                    break;
                case R.id.utkarsh3 :
                    i=new Intent(this,utkarsh_members.class);
                    startActivity(i);
                    break;

                case R.id.utkarsh4:
                    i=new Intent(this,progress.class);
                    startActivity(i);
                    break;
default:break;
            }

        }



}
