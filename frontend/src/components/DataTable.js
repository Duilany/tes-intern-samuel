import { Link } from "react-router-dom";

function DataTable({ transactions, statusMap }) {
  // Mengelompokkan data berdasarkan tahun dan bulan
  const groupedData = transactions.reduce((acc, item) => {
    const date = new Date(item.transactionDate);
    const year = date.getFullYear();
    const month = date.toLocaleString("default", { month: "long" });
    const key = `${year}-${month}`;
    if (!acc[key]) acc[key] = [];
    acc[key].push(item);
    return acc;
  }, {});

  return (
    <div className="p-4 container mx-auto">
      {Object.entries(groupedData).map(([key, items]) => (
        <div key={key} className="mb-6">
          <h2 className="text-lg font-bold mb-2">{key}</h2>
          <table className="w-full border">
            <thead>
              <tr className="bg-gray-200">
                <th className="border p-2">ID</th>
                <th className="border p-2">ID Produk</th>
                <th className="border p-2">Nama Produk</th>
                <th className="border p-2">Jumlah</th>
                <th className="border p-2">Nama Pelanggan</th>
                <th className="border p-2">Status</th>
                <th className="border p-2">Tanggal</th>
                <th className="border p-2">Aksi</th>
              </tr>
            </thead>
            <tbody>
              {items.map((item) => (
                <tr key={item.id} className="hover:bg-gray-100">
                  <td className="border p-2">{item.id}</td>
                  <td className="border p-2">{item.productID}</td>
                  <td className="border p-2">{item.productName}</td>
                  <td className="border p-2">{item.amount}</td>
                  <td className="border p-2">{item.customerName}</td>
                  <td className="border p-2">
                    {statusMap[item.status] === "SUCCESS"
                      ? "BERHASIL"
                      : "GAGAL"}
                  </td>
                  <td className="border p-2">{item.transactionDate}</td>
                  <td className="border p-2">
                    <Link
                      to={`/view/${item.id}`}
                      className="text-blue-500 mr-2"
                    >
                      Lihat
                    </Link>
                    <Link to={`/edit/${item.id}`} className="text-green-500">
                      Edit
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
}

export default DataTable;
