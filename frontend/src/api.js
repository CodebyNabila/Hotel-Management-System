
const BASE_URL = "http://localhost:8080/api/rooms";

export async function fetchAvailable() {
  const res = await fetch(`${BASE_URL}/available`);
  return res.json();
}

export async function bookRoom(roomNumber, payload) {
  const res = await fetch(`${BASE_URL}/book/${roomNumber}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });
  return res.json();
}

export async function orderFood(roomNumber, payload) {
  const res = await fetch(`${BASE_URL}/order/${roomNumber}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });
  return res.json();
}

export async function checkoutRoom(roomNumber) {
  const res = await fetch(`${BASE_URL}/checkout/${roomNumber}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
  });
  return res.json();
}

export async function getRoom(roomNumber) {
  const res = await fetch(`${BASE_URL}/${roomNumber}`);
  return res.json();
}
