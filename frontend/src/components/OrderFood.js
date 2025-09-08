
import React, { useState } from "react";
import { orderFood } from "../api";

const menu = [
  { id: 1, name: "Sandwich", price: 50 },
  { id: 2, name: "Pasta", price: 60 },
  { id: 3, name: "Noodles", price: 70 },
  { id: 4, name: "Coke", price: 30 }
];

export default function OrderFood({ onDone }) {
  const [roomNumber, setRoomNumber] = useState("");
  const [itemNo, setItemNo] = useState(1);
  const [quantity, setQuantity] = useState(1);

  async function handleOrder(e) {
    e.preventDefault();
    await orderFood(parseInt(roomNumber), { itemNo, quantity });
    alert("Ordered!");
    if(onDone) onDone();
  }

  return (
    <div>
      <h2>Order Food</h2>
      <form onSubmit={handleOrder} className="form">
        <input required placeholder="Room number" value={roomNumber} onChange={e => setRoomNumber(e.target.value)} />
        <select value={itemNo} onChange={e => setItemNo(parseInt(e.target.value))}>
          {menu.map(m => <option key={m.id} value={m.id}>{m.name} - Rs.{m.price}</option>)}
        </select>
        <input type="number" min="1" value={quantity} onChange={e => setQuantity(parseInt(e.target.value))} />
        <button type="submit">Order</button>
      </form>
    </div>
  );
}
