import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Первая задача
        //Дан класс User, который содержит поля с именем и возрастом. Необходимо реализовать следующий метод:
        //На входе коллекция пользователей (как я поняла любая коллекция, т.е. класс Collection)
        //Необходимо удалить все дубли (одинаковое имя и возраст)
        //Отсортировать по возрасту и имени
        //Вернуть самого старого пользователя

        User ivan = new User("Иван", 30);
        User anna = new User("Анна", 25);
        User viktor = new User("Виктор", 55);
        User anna2 = new User("Анна", 25);
        User maria = new User("Мария", 25);

        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(ivan);
        arrayList.add(anna);
        arrayList.add(viktor);
        arrayList.add(anna2);
        arrayList.add(maria);

        User oldestUser = prepareCollection(arrayList);//метод для решения задачи 1

        System.out.println("Старейший User = "+oldestUser);

        System.out.println();

        //тестируем на HashSet'e
        HashSet<User> hashSet = new HashSet<>();
        hashSet.add(ivan);
        hashSet.add(anna);
        hashSet.add(viktor);
        hashSet.add(anna2);
        hashSet.add(maria);

        User oldestUser1 = prepareCollection(hashSet);
        System.out.println("Старейший User = "+oldestUser1);



        System.out.println();
        System.out.println("Задача 2. Напишите программу на Java для подсчета количества конкретных слов в строке, используя HashMap.");


        String string = "яблоко груша    арбуз яблоко тыква   арбуз яблоко морковь груша";

        //получаем карту повторов
        HashMap<String, Integer> map = countWords(string);//метод для решения второй задачи

        //сканируем и печатаем карту повторов
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            String word = entry.getKey();
            int counter = entry.getValue();
            System.out.println("Слово "+word+" содержится в строке "+counter+" раз");
        }
    }

    /////////////////////////////////////////////////////////////////////
    //метод для первой задачи
    private static User prepareCollection(Collection<User> collection){
        Iterator<User> iterator = collection.iterator();
        ArrayList<User> uniqueList = new ArrayList<>();//список уникальных Users, пройденных итератором

        //сканируем коллекцию
        while(iterator.hasNext()){
            User user = iterator.next();
            if(uniqueList.contains(user)){
                //если уже ранее итератор проходил такого же пользователя
                // это значит что user повторяется, удаляем его в исходной коллекции
                iterator.remove();//удалять обезательно итератором
                System.out.println("удален дубликат = "+user);
            }else {
                uniqueList.add(user);//добавляем user в список пройденных
            }
        }

        //список uniqueList содержит только уникальные объекты User
        //сортируем его при помощи компаратора
        uniqueList.sort(new Comparator<User>() {
            @Override
            public int compare(User userNext, User userPrev) {
                if(userNext.getAge() > userPrev.getAge()){
                    return 1;

                }else if(userNext.getAge() < userPrev.getAge()){
                    return -1;

                }else{
                    //возрасты равны - сравниваем по названию
                    return userNext.getName().compareTo(userPrev.getName());
                }
            }
        });

        System.out.println("Отсортированный список");
        for(User user: uniqueList){
            System.out.println(user);
        }

        int lastIndex = uniqueList.size()-1;
        User oldestUser = uniqueList.get(lastIndex);

        return oldestUser;

    }

    /////////////////////////////////////////////////////////////////////
    //метод для второй задачи
    private static HashMap<String,Integer> countWords(String string){
        //делим строку на массив отдельных слов, разделителем слов будет пробел
        String[] wordArray = string.split(" ");

        //создаем карту, ключом будет слово, а значением - счетчик повторов этого слова
        HashMap<String, Integer> map = new HashMap<>();

        //сканируем наш массив слов
        for(String word : wordArray){
            if(word.isBlank()){
                //если слово состоит из одних пробелов возвращеемся на начало цикла
                continue;
            }

            int counter = 0;//по умолчанию счетчик = 0
            if(map.containsKey(word)){
                //если в карте уже есть текущее слово, достаем из карты значение счетчика
                counter = map.get(word);
            }
            counter++;//увеличиваем счетчик на единицу
            map.put(word, counter);//записываем в карту новое значение счетчика
        }
        return map;
    }


}