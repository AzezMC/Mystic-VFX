# Mystic VFX 🌌

**A High-Performance, Data-Driven Visual Effects Library for Minecraft Fabric 1.20.1.**

## 📖 Giới thiệu

**Mystic VFX** là thư viện hỗ trợ render đồ họa nâng cao, được thiết kế tối ưu để tạo ra các hiệu ứng kỹ năng hoành tráng (tu tiên, ma pháp, tận thế, v.v.). Nhằm vượt qua giới hạn render mặc định của Minecraft, thư viện mang đến cấu trúc xử lý linh hoạt, lấy cảm hứng từ hệ thống VFX của các Game Engine chuyên nghiệp.

## ✨ Tính năng nổi bật

* **Render Đa Tầng (Composition):** Kết hợp đồng thời nhiều dạng đồ họa (Decal, Mesh, Trail, Billboard) vào chung một hiệu ứng.
* **Custom Shader & Cường độ sáng:** Tích hợp sẵn `VFXRenderTypes` với tính năng cộng dồn màu (Additive Blending), giúp hiệu ứng rực rỡ độc lập với ánh sáng môi trường.
* **Hệ Thống Trail & Ribbon:** Nội suy tọa độ để tạo các vệt chém vũ khí và luồng năng lượng mượt mà.
* **Data-Driven (Đang phát triển):** Quản lý Timeline, Keyframe, và thông số vật lý của hạt 100% qua file cấu hình `.json`.
* **Tối ưu Hiệu suất:** Vòng đời hiệu ứng được tự động kiểm soát chặt chẽ bởi `VFXManager`, chống rò rỉ bộ nhớ (Memory Leak).

## 🛠️ Cấu trúc Cốt lõi

Thư viện được xây dựng dựa trên 3 module nền tảng:

1. `VFXManager`: Class trung tâm xử lý logic (`tick`) và gọi lệnh vẽ (`render`) cho toàn bộ hiệu ứng trên Client.
2. `VFXInstance`: Class trừu tượng cơ sở, mọi hiệu ứng custom sẽ kế thừa từ đây để định nghĩa vòng đời (Age) và hình dạng.
3. `VFXRenderTypes`: Lưu trữ các định dạng RenderLayer chuyên dụng (như bỏ culling, vô hiệu hóa lightmap).

## 📱 Social
* Facebook: https://www.facebook.com/imquangsigmaboy
* Youtube: https://www.youtube.com/@azezmc
* Tiktok: https://www.tiktok.com/@quang.dap.da
* Hoặc tất cả thông tin ở trong này ^_^: https://linktr.ee/azez.info

## 🚀 Cài đặt (Dành cho Developer)

Thêm vào `build.gradle` của bạn:

```gradle
repositories {
    // Thêm repository của Mystic VFX tại đây
}

dependencies {
    modImplementation "net.azezmc:mysticvfx:${project.mysticvfx_version}"
}
```