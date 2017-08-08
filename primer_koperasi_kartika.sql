-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 06 Agu 2017 pada 17.23
-- Versi Server: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `primer_koperasi_kartika`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE `anggota` (
  `no` int(11) NOT NULL,
  `id` varchar(8) NOT NULL,
  `nama` varchar(32) NOT NULL,
  `nrp` varchar(16) NOT NULL,
  `pangkat` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`no`, `id`, `nama`, `nrp`, `pangkat`) VALUES
(10, 'AG00001', 'Ibnul Mutaki', '3201416071', 'Kolonel'),
(11, 'AG00002', 'Galang Septiadi', '3201416092', 'Serda'),
(12, 'AG00003', 'Ibnu Afriza', '3201516089', 'Bintara'),
(13, 'AG00004', 'Anggota Tester 1', '00001111', 'Pangkat 1'),
(14, 'AG00005', 'Anggota Tester 2', '11112222', 'Pangkat 2'),
(15, 'AG00006', 'Anggota Tester 3', '22223333', 'Pangkat 3');

-- --------------------------------------------------------

--
-- Struktur dari tabel `angsuran`
--

CREATE TABLE `angsuran` (
  `no` int(11) NOT NULL,
  `no_pinjam` varchar(8) NOT NULL,
  `angsuran_ke` int(11) NOT NULL,
  `jumlah_angsuran` int(11) NOT NULL,
  `jasa` int(11) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `angsuran`
--

INSERT INTO `angsuran` (`no`, `no_pinjam`, `angsuran_ke`, `jumlah_angsuran`, `jasa`, `tanggal`) VALUES
(2, 'PJ00001', 1, 120000, 0, '2017-08-06'),
(3, 'PJ00001', 2, 166666, 0, '2017-08-08'),
(4, 'PJ00001', 3, 166666, 0, '2017-08-07'),
(5, 'PJ00001', 4, 166666, 0, '2017-08-07'),
(6, 'PJ00001', 5, 166666, 0, '2017-08-07'),
(7, 'PJ00002', 1, 300000, 0, '2017-08-07'),
(8, 'PJ00002', 2, 300000, 0, '2017-08-08'),
(9, 'PJ00002', 3, 300000, 0, '2017-08-07'),
(10, 'PJ00001', 6, 166666, 0, '2017-08-07'),
(11, 'PJ00003', 1, 175000, 21000, '2017-08-16'),
(12, 'PJ00003', 2, 175000, 21000, '2017-08-07');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pinjaman`
--

CREATE TABLE `pinjaman` (
  `no` varchar(8) NOT NULL,
  `id_anggota` varchar(8) NOT NULL,
  `lama_pinjaman` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pinjaman`
--

INSERT INTO `pinjaman` (`no`, `id_anggota`, `lama_pinjaman`, `jumlah`, `tanggal`) VALUES
('PJ00001', 'AG00001', 12, 2000000, '2017-08-06'),
('PJ00002', 'AG00002', 10, 3000000, '2017-08-07'),
('PJ00003', 'AG00006', 12, 2100000, '2017-08-08'),
('PJ00004', 'AG00005', 12, 3400000, '2017-08-09');

-- --------------------------------------------------------

--
-- Struktur dari tabel `simpanan`
--

CREATE TABLE `simpanan` (
  `no` varchar(8) NOT NULL,
  `id_anggota` varchar(8) NOT NULL,
  `sukarela` int(11) NOT NULL,
  `wajib` int(11) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `simpanan`
--

INSERT INTO `simpanan` (`no`, `id_anggota`, `sukarela`, `wajib`, `tanggal`) VALUES
('SP00001', 'AG00004', 5000, 5000000, '2017-08-08'),
('SP00002', 'AG00006', 3500, 3500000, '2017-08-09'),
('SP00003', 'AG00001', 4000, 4200000, '2017-08-10'),
('SP00004', 'AG00003', 3000, 3500000, '2017-08-11');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `tipe` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `tipe`, `password`) VALUES
('admin', 'bendahara', 'admin'),
('asd', '[C@21a9e7f8', 'Bendahara'),
('bendahara', 'bendahara', 'bendahara'),
('ketua', 'ketua', 'ketua'),
('tester', '[C@8eb1e49', 'Ketua');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`no`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `angsuran`
--
ALTER TABLE `angsuran`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `simpanan`
--
ALTER TABLE `simpanan`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `angsuran`
--
ALTER TABLE `angsuran`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
