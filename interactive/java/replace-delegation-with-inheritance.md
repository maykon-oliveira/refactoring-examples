replace-delegation-with-inheritance:java

###

1.ru. Сделайте класс подклассом класса-делегата.
1.uk. Зробіть клас підкласом класу-делегату.

2.ru. В поле, содержащее ссылку на объект-делегат, поставьте текущий объект.
2.uk. У поле, що містить посилання на об'єкт-делегат, поставте поточний об'єкт.

3.ru. Один за другим удаляйте методы с простой делегацией. Если у них отличались названия, используйте <a href="/rename-method">переименование метода</a> чтобы привести все методы к одному названию.
3.uk. Один за іншим видаляйте методи з простою делегацією. Якщо у них відрізнялися назви, використайте <a href="/rename-method">перейменування методу</a> щоб привести усі методи до однієї назви.

4.ru. Замените все обращения к полю-делегату обращениями к текущему объекту.
4.uk. Замініть усі звернення до поля-делегата зверненнями до поточного об'єкту.

5.ru. Удалите поле-делегат.
5.uk. Видаліть поле-делегат.



###

```
class Person {
  private String name;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLastName() {
    return name.substring(name.lastIndexOf(' ') + 1);
  }
}

class Employee {
  protected Person person;

  public Employee() {
    this.person = new Person();
  }
  public String getName() {
    return person.getName();
  }
  public void setName(String name) {
    person.setName(name);
  }
  public String toString () {
    return "Emp: " + person.getLastName();
  }
}
```

###

```
class Person {
  private String name;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLastName() {
    return name.substring(name.lastIndexOf(' ') + 1);
  }
}

class Employee extends Person {
  public String toString () {
    return "Emp: " + getLastName();
  }
}
```

###

Set step 1

#|ru| У нас есть класс служащего <code>Employee</code>, который делегирует некоторою работу классу личности <code>Person</code>.
#|uk| У нас є клас службовця <code>Employee</code>, який делегує деяку роботу класу особистості <code>Person</code>.

#|ru| Здесь наследование было бы уместнее, так как классу служащих необходимы практически все данные из <code>Person</code>.
#|uk| Тут успадкування було б доречніше, так як класу службовців необхідні практично всі дані з <code>Person</code>.

#|ru| Начинаем рефакторинг с объявления наследования в классе <code>Employee</code>
#|uk| Починаємо рефакторинг з оголошення успадкування в класі <code>Employee</code>

Go to "class Employee|||"

Print " extends Person"

#C|ru| Здесь стоит запустить компиляцию, чтобы убедиться в отсутствии конфликтующих методов. Они возникают, если методы с одинаковым именем возвращают значения различных типов или генерируют разные исключительные ситуации. Все проблемы такого рода исправляются с помощью <a href="/rename-method">Переименования метода</a>.
#S В данном простом примере таких затруднений не возникает.

#C|uk| Тут варто запустити компіляцію, щоб переконатися у відсутності конфліктуючих методів. Вони виникають, якщо методи з однаковим ім'ям повертають значення різних типів або генерують різні виняткові ситуації. Всі проблеми такого роду виправляються за допомогою <a href="/rename-method">Перейменування методу</a>.
#S В даному простому прикладі таких ускладнень не виникає.

Set step 2

Select "new Person()"

#|ru| Следующим шагом заставляем поле делегирования ссылаться на сам объект.
#|uk| Наступним кроком змушуємо поле делегування посилатися на сам об'єкт.

Print "this"

Set step 3

Select whole "getName" in "Employee"
+ Select whole "setName" in "Employee"

#|ru| Кроме того, мы должны удалить из <code>Employee</code> все простые методы делегирования, такие, как <code>getName</code> и <code>setName</code>. Если их оставить, возникнет ошибка переполнения стека, вызванная бесконечной рекурсией.
#|uk| Крім того, ми повинні видалити з <code>Employee</code> всі прості методи делегування, такі, як <code>getName</code> і <code>setName</code>. Якщо їх залишити, виникне помилка переповнення стека, викликана нескінченної рекурсією.

Remove selected

Set step 4

Select "person."

#|ru| Далее следует избавиться от обращений к связанному полю, заменив их вызовами из собственного класса.
#|uk| Далі слід позбутися звернень до пов'язаного поля, замінивши їх викликами з власного класу.

Remove selected

Set step 5

Select:
```
  protected Person person;

  public Employee() {
    this.person = this;
  }

```

#|ru| После всех замен поле связанного объекта, а также код его инициализации становятся бесполезными и их можно убрать.
#|uk| Після всіх замін поле пов'язаного об'єкта, а також код його ініціалізації стають даремними і їх можна прибрати.

Remove selected

#C|ru| Запускаем финальную компиляцию.
#S Отлично, все работает!

#C|uk| Запускаємо фінальну компіляцію.
#S Супер, все працює.

Set final step

#|ru|Q На этом рефакторинг можно считать оконченным. В завершение, можете посмотреть разницу между старым и новым кодом.
#|uk|Q На цьому рефакторинг можна вважати закінченим. На завершення, можете подивитися різницю між старим та новим кодом.