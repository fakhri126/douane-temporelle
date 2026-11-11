package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Hadhi el Activite 3 : Le Verdict (ResultActivity)
public class ResultActivity extends AppCompatActivity {

    // Les composants mta3 el interface
    private LinearLayout layoutResultFk;
    private TextView tvStatutVerdictFk;
    private TextView tvVerdictMessageFk;
    private Button btnNouveauVoyageurFk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Nrabtou les elements XML b el code Java
        layoutResultFk = findViewById(R.id.layout_result_fk);
        tvStatutVerdictFk = findViewById(R.id.tv_statut_verdict_fk);
        tvVerdictMessageFk = findViewById(R.id.tv_verdict_message_fk);
        btnNouveauVoyageurFk = findViewById(R.id.btn_nouveau_voyageur_fk);

        // Na5dhou el Intent elli ja men VerificationActivity
        Intent intentRecu = getIntent();

        // Njibou esm el mosafar w el verdict (booleen) b el methode statique construireCle
        String nom = intentRecu.getStringExtra(MainActivity.construireCle(MainActivity.MATRICULE, "NOM_VOYAGEUR"));
        boolean verdict = intentRecu.getBooleanExtra(MainActivity.construireCle(MainActivity.MATRICULE, "VERDICT"), false);

        // Nbaddlou loun el fond w el message 7asb el verdict
        if (verdict) {
            // Ken approuve (true) : fond akhdhar (Vert) w message Bon voyage
            layoutResultFk.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvStatutVerdictFk.setText("SAUT TEMPOREL AUTORISÉ");
            tvStatutVerdictFk.setTextColor(Color.parseColor("#2E7D32"));
            tvVerdictMessageFk.setText("Bon voyage " + nom);
        } else {
            // Ken refuse (false) : fond a7mar (Rouge) w message arrestation temporelle
            layoutResultFk.setBackgroundColor(Color.parseColor("#C62828"));
            tvStatutVerdictFk.setText("ALERTE PARADOXE : REFUSÉ");
            tvStatutVerdictFk.setTextColor(Color.parseColor("#C62828"));
            tvVerdictMessageFk.setText(nom + " est en état d'arrestation temporelle");
        }

        // Ki nenzlou 3al bouton Nouveau voyageur, narj3ou lel MainActivity w nsakrou les activites el 9dom
        btnNouveauVoyageurFk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRetour = new Intent(ResultActivity.this, MainActivity.class);
                // Les flags hedhom ysakrou les activites elli 9balha el kol
                intentRetour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentRetour);
                finish();
            }
        });
    }
}
