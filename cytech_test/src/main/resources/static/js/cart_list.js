const sidebarQuantity = document.querySelector(".sidebar-quantity");
const sidebarPrice = document.querySelector(".sidebar-price");
const sidebarTaxPrice = document.querySelector(".sidebar-tax-price");

document.querySelectorAll(".quantity-select").forEach(select => {

  const unitPrice = Number(select.dataset.unitPrice);
  const unitTax = Number(select.dataset.unitTax);

  const container = select.closest(".container");
  const priceSpan = container.querySelector(".price");
  const taxSpan = container.querySelector(".tax-price");

  select.addEventListener("change", () => {
    const quantity = Number(select.value);

    // 商品単体の表示更新
    priceSpan.textContent = unitPrice * quantity;
    taxSpan.textContent = unitTax * quantity;

    // --- サイドバー（合計）再計算 ---
    let totalQuantity = 0;
    let totalPrice = 0;
    let totalTaxPrice = 0;

    document.querySelectorAll(".quantity-select").forEach(s => {
      const q = Number(s.value);
      const p = Number(s.dataset.unitPrice);
      const t = Number(s.dataset.unitTax);

      totalQuantity += q;
      totalPrice += p * q;
      totalTaxPrice += t * q;
    });

    sidebarQuantity.textContent = totalQuantity;
    sidebarPrice.textContent = totalPrice;
    sidebarTaxPrice.textContent = totalTaxPrice;
  });
});