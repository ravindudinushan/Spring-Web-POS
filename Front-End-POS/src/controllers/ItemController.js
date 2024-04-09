// let baseUrl = "http://localhost:8080/app/";
loadAllItems();

$("#btnAddItem").attr('disabled', true);
$("#btnUpdateItem").attr('disabled', true);
$("#btnDeleteItem").attr('disabled', true);

function generateItemID() {
    $("#txtItemID").val("I00-001");
    $.ajax({
        url: "http://localhost:8080/app/item?option=ItemIdGenerate",
        method: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (resp) {
            let code = resp.code;
            let tempId = parseInt(code.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                $("#txtItemID").val("I00-00" + tempId);
            } else if (tempId <= 99) {
                $("#txtItemID").val("I00-0" + tempId);
            } else {
                $("#txtItemID").val("I00-" + tempId);
            }
        },
        error: function (ob, statusText, error) {

        }
    });
}

/**
 * Add New Item
 * */
$("#btnAddItem").click(function () {
    let formData = $("#itemForm").serialize();
    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "post",
        data: formData,
        dataType: "json",
        success: function (res) {
            saveAlert("item");
            loadAllItems();
        },
        error: function (error) {
            unSuccessUpdateAlert("item", JSON.parse(error.responseText).message);
        }
    });
});

/**
 * clear input fields Values
 * */
function setTextFieldValues(code, description, qty, price) {
    $("#txtItemID").val(code);
    $("#txtItemName").val(description);
    $("#txtItemQty").val(qty);
    $("#txtItemPrice").val(price);
    $("#txtItemName").focus();
    checkValidity(ItemsValidations);
    $("#btnAddItem").attr('disabled', true);
    $("#btnUpdateItem").attr('disabled', true);
    $("#btnDeleteItem").attr('disabled', true);

}

/**
 * load all item
 * */
function loadAllItems() {
    $("#ItemTable").empty();
    $.ajax({
        url: "http://localhost:8080/app/item?option=loadAllItem",
        method: "GET",
        dataType: "json",
        success: function (res) {
            console.log(res);
            for (let i of res.data) {
                let code = i.code;
                let description = i.description;
                let qty = i.qty;
                let unitPrice = i.unitPrice;

                let row = "<tr><td>" + code + "</td><td>" + description + "</td><td>" + qty + "</td><td>" + unitPrice + "</td></tr>";
                $("#ItemTable").append(row);
                console.log(code,description,qty,unitPrice);
            }
            blindClickEvents();
            generateItemID();
            setTextFieldValues("", "", "", "");
            console.log("res message: ", res.message);
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            console.log(message);
        }
    });
}

/**
 * Table Click and Load textFields
 * */
function blindClickEvents() {
    $("#ItemTable>tr").on("click", function () {
        let code = $(this).children().eq(0).text();
        let description = $(this).children().eq(1).text();
        let qty = $(this).children().eq(2).text();
        let unitPrice = $(this).children().eq(3).text();
        console.log(code, description, qty, unitPrice);

        $("#txtItemID").val(code);
        $("#txtItemName").val(description);
        $("#txtItemQty").val(qty);
        $("#txtItemPrice").val(unitPrice);

        $("#btnDeleteItem").attr('disabled', false);
    });
    $("#btnAddItem").attr('disabled', true);
}


/**
 * Search id
 * */
$("#ItemIdSearch").on("keypress", function (event) {
    if (event.which === 13) {
        event.preventDefault();
        var search = $("#ItemIdSearch").val();
        $("#ItemTable").empty();
        $.ajax({
            url: "http://localhost:8080/app/item?code=" + search + "&option=searchItemCode",
            method: "GET",
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log(res);
                let row = "<tr><td>" + res.code + "</td><td>" + res.description + "</td><td>" + res.qty + "</td><td>" + res.unitPrice + "</td></tr>";
                $("#ItemTable").append(row);
                blindClickEvents();
            },
            error: function (error) {
                loadAllItems();
                let message = JSON.parse(error.responseText).message;
                emptyMassage(message);
            }
        })
    }
});

/**Item Update
 * */

$("#btnUpdateItem").click(function () {

    let code = $("#txtItemID").val();
    let description = $("#txtItemName").val();
    let qty = $("#txtItemQty").val();
    let unitPrice = $("#txtItemPrice").val();

    var itemOb = {
        code: code,
        description: description,
        qty: qty,
        unitPrice: unitPrice
    }

    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "put",
        contentType: "application/json",
        data: JSON.stringify(itemOb),
        success: function (res) {
            saveUpdateAlert("Item");
            loadAllItems();
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            unSuccessUpdateAlert("Item", message);
        }
    });
});

/**
 * Item Delete
 * */

$("#btnDeleteItem").click(function () {

    let itCode = $("#txtItemID").val();
    let itDescription = $("#txtItemName").val();
    let itQty = $("#txtItemQty").val();
    let itUnitPrice = $("#txtItemPrice").val();

    const itemOb = {
        code: itCode,
        description: itDescription,
        qty: itQty,
        unitPrice: itUnitPrice
    }
    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "delete",
        contentType: "application/json",
        data: JSON.stringify(itemOb),
        success: function (res) {
            deleteAlert("Item");
            loadAllItems();
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            unSuccessUpdateAlert("Item", message);
        }
    });
});

$("#txtItemID").focus();
const regExItemCode = /^(I00-)[0-9]{3,4}$/;
const regExItemName = /^[A-z ]{3,20}$/;
const regExItemPrice = /^[0-9]{1,10}$/;
const regExItemQtyOnHand = /^[0-9]{1,}[.]?[0-9]{1,2}$/;

let ItemsValidations = [];
ItemsValidations.push({
    reg: regExItemCode,
    field: $('#txtItemID'),
    error: 'Item ID Pattern is Wrong : I00-001'
});
ItemsValidations.push({
    reg: regExItemName,
    field: $('#txtItemName'),
    error: 'Item Name Pattern is Wrong : A-z 3-20'
});
ItemsValidations.push({
    reg: regExItemPrice,
    field: $('#txtItemQty'),
    error: 'Item Qty Pattern is Wrong : 0-9 1-10'
});
ItemsValidations.push({
    reg: regExItemQtyOnHand,
    field: $('#txtItemPrice'),
    error: 'Item Salary Pattern is Wrong : 100 or 100.00'
});

//disable tab key of all four text fields using grouping selector in CSS
$("#txtItemID,#txtItemName,#txtItemQty,#txtItemPrice").on('keydown', function (event) {
    if (event.key === "Tab") {
        event.preventDefault();
    }
});

$("#txtItemID,#txtItemName,#txtItemQty,#txtItemPrice").on('keyup', function (event) {
    checkValidity(ItemsValidations);
});

$("#txtItemID,#txtItemName,#txtItemQty,#txtItemPrice").on('blur', function (event) {
    checkValidity(ItemsValidations);
});

$("#txtItemID").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemCode, $("#txtItemID"))) {
        $("#txtItemName").focus();
    } else {
        focusText($("#txtItemID"));
    }
});

$("#txtItemName").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemName, $("#txtItemName"))) {
        focusText($("#txtItemQty"));
    }
});

$("#txtItemQty").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemPrice, $("#txtItemQty"))) {
        focusText($("#txtItemPrice"));
    }
});

$("#txtItemPrice").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemQtyOnHand, $("#txtItemPrice"))) {
        if (event.which === 13) {
            $('#btnAddItem').focus();
        }
    }
});

function setButtonState(value) {
    if (value > 0) {
        $("#btnAddItem").attr('disabled', true);
        $("#btnUpdateItem").attr('disabled', true);
        $("#btnDeleteItem").attr('disabled', true);
    } else {
        $("#btnAddItem").attr('disabled', false);
        $("#btnUpdateItem").attr('disabled', false);
        $("#btnDeleteItem").attr('disabled', false);
    }
}