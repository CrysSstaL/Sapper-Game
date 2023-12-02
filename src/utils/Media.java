package src.utils;

public class Media {
    private final String menuTitle;
    private final String menuText;
    private final String helpText;
    private final String endText;
    private final String dir;
    public Media() {
        this.dir = System.getProperty("user.dir") + "/photo";
        this.menuTitle = "Добро пожаловать в игру Сапер!";
        this.menuText = "Рады приветствовать тебя в этом увлекательном мире разминирования полей.\n" +
                " Готов ли ты проверить свою логику, смекалку и умение мыслить стратегически?\n" +
                " Надеемся, что тебе понравится эта классическая головоломка," +
                " которая дарит столько приятных эмоций.\n" +
                " Итак, приготовься к приключению и пойдем саперить!";

        this.helpText =
                " Сапер - это классическая игра-головоломка," +
                " изначально созданная для компьютеров с операционной системой Windows." +
                " В игре задача игрока состоит в том, чтобы безопасно разминировать поле," +
                " составленное из клеток, в которых могут находиться или отсутствовать мины.\n" +
                "Вот руководство по игре Сапер:\n" +
                "\n" +
                "1 - Поле игры представляет собой сетку из клеток. Каждая клетка может быть закрытой или открытой." +
                "  Закрытые клетки показываются в виде пустых квадратов. \n" +
                "2 - Цель игры - открыть все клетки, не находящиеся в угрозе мины. Если вы откроете клетку с миной, вы проиграете.\n" +
                "3 - Перед тем как начать игру, выберите уровень сложности." +
                " Обычно предлагаются три уровня сложности: начинающий, продвинутый и эксперт." +
                " Чем выше уровень сложности, тем больше мин будет расположено на поле.\n" +
                "4 - Чтобы открыть клетку, щелкните на нее левой кнопкой мыши." +
                " Если под клеткой находится мина, игра закончится. Если под клеткой нет мины, она откроется," +
                " и вы увидите число, которое показывает, сколько мин находится в соседних клетках." +
                " Эта информация поможет вам определить, где могут находиться мины.\n" +
                "5 - Если вы уверены, что под клеткой находится мина, вы можете поставить на нее флажок." +
                " Щелкните правой кнопкой мыши, чтобы пометить клетку флажком." +
                " Флажок намекает, что там находится мина и помогает вам запомнить опасные клетки.\n" +
                "6 - Игра продолжается до тех пор, пока вы не откроете все клетки, не содержащие мины." +
                " Если вы откроете клетку с миной, игра закончится, и вы проиграете.\n" +
                "7 - Если вы успешно открыли все клетки без мин, игра будет выиграна," +
                " и вы сможете поздравить себя с победой!\n" +
                "8 - Если вы хотите начать игру заново или перейти на другой уровень сложности, " +
                "обычно в меню игры есть соответствующие опции.\n" +
                "Сапер - отличная игра для тренировки логики и аналитических навыков." +
                " Играйте, наслаждайтесь игрой и удачи в разминировании полей!";

        this.endText = "Вы точно хотите выйти?";
    }

    public String getText(String text){
        return "<html>" + text.replaceAll("\n", "<br/>") + "</html>";
    }

    public String getMenuTitle(){return this.menuTitle;}

    public String getMenuText(){
        return this.menuText;
    }
    public String getHelpText(){
        return this.helpText;
    }
    public String getEndText(){ return this.endText; }

    public String getDir(){return this.dir;}
}