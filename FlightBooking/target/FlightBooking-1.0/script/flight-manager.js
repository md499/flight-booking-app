$(function () {
    $("#all-flight-table tbody tr").click(function ()
    {
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
            
            let value=$(this).find('th:first').html();
            // console.log(value);
            $("#update-flight-btn").attr("disabled", false);
            $("#update-flight-btn").attr("value", value);
            $("#remove-flight-btn").attr("disabled", false);
            $("#remove-flight-btn").attr("value", value);
            
        }
    });
    
    $("#update-flight-btn").click(function () 
    {
        let id = $(this).attr("value");

        // Change label
        let form = $("#update-flight-form .modal-body");
        $(form).find("label:first").html("Flight ID:  " + id);
        $(form).find("#flightID").attr("value", id);
        
        // Find the row
        let row = $(".selected");
        //console.log(row[0]);
        //Show current departure location
        $(form).find("#departureLoc").attr("value", $(row[0]).find("#departure-loc-col").html());
        
        //Show current arrival location
        $(form).find("#arrivalLoc").attr("value", $(row[0]).find("#arrival-loc-col").html());
        
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
    });
});

