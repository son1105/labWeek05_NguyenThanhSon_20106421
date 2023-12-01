# labWeek05_NguyenThanhSon_20106421
project week05

- Khi khởi chạy chương trình sẽ tự động tạo database và insert dữ liệu mẫu vào
- Vào trang chủ để thực hiện đăng nhập: http://localhost:8080/
- Sau khi vào trang chủ, chọn Go To Log In để thực hiện đăng nhập.
- Đây là trang giả lập việc đăng nhập, bạn chỉ cần nhập Candidate Id vào là có thể đăng nhập được, nếu nhập sai sẽ báo lỗi, nhập đúng sẽ tiến đến trang hiển thị danh sách Company
  ![Screenshot (69)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/78b545d8-8895-40f8-ad49-33dcfc56f77b)
  ![Screenshot (70)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/159698ab-edb0-48c5-9742-8c51e6e1604e)
  
- Có thể xem danh sách các company bằng đường dẫn: http://localhost:8080/companies?size=10&page=1
  + Mỗi company sẽ có các Job khác nhau, có thể xem thông tin Job của Company bằng cách nhấn vào nút View
    ![Screenshot (71)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/8464a919-b2eb-4ea2-9109-e39d43c56991)
  + Trang Job của Company vừa chọn sẽ hiển thị ra, có thể chọn xem Skill của Job đó bằng cách nhấn vào nút Edit bên cạnh
    ![Screenshot (11)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/c26f243b-9126-4a4c-87b9-cd189d8d7ec7)
  + Sau khi vào trang Skill, sẽ hiển thị danh sách các Skill phù hợp với Job của Company đó
    ![Screenshot (12)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/41216cc3-5dab-4c2b-ab8f-d03704af31e3)
  + Có thể thêm mới một Skill vào Job này bằng cách nhấn vào nút Insert, điền thông tin và Add Skill
    ![Screenshot (13)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/6ac1c156-9a83-43d2-95e9-ebf760761634)
  + Có thể xoá một Skill trong Job đó bằng cách nhấn vào nút Delete.
  + Trong trang Company có nút User để thực hiện chuyển sang trang hiển thị thông tin của user đã đăng nhập.
    ![Screenshot (71)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/a5ece5e0-a020-47fa-9704-aac115a7d2e2)
  + Trong trang user, click vào View Job For You để tìm kiếm các công việc có skill và skill level phù hợp với bản thân(hệ thống sẽ tự động tìm kiếm skill và skill level của Candidate trong bảng CandidateSkill sau đó tìm trong bảng JobSkill có các skill và skill level phù hợp rồi hiển thị danh sách các job cho nhân viên xem).
    ![Screenshot (72)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/ccac307e-4740-40e6-a3b0-4669c2eb9c04)
    ![Screenshot (73)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/ef14e22c-cf46-4f01-a924-c630d92eabfd)

- Đường dẫn đến trang candidate hiển thị danh sách các candidate: http://localhost:8080/candidates?size=10&page=1
  ![Screenshot (7)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/eb8df129-596f-4792-9d00-9c8787595728)
- Có thể thêm mới 1 candidate bằng cách nhấn vào nút Insert góc trên bên trái màn hình, sau đó điền thông tin và Add Candidate.
  ![Screenshot (8)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/9c177983-325d-4424-97b3-01a29f15f718)
- Có thể sửa thông tin candidate bằng cách nhấn nút Edit bên cạnh, điền thông tin và Update Candidate
  ![Screenshot (9)](https://github.com/son1105/labWeek05_NguyenThanhSon_20106421/assets/115455297/1390434f-be71-486a-aa82-af3c3a04d7db)


