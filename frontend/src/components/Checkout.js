
import React, { useState } from "react";
import { checkoutRoom } from "../api";

export default function Checkout({ onDone }) {
  const [roomNumber, setRoomNumber] = useState("");
  const [result, setResult] = useState(null);

  async function handleCheckout(e) {
    e.preventDefault();
    const resp = await checkoutRoom(parseInt(roomNumber));
    setResult(resp);
    if(onDone) onDone();
  }

  return (
    <div>
      <h2>Checkout</h2>
      <form onSubmit={handleCheckout} className="form">
        <input required placeholder="Room number" value={roomNumber} onChange={e => setRoomNumber(e.target.value)} />
        <button type="submit">Checkout</button>
      </form>

      {result && (
        <div className="receipt">
          <h3>Bill</h3>
          <p>Room Charge: Rs. {result.roomCharge}</p>
          <h4>Food</h4>
          <ul>
            {result.foodSummary.map((s, i) => <li key={i}>{s}</li>)}
          </ul>
          <p><strong>Total: Rs. {result.total}</strong></p>
        </div>
      )}
    </div>
  );
}
