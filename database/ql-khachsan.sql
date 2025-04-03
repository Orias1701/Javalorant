-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ql_khachsan
-- ------------------------------------------------------
-- Server version	8.4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitietphieusddv`
--

DROP TABLE IF EXISTS `chitietphieusddv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieusddv` (
  `MaPhieuSDDV` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaDichVu` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `SoLuongDichVu` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaPhieuSDDV`,`MaDichVu`),
  KEY `MaDichVu` (`MaDichVu`),
  CONSTRAINT `chitietphieusddv_ibfk_1` FOREIGN KEY (`MaPhieuSDDV`) REFERENCES `phieusddv` (`MaPhieuSDDV`),
  CONSTRAINT `chitietphieusddv_ibfk_2` FOREIGN KEY (`MaDichVu`) REFERENCES `dichvu` (`MaDichVu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietphieusddv`
--

LOCK TABLES `chitietphieusddv` WRITE;
/*!40000 ALTER TABLE `chitietphieusddv` DISABLE KEYS */;
INSERT INTO `chitietphieusddv` VALUES ('PSDDV001','DV001',2),('PSDDV001','DV002',1),('PSDDV002','DV003',3),('PSDDV002','DV004',2),('PSDDV003','DV005',1),('PSDDV003','DV006',2);
/*!40000 ALTER TABLE `chitietphieusddv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS `dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu` (
  `MaDichVu` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `LoaiDichVu` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `GiaDichVu` decimal(10,2) NOT NULL,
  PRIMARY KEY (`MaDichVu`),
  CONSTRAINT `dichvu_chk_1` CHECK ((`LoaiDichVu` in (_utf8mb4'Ăn uống',_utf8mb4'Giặt ủi',_utf8mb4'Spa',_utf8mb4'Thuê xe',_utf8mb4'Công viên',_utf8mb4'Massage',_utf8mb4'Bể bơi')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

LOCK TABLES `dichvu` WRITE;
/*!40000 ALTER TABLE `dichvu` DISABLE KEYS */;
INSERT INTO `dichvu` VALUES ('DV001','Ăn uống',500000.00),('DV002','Giặt ủi',200000.00),('DV003','Spa',1000000.00),('DV004','Thuê xe',300000.00),('DV005','Công viên',150000.00),('DV006','Massage',800000.00),('DV007','Bể bơi',250000.00);
/*!40000 ALTER TABLE `dichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHoaDon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaNhanVien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaKhachHang` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Ngay` date NOT NULL,
  PRIMARY KEY (`MaHoaDon`),
  KEY `MaNhanVien` (`MaNhanVien`),
  KEY `MaKhachHang` (`MaKhachHang`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`),
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('HD001','NV001','KH001','2025-03-01'),('HD002','NV002','KH002','2025-03-02'),('HD003','NV003','KH003','2025-03-03');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKhachHang` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TenKhachHang` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Sdt` varchar(15) NOT NULL,
  `TinhTrangKhach` enum('Đang ở','Đã rời đi','Hủy đặt phòng') NOT NULL,
  PRIMARY KEY (`MaKhachHang`),
  UNIQUE KEY `Sdt` (`Sdt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('KH001','Nguyen Van A','0987654321','Đang ở'),('KH002','Le Thi B','0977654321','Đã rời đi'),('KH003','Tran Van C','0967654321','Hủy đặt phòng'),('KH004','Pham Thi D','0957654321','Đang ở'),('KH005','Hoang Van E','0947654321','Đã rời đi');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaiphong`
--

DROP TABLE IF EXISTS `loaiphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaiphong` (
  `Maloai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TenLoaiPhong` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `GiaLoai` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Maloai`),
  CONSTRAINT `loaiphong_chk_1` CHECK ((`TenLoaiPhong` in (_utf8mb4'VIP',_utf8mb4'Đơn',_utf8mb4'Đôi')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaiphong`
--

LOCK TABLES `loaiphong` WRITE;
/*!40000 ALTER TABLE `loaiphong` DISABLE KEYS */;
INSERT INTO `loaiphong` VALUES ('LP001','VIP',2000000.00),('LP002','Đơn',800000.00),('LP003','Đôi',1200000.00);
/*!40000 ALTER TABLE `loaiphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TenNhanVien` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Sdt` varchar(15) NOT NULL,
  `GioiTinh` enum('Nam','Nữ') NOT NULL,
  PRIMARY KEY (`MaNhanVien`),
  UNIQUE KEY `Sdt` (`Sdt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV001','Nguyen Van X','0912345678','Nam'),('NV002','Tran Thi Y','0922345678','Nữ'),('NV003','Pham Van Z','0932345678','Nam'),('NV004','Le Thi M','0942345678','Nữ'),('NV005','Hoang Van N','0952345678','Nam');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieudatphong`
--

DROP TABLE IF EXISTS `phieudatphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieudatphong` (
  `MaPhieuDatPhong` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaPhong` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaNhanVien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaKhachHang` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaHoaDon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NgayNhanPhong` date NOT NULL,
  `NgayTraPhong` date NOT NULL,
  PRIMARY KEY (`MaPhieuDatPhong`),
  KEY `MaPhong` (`MaPhong`),
  KEY `MaNhanVien` (`MaNhanVien`),
  KEY `MaKhachHang` (`MaKhachHang`),
  KEY `MaHoaDon` (`MaHoaDon`),
  CONSTRAINT `phieudatphong_ibfk_1` FOREIGN KEY (`MaPhong`) REFERENCES `phong` (`MaPhong`),
  CONSTRAINT `phieudatphong_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`),
  CONSTRAINT `phieudatphong_ibfk_3` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`),
  CONSTRAINT `phieudatphong_ibfk_4` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieudatphong`
--

LOCK TABLES `phieudatphong` WRITE;
/*!40000 ALTER TABLE `phieudatphong` DISABLE KEYS */;
INSERT INTO `phieudatphong` VALUES ('PDP001','P101','NV001','KH001','HD001','2025-03-01','2025-03-05'),('PDP002','P102','NV002','KH002','HD002','2025-03-02','2025-03-06'),('PDP003','P103','NV003','KH003','HD003','2025-03-03','2025-03-07');
/*!40000 ALTER TABLE `phieudatphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieusddv`
--

DROP TABLE IF EXISTS `phieusddv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieusddv` (
  `MaPhieuSDDV` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaKhachHang` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaNhanVien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `NgaySDDV` date NOT NULL,
  PRIMARY KEY (`MaPhieuSDDV`),
  KEY `MaKhachHang` (`MaKhachHang`),
  KEY `MaNhanVien` (`MaNhanVien`),
  CONSTRAINT `phieusddv_ibfk_1` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`),
  CONSTRAINT `phieusddv_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieusddv`
--

LOCK TABLES `phieusddv` WRITE;
/*!40000 ALTER TABLE `phieusddv` DISABLE KEYS */;
INSERT INTO `phieusddv` VALUES ('PSDDV001','KH001','NV001','2025-03-01'),('PSDDV002','KH002','NV002','2025-03-02'),('PSDDV003','KH003','NV003','2025-03-03');
/*!40000 ALTER TABLE `phieusddv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong` (
  `MaPhong` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TenPhong` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaLoai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MoTa` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `TinhTrangPhong` enum('Trống','Đang sử dụng','Đã đặt trước','Bảo trì') NOT NULL,
  PRIMARY KEY (`MaPhong`),
  KEY `MaLoai` (`MaLoai`),
  CONSTRAINT `phong_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaiphong` (`Maloai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Phòng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
INSERT INTO `phong` VALUES ('P101','Phòng 101','LP001','Hướng biển','Trống'),('P102','Phòng 102','LP002','Có ban công','Đang sử dụng'),('P103','Phòng 103','LP003','Gần hồ bơi','Đã đặt trước'),('P104','Phòng 104','LP001','VIP view đẹp','Trống'),('P105','Phòng 105','LP002','Có cửa sổ','Bảo trì'),('P106','Phòng 106','LP003','Hướng núi','Trống'),('P107','Phòng 107','LP001','VIP rộng rãi','Đã đặt trước'),('P108','Phòng 108','LP002','Có bồn tắm','Đang sử dụng'),('P109','Phòng 109','LP003','Gần phòng gym','Trống'),('P110','Phòng 110','LP001','Siêu VIP','Trống');
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-04  0:55:46
