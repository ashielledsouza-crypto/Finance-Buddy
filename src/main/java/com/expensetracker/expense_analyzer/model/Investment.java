package com.expensetracker.expense_analyzer.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String category; // "Crypto", "Mutual Funds", "Stocks", "Gold", "Bonds"

    @NotBlank
    private String assetId;  // e.g. "bitcoin", "119551" (AMFI Scheme Code), or "RELIANCE.BOM"

    @NotBlank
    private String assetName;

    @Positive
    private double buyPrice;

    @Positive
    private double quantity;

    private double annualYield; // Used for coupon/interest calculation on Bonds

    @NotNull
    private LocalDate date;

    public Investment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAssetId() { return assetId; }
    public void setAssetId(String assetId) { this.assetId = assetId; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public double getBuyPrice() { return buyPrice; }
    public void setBuyPrice(double buyPrice) { this.buyPrice = buyPrice; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getAnnualYield() { return annualYield; }
    public void setAnnualYield(double annualYield) { this.annualYield = annualYield; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
