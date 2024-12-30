// Sample attendees array (replace with API calls in production)
let attendees = [];

// Get Attendees button event
document.getElementById("getAttendeesBtn").addEventListener("click", fetchAttendees);

// Add Attendee button event
document.getElementById("addAttendeeBtn").addEventListener("click", function() {
    document.getElementById("addAttendeeForm").style.display = "block";
});

// Attendee form submission
document.getElementById("attendeeForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    const name = document.getElementById("attendeeName").value;
    const email = document.getElementById("attendeeEmail").value;

    // Simulating adding an attendee (replace with API call in production)
    const newAttendee = { name, email, id: Date.now() }; // Unique ID for each attendee
    attendees.push(newAttendee);

    // Clear the form and hide the add attendee form
    document.getElementById("attendeeForm").reset();
    document.getElementById("addAttendeeForm").style.display = "none";

    // Refresh the table
    renderAttendees();
});

// Fetch Attendees (replace this with API call in production)
function fetchAttendees() {
    // Simulating fetching attendees (replace with actual API call)
    // In production, replace this with a fetch call to your backend
    attendees = [
        { id: 1, name: "John Doe", email: "john@example.com" },
        { id: 2, name: "Jane Smith", email: "jane@example.com" },
    ];

    // Render the table with attendees
    renderAttendees();
}

// Render attendees in a table
function renderAttendees() {
    const attendeesTableBody = document.getElementById("attendeesTableBody");
    attendeesTableBody.innerHTML = ""; // Clear existing rows

    // If no attendees, show a message
    if (attendees.length === 0) {
        attendeesTableBody.innerHTML = "<tr><td colspan='3'>No attendees found.</td></tr>";
    } else {
        // Populate table with attendees
        attendees.forEach(attendee => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${attendee.name}</td>
                <td>${attendee.email}</td>
                <td><button onclick="deleteAttendee(${attendee.id})">Delete</button></td>
            `;
            attendeesTableBody.appendChild(row);
        });
    }

    // Show the table
    document.getElementById("attendeesTable").style.display = "table";
}

// Delete Attendee (replace with API call in production)
function deleteAttendee(id) {
    // Simulating deletion (replace with API call to backend)
    attendees = attendees.filter(attendee => attendee.id !== id);

    // Refresh the table
    renderAttendees();
}
