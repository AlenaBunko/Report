let addPurchaseSubmit = (function () {

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
})();