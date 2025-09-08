
import React, { useState } from "react";
import { bookRoom } from "../api";

export default function BookingForm({ room, onDone }) {
  const [name, setName] = useState("");
  const [contact, setContact] = useState("");
  const [gender, setGender] = useState("");
  const [name2, setName2] = useState("");
  const [contact2, setContact2] = useState("");
  const [gender2, setGender2] = useState("");
  const [loading, setLoading] = useState(false);

  async function handleSubmit(e) {
    e.preventDefault();
    setLoading(true);
    const payload = { name, contact, gender, name2, contact2, gender2 };
    await bookRoom(room.roomNumber, payload);
    setLoading(false);
    alert("Booked successfully!");
    onDone();
  }

  return (
    <div>
      <h2>Book Room {room.roomNumber} ({room.roomType})</h2>
      <form onSubmit={handleSubmit} className="form">
        <input required placeholder="Name" value={name} onChange={e => setName(e.target.value)} />
        <input required placeholder="Contact" value={contact} onChange={e => setContact(e.target.value)} />
        <input required placeholder="Gender" value={gender} onChange={e => setGender(e.target.value)} />

        {(room.roomType.includes("DOUBLE")) && (
          <>
            <h4>Second Guest (optional)</h4>
            <input placeholder="Name 2" value={name2} onChange={e => setName2(e.target.value)} />
            <input placeholder="Contact 2" value={contact2} onChange={e => setContact2(e.target.value)} />
            <input placeholder="Gender 2" value={gender2} onChange={e => setGender2(e.target.value)} />
          </>
        )}

        <button type="submit" disabled={loading}>{loading ? "Booking..." : "Book"}</button>
      </form>
    </div>
  );
}
