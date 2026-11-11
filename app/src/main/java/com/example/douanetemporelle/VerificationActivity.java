package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Hadhi el Activite 2 : Le Scanner de Paradoxe (VerificationActivity)
public class VerificationActivity extends AppCompatActivity {

    // Les composants mta3 el interface
    private TextView tvNomVoyageurFk;
    private TextView tvAgeTemporelFk;
    private TextView tvMotifVoyageFk;
    private Button btnApprouverFk;
    private Button btnRefuserFk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // Nrabtou les elements XML b el code Java
        tvNomVoyageurFk = findViewById(R.id.tv_nom_voyageur_fk);
        tvAgeTemporelFk = findViewById(R.id.tv_age_temporel_fk);
        tvMotifVoyageFk = findViewById(R.id.tv_motif_voyage_fk);
        btnApprouverFk = findViewById(R.id.btn_approuver_fk);
        btnRefuserFk = findViewById(R.id.btn_refuser_fk);

        // Na5dhou el Intent elli ja men MainActivity
        Intent intentRecu = getIntent();

        // Njibou les donnees b isti3mel el methode statique construireCle
        String nom = intentRecu.getStringExtra(MainActivity.construireCle(MainActivity.MATRICULE, "NOM_VOYAGEUR"));
        int anneeNaissance = intentRecu.getIntExtra(MainActivity.construireCle(MainActivity.MATRICULE, "ANNEE_NAISSANCE"), 0);
        int anneeDestination = intentRecu.getIntExtra(MainActivity.construireCle(MainActivity.MATRICULE, "ANNEE_DESTINATION"), 0);
        String motif = intentRecu.getStringExtra(MainActivity.construireCle(MainActivity.MATRICULE, "MOTIF_VOYAGE"));

        // Na7sbou el far9 bin 3am el destination w 3am el naissance (Age temporel)
        int differenceAnnees = Math.abs(anneeDestination - anneeNaissance);

        // N3ardhou el resume complet f el passeport temporel
        tvNomVoyageurFk.setText("Nom : " + nom);
        tvAgeTemporelFk.setText("Âge temporel : " + differenceAnnees + " ans");
        tvMotifVoyageFk.setText("Motif : " + motif);

        // Contrainte : Ken el motif "Modification du passé" w el far9 nombre premier, n7ebsou el bouton Approuver
        if ("Modification du passé".equals(motif) && estNombrePremier(differenceAnnees)) {
            btnApprouverFk.setEnabled(false);
        }

        // Action mta3 el bouton Approuver (yab3ath true)
        btnApprouverFk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(nom, true);
            }
        });

        // Action mta3 el bouton Refuser (yab3ath false)
        btnRefuserFk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(nom, false);
            }
        });
    }

    // Methode bech nab3thou el verdict (booleen) w esm el mosafar lel Activite 3 (ResultActivity)
    private void envoyerVerdict(String nomVoyageur, boolean estApprouve) {
        Intent intentVerdict = new Intent(VerificationActivity.this, ResultActivity.class);
        intentVerdict.putExtra(MainActivity.construireCle(MainActivity.MATRICULE, "NOM_VOYAGEUR"), nomVoyageur);
        intentVerdict.putExtra(MainActivity.construireCle(MainActivity.MATRICULE, "VERDICT"), estApprouve);
        startActivity(intentVerdict);
    }

    // Methode tthabet ken el 3adad awali (nombre premier) walla le
    private boolean estNombrePremier(int n) {
        // El a3ded elli asghar walla ysewiw 1 mahomch premiers
        if (n <= 1) {
            return false;
        }
        // Njarbou el 9isma men 2 7atta l n - 1
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
