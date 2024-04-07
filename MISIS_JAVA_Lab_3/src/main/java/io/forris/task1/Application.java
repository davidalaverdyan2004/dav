package io.forris.task1;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


// Интерфейс для стратегии обработки списка строк
interface Strategy {
    @NotNull
    String process(@NotNull List<String> strings);
}

// Интерфейс для сервиса стратегии
interface StrategyService {
    String exec(List<String> strings);
    StrategyService setStrategy(@NotNull Strategy strategy);
}

// Стратегия для объединения строк запятой
class CommaSeparatedStrategy implements Strategy {

    @NotNull
    @Override
    public String process(@NotNull List<String> strings) {
        return StringUtils.join(strings, ",");
    }
}

// Стратегия для объединения строк точкой с запятой
class SemicolonSeparatedStrategy implements Strategy {
    @NotNull
    @Override
    public String process(@NotNull List<String> strings) {
        return StringUtils.join(strings, ";");
    }
}

// Реализация сервиса стратегии
class StrategyServiceImpl implements StrategyService {
    private Strategy strategy;

    public StrategyServiceImpl(@NotNull Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    @NotNull
    public String exec(@NotNull List<String> strings) {
        String result = "";
        if(strings.isEmpty())
            return result;

        return strategy.process(strings);
    }

    public StrategyService setStrategy(@NotNull Strategy strategy) {
        this.strategy = strategy;
        return this;
    }
}

// Основной класс приложения
public class Application {
    public static void main(String[] args) {
        // Создание сервиса стратегии с начальной стратегией разделения запятой
        StrategyServiceImpl service = new StrategyServiceImpl(new CommaSeparatedStrategy());
        List<String> strings = Arrays.asList("Hello", "World", "Forris");
        // Выполнение операции и вывод результата
        String result = service.exec(strings);
        System.out.println("Result: " + result);
    }
}