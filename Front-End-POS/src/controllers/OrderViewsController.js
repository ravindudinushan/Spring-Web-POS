loadAllOrderDetail();

function loadAllOrderDetail() {
    $("#tblOrderDetails").empty();
    $.ajax({
        url: baseUrl+ "order?option=LoadOrderDetails", method: "GET", dataType: "json", success: function (res) {
            console.log(res);

            for (let i of res.data) {
                let OrderId = i.OrderId;
                let code = i.code;
                let qty = i.qty;
                let unitPrice = i.unitPrice;

                let row = "<tr><td>" + OrderId + "</td><td>" + code + "</td><td>" + qty + "</td><td>" + unitPrice + "</td></tr>";
                $("#tblOrderDetails").append(row);

            }
        }, error: function (error) {

        }

    });
}
