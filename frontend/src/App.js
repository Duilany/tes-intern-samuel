import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";
import Navbar from "./components/Navbar";
import DataTable from "./components/DataTable";
import TransactionForm from "./components/TransactionForm";
import TransactionDetail from "./components/TransactionDetail";

function App() {
  const [transactions, setTransactions] = useState([]);
  const [statusMap, setStatusMap] = useState({});

  // Ambil data dari viewData.json
  useEffect(() => {
    fetch("/viewData.json")
      .then((response) => response.json())
      .then((data) => {
        setTransactions(data.data);
        const map = data.status.reduce((acc, status) => {
          acc[status.id] = status.name;
          return acc;
        }, {});
        setStatusMap(map);
      })
      .catch((error) => console.error("Error mengambil data:", error));
  }, []);

  // Tambah transaksi baru
  const addTransaction = (formData) => {
    const newTransaction = {
      id: Math.max(...transactions.map((t) => t.id)) + 1,
      ...formData,
      createBy: formData.customerName,
      createOn: new Date().toISOString().slice(0, 19).replace("T", " "),
    };
    setTransactions([...transactions, newTransaction]);
  };

  // Edit transaksi yang ada
  const editTransaction = (id, formData) => {
    const updatedTransactions = transactions.map((t) =>
      t.id === id ? { ...t, ...formData } : t
    );
    setTransactions(updatedTransactions);
  };

  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <Navbar />
        <Routes>
          <Route
            path="/"
            element={
              <DataTable transactions={transactions} statusMap={statusMap} />
            }
          />
          <Route
            path="/add"
            element={<TransactionForm addTransaction={addTransaction} />}
          />
          <Route
            path="/edit/:id"
            element={
              <TransactionForm
                transactions={transactions}
                editTransaction={editTransaction}
              />
            }
          />
          <Route
            path="/view/:id"
            element={
              <TransactionDetail
                transactions={transactions}
                statusMap={statusMap}
              />
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
