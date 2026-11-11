package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Hedhi el Activite 1 elli fiha el formulaire mta3 tasjil el mousafer
public class MainActivity extends AppCompatActivity {

    // 4 ar9am lounenin mta3 CIN
    public static final String MATRICULE = "1502";

    // Les composants mta3 el interface
    private EditText etNomFk;
    private EditText etAnneeNaissanceFk;
    private EditText etAnneeDestinationFk;
    private RadioGroup rgMotifFk;
    private Button btnInspecterFk;

    // Methode statique tasna3 el cle mta3 putExtra b el matricule
    public static String construireCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Narbtou les elements XML b el code Java b findViewById
        etNomFk = findViewById(R.id.et_nom_fk);
        etAnneeNaissanceFk = findViewById(R.id.et_annee_naissance_fk);
        etAnneeDestinationFk = findViewById(R.id.et_annee_destination_fk);
        rgMotifFk = findViewById(R.id.rg_motif_fk);
        btnInspecterFk = findViewById(R.id.btn_inspecter_fk);

        // Ki nenzlou 3al bouton Inspecter le profil
        btnInspecterFk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Na5dhou el texte elli katbou el utilisateur
                String nom = etNomFk.getText().toString().trim();
                String naissanceStr = etAnneeNaissanceFk.getText().toString().trim();
                String destinationStr = etAnneeDestinationFk.getText().toString().trim();

                // Nthabtou elli el champs mahomch ferghin
                if (nom.isEmpty() || naissanceStr.isEmpty() || destinationStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // N7awlou el a3wem men String l int
                int anneeNaissance = Integer.parseInt(naissanceStr);
                int anneeDestination = Integer.parseInt(destinationStr);

                // Nekhdhou el motif elli khtarou el mousefer mel RadioGroup
                int selectedId = rgMotifFk.getCheckedRadioButtonId();
                RadioButton rbChoisi = findViewById(selectedId);
                String motif = rbChoisi.getText().toString();

                // Nasn3ou Intent explicite bch nemchiw lel VerificationActivity (Activite 2)
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                // N7otou les donnees fel Intent bel methode statique construireCle
                intent.putExtra(construireCle(MATRICULE, "NOM_VOYAGEUR"), nom);
                intent.putExtra(construireCle(MATRICULE, "ANNEE_NAISSANCE"), anneeNaissance);
                intent.putExtra(construireCle(MATRICULE, "ANNEE_DESTINATION"), anneeDestination);
                intent.putExtra(construireCle(MATRICULE, "MOTIF_VOYAGE"), motif);

                // N7ellou el Activite 2
                startActivity(intent);
            }
        });
    }
}
