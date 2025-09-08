
import React, { useEffect, useState } from "react";
import { fetchAvailable } from "./api";
import RoomList from "./components/RoomList";
import BookingForm from "./components/BookingForm";
import OrderFood from "./components/OrderFood";
import Checkout from "./components/Checkout";

function App(){
  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [view, setView] = useState("list");

  useEffect(() => { load(); }, []);

  async function load(){
    const data = await fetchAvailable();
    setRooms(data);
  }

  function onSelectRoom(room){
    setSelectedRoom(room);
    setView("book");
  }

  return (
    <div className="container">
      <h1>Hotel Management</h1>
      <div className="nav">
        <button onClick={() => { setView("list"); setSelectedRoom(null); load(); }}>Available Rooms</button>
        <button onClick={() => { setView("order"); }}>Order Food</button>
        <button onClick={() => { setView("checkout"); }}>Checkout</button>
      </div>

      {view === "list" && (
        <RoomList rooms={rooms} onSelect={onSelectRoom} />
      )}

      {view === "book" && selectedRoom && (
        <BookingForm room={selectedRoom} onDone={() => { setView("list"); load(); }} />
      )}

      {view === "order" && (
        <OrderFood onDone={() => { setView("list"); load(); }} />
      )}

      {view === "checkout" && (
        <Checkout onDone={() => { setView("list"); load(); }} />
      )}
    </div>
  );
}

export default App;
