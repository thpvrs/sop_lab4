package com.example.lab4;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;

@Route(value = "index2")
public class CashierView extends FormLayout {
    private NumberField tf, tf1000, tf500, tf100, tf20, tf10, tf5, tf1;
    private Button cal;
    private Label lb1;

    public CashierView(){
        lb1 = new Label("เงินทอน");
        cal = new Button("คำนวณเงินทอน");
        tf = new NumberField();
        tf.setPrefixComponent(new Span("$"));
        tf1000 = new NumberField();
//        tf1000.setEditable(false);
        tf1000.setPrefixComponent(new Span("$1000:"));
        tf500 = new NumberField();
        tf500.setPrefixComponent(new Span("$500:"));
        tf100 = new NumberField();
        tf100.setPrefixComponent(new Span("$100:"));
        tf20 = new NumberField();
        tf20.setPrefixComponent(new Span("$20:"));
        tf10 = new NumberField();
        tf10.setPrefixComponent(new Span("$10:"));
        tf5 = new NumberField();
        tf5.setPrefixComponent(new Span("$5:"));
        tf1 = new NumberField();
        tf1.setPrefixComponent(new Span("$1:"));
        VerticalLayout v1 = new VerticalLayout();
        v1.add(lb1,tf,cal,tf1000,tf500,tf100,tf20,tf10,tf5,tf1);
        this.add(v1);

        cal.addClickListener(event ->{
            Double num1 = Double.valueOf(((tf.getValue())));

            Change out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/"+num1)
                    .retrieve()//รอเอาข้อมูลกลับมา
                    .bodyToMono(Change.class)//เปลี่ยนresที่กลับเข้ามาให้เป็นชนิดที่เราต้องการ
                    .block(); //   ทำเสร็จแล้วหยุด
            Change money =out;
            tf1000.setValue(Double.valueOf(((money.getB1000()))));
            tf500.setValue(Double.valueOf((money.getB500())));
            tf100.setValue(Double.valueOf((money.getB100())));
            tf20.setValue(Double.valueOf((money.getB20())));
            tf10.setValue(Double.valueOf((money.getB10())));
            tf5.setValue(Double.valueOf((money.getB5())));
            tf1.setValue(Double.valueOf((money.getB1())));
        });
    }
}

