import { useParams, useNavigate } from "react-router-dom";

function TransactionDetail({ transactions, statusMap }) {
  const { id } = useParams();
  const navigate = useNavigate();
  const transaction = transactions.find((t) => t.id === parseInt(id));

  if (!transaction) return <div>Transaksi tidak ditemukan</div>;

  return (
    <div className="p-4 max-w-md mx-auto">
      <h2 className="text-xl font-bold mb-4">Detail Transaksi</h2>
      <div className="space-y-2">
        <p>
          <strong>ID:</strong> {transaction.id}
        </p>
        <p>
          <strong>ID Produk:</strong> {transaction.productID}
        </p>
        <p>
          <strong>Nama Produk:</strong> {transaction.productName}
        </p>
        <p>
          <strong>Jumlah:</strong> {transaction.amount}
        </p>
        <p>
          <strong>Nama Pelanggan:</strong> {transaction.customerName}
        </p>
        <p>
          <strong>Status:</strong>{" "}
          {statusMap[transaction.status] === "SUCCESS" ? "BERHASIL" : "GAGAL"}
        </p>
        <p>
          <strong>Tanggal Transaksi:</strong> {transaction.transactionDate}
        </p>
        <p>
          <strong>Dibuat Oleh:</strong> {transaction.createBy}
        </p>
        <p>
          <strong>Tanggal Dibuat:</strong> {transaction.createOn}
        </p>
      </div>
      <button
        onClick={() => navigate("/")}
        className="w-full bg-gray-500 text-white p-2 rounded hover:bg-gray-600 mt-4"
      >
        Kembali
      </button>
    </div>
  );
}

export default TransactionDetail;
