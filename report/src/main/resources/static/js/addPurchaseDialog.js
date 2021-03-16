let addPurchaseDialogFunctions = (function () {

    let isCreate;

    let cancelClickEvent = function() {
        let dialog = document.getElementById('addPurchaseDialog');
        dialog.close();
    };

    let setCreate = function() {
        addPurchaseDialogFunctions.isCreate = true;
    };

     let onTableClick = function (product, id, purchaseAmount, date, warrantyPeriod) {
            let dialog = document.getElementById('addPurchaseDialog'),
                productIn = document.getElementById('product'),
                purchaseAmountIn = document.getElementById('purchaseAmount'),
                dateIn = document.getElementById('date'),
                warrantyPeriodIn = document.getElementById('warrantyPeriod'),
                productIdInput = document.getElementById('productIdInput');
            productIn.value = product;
            purchaseAmountIn.value = purchaseAmount;
            dateIn.value = date;
            warrantyPeriodIn.value = warrantyPeriod;
            productIdInput.value = id;
            addPurchaseDialogFunctions.isCreate = false;
            dialog.show();
        };

     let submit = function () {
            let url = addPurchaseDialogFunctions.isCreate ? '/user/addPurchase' : '/user/updatePurchase',
                product = document.getElementById('product').value,
                purchaseAmount = document.getElementById('purchaseAmount').value,
                date = document.getElementById('date').value,
                warrantyPeriod = document.getElementById('warrantyPeriod').value,
                productIdInput = document.getElementById('productIdInput').value,
                form = new FormData();
            form.append('product', product);
            form.append('purchaseAmount', purchaseAmount);
            form.append('date', date);
            form.append('warrantyPeriod', warrantyPeriod);
            form.append('id', productIdInput);
            axios.post(url, form)
                .then(function(response) {
                    onAddPurchaseSuccess(response);
                })
                .catch(function(error) {
                    onAddPurchaseError(error)
                });
            document.getElementById('addPurchaseDialog').close();
        };

        let onAddPurchaseSuccess = function (response) {
                if (response.data.success) {
                    let table = document.getElementById("tableId");
                    if (addPurchaseDialogFunctions.isCreate) {
                        createFun(response, table);
                    } else {
                        updateFun(response, table);
                    }
                } else {
                    document.getElementById('error').textContent = response.data.msg;
                }
            };

          let onAddPurchaseError = function (error) {
                 console.log(error);
             };

             return {
                 onTableClick: onTableClick,
                 cancelEvent: cancelClickEvent,
                 submit: submit,
                 setCreate: setCreate
             }

         })();