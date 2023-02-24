<h1>Начальный экран</h1>
При запуске приложения появляется экран со всеми возможными средствами защиты(RecyclerView)

![Screenshot_20230224_131957_BnetTestDudnikov](https://user-images.githubusercontent.com/86302070/221155334-84832f3e-ee60-47ed-bd7c-f34feb6b3e3a.jpg)

При отсутствии подключения выводится соответствующее сообщение, а также отключается возможность поиска.

![Screenshot_20230224_132050_BnetTestDudnikov](https://user-images.githubusercontent.com/86302070/221156516-943ffba8-098e-49d2-9e0b-8fc59fd08af3.jpg)
Поиск реализован следующим образом. После того, как пользователь нажимает на лупу во второй раз(если текст не пустой), отправляется запрос на сервер. При переходе к карточке и возвращении на главный экран, подгружаются данные всех товаров(не тех, которые искал пользователь).

![Screenshot_20230224_132013_BnetTestDudnikov](https://user-images.githubusercontent.com/86302070/221158148-072c098e-a837-4ffe-a85b-afa544c86dba.jpg)

При при нажатии на элеменет списка, происходит переход к карточке с экраном товара(вертикальное и горизонтальное положение).

![Screenshot_20230224_132018_BnetTestDudnikov](https://user-images.githubusercontent.com/86302070/221157941-b2c4f180-dedf-4516-93d9-2598679c43b6.jpg)

![Screenshot_20230224_132037_BnetTestDudnikov](https://user-images.githubusercontent.com/86302070/221157981-fbd57708-db26-4e2c-9d5d-0ffd58249140.jpg)

При нажатии на "Где купить?" открывается браузер с сайтом партнёра.

![Screenshot_20230224_132028_Browser](https://user-images.githubusercontent.com/86302070/221158681-e472786a-62f6-4640-8dd2-b616b5e1141a.jpg)
