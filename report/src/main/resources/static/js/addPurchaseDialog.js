let addPurchaseSubmit = (function () {

<<<<<<< HEAD
 let submitFn = function () {
   let form = document.getElementById('addPurchaseForm');
   form.action = '/user/addPurchase';
   form.method = 'POST';
   form.submit();
 };

 let cancelClickEvent = function() {
         let dialog = document.getElementById('addPurchaseDialog');
         dialog.close();
     };

 return {
   submit: submitFn,
   cancelEvent: cancelClickEvent
 }
=======
    let submitFn = function () {
        let form = document.getElementById('addPurchaseForm');
        form.action = '/user/addPurchase';
        form.method = 'POST';
        form.submit();
    };

    let canselFn = function () {
        let canselBt = document.getElementById('cansel');
        dialog = document.getElementById('addPurchaseDialog');
        canselBt.addEventListener('click', function () {
            dialog.close();
        });
    };
    canselFn();

    return {
        submit: submitFn
    }
>>>>>>> 3614060371443a13d3266ffd672b52bde2162ce4
})();