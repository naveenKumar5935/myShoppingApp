package naveen.kumar.n01355935;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    ImageView tShirts, sports, dress, sweater, glasses, purses, hats, shoes, headphones, laptops, watches, mobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tShirts = findViewById(R.id.imageShirt);
        sports = findViewById(R.id.imageSports);
        dress = findViewById(R.id.imageDress);
        sweater = findViewById(R.id.imageSweater);
        glasses = findViewById(R.id.imageGlasses);
        purses = findViewById(R.id.imagePurse);
        hats = findViewById(R.id.imageHats);
        shoes = findViewById(R.id.imageShoes);
        headphones = findViewById(R.id.imageHeadphones);
        laptops = findViewById(R.id.imageLaptop);
        watches = findViewById(R.id.imageWatches);
        mobiles = findViewById(R.id.imageMobiles);

        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        purses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });

        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminActivity.class);
                intent.putExtra("category","tShirts");

            }
        });







    }
}