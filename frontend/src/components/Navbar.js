import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="bg-blue-500 p-4 text-white">
      <div className="container mx-auto flex justify-between">
        <Link to="/" className="text-xl font-bold">
          Aplikasi Transaksi
        </Link>
        <Link
          to="/add"
          className="bg-green-500 px-4 py-2 rounded hover:bg-green-600"
        >
          Tambah Data
        </Link>
      </div>
    </nav>
  );
}

export default Navbar;
