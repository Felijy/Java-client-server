package GUI;

public class Lang {
    protected static String lang;

    public Lang() {
        lang = "en";
    }

    public static void setLang(String lang) {
        Lang.lang = lang;
    }

    public static class MainWindow {
        public MainWindow() {}

        public static String getLanguage() {
            return switch (lang) {
                case "ru" -> "Язык";
                case "po" -> "Linguagem";
                case "bo" -> "Eзик";
                case "es" -> "Idioma";
                default -> "Language";
            };
        }

        public static String getTitle() {
            return switch (lang) {
                case "ru" -> "Билеты";
                case "po" -> "Ingressos";
                case "bo" -> "Билети";
                case "es" -> "Entradas";
                default -> "Tickets";
            };
        }

        public static String getVisualization() {
            return switch (lang) {
                case "ru" -> "Визуализация";
                case "po" -> "Visualização";
                case "bo" -> "Визуализация";
                case "es" -> "Visualización";
                default -> "Visualization";
            };
        }

        public static String getExit() {
            return switch (lang) {
                case "ru" -> "Выход";
                case "po" -> "Saída";
                case "bo" -> "Изход";
                case "es" -> "Salida";
                default -> "Exit";
            };
        }

        public static String getUser() {
            return switch (lang) {
                case "ru" -> "Текущий пользователь: ";
                case "po" -> "Usuário atual: ";
                case "bo" -> "Текущия потребител: ";
                case "es" -> "Usuario actual: ";
                default -> "Current User: ";
            };
        }

        public static String getSort() {
            return switch (lang) {
                case "ru" -> "Сортировать по:";
                case "po" -> "Ordenar por:";
                case "bo" -> "Сортиране по:";
                case "es" -> "Ordenar por:";
                default -> "Sort by:";
            };
        }

        public static String getSortHow1() {
            return switch (lang) {
                case "ru" -> "По возрастанию";
                case "po" -> "Ascendente";
                case "bo" -> "Възходящ";
                case "es" -> "Ascendente";
                default -> "Ascending";
            };
        }

        public static String getSortHow2() {
            return switch (lang) {
                case "ru" -> "По убыванию";
                case "po" -> "Descendente";
                case "bo" -> "Спускане";
                case "es" -> "Descendente";
                default -> "Descending";
            };
        }

        public static String getFilter() {
            return switch (lang) {
                case "ru" -> "Фильтровать по:";
                case "po" -> "Filtrar por:";
                case "bo" -> "Филтриране по:";
                case "es" -> "Filtrado por:";
                default -> "Filter by:";
            };
        }

        public static String getInsert() {
            return switch (lang) {
                case "ru" -> "Вставка";
                case "po" -> "Inserir";
                case "bo" -> "Поставете";
                case "es" -> "Insertar";
                default -> "Insert";
            };
        }

        public static String getRefresh() {
            return switch (lang) {
                case "ru" -> "Обновить";
                case "po" -> "Atualizar";
                case "bo" -> "Актуализация";
                case "es" -> "Actualizar";
                default -> "Refresh";
            };
        }

        public static String getOther() {
            return switch (lang) {
                case "ru" -> "Другие команды";
                case "po" -> "Outros comandos";
                case "bo" -> "Други команди";
                case "es" -> "Otros comandos";
                default -> "Other commands";
            };
        }

        public static String getError() {
            return switch (lang) {
                case "ru" -> "Ошибка";
                case "po" -> "Erro";
                case "bo" -> "Грешка";
                case "es" -> "Error";
                default -> "Error";
            };
        }

        public static String getExitDone() {
            return switch (lang) {
                case "ru" -> "Выполнен выход";
                case "po" -> "Assinado";
                case "bo" -> "Отписан";
                case "es" -> "Cerró sesión";
                default -> "Exit completed";
            };
        }

        public static String getRefreshed() {
            return switch (lang) {
                case "ru" -> "Обновлено!";
                case "po" -> "Atualizada!";
                case "bo" -> "Актуализиран!";
                case "es" -> "Actualizado!";
                default -> "Refreshed!";
            };
        }
    }

    public static class InsertWindow {
        public InsertWindow() {}

        public static String getAddVenue() {
            return switch (lang) {
                case "ru" -> "Добавить место встречи?";
                case "po" -> "Adicionar local?";
                case "bo" -> "Добавяне на място?";
                case "es" -> "¿Agregar lugar?";
                default -> "Add Venue?";
            };
        }

        public static String getSuccessInsert() {
            return switch (lang) {
                case "ru" -> "Билет успешно добавлен!";
                case "po" -> "Bilhete adicionado com sucesso!";
                case "bo" -> "Билетът е добавен успешно!";
                case "es" -> "Boleto agregado exitosamente!";
                default -> "Successfully inserted ticket!";
            };
        }

        public static String getSuccess() {
            return switch (lang) {
                case "ru" -> "Успех";
                case "po" -> "Sucesso";
                case "bo" -> "Успех";
                case "es" -> "Éxito";
                default -> "Success";
            };
        }

        public static String getIncorrectInput() {
            return switch (lang) {
                case "ru" -> "Некорректный ввод!";
                case "po" -> "Entrada inválida!";
                case "bo" -> "Невалиден вход!";
                case "es" -> "¡Entrada inválida!";
                default -> "Incorrect input!";
            };
        }

        public static String getIncorrectKey() {
            return switch (lang) {
                case "ru" -> "Ошибка при вставке билета! Неправильный ключ! Попробуйте другой ключ";
                case "po" -> "Erro ao inserir o ticket! Chave incorreta! Tente outra chave";
                case "bo" -> "Грешка при поставяне на билет! Неправилен ключ! Опитайте друг ключ";
                case "es" -> "¡Error al insertar el billete! ¡Clave incorrecta! Prueba con otra clave";
                default -> "Error inserting ticket! Incorrect key! Try another key";
            };
        }
    }

    public static class LoginWindow {
        public LoginWindow() {}

        public static String getWelcome() {
            return switch (lang) {
                case "ru" -> "Добро пожаловать!";
                case "po" -> "Bem-vindo!";
                case "bo" -> "Добре дошли!";
                case "es" -> "¡Bienvenido!";
                default -> "Welcome!";
            };
        }

        public static String getLogin() {
            return switch (lang) {
                case "ru" -> "Введите логин:";
                case "po" -> "Digite o login:";
                case "bo" -> "Въведете логин:";
                case "es" -> "Ingrese inicio de sesión:";
                default -> "Enter login:";
            };
        }

        public static String getNext() {
            return switch (lang) {
                case "ru" -> "Далее";
                case "po" -> "Próxima";
                case "bo" -> "Следващия";
                case "es" -> "Próxima";
                default -> "Next";
            };
        }

        public static String getTitle() {
            return switch (lang) {
                case "ru" -> "Авторизация";
                case "po" -> "Autorização";
                case "bo" -> "Упълномощаване";
                case "es" -> "Autorización";
                default -> "Authorisation";
            };
        }
    }

    public static class PasswordWindow {
        public PasswordWindow() {}

        public static String getExist() {
            return switch (lang) {
                case "ru" -> "Пользователь с таким логином найден!";
                case "po" -> "Um usuário com este login foi encontrado!";
                case "bo" -> "Намерен е потребител с това име!";
                case "es" -> "¡Se ha encontrado un usuario con este inicio de sesión!";
                default -> "User with this login has been found!";
            };
        }

        public static String getNotExist() {
            return switch (lang) {
                case "ru" -> "Пользователя с таким логином не существует. Создание нового пользователя.";
                case "po" -> "Não há nenhum usuário com este login. Criando um novo usuário.";
                case "bo" -> "Няма потребител с това влизане. Създаване на нов потребител.";
                case "es" -> "No hay ningún usuario con este inicio de sesión. Creando un nuevo usuario.";
                default -> "The user with this login does not exist. Create a new user.";
            };
        }

        public static String getEnterPass() {
            return switch (lang) {
                case "ru" -> "Введите пароль:";
                case "po" -> "Digite a senha:";
                case "bo" -> "Въведете паролата:";
                case "es" -> "Introducir la contraseña:";
                default -> "Enter password:";
            };
        }

        public static String getSignIn() {
            return switch (lang) {
                case "ru" -> "Войти";
                case "po" -> "Entrar";
                case "bo" -> "Да вляза";
                case "es" -> "Entrar";
                default -> "Sing in";
            };
        }

        public static String getSuccessfulSingIn() {
            return switch (lang) {
                case "ru" -> "Вход выполнен";
                case "po" -> "Login concluído";
                case "bo" -> "Входът е извършен";
                case "es" -> "Iniciar sesión hecho";
                default -> "Sing in completed";
            };
        }

        public static String getInvalidPass() {
            return switch (lang) {
                case "ru" -> "Пароль неверен";
                case "po" -> "Senha é incorreta";
                case "bo" -> "Паролата е неправилна";
                case "es" -> "La contraseña es incorrecta";
                default -> "Invalid password";
            };
        }

        public static String getNewPass() {
            return switch (lang) {
                case "ru" -> "Придумайте пароль:";
                case "po" -> "Criar uma senha:";
                case "bo" -> "Създай парола:";
                case "es" -> "Crear una contraseña:";
                default -> "Make up a password:";
            };
        }
    }

    public static class OtherWindow {
        public OtherWindow() {}

        public static String getClearTitle() {
            return switch (lang) {
                case "ru" -> "Очистить";
                case "po" -> "Clara";
                case "bo" -> "Ясно";
                case "es" -> "Clara";
                default -> "Clear";
            };
        }

        public static String getClearText() {
            return switch (lang) {
                case "ru" -> "Очищено";
                case "po" -> "Desmarcado";
                case "bo" -> "Изчистено";
                case "es" -> "Borrado";
                default -> "Cleared";
            };
        }

        public static String getEnterAnArgumentTitile() {
            return switch (lang) {
                case "ru" -> "Аргумент необходим";
                case "po" -> "Argumento é necessário";
                case "bo" -> "Необходим е аргумент";
                case "es" -> "Se requiere argumento";
                default -> "Argument is require";
            };
        }

        public static String getEnterAnArgumentText() {
            return switch (lang) {
                case "ru" -> "Вам необходимо ввести аргумент!";
                case "po" -> "Você precisa inserir um argumento!";
                case "bo" -> "Трябва да въведете аргумент!";
                case "es" -> "¡Necesitas ingresar un argumento!";
                default -> "You have to enter an argument!";
            };
        }

        public static String getHistoryTitle() {
            return switch (lang) {
                case "ru" -> "История";
                case "po" -> "História";
                case "bo" -> "История";
                case "es" -> "Historia";
                default -> "History";
            };
        }

        public static String getInfoTitle() {
            return switch (lang) {
                case "ru" -> "Информация";
                case "po" -> "Informação";
                case "bo" -> "Информация";
                case "es" -> "Información";
                default -> "Info";
            };
        }

        public static String getMaxVenueTitle() {
            return switch (lang) {
                case "ru" -> "Максимальное место";
                case "po" -> "Local máximo";
                case "bo" -> "Макс място";
                case "es" -> "Lugar máximo";
                default -> "Max Venue";
            };
        }

        public static String getUpdateTitle() {
            return switch (lang) {
                case "ru" -> "Обновить (key)";
                case "po" -> "Atualizar (key)";
                case "bo" -> "Актуализация (key)";
                case "es" -> "Actualizar (key)";
                default -> "Update (key)";
            };
        }

        public static String getForbiddenTitle() {
            return switch (lang) {
                case "ru" -> "Запрещено";
                case "po" -> "Proibida";
                case "bo" -> "Забранен";
                case "es" -> "Prohibida";
                default -> "Forbidden";
            };
        }

        public static String getForbiddenText() {
            return switch (lang) {
                case "ru" -> "В разрешении отказано!";
                case "po" -> "Permissão negada!";
                case "bo" -> "Разрешението е отказано!";
                case "es" -> "¡Permiso denegado!";
                default -> "Permission denied!";
            };
        }

        public static String getCountByPriveTitle() {
            return switch (lang) {
                case "ru" -> "Подсчет по цене";
                case "po" -> "Cálculo por preço";
                case "bo" -> "Калкулация по цена";
                case "es" -> "Cálculo por precio";
                default -> "Count by Price";
            };
        }

        public static String getRemoveByKey() {
            return switch (lang) {
                case "ru" -> "Удалить по <key>";
                case "po" -> "Excluir por <key>";
                case "bo" -> "Изтриване чрез <key>";
                case "es" -> "Eliminar por <key>";
                default -> "Remove by <key>";
            };
        }

        public static String getRemoveByComment() {
            return switch (lang) {
                case "ru" -> "Удалить по <comment>";
                case "po" -> "Excluir por <comment>";
                case "bo" -> "Изтриване чрез <comment>";
                case "es" -> "Eliminar por <comment>";
                default -> "Remove by <comment>";
            };
        }

        public static String getRemoveGraterID() {
            return switch (lang) {
                case "ru" -> "Удалить больше <id>";
                case "po" -> "Remover mais <id>";
                case "bo" -> "Премахнете повече <id>";
                case "es" -> "Quitar más <id>";
                default -> "Remove greater <id>";
            };
        }

        public static String getRemoveGraterKey() {
            return switch (lang) {
                case "ru" -> "Удалить больше <key>";
                case "po" -> "Remover mais <key>";
                case "bo" -> "Премахнете повече <key>";
                case "es" -> "Quitar más <key>";
                default -> "Remove greater <key>";
            };
        }
    }

    public static class Info {
        public Info() {}

        public static String getCount() {
            return switch (lang) {
                case "ru" -> "Количество элементов в коллекции с такой ценой: ";
                case "po" -> "O número de itens em uma coleção com este preço: ";
                case "bo" -> "Броят артикули в колекция с тази цена: ";
                case "es" -> "El número de artículos de una colección con este precio: ";
                default -> "The number of items in a collection with this price: ";
            };
        }

        public static String getInfoRow1() {
            return switch (lang) {
                case "ru" -> "Основная информация о коллекции:";
                case "po" -> "Informações básicas sobre a coleção:";
                case "bo" -> "Основна информация за колекцията:";
                case "es" -> "Información básica sobre la colección:";
                default -> "Main information about this collection:";
            };
        }

        public static String getInfoRow2() {
            return switch (lang) {
                case "ru" -> "Инициализация: ";
                case "po" -> "Inicialização: ";
                case "bo" -> "Инициализация: ";
                case "es" -> "Inicialización: ";
                default -> "Initialization: ";
            };
        }

        public static String getInfoRow3() {
            return switch (lang) {
                case "ru" -> "Последний доступ: ";
                case "po" -> "Último acesso: ";
                case "bo" -> "Последен достъп: ";
                case "es" -> "Último accedido: ";
                default -> "Last access: ";
            };
        }

        public static String getInfoRow4() {
            return switch (lang) {
                case "ru" -> "Размер: ";
                case "po" -> "Tamanho: ";
                case "bo" -> "Размер: ";
                case "es" -> "Tamaño: ";
                default -> "Size: ";
            };
        }

        public static String getInfoRow5() {
            return switch (lang) {
                case "ru" -> "Тип данных: ";
                case "po" -> "Tipo de dados: ";
                case "bo" -> "Тип данни: ";
                case "es" -> "Tipo de datos: ";
                default -> "Data type: ";
            };
        }

        public static String getMaxVenue() {
            return switch (lang) {
                case "ru" -> "Билет с наибольшим <venue>:";
                case "po" -> "Ingresso com o maior <venue>:";
                case "bo" -> "Билет с най-голямото <venue>:";
                case "es" -> "Entrada con el <venue> más grande:";
                default -> "Ticket with the highest <venue>:";
            };
        }

        public static String getConnectingTitle() {
            return switch (lang) {
                case "ru" -> "Подключение к серверу";
                case "po" -> "Conectando-se ao servidor";
                case "bo" -> "Свързване към сървъра";
                case "es" -> "Conectando al servidor";
                default -> "Connectiong to the server";
            };
        }

        public static String getConnectingText() {
            return switch (lang) {
                case "ru" -> "Подключение, пожалуйста, подождите...";
                case "po" -> "Conectando, aguarde...";
                case "bo" -> "Установява се връзка, моля изчакайте...";
                case "es" -> "Conectando, espere por favor...";
                default -> "Connection, please wait...";
            };
        }
    }
}
