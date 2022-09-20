package com.example.lab4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.Notification;

//import java.awt.*;

@Route(value = "index1")
public class MathView extends FormLayout {
    private TextField n1, n2, n3;
    private Label l1;
    private Button btnPlus, btnMinus, btnMul, btnDivide, btnMod, btnMax;
    public MathView() {
        n1 = new TextField("Number 1");
        n1.setWidthFull();
        n2 = new TextField("Number 2");
        n2.setWidthFull();
        n3 = new TextField("Answer");
        n3.setWidthFull();
        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMul = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");
        l1 = new Label("Operator");
        HorizontalLayout h1 = new HorizontalLayout();
        VerticalLayout v1 = new VerticalLayout();
        VerticalLayout v2 = new VerticalLayout();
        VerticalLayout v3 = new VerticalLayout();
        v1.add(n1, n2);
        h1.add(btnPlus, btnMinus, btnMul, btnDivide, btnMod, btnMax);
        v2.add(n3);
        v3.add(l1);
        v1.add(v3);
        v1.add(h1);
        v1.add(v2);
        this.add(v1);
        this.setResponsiveSteps(new ResponsiveStep("1px", 1));
//        when user click button

        btnPlus.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            n3.setValue(out);
        });
        btnMinus.addClickListener((event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/"+"minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            n3.setValue(out);
        }));
        btnMul.addClickListener((event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/"+"multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            n3.setValue(out);
        }));
        btnDivide.addClickListener((event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/"+"divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            n3.setValue(out);
        }));
        btnMod.addClickListener((event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/"+"mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            n3.setValue(out);
        }));
        btnMax.addClickListener((event ->{
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", n1.getValue());
            formData.add("n2", n2.getValue());

            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/"+"max")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            n3.setValue(out);
        }));

    }
}
