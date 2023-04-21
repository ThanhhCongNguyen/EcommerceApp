package com.example.ecommerceapp.utils;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Review;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class AddData {
    private FirebaseFirestore db;

    public AddData() {
        db = FirebaseFirestore.getInstance();
    }

    public void addProductToFirestore() {
        for (int i = 130; i < 140; i++) {
            if (i == 130) {
                String categoryName = "HangingGardens";
                String description = "Cây mắt huyền là loài cây dạng dây leo trồng chậu treo trang trí hàng hiên trước nhà, trang trí ban công, sân thượng,…hay trồng hàng rào, trồng trước cổng nhà…";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/mat_huyen3.jpg?alt=media&token=251db3e9-dfd9-445e-b7d7-a744e6645014";
                String price = "295000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Mắt Huyền";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 131) {
                String categoryName = "HangingGardens";
                String description = "Cây Chậu Treo Lan Tim hay còn có tên gọi khác là cây Khúc Thủy, là loại cây chậu treo trang trí ban công, sân nhà rất đẹp, đây sẽ là món quà ý nghĩa để bạn dành tặng cho những người thương yêu.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-lan-tim-2.jpg?alt=media&token=89264fcd-d41c-4aef-adaa-d46ac29d01ba";
                String price = "219000";
                String productId = String.valueOf(i);
                String productName = "Cây chậu treo Lan Tim";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 132) {
                String categoryName = "HangingGardens";
                String description = "Chậu treo Hoa dừa cạn còn có tên tiếng Việt là hoa hải đăng, trường xuân hoa, bông dừa, dương giác, nhật nhật thảo, hoa đồng hồ… Hoa dừa cạn có cánh đơn, mỏng. Có nhiều mầu sắc như trắng, tím, hồng, đỏ.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-hoa-dua-can-chau-treo-6.jpg?alt=media&token=a7cbf634-0dda-450c-9c2f-2d57421af686";
                String price = "222000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Dừa Cạn nhiều màu sắc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 133) {
                String categoryName = "HangingGardens";
                String description = "Cây Chậu Treo Lan Đồng Tiền:  Lá cây có hình dạng tròn như đồng tiền, đi kèm với những đường hoa văn màu xanh mạ non đi dọc theo lá, mang đến cho cây một vẻ đẹp mộc mạc quyến rũ. Lan Đồng Tiền thường được treo ở ban công, cửa sổ,.. giúp tô điểm thêm nét xanh mát cho không gian sống và làm việc";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/lan-dong-tien-3.jpg?alt=media&token=517999ff-3f44-45a3-9b93-415aa6f50b5b";
                String price = "249000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Lan Đồng Tiền";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 134) {
                String categoryName = "HangingGardens";
                String description = "Cây Hoa Mười Giờ là loài hoa đẹp, có nhiều màu sắc, dễ trồng và chăm sóc, được nhiều người tìm mua, cây thường được trồng làm chậu hoa treo hoặc trồng trong bồn cây, hoa viên sân vườn,…";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/hoa-muoi-gio-chau-treo_hoa-muoi-gio-chau-treo-2.jpg?alt=media&token=9d1fb4b4-953c-4051-9e11-15e6de71c92c";
                String price = "225000";
                String productId = String.valueOf(i);
                String productName = "Cây chậu treo Hoa Mười Giờ";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 135) {
                String categoryName = "HangingGardens";
                String description = "Chậu treo Hoa dừa cạn còn có tên tiếng Việt là hoa hải đăng, trường xuân hoa, bông dừa, dương giác, nhật nhật thảo, hoa đồng hồ… Hoa dừa cạn có cánh đơn, mỏng. Có nhiều mầu sắc như trắng, tím, hồng, đỏ.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-hoa-dua-can-chau-treo-6.jpg?alt=media&token=a7cbf634-0dda-450c-9c2f-2d57421af686";
                String price = "222000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Dừa Cạn nhiều màu sắc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 136) {
                String categoryName = "HangingGardens";
                String description = "Chậu treo Hoa dừa cạn còn có tên tiếng Việt là hoa hải đăng, trường xuân hoa, bông dừa, dương giác, nhật nhật thảo, hoa đồng hồ… Hoa dừa cạn có cánh đơn, mỏng. Có nhiều mầu sắc như trắng, tím, hồng, đỏ.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-hoa-dua-can-chau-treo-6.jpg?alt=media&token=a7cbf634-0dda-450c-9c2f-2d57421af686";
                String price = "222000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Dừa Cạn nhiều màu sắc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            } else if (i == 137) {
                String categoryName = "HangingGardens";
                String description = "Chậu treo Hoa dừa cạn còn có tên tiếng Việt là hoa hải đăng, trường xuân hoa, bông dừa, dương giác, nhật nhật thảo, hoa đồng hồ… Hoa dừa cạn có cánh đơn, mỏng. Có nhiều mầu sắc như trắng, tím, hồng, đỏ.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-hoa-dua-can-chau-treo-6.jpg?alt=media&token=a7cbf634-0dda-450c-9c2f-2d57421af686";
                String price = "222000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Dừa Cạn nhiều màu sắc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("HangingGardens").document(String.valueOf(i)).set(product);
            }
        }
    }

    public void addProductToFirestore1() {
        for (int i = 110; i < 120; i++) {
            if (i == 110) {
                String categoryName = "DesktopPlants";
                String description = "Cây lan ý thái để bàn là loài cây có hoa đẹp màu trắng tinh khôi thường được nhiều người chọn trồng trong chậu để bàn hậu đứng để trong văn phòng, trang trí hành lang, chân cầu thang hoặc làm cây cảnh nội ngoại thất bằng cách trồng chúng dưới những bóng râm hoặc trồng làm hàng rào cây xanh.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-lan-y-d%C4%A9-an-b%C3%ACnh-d%C6%B0%C6%A1ng2.png?alt=media&token=024f1499-d04e-4b4f-b7cc-c5e3bb25319e";
                String price = "229000";
                String productId = String.valueOf(i);
                String productName = "Cây Lan Ý Thái Để Bàn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            } else if (i == 111) {
                String categoryName = "DesktopPlants";
                String description = "Cây hoa hồng môn là loài cây có hoa lạ mắt và được sử dụng nhiều trong các phòng khách, quầy lễ tân hay các nơi đông người có nhiều người thường xuyên qua lại hoặc các bạn có thể trang trí nội thất bên trong đều rất vừa ý.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-hong-mon-de-ban-gia-re-hcm-2a.jpg?alt=media&token=9f1a285f-885f-4c91-b221-6bef2816767e";
                String price = "279000";
                String productId = String.valueOf(i);
                String productName = "Cây Hoa Hồng Môn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            } else if (i == 112) {
                String categoryName = "DesktopPlants";
                String description = "Cây dứa cảnh nến là loài cây thân thảo có chiều cao khiêm tốn và dường như ít khi nào có chiều cao đến 50 cm.\n" +
                        ".Các bạn trẻ trồng cây trong chậu để bàn hoặc chậu đứng trang trí cho nội thất văn phòng, nhà ở…";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/c%C3%A2y-d%E1%BB%A9a-c%E1%BA%A3nh-n%E1%BA%BFn.jpg?alt=media&token=be677747-c796-4edc-995a-ac3a25d44bed";
                String price = "229000";
                String productId = String.valueOf(i);
                String productName = "Cây dứa cảnh nến";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            } else if (i == 113) {
                String categoryName = "DesktopPlants";
                String description = "Cây kim ngân phù hợp với gia chủ muốn mua cây đặt ở phòng khách, phòng hội họp, văn phòng công sở, nhà hàng, khách sạn, hoặc dùng làm quà tặng trong những dịp mừng lễ, tết, thăng chức, khai trương";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/download.jpg?alt=media&token=06d1845a-1042-44fe-86ca-504c88867f35";
                String price = "229000";
                String productId = String.valueOf(i);
                String productName = "Cây kim ngân củ để bàn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            } else if (i == 114) {
                String categoryName = "DesktopPlants";
                String description = "";
                String image = "";
                String price = "229000";
                String productId = String.valueOf(i);
                String productName = "Cây Chậu Treo Dừa Cạn nhiều màu sắc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            } else if (i == 115) {
                String categoryName = "DesktopPlants";
                String description = "Cây Tùng Thơm là cây lá kim thường được trồng trang trí trên bàn làm việc, hay trồng trong sân vườn tạo cảnh quan rất đẹp, ngoài ra cây còn có tác dụng đuổi muỗi và các loại côn trùng khác, bởi mùi thơm mà cây tạo ra.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/download%20(2).jpg?alt=media&token=a8a7b1ee-7ddc-42b3-a323-7dc7996be9f1";
                String price = "219000";
                String productId = String.valueOf(i);
                String productName = "Cây Tùng Thơm để bàn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            } else {
                String categoryName = "DesktopPlants";
                String description = "Cây dứa cảnh nến là loài cây thân thảo có chiều cao khiêm tốn và dường như ít khi nào có chiều cao đến 50 cm.\n" +
                        ".Các bạn trẻ trồng cây trong chậu để bàn hoặc chậu đứng trang trí cho nội thất văn phòng, nhà ở…";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/c%C3%A2y-d%E1%BB%A9a-c%E1%BA%A3nh-n%E1%BA%BFn.jpg?alt=media&token=be677747-c796-4edc-995a-ac3a25d44bed";
                String price = "229000";
                String productId = String.valueOf(i);
                String productName = "Cây dứa cảnh nến";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("DesktopPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("DesktopPlants").document(String.valueOf(i)).set(product);
            }
        }
    }

    public void addProductToFirestore2() {
        for (int i = 120; i < 130; i++) {
            if (i == 120) {
                String categoryName = "WaterPlants";
                String description = "Cây phát tài búp sen mang lại sự phát tài cho gia chủ. Đây cũng là loài cây mang ý nghĩa tài lộc may mắn và thành công và giống Búp Sen tượng trưng cho sự thanh khiết, thánh thiện đến với gia chủ mệnh kim, mệnh mộc.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-ngu-gia-bi-1.jpg?alt=media&token=28016717-c977-4af8-9fb2-27b79190e223";
                String price = "179000";
                String productId = String.valueOf(i);
                String productName = "Cây Phát Tài Búp Sen";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            } else if (i == 121) {
                String categoryName = "WaterPlants";
                String description = "Nuôi dưỡng tâm hồn để cuộc sống hướng thiện, bình yên và hạnh phúc là ý nghĩa của cây Thanh Tâm. Thích hợp trưng bày nơi thờ cúng và là món quà sâu sắc đối với người theo đạo .";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/C%C3%A2y-thanh-t%C3%A2m-2.jpg?alt=media&token=9400aabb-2b2f-4bd0-9699-0237c6313c6b";
                String price = "229000";
                String productId = String.valueOf(i);
                String productName = "Cây Thanh Tâm";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            } else if (i == 122) {
                String categoryName = "WaterPlants";
                String description = "Cây Ngọc ngân là loại cây dành cho tình yêu! Đây là món quà bất ngờ để bạn tặng “người ấy”. Ngọc ngân (Valentine) không chỉ đẹp ở phiến lá xanh đốm trắng mà bộ rễ mạnh khỏe xanh tươi đem lại sự hài hòa tuyệt đẹp.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-ngoc-ngan.jpg?alt=media&token=2d463b0c-5537-47a5-89fd-ab186bdd53ab";
                String price = "250000";
                String productId = String.valueOf(i);
                String productName = "Cây Ngọc Ngân";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            } else if (i == 123) {
                String categoryName = "WaterPlants";
                String description = "Cây Bao Thanh Thiên Mang ý nghĩa quý phái và sang trọng, là cây để bàn với ý nghĩa như một lời chúc phúc cho cá nhân hay doanh nghiệp luôn được thịnh vượng, phát tài.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/dac-diem-cay-bao-thanh-thien.jpg?alt=media&token=30423723-1b49-4aec-88c8-b3a88ddf665c";
                String price = "290000";
                String productId = String.valueOf(i);
                String productName = "Cây Bao Thanh Thiên";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            } else if (i == 124) {
                String categoryName = "WaterPlants";
                String description = "Cây Vạn Lộc theo cha ông xưa thì Lộc ứng với Tài lộc, thêm hoa của cây màu đỏ và rất đẹp mang ý nghĩa là sự thịnh vượng , phát lộc. Cây không chỉ là vật trang trí mà mang Tài lộc vào nhà của bạn nhờ cái tên may mắn”Vạn Lộc”,";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-van-loc-trong-dat.jpg?alt=media&token=0c6d1901-d973-41c7-b22d-14cfb3c9529b";
                String price = "350000";
                String productId = String.valueOf(i);
                String productName = "Cây Vạn Lộc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            } else if (i == 125) {
                String categoryName = "WaterPlants";
                String description = "Cây trầu bà giúp có khả năng hút được khí độc từ máy vi tính, loại bỏ chất gây ung thư formaldehydes và nhiều chất hóa học dễ bay hơi khác.Thích hợp để trồng trang trí trong văn phòng, nhà hàng – khách sạn, tại nhà…hoặc môi trường có nhiều tiếng ồn.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/trau-ba-thuy-sinh-2.jpg?alt=media&token=08ab74c3-d446-401f-8f99-db98235eb140";
                String price = "179000";
                String productId = String.valueOf(i);
                String productName = "Cây Trầu Bà Trong Nước";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            } else {
                String categoryName = "WaterPlants";
                String description = "Cây trầu bà giúp có khả năng hút được khí độc từ máy vi tính, loại bỏ chất gây ung thư formaldehydes và nhiều chất hóa học dễ bay hơi khác.Thích hợp để trồng trang trí trong văn phòng, nhà hàng – khách sạn, tại nhà…hoặc môi trường có nhiều tiếng ồn.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/trau-ba-thuy-sinh-2.jpg?alt=media&token=08ab74c3-d446-401f-8f99-db98235eb140";
                String price = "179000";
                String productId = String.valueOf(i);
                String productName = "Cây Trầu Bà Trong Nước";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("WaterPlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("WaterPlants").document(String.valueOf(i)).set(product);
            }
        }
    }

    public void addProductToFirestor3() {
        for (int i = 100; i < 110; i++) {
            if (i == 100) {
                String categoryName = "OfficePlants";
                String description = "Cây Thanh Lam là một loại cây thân thảo thuộc họ ráy. Cây thường mọc thành bụi lớn và có tán lá rộng. Thân và lá của cây mọc vươn thẳng thường có chiều cao từ 30 đến 80cm.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/download%20(1).jpg?alt=media&token=37e32139-91f4-4828-9d3f-d0a1ea6a530f";
                String price = "690000";
                String productId = String.valueOf(i);
                String productName = "Cây Thanh Lam Lớn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            } else if (i == 101) {
                String categoryName = "OfficePlants";
                String description = "Cây Hạnh Phúc còn là một biểu tượng thể hiện niềm hạnh phúc, sự ấm êm trong gia đình. Mỗi lúc rảnh rỗi chỉ cần ngắm cây là mọi thứ dường như bình yên trong lòng.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cach-trong-va-nhung-loi-ich-bat-ngo-cua-cay-hanh-phuc.jpg?alt=media&token=842e1ffa-b142-454b-b3e7-781f04ca4618";
                String price = "790000";
                String productId = String.valueOf(i);
                String productName = "Cây Hạnh Phúc";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            } else if (i == 103) {
                String categoryName = "OfficePlants";
                String description = "Cây Trúc bách hợp là một loại cây nội thất với vẻ đẹp tự nhiên và thanh thoát; không chỉ làm đẹp không gian nhà bạn mà trong phong thủy còn mang đến cho gia chủ nhiều may mắn bình an";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-truc-bach-hop-4.jpg?alt=media&token=e745e5a2-f01a-48a0-96cc-671d6fb11c8f";
                String price = "590000";
                String productId = String.valueOf(i);
                String productName = "Cây Trúc Bách Hợp";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            } else if (i == 104) {
                String categoryName = "OfficePlants";
                String description = "Cây bạch mã hoàng tử là loại cây thân thảo mọc thành bụi, mang dáng vóc của một chàng trai lịch lãm và phong độ, ngoài ra cây còn có nghĩa là tiến nhanh, thuận buồm xuôi gió trong công việc cũng như trong cuộc sống. Cây có tác dụng thanh lọc và điều hòa không khí, tốt cho sức khỏe";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/la-cay-bach-ma-hoang-tu-co-soc-trang-rat-dac-trung-anh-minh-hoa-7f69db1beb9b48f690f934b0d3ee6730.jpg?alt=media&token=0daf3d7f-0f9b-45ae-94b9-a041b8043928";
                String price = "340000";
                String productId = String.valueOf(i);
                String productName = "Cây bạch mã hoàng tử Trung";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            } else if (i == 105) {
                String categoryName = "OfficePlants";
                String description = "Cây kim tiền hay còn gọi là cây kim phát tài, phát tài thể hiện sự phú quý, giàu sang và tiền bạc. Cây Kim Tiền là một trong những loại cây may mắn nhất trong phong thủy. Lúc cây kim tiền nở hoa sẽ mang lại nhiều lộc và may mắn nhất.";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/156821324-1184334941997969-7778308532895942691-n.jpg?alt=media&token=4c1e6b89-70a2-40b3-89ee-69f31af522ed";
                String price = "949000";
                String productId = String.valueOf(i);
                String productName = "Cây Kim Tiền Lớn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            } else if (i == 106) {
                String categoryName = "OfficePlants";
                String description = "Cây hoa đại hồng môn là loài cây vừa có hoa đẹp mà thân cây cũng đẹp cho nên chúng thường được trồng trong các chậu để trang trí nội thất cho văn phòng, nhà ở…";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/Cach-cham-soc-hoa-dai-hong-mon-no-dep-ban-nen-biet-2.jpg?alt=media&token=55d5b9c8-3e20-40d0-9500-ec260977df6d";
                String price = "799000";
                String productId = String.valueOf(i);
                String productName = "Cây Hoa Đại Hồng Môn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            } else {
                String categoryName = "OfficePlants";
                String description = "Cây hoa đại hồng môn là loài cây vừa có hoa đẹp mà thân cây cũng đẹp cho nên chúng thường được trồng trong các chậu để trang trí nội thất cho văn phòng, nhà ở…";
                String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/Cach-cham-soc-hoa-dai-hong-mon-no-dep-ban-nen-biet-2.jpg?alt=media&token=55d5b9c8-3e20-40d0-9500-ec260977df6d";
                String price = "799000";
                String productId = String.valueOf(i);
                String productName = "Cây Hoa Đại Hồng Môn";
                Product product = new Product(productId, productName, price, image, description, categoryName);
                Review revieww = null;

                for (int j = 0; j < 5; j++) {
                    String userId = String.valueOf(j + 810);
                    String productIdd = productId;
                    String time = String.valueOf(Calendar.getInstance().getTime());
                    String review = "Very good";
                    String rating = "5";
                    revieww = new Review(userId, productIdd, time, review, rating);
                    db.collection("OfficePlants").document(String.valueOf(i)).collection("reviews").document().set(revieww);
                }
                db.collection("OfficePlants").document(String.valueOf(i)).set(product);
            }
        }
    }
}
