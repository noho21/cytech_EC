const quantitySelect = document.getElementById("quantity");
  const priceSpan = document.getElementById("price");
  const taxPriceSpan = document.getElementById("taxPrice");

  const unitPrice = Number(priceSpan.dataset.unitPrice);
  const unitTaxPrice = Number(taxPriceSpan.dataset.unitTax);

  quantitySelect.addEventListener("change", () => {
    const quantity = Number(quantitySelect.value);

    priceSpan.textContent = unitPrice * quantity;
    taxPriceSpan.textContent = unitTaxPrice * quantity;
  });