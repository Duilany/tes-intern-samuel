import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

function TransactionForm({ transactions, addTransaction, editTransaction }) {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEdit = !!id;

  // Inisialisasi data form
  const [formData, setFormData] = useState({
    productID: "",
    productName: "",
    amount: "",
    customerName: "",
    status: 0,
    transactionDate: "",
  });

  // Muat data transaksi untuk diedit
  useEffect(() => {
    if (isEdit) {
      const transaction = transactions.find((t) => t.id === parseInt(id));
      if (transaction) {
        setFormData({
          productID: transaction.productID,
          productName: transaction.productName,
          amount: transaction.amount,
          customerName: transaction.customerName,
          status: transaction.status,
          transactionDate: transaction.transactionDate.slice(0, 16),
        });
      }
    }
  }, [id, transactions]);

  // Tangani perubahan input
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Tangani pengiriman form
  const handleSubmit = () => {
    if (isEdit) {
      editTransaction(parseInt(id), formData);
    } else {
      addTransaction(formData);
    }
    navigate("/");
  };

  return (
    <div className="p-4 max-w-md mx-auto">
      <h2 className="text-xl font-bold mb-4">
        {isEdit ? "Edit" : "Tambah"} Transaksi
      </h2>
      <div className="space-y-4">
        <div>
          <label className="block">ID Produk</label>
          <input
            name="productID"
            value={formData.productID}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <div>
          <label className="block">Nama Produk</label>
          <input
            name="productName"
            value={formData.productName}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <div>
          <label className="block">Jumlah</label>
          <input
            name="amount"
            type="number"
            value={formData.amount}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <div>
          <label className="block">Nama Pelanggan</label>
          <input
            name="customerName"
            value={formData.customerName}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <div>
          <label className="block">Status</label>
          <select
            name="status"
            value={formData.status}
            onChange={handleChange}
            className="w-full p-2 border rounded"
          >
            <option value={0}>BERHASIL</option>
            <option value={1}>GAGAL</option>
          </select>
        </div>
        <div>
          <label className="block">Tanggal Transaksi</label>
          <input
            name="transactionDate"
            type="datetime-local"
            value={formData.transactionDate}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <button
          onClick={handleSubmit}
          className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600"
        >
          {isEdit ? "Perbarui" : "Tambah"}
        </button>
        <button
          onClick={() => navigate("/")}
          className="w-full bg-gray-500 text-white p-2 rounded hover:bg-gray-600"
        >
          Batal
        </button>
      </div>
    </div>
  );
}

export default TransactionForm;
