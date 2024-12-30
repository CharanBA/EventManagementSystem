document.addEventListener("DOMContentLoaded", function () {
    // Handle form submission for creating a new event
    const createEventForm = document.querySelector("#createEventForm form");

    createEventForm.addEventListener("submit", function (e) {
        e.preventDefault(); // Prevent the default form submission

        // Get form data
        const eventName = document.querySelector("#eventName").value;
        const eventDate = document.querySelector("#eventDate").value;
        const eventLocation = document.querySelector("#eventLocation").value;
        const eventDescription = document.querySelector("#eventDescription").value;

        const eventDto = {
            name: eventName,
            date: eventDate,
            location: eventLocation,
            description: eventDescription
        };

        // Send POST request to the backend to create a new event
        fetch("/events", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(eventDto)
        })
        .then(response => {
            // Check if the response status is OK (2xx)
            if (response.ok) {
                return response.json(); // Parse the response as JSON
            } else {
                return Promise.reject("Failed to create event, server returned an error");
            }
        })
        .then(data => {
            alert("Event created successfully!");
            createEventForm.reset(); // Reset the form
            // Optionally, redirect to a different page or update the event list
        })
        .catch(error => {
            console.error("Error creating event:", error);
            alert(error);
        });
    });

    // Function to fetch and display all events
    function fetchEvents() {
        fetch('/events') // Endpoint to get all events
            .then(response => response.json())
            .then(data => {
                const eventList = document.getElementById('eventList');
                eventList.innerHTML = ''; // Clear existing events

                data.forEach(event => {
                    const row = document.createElement('tr');

                    const eventIdCell = document.createElement('td');
                    eventIdCell.textContent = event.id;

                    const eventNameCell = document.createElement('td');
                    eventNameCell.textContent = event.name;

                    const eventDateCell = document.createElement('td');
                    eventDateCell.textContent = event.date;

                    const eventLocationCell = document.createElement('td');
                    eventLocationCell.textContent = event.location;

                    const eventDescriptionCell = document.createElement('td');
                    eventDescriptionCell.textContent = event.description;

                    // Create Update and Delete buttons
                    const actionCell = document.createElement('td');

                    // Update Button
                    const updateButton = document.createElement('button');
                    updateButton.textContent = 'Update';
                    updateButton.style.backgroundColor = '#ffc107';
                    updateButton.style.marginRight = '10px';
                    updateButton.addEventListener('click', function () {
                        // Fill the form with the event data for updating
                        document.querySelector("#eventName").value = event.name;
                        document.querySelector("#eventDate").value = event.date;
                        document.querySelector("#eventLocation").value = event.location;
                        document.querySelector("#eventDescription").value = event.description;

                        // Change the form action to update
                        createEventForm.removeEventListener('submit', createEventHandler); // Remove existing handler
                        createEventForm.addEventListener('submit', function (e) {
                            e.preventDefault();
                            updateEvent(event.id); // Call update function with the event id
                        });
                    });

                    // Delete Button
                    const deleteButton = document.createElement('button');
                    deleteButton.textContent = 'Delete';
                    deleteButton.addEventListener('click', function () {
                        fetch(`/events/${event.id}`, {
                            method: 'DELETE',
                        })
                            .then(response => {
                                if (response.ok) {
                                    alert('Event deleted successfully');
                                    row.remove(); // Remove the event row from the table
                                } else {
                                    alert('Failed to delete event');
                                }
                            })
                            .catch(error => {
                                console.error('Error deleting event:', error);
                                alert('Error deleting event');
                            });
                    });

                    actionCell.appendChild(updateButton);
                    actionCell.appendChild(deleteButton);

                    row.appendChild(eventIdCell);
                    row.appendChild(eventNameCell);
                    row.appendChild(eventDateCell);
                    row.appendChild(eventLocationCell);
                    row.appendChild(eventDescriptionCell);
                    row.appendChild(actionCell);

                    eventList.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error fetching events:', error);
                alert('Failed to load events.');
            });
    }

    // Function to handle updating an event
    function updateEvent(eventId) {
        // Get the updated form data
        const updatedEventDto = {
            name: document.querySelector("#eventName").value,
            date: document.querySelector("#eventDate").value,
            location: document.querySelector("#eventLocation").value,
            description: document.querySelector("#eventDescription").value
        };

        // Send PUT request to update the event
        fetch(`/events/${eventId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updatedEventDto)
        })
            .then(response => {
                if (response.ok) {
                    alert("Event updated successfully!");
                    createEventForm.reset(); // Reset the form
                    fetchEvents(); // Re-fetch events to display the updated list
                } else {
                    alert("Failed to update event.");
                }
            })
            .catch(error => {
                console.error("Error updating event:", error);
                alert(error);
            });
    }

    // Initial fetch of events
    fetchEvents();
});
