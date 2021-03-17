let addPurchaseSubmit = (function() {

let submitFn = function() {
let form= document.getElementById('addPurchaseForm');
form.action = '/user/userPage';
form.method = 'POST';
form.submit();
};

let canselFn = function() {
let canselBt = document.getElementById('cansel');
dialog = document.getElementById('addPurchaseDialog');
canselBt.addEventListener('click', function(){
dialog.close();
});
};
canselFn();

return {
submit: submitFn
}
})();