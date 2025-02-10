Telegram Clone - Spring Boot Backend

Bu loyiha Telegram kabi chat platformasi bo‘lib, Spring Boot va boshqa zamonaviy backend texnologiyalaridan foydalangan holda yaratilgan. Foydalanuvchilar autentifikatsiya qilinishi, real vaqtda xabar almashishi va profilini boshqarishi mumkin.

🚀 Texnologiyalar

Spring Boot - Backend framework

Spring Security & JWT - Autentifikatsiya va avtorizatsiya

PostgreSQL - Ma'lumotlar bazasi

Local Storage - Fayllarni yuklash

Thymeleaf - Server tomonida renderlash

Frontend: HTML & JavaScript (Thymeleaf UI asosida)

⚙ O‘rnatish va Ishga Tushirish

Talablar

Quyidagi dasturlar o‘rnatilgan bo‘lishi kerak:

Java 17+

Maven

PostgreSQL (localhost:5432 da ishlashi kerak)

Loyihani klonlash

 git clone https://github.com/Fazliddin002/telegram-clone.git
 cd telegram-clone

Ma'lumotlar bazasini sozlash

application.properties faylini yangilang yoki muhit o‘zgaruvchilari orqali sozlang:

spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.datasource.url=jdbc:postgresql://localhost:5432/telegram

Loyihani ishga tushirish

mvn spring-boot:run

Tizim http://localhost:8080 da ishlaydi 🚀

📌 API Endpoints

1. Autentifikatsiya va Foydalanuvchi Boshqaruvi

POST /auth/register – Yangi foydalanuvchi ro‘yxatdan o‘tadi

POST /auth/login – JWT token olish uchun login qilish

GET /users – Barcha foydalanuvchilar ro‘yxati

2. Chat va Xabarlar

GET /chats/{id} – Berilgan ID bo‘yicha chat xabarlarini olish

POST /messages – Yangi xabar jo‘natish

🔒 Xavfsizlik

JWT Authentication orqali endpointlar himoyalangan

Ma'lumotlar maxfiyligi uchun .env yoki application.properties orqali parollar saqlanishi kerak

Fayllarni yuklash funksiyasi xavfsizlik choralari bilan ta’minlangan

💡 Kelajakdagi Takomillashtirishlar

WebSocket qo‘shish orqali real vaqt rejimida chat qilish

Frontend React yoki Vue.js kabi zamonaviy framework yordamida qayta yozish

Cloud Deploy – AWS yoki GCP ga joylashtirish

💬 Taklif va fikrlar uchun ochiqmiz! 😊

