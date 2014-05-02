// creating date string
function currentDate()
{
	var today = new Date();
	today = today.getMonth()+1 + '/' + today.getDate() + '/' + today.getFullYear();
	return today;
}

// sets the modal window with selected room information
function setModal(number)
{
	// find necessary element in the webpage
	var element = document.getElementById("popover_room");
	element.innerText = "Room " + number;
	var row = document.getElementById(number);
	var classes = row.getAttribute("class").split(" ");
	var noteField = document.getElementById("note");
	var dateField = document.getElementById("modified");

	// set note field and date field
	noteField.value = row.getAttribute("note");
	dateField.innerText = "Last Modified: " + row.getAttribute("date");

	// select current room state radio button
	if(classes[0] == "info")
		document.getElementById("radio4").checked = true;
	else if(classes[0] == "success")
		document.getElementById("radio1").checked = true;
	else if(classes[0] == "warning")
		document.getElementById("radio2").checked = true;
	else if(classes[0] == "danger")
		document.getElementById("radio3").checked = true;
}

// change selected room visual appearance only if the room can be
// saved to the database. called from the .click() function below
function saveModal()
{
	var element = document.getElementById("myModal");
	var room = document.getElementById("popover_room");

	var text = room.innerText.substring(5);
	var row = document.getElementById(text);
	var classes = row.getAttribute("class").split(" ");

	// move each history circle down
	var previous = row.getElementsByClassName("previous");
	previous[2].setAttribute("class", previous[1].getAttribute("class"));
	previous[1].setAttribute("class", previous[0].getAttribute("class"));
	if(classes[0] == "info")
		previous[0].setAttribute("class", "previous empty");
	else if(classes[0] == "success")
		previous[0].setAttribute("class", "previous green");
	else if(classes[0] == "warning")
		previous[0].setAttribute("class", "previous yellow");
	else if(classes[0] == "danger")
		previous[0].setAttribute("class", "previous red");

	// set the background color depending on selected radio button
	if(document.getElementById("radio4").checked == true)
		row.setAttribute("class", "info room " + classes[2]);
	else if(document.getElementById("radio1").checked == true)
		row.setAttribute("class", "success room " + classes[2]);
	else if(document.getElementById("radio2").checked == true)
		row.setAttribute("class", "warning room " + classes[2]);
	else if(document.getElementById("radio3").checked == true)
		row.setAttribute("class", "danger room " + classes[2]);

	// set note and date field
	var input = document.getElementById("note");
	row.setAttribute("note", input.value);
	row.setAttribute("date", currentDate());
}

// mark all rooms as red
function markAll()
{
	// grab all cells in the table
	var today = currentDate()
	var elements = $('table').children('tbody').children('tr').children('td');

	$.post('scripts/database-class.php', "connect=yes",
	function(response)
	{
		// make sure the database is online
		if(response.indexOf("mysql_connect()") > 0)
			alert("Could not save. Contact administrator.");
		else
		{
			// loop through all cells in the table
			for(var i=0; i<elements.length; i++)
			{
				var classes = elements[i].getAttribute("class").split(" ");
				
				// no halls, empty rooms or bathrooms should be changed
				if(classes[0] != "hall" && classes[2] != 'rnone' && classes[2] != 'rnone')
				{
					var id = elements[i].getAttribute("id");
					var post_data = "room=" + id + "&date=" + today;

					// sve data to database through a post request
					$.post('/scripts/saveallrooms.php', post_data,
					function(response)
					{
						if(response.indexOf("mysql_connect()") > 0)
							alert("Could not save. Contact administrator.");
					});
					elements[i].setAttribute("class", "danger room " + classes[2]);
					elements[i].setAttribute("date", today);
				}	
			}
		}
	});
}

// 'Save' data button has been clicked
$("a[type=submit]").click(function()
{
	// grab JSON data from form
	var post_data = $('#room_save').serialize();
	var room = document.getElementById("popover_room");
	var text = room.innerText.substring(5);

	// begin creating POST request
	post_data = post_data + "&room=" + text + "&date=" + currentDate();
	
	$.post('scripts/database-class.php', "connect=yes",
	function(response)
	{
		// make sure the database is online
		if(response.indexOf("mysql_connect()") > 0)
			alert("Could not save. Contact administrator.");
		else
		{
			saveModal();
			// build string for the history circles
			var previous = document.getElementById(text).getElementsByClassName("previous");
			var classes1 = previous[0].getAttribute("class").split(" ")[1];
			var classes2 = previous[1].getAttribute("class").split(" ")[1];
			var classes3 = previous[2].getAttribute("class").split(" ")[1];
			post_data = post_data + "&s1=" + classes1 + "&s2=" + classes2 + "&s3=" + classes3;
			
			// sve data to database through a post request
			$.post('scripts/saveroom.php', post_data,
			function(response)
			{
				if(response.indexOf("mysql_connect()") > 0)
					alert("Could not save. Contact administrator.");
			});
		}
	});

});