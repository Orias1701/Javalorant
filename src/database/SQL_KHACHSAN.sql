USE ql_khachsan;
-- Bảng LoaiPhong
CREATE TABLE LoaiPhong (
    Maloai NVARCHAR(50) PRIMARY KEY,
    TenLoaiPhong NVARCHAR(100) NOT NULL CHECK (TenLoaiPhong IN ('VIP','Đơn','Đôi')),
    GiaLoai DECIMAL(10,2) NOT NULL
);

-- Bảng Phong
CREATE TABLE Phong (
    MaPhong NVARCHAR(50) PRIMARY KEY,
    TenPhong NVARCHAR(100) NOT NULL,
    MaLoai NVARCHAR(50) NOT NULL,
    MoTa NVARCHAR(50),
    TinhTrangPhong ENUM('Trống','Đang sử dụng', 'Đã đặt trước','Bảo trì') NOT NULL, 
    FOREIGN KEY (MaLoai) REFERENCES LoaiPhong(MaLoai)
);

-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKhachHang NVARCHAR(50) PRIMARY KEY,
    TenKhachHang NVARCHAR(100) NOT NULL,
    Sdt VARCHAR(15) NOT NULL UNIQUE,
    TinhTrangKhach ENUM('Đang ở', 'Đã rời đi', 'Hủy đặt phòng') NOT NULL
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    MaNhanVien NVARCHAR(50) PRIMARY KEY,
    TenNhanVien NVARCHAR(100) NOT NULL,
    Sdt VARCHAR(15) NOT NULL UNIQUE,
    GioiTinh ENUM('Nam', 'Nữ') NOT NULL
);

-- Bảng HoaDon
CREATE TABLE HoaDon (
    MaHoaDon NVARCHAR(50) PRIMARY KEY,
    MaNhanVien NVARCHAR(50) NOT NULL,
    MaKhachHang NVARCHAR(50) NOT NULL,
    Ngay DATE NOT NULL,
    -- TongTien DECIMAL(15,2) NOT NULL DEFAULT 0,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
);

-- Bảng PhieuDatPhong
CREATE TABLE PhieuDatPhong (
    MaPhieuDatPhong NVARCHAR(50) PRIMARY KEY,
    MaPhong NVARCHAR(50) NOT NULL,
    MaNhanVien NVARCHAR(50) NOT NULL,
    MaKhachHang NVARCHAR(50) NOT NULL,
    MaHoaDon NVARCHAR(50),
    NgayNhanPhong DATE NOT NULL,
    NgayTraPhong DATE NOT NULL,
    FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon)
);

-- Bảng DichVu
CREATE TABLE DichVu (
    MaDichVu NVARCHAR(50) PRIMARY KEY,
    LoaiDichVu NVARCHAR(100) NOT NULL CHECK (LoaiDichVu IN ('Ăn uống', 'Giặt ủi', 'Spa', 'Thuê xe', 'Công viên', 'Massage', 'Bể bơi')),
    GiaDichVu DECIMAL(10,2) NOT NULL
);

-- Bảng PhieuSDDV
CREATE TABLE PhieuSDDV (
    MaPhieuSDDV NVARCHAR(50) PRIMARY KEY,
    MaKhachHang NVARCHAR(50) NOT NULL,
    MaNhanVien NVARCHAR(50) NOT NULL,
    NgaySDDV DATE NOT NULL,
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

-- Bảng ChiTietPhieuSDDV
CREATE TABLE ChiTietPhieuSDDV (
    MaPhieuSDDV NVARCHAR(50) NOT NULL,
    MaDichVu NVARCHAR(50) NOT NULL,
    SoLuongDichVu INT NOT NULL DEFAULT 1,
   -- TongTienDV DECIMAL(15,2) NOT NULL DEFAULT 0, 
    PRIMARY KEY (MaPhieuSDDV, MaDichVu),
    FOREIGN KEY (MaPhieuSDDV) REFERENCES PhieuSDDV(MaPhieuSDDV),
    FOREIGN KEY (MaDichVu) REFERENCES DichVu(MaDichVu)
);

