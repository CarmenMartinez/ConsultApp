package com.wired.ctapp.consultall.projects;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wired.ctapp.consultall.R;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class ResponderActivity extends AppCompatActivity {

    public static final String PROJECT_NAME = "PROJECT_NAME";

    private TextView questionLabel;
    private EditText respuestaText;
    private Button nextPregunta;
    private Button prevPregunta;

    private Stack<Entry> prevEntry;
    private Stack<Entry> nextEntry;
    private String[] staticQuestions;
    private LinkedList<String> randomQuestions;

    private int questionNumber = 0;
    private int questionNumberLock = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder);

        Intent i = getIntent();
        String projectName = i.getStringExtra(ResponderActivity.PROJECT_NAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_responder);
        toolbar.setTitle(projectName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        respuestaText = (EditText) findViewById(R.id.text_pregunta_responder);
        questionLabel = (TextView) findViewById(R.id.lbl_pregunta_responder);
        nextPregunta = (Button) findViewById(R.id.btn_next_pregunta);
        prevPregunta = (Button) findViewById(R.id.btn_prev_pregunta);

        prevEntry = new Stack<>();
        nextEntry = new Stack<>();
        randomQuestions = new LinkedList<>();
        staticQuestions = new String[5];
        staticQuestions[0] = "¿Cuentas con alguna certificación?";
        staticQuestions[1] = "¿Cómo realizan sus análisis de riesgos?";
        staticQuestions[2] = "¿En su negocio o empresa se tiene una cultura de innovación bien definida?";
        staticQuestions[3] = "¿Qué diferencia a tu empresa de la competencia?";
        staticQuestions[4] = "¿Documentas tus procesos?";

        addQuestionsToList();
        next();

        nextPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store();
            }
        });
        prevPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    /**
     * Sets the next question
     */
    private void next() {
        int numOfQuestions = 25; // 25 Questions for testing. Total number of questions is 41. Not necesary to use all of them.
        if(questionNumberLock == questionNumber && questionNumber <= numOfQuestions) {
            questionLabel.setText("Pregunta" + (questionNumber + 1) + "\n");
            if (questionNumber < 5) {
                questionLabel.setText(staticQuestions[questionNumber]);
            } else {
                questionLabel.setText(randomQuestions.remove((int) (Math.random() * randomQuestions.size())));
            }
            respuestaText.setText("");
        }else{
            Entry entry = nextEntry.pop();
            prevEntry.push(entry);
            questionLabel.setText(entry.pregunta);
            respuestaText.setText(entry.respuesta);
        }
        questionNumber++;
    }

    /**
     * Store the question in the history
     */
    private void store(){
        if(Objects.equals(respuestaText.getText().toString(), "")){
            respuestaText.setError("Completa la respuesta");
            return;
        }

        Entry entry = new Entry();
        entry.pregunta = questionLabel.getText().toString();
        entry.respuesta = respuestaText.getText().toString();
        prevEntry.push(entry);
        questionNumberLock++;
        next();
    }

    private void pop(){
        Entry entry = prevEntry.pop();
        nextEntry.push(entry);
        questionLabel.setText(entry.pregunta);
        respuestaText.setText(entry.respuesta);
        questionNumberLock--;
    }

    public void addQuestionsToList() {
        randomQuestions.add("¿Tienes un proceso de verificación de calidad de tus productos?");
        randomQuestions.add("¿Tienes procedimiento de pruebas?");
        randomQuestions.add("¿Cuentan con estándares que deban cumplir todos los productos?");
        randomQuestions.add("¿Has continuado con la actualización de dicha certificación?");
        randomQuestions.add("¿Conoces o has aplicado alguna metodología ágil en tus procesos?");
        randomQuestions.add("¿Cuál metodología aplicas en tus procesos?");
        randomQuestions.add("¿Cuentas con personal capacitado en el área de pruebas?");
        randomQuestions.add("¿Cuentas con un plan de costos de calidad (prevención, evaluación y fallo)?");
        randomQuestions.add("¿Cómo manejan la comunicación? Esta pregunta no está relacionada con el chat o correo, "
                + "si no más hacia las juntas o estrategias que tengan, cómo las realizan, "
                + "cómo miden que se hagan satisfactoriamente y cómo miden que se haga efectivamente.");
        randomQuestions.add("¿Cómo aseguran el cumplimiento de los objetivos en tiempo y forma? "
                + "¿Cómo tienen organizado el calendario? ¿Las tareas se distribuyen adecuadamente en el tiempo? ¿Por qué?");
        randomQuestions.add("¿Cómo tienen organizado a su equipo de trabajo durante las etapas del proyecto?");
        randomQuestions.add("¿Cuentan con al menos un PM para cada proyecto?");
        randomQuestions.add("¿Sus equipos de trabajo suelen tener más de dos proyectos a la vez?");
        randomQuestions.add("¿Cómo controlan los costos del proyecto? ¿Tienen alguna metodología? "
                + "¿Qué porcentaje del presupuesto se destina a los problemas que puede haber?");
        randomQuestions.add("¿Consideran que la calidad de software, creación de valor e innovación afectan la gestión de proyectos? ¿Cómo?");
        randomQuestions.add("¿En qué se basan para dar seguimiento a los entregables? ¿Tienen algún control de calidad?");
        randomQuestions.add("¿Cómo controlan los cambios en el alcance de los proyectos? ¿Tienen alguna metodología?");
        randomQuestions.add("¿Que tecnologias se han implementado en su empresa y cómo han beneficiado su sistema de negocios?");
        randomQuestions.add("¿Qué métodos se usan para estructurar la información en la empresa?");
        randomQuestions.add("¿Han desarrollado sus propias tecnologías o usan tecnologías externas?");
        randomQuestions.add("¿Cuánta parte de los recursos financieros se dedican a la innovación de la empresa?");
        randomQuestions.add("¿Qué tanta libertad tienen los empleados en su trabajo?");
        randomQuestions.add("¿La empresa cuenta con área de investigación?¿Qué metodologías se usan en el área de investigación de la empresa?");
        randomQuestions.add("¿Qué sistemas de apoyo en tecnologías de información usa la empresa?");
        randomQuestions.add("¿Qué objetivos dirigidos al área de investigación se han propuesto en la empresa en el último año?");
        randomQuestions.add("¿Al sacar al mercado algún producto o servicio se hace un estudio de mercado para ver a qué cliente va dirigido y cómo procese ese proceso?");
        randomQuestions.add("¿Qué procesos se realizan en la pre-venta, venta y post-venta de los productos o servicios que ofreces?");
        randomQuestions.add("¿Cuál es la esencia de tu empresa?");
        randomQuestions.add("¿Cuáles son las estrategias de venta de tu empresa?");
        randomQuestions.add("¿Qué tan involucrados están tus empleados en la misión y visión de la empresa?");
        randomQuestions.add("¿Tu como empresa, que actividades realizas para conocer lo que tu cliente realmente quiere del producto o servicio que ofreces?");
        randomQuestions.add("¿Utilizas indicadores para conocer si el producto o servicio que ofreces tuvo éxito?");
        randomQuestions.add("¿Cómo evalúas el impacto de tu empresa en el mercado?");
        randomQuestions.add("¿Cuentas con un respaldo que solucione algún tipo de inconveniente emergente en alguno de tus procesos?");
        randomQuestions.add("¿Describe a tu empresa en una frase?");
        randomQuestions.add("¿Tienes claro el modelo de negocio de tu empresa?");
    }

    private class Entry {
        String pregunta;
        String respuesta;
    }

}
