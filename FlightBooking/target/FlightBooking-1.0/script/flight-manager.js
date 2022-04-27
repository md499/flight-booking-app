$(function () {
    /**
     * Function for enable/disable update and remove flight buttons
     */
    $("#all-flight-table tbody tr").click(function () {
        if ($(this).hasClass("selected")) {
            $(this).removeClass("selected");

            // Deselect a flight, so disable update and remove buttons
            $("#update-flight-btn").attr("disabled", true);
            $("#update-flight-btn").removeAttr("value");
            $("#remove-flight-btn").attr("disabled", true);
            $("#remove-flight-btn").removeAttr("value");
        } else {
            //Select a flight
            $(this).closest('table').find('tbody tr').not(this).removeClass('selected');
            $(this).addClass('selected');

            let value = $(this).find('th:first').html();
            // console.log(value);
            $("#update-flight-btn").attr("disabled", false);
            $("#update-flight-btn").attr("value", value);
            $("#remove-flight-btn").attr("disabled", false);
            $("#remove-flight-btn").attr("value", value);

        }
    });

    /**
     * Function for turn on update-flight modal
     */
    $("#update-flight-btn").click(function () {
        let id = $(this).attr("value");

        // Change label
        let form = $("#update-flight-form .modal-body");
        $(form).find("label:first").html("Flight ID:  " + id);
        $(form).find("#flightID").attr("value", id);

        // Find the row
        let row = $(".selected");

        //Show current departure location
        $(form).find("#update-flight-departureLoc").val($(row[0]).find("#departure-loc-col").html());

        //Show current arrival location
        $(form).find("#update-flight-arrivalLoc").val($(row[0]).find("#arrival-loc-col").html());

        //Show current departure time
        $(form).find("#departureTime").attr("value", $(row[0]).find("#departure-time-col").html());

        //Show current arrival time
        $(form).find("#arrivalTime").attr("value", $(row[0]).find("#arrival-time-col").html());

        //Show current capacity and number of flights booked 
        let old_capacity = $(row[0]).find("#capacity-col").html();
        let old_available = $(row[0]).find("#available-col").html();
        let num_booked = old_capacity - old_available;
        $(form).find("#capacity-in").attr("value", old_capacity).attr("min", num_booked);
        $(form).find("#num-flights-booked").attr("value", num_booked);

        //Show current price
        $(form).find("#price-in").attr("value", $(row[0]).find("#price-col").html());

        let today = new Date();
        form.find("#departureTime:first").attr("min", today.toISOString().split('T')[0] + "T00:00");
        form.find("#arrivalTime:first").attr("min", today.toISOString().split('T')[0] + "T00:00");
    });

    /**
     * Function for turn on remove-flight modal
     */
    $("#remove-flight-btn").click(function () {
        let id = $(this).attr("value");
        let form = $("#remove-flight-form .modal-body");
        $(form).find("#flightID").attr("value", id);

        // Remove all table in this form
        $(form).find("table:first").remove();

        let row = $(".selected:first");

        console.log($(row[0]).html());
        let $table = $("<div class='container col-md-12'>" + "<table class='table table-hover table-responsive' id='all-flight-table'>" +
                "<thead> <tr class='text-center'>" +
                "<th scope='col'>ID</th>" +
                "<th scope='col'>Departure</th>" +
                "<th scope='col'>Arrival</th>" +
                "<th scope='col'>From</th>" +
                "<th scope='col'>To</th>" +
                "<th scope='col'>Capacity</th>" +
                "<th scope='col'>Available</th>" +
                "<th scope='col'>Price</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody class='text-center'>" +
                $(row[0]).html() +
                "</tbody>" + "</table> </div>");


        $(form).find(".container-fluid").append($table);
    });

    /*
     * Validate date input
     */
    $("#add-flight-btn").click(function () {
        let form = $("#add-flight-form");
        let today = new Date();
        form.find("#departure-time:first").attr("min", today.toISOString().split('T')[0] + "T00:00");
        form.find("#arrival-time:first").attr("min", today.toISOString().split('T')[0] + "T00:00");
    });

    $("#departure-time").change(function () {
        console.log($(this));
        let departureTimeString = $(this).val();
        let departureTime = new Date(departureTimeString);

        let arrivalInput = $("#arrival-time");
        let arrivalTimeString = arrivalInput.val();
        let arrivalTime = new Date(arrivalTimeString);

        if (arrivalTime < departureTime) {
            alert("Cannor arrive before departure");
            arrivalInput.val('');
        }
        arrivalInput.attr("min", departureTimeString);

    });

    $("#arrival-time").change(function () {
        console.log($(this));
        let arrivalTimeString = $(this).val();
        let arrivalTime = new Date(arrivalTimeString);

        let departureInput = $("#departure-time");
        let departureTimeString = departureInput.val();
        let departureTime = new Date(departureTimeString);

        if (arrivalTime < departureTime) {
            alert("Cannor arrive before departure");
            $(this).val('');
        }
        $(this).attr("min", departureTimeString);

    });

    $("#departureTime").change(function () {
        console.log($(this));
        let departureTimeString = $(this).val();
        let departureTime = new Date(departureTimeString);

        let arrivalInput = $("#arrivalTime");
        let arrivalTimeString = arrivalInput.val();
        let arrivalTime = new Date(arrivalTimeString);

        if (arrivalTime < departureTime) {
            alert("Cannor arrive before departure");
            arrivalInput.val('');

        }
        arrivalInput.attr("min", departureTimeString);

    });

    $("#arrivalTime").change(function () {
        console.log($(this));
        let arrivalTimeString = $(this).val();
        let arrivalTime = new Date(arrivalTimeString);

        let departureInput = $("#departurTime");
        let departureTimeString = departureInput.val();
        let departureTime = new Date(departureTimeString);

        if (arrivalTime < departureTime) {
            alert("Cannor arrive before departure");
            $(this).val('');
            $(this).attr("min", departureTimeString);
        }
        $(this).attr("min", departureTimeString);

    });
});