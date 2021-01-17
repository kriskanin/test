package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shop.models.MemberCard;

import java.io.IOException;

public class MemberCardDetailController {
    // ระบุชื่อ field ตามชื่อ fx:id และใส่ @FXML ไว้ด้านหน้าทุก field ที่เชื่อมกับ view
    // ตอนพิมพ์ ให้เลือก import จาก javafx.scene.xxxx
    @FXML private Label nameLabel;
    @FXML private Label phoneLabel;
    @FXML private Label cumulativePurchaseLabel;
    @FXML private Label pointLabel;
    @FXML private TextField purchaseTextField;
    @FXML private TextField pointTextField;



    // controller เชื่อมต่อกับ model เพื่อเก็บข้อมูลและเรียกข้อมูลมาแสดงผลที่ view
    private MemberCard memberCard;

    @FXML
    public void initialize() {
        // initialize จะถูกเรียกให้ทำงานเมื่อมีการ load Controller นี้
        System.out.println("initialize MemberCardDetailController");
        memberCard = (MemberCard) FXRouter.getData();
        showMemberCardData();
    }
    @FXML
    public void handleAddPurchaseButton(ActionEvent actionEvent) {
        // รับข้อมูลจาก TextField ข้อมูลที่รับเป็น String เสมอ
        String purchaseString = purchaseTextField.getText();
        // แปลงชนิดข้อมูล String เป็น double ด้วย Double.parseDouble()
        double purchase = Double.parseDouble(purchaseString);
        // เรียกการคำนวณต่าง ๆ จาก model
        memberCard.addPurchase(purchase);
        // แสดงผลข้อมูล
        showMemberCardData();
        // clear ช่อง TextField
        purchaseTextField.clear();
    }
    @FXML
    public void handleUsePointButton(ActionEvent actionEvent) {
        String pointString = pointTextField.getText();
        // แปลงชนิดข้อมูล String เป็น int ด้วย Integer.parseInt()
        int point = Integer.parseInt(pointString);
        memberCard.useStamp(point);
        showMemberCardData();
        pointTextField.clear();
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }


    private void showMemberCardData() {
        nameLabel.setText(memberCard.getName());
        phoneLabel.setText(memberCard.getPhone());
        String cumulativePurchase =
                String.format("%.2f", memberCard.getCumulativePurchase());
        cumulativePurchaseLabel.setText(cumulativePurchase);
        String point = "" + memberCard.getStamp();
        pointLabel.setText(point);
    }


}
