
import React from "react";

export default function RoomList({ rooms, onSelect }) {
  if (!rooms || rooms.length === 0) return <div>No rooms available</div>;

  return (
    <div>
      <h2>Available Rooms</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Room No</th>
            <th>Type</th>
            <th>Charge/Day</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {rooms.map(r => (
            <tr key={r.roomNumber}>
              <td>{r.roomNumber}</td>
              <td>{r.roomType}</td>
              <td>{r.roomChargePerDay}</td>
              <td>
                <button onClick={() => onSelect(r)}>Book</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
